package com.genesys.workspace;

import java.net.URI;
import java.net.HttpCookie;
import java.net.CookieManager;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Optional;

import com.genesys.internal.common.ApiClient;
import com.genesys.internal.common.ApiResponse;
import com.genesys.internal.common.ApiException;

import com.genesys.workspace.common.StatusCode;
import com.genesys.workspace.common.WorkspaceApiException;
import com.genesys.workspace.events.*;
import com.genesys.workspace.models.Dn;
import com.genesys.workspace.models.Call;
import com.genesys.workspace.models.User;
import com.genesys.internal.workspace.api.SessionApi;
import com.genesys.internal.workspace.api.VoiceApi;
import com.genesys.internal.workspace.model.*;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.cometd.client.BayeuxClient;
import org.cometd.client.transport.ClientTransport;
import org.cometd.client.transport.LongPollingTransport;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class WorkspaceApi {
    private String apiKey;
    private String baseUrl;
    private boolean debugEnabled;
    private String workspaceUrl;
    private ApiClient workspaceClient;
    private HttpClient cometdHttpClient;
    private BayeuxClient cometdClient;
    private SessionApi sessionApi;
    private VoiceApi voiceApi;
    private String sessionCookie;
    private String workspaceSessionId;
    private CompletableFuture<User> initFuture;
    private boolean workspaceInitialized = false;
    private User user;
    private Dn dn;
    private Map<String, Call> calls;
    private Set<DnEventListener> dnEventListeners;
    private Set<CallEventListener> callEventListeners;
    private Set<ErrorEventListener> errorEventListeners;

    /**
     * Constructor 
     * @param apiKey The API key to be used.
     * @param baseUrl base URL for the workpace API
     * @param debugEnabled enable debug (or not) 
    */
    public WorkspaceApi(
            String apiKey,
            String baseUrl,
            boolean debugEnabled
    ) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.debugEnabled = debugEnabled;
        this.workspaceUrl = this.baseUrl + "/workspace/v3";

        this.calls = new HashMap<>();
        this.dnEventListeners = new HashSet<>();
        this.callEventListeners = new HashSet<>();
        this.errorEventListeners = new HashSet<>();
    }

    private void debug(String msg) {
        if (this.debugEnabled) {
            System.out.println(msg);
        }
    }

    private void publishCallStateChanged(CallStateChanged msg) {
        this.callEventListeners.forEach(listener -> {
            try {
                listener.handleCallStateChanged(msg);
            } catch (Exception e) {
                this.debug("Exception in listener" + e);
            }
        });
    }

    private void publishDnStateChanged(DnStateChanged msg) {
        this.dnEventListeners.forEach(listener -> {
            try {
                listener.handleDnStateChanged(msg);
            } catch (Exception e) {
                this.debug("Exception in listener" + e);
            }
        });
    }

    private void publishErrorEvent(EventError msg) {
        this.errorEventListeners.forEach(listener -> {
            try {
                listener.handleEventError(msg);
            } catch (Exception e) {
                this.debug("Exception in listener" + e);
            }
        });
    }

    /**
     * Adds a listener for CallStateChanged events.
     * @param listener the listener to be added.
     */
    public void addCallEventListener(CallEventListener listener) {
        this.callEventListeners.add(listener);
    }

    /**
     * Removes a previously added CallStateChanged listener.
     * @param listener the listener to be removed
     */
    public void removeCallEventListener(CallEventListener listener) {
        this.callEventListeners.remove(listener);
    }

    /**
     * Add a listener for DnStateChanged events.
     * @param listener the listener to be added.
     */
    public void addDnEventListener(DnEventListener listener) {
        this.dnEventListeners.add(listener);
    }

    /**
     * Remove a previously added DnStateChanged listener.
     * @param listener the listener to be removed.
     */
    public void removeDnEventListener(DnEventListener listener) {
        this.dnEventListeners.remove(listener);
    }

    /**
     * Add a listener for EventError
     * @param listener the listener to be added.
     */
    public void addErrorEventListener(ErrorEventListener listener) {
        this.errorEventListeners.add(listener);
    }

    /**
     * Remove a previously added EventError listener.
     * @param listener the listener to be removed.
     */
    public void removeErrorEventListener(ErrorEventListener listener) {
        this.errorEventListeners.remove(listener);
    }

    private void extractSessionCookie(ApiResponse<ApiSuccessResponse> response) throws WorkspaceApiException {
        this.debug("Extracting session cookie...");
        Optional<String> cookie = response.getHeaders().get("set-cookie")
                .stream().filter(v -> v.startsWith("WORKSPACE_SESSIONID")).findFirst();

        if(!cookie.isPresent()) {
            throw new WorkspaceApiException("Failed to extract workspace session cookie.");
        }

        this.sessionCookie = cookie.get();
        this.workspaceSessionId = this.sessionCookie.split(";")[0].split("=")[1];
        this.debug("WORKSPACE_SESSIONID is " + this.workspaceSessionId);

        this.workspaceClient.addDefaultHeader("Cookie", this.sessionCookie);
    }

    private void onInitMessage(Message message) {
        this.debug("Message received for /workspace/v3/initialization:\n" + message.toString());

        Map<String, Object> data = message.getDataAsMap();
        String messageType = (String)data.get("messageType");

        if ("WorkspaceInitializationComplete".equals(messageType)) {
            String state = (String)data.get("state");
            if ("Complete".equals(state)) {
                this.workspaceInitialized = true;

                Map<String, Object> initData = (Map<String, Object>)data.get("data");
                Map<String, Object> userData = (Map<String, Object>)initData.get("user");
                String employeeId = (String)userData.get("employeeId");
                String defaultPlace = (String)userData.get("defaultPlace");
                String agentId = (String)userData.get("agentLogin");
                if (this.user == null) {
                    this.user = new User();
                }

                this.user.setEmployeeId(employeeId);
                this.user.setAgentId(agentId);
                this.user.setDefaultPlace(defaultPlace);

                this.initFuture.complete(this.user);
                this.workspaceInitialized = true;

            } else if ("Failed".equals(state)) {
                this.debug("Workspace initialization failed!");
                this.initFuture.completeExceptionally(
                        new WorkspaceApiException("initialize workspace failed"));
            }
        }
    }

    private void onDnStateChanged(Map<String, Object> data) {
        if (this.dn == null) {
            this.dn = new Dn();
        }

        Map<String, Object> dnData = (Map<String, Object>)data.get("dn");

        String number = (String)dnData.get("number");
        String agentId = (String)dnData.get("agentId");
        String agentState = (String)dnData.get("agentState");
        String workMode = (String)dnData.get("agentWorkMode");

        this.dn.setAgentId(agentId);
        this.dn.setNumber(number);
        this.dn.setAgentState(agentState);
        this.dn.setWorkMode(workMode);

        this.debug("Dn updated: state [" + agentState + "] workMode [" + workMode + "]...");
        this.publishDnStateChanged(new DnStateChanged(this.dn));
    }

    private String[] extractParticipants(Object[] data) {
        String[] participants = new String[data.length];
        for(int i = 0; i < data.length; i++) {
            Map<String, Object> p = (Map<String, Object>)data[i];
            String number = (String)p.get("number");
            participants[i] = number;
        }

        return participants;
    }

    private void onCallStateChanged(Map<String, Object> data) {
        Map<String, Object> callData = (Map<String, Object>)data.get("call");

        String id = (String)callData.get("id");
        String state = (String)callData.get("state");
        String parentConnId = (String)callData.get("parentConnId");
        String previousConnId = (String)callData.get("previousConnId");
        Object[] participantData = (Object[])callData.get("participants");

        String[] participants = this.extractParticipants(participantData);

        boolean connIdChanged = false;
        String callType = (String)callData.get("callType");

        Call call;

        if (previousConnId != null && this.calls.containsKey(previousConnId)) {
            call = this.calls.remove(previousConnId);
            call.setId(id);
            call.setPreviousConnId(previousConnId);
            this.calls.put(id, call);
            connIdChanged = true;
        } else {
            switch (state) {
                case "Ringing":
                case "Dialing":
                    call = new Call();
                    call.setId(id);
                    call.setCallType(callType);
                    if (parentConnId != null) {
                        call.setParentConnId(parentConnId);
                    }

                    this.calls.put(id, call);
                    debug("Added call " + id + " (" + state + ")");
                    break;

                case "Released":
                    call = this.calls.remove(id);
                    debug("Removed call " + id + "(" + state + ")");
                    break;

                default:
                    call = this.calls.get(id);
                    break;

            }
        }

        if (call != null) {
            call.setState(state);
            call.setParticipants(participants);
            this.publishCallStateChanged(new CallStateChanged(call, connIdChanged ? previousConnId : null));
        } else {
            debug("Call " + id + " was not found...");
        }
    }

    private void onEventError(Map<String, Object> data) {
        // TODO
    }

    private void onVoiceMessage(Message message) {
        this.debug("Message received for /workspace/v3/voice:\n" + message.toString());

        Map<String, Object> data = message.getDataAsMap();
        String messageType = (String)data.get("messageType");

        switch(messageType) {
            case "DnStateChanged":
                this.onDnStateChanged(data);
                break;

            case "CallStateChanged":
                this.onCallStateChanged(data);
                break;

            case "EventError":
                this.onEventError(data);
                break;

            default:
                this.debug("Unexpected messageType: " + messageType);
        }
    }

    private void onHandshake(Message handshakeMessage) {
        if(!handshakeMessage.isSuccessful()) {
            this.debug("Cometd handshake failed:\n" + handshakeMessage.toString());
            return;
        }

        this.debug("Cometd handshake successful.");
        this.debug("Subscribing to channels...");
        this.cometdClient.getChannel("/workspace/v3/initialization").subscribe(
                (ClientSessionChannel channel, Message msg) -> this.onInitMessage(msg));

        this.cometdClient.getChannel("/workspace/v3/voice").subscribe(
                (ClientSessionChannel channel, Message msg) -> this.onVoiceMessage(msg));
    }

    private void initializeCometd() throws WorkspaceApiException {
        try {
            this.debug("Initializing cometd...");
            SslContextFactory sslContextFactory = new SslContextFactory();
            this.cometdHttpClient = new HttpClient(sslContextFactory);
            cometdHttpClient.start();

            CookieManager manager = new CookieManager();
            cometdHttpClient.setCookieStore(manager.getCookieStore());
            cometdHttpClient.getCookieStore().add(new URI(workspaceUrl),
                    new HttpCookie("WORKSPACE_SESSIONID", this.workspaceSessionId));

            ClientTransport transport = new LongPollingTransport(new HashMap(), cometdHttpClient) {
                @Override
                protected void customize(Request request) {
                    request.header("x-api-key", apiKey);
                }
            };

            this.cometdClient = new BayeuxClient(this.workspaceUrl + "/notifications", transport);
            this.debug("Starting cometd handshake...");
            this.cometdClient.handshake((ClientSessionChannel channel, Message msg) -> this.onHandshake(msg));

        } catch(Exception e) {
            throw new WorkspaceApiException("Cometd initialization failed.", e);
        }
    }

    private void throwIfNotOk(String requestName, ApiSuccessResponse response) throws WorkspaceApiException {
        if (response.getStatus().getCode() != StatusCode.ASYNC_OK) {
            throw new WorkspaceApiException(
                    requestName + " failed with code: " + response.getStatus().getCode());
        }
    }

    /**
     * Initializes the API using the provided authCode and redirectUri. This is the preferred means of init.
     * @param authCode the auth code to be used for initialization
     * @param redirectUri the redirectUri to be used for initialization. Since this is not being sent by the UI, this just
     * needs to match the redirectUri that was sent when obtaining the authCode.
     */
    public CompletableFuture<User> initialize(String authCode, String redirectUri) throws WorkspaceApiException {
        return this.initialize(authCode, redirectUri, null);
    }

    /**
     * Initializes the API using the provided auth token.
     * @param token The auth token to use for initialization.
     */
    public CompletableFuture<User> initialize(String token) throws WorkspaceApiException {
        return this.initialize(null, null, token);
    }

    private CompletableFuture<User> initialize(String authCode, String redirectUri, String token) throws WorkspaceApiException {
        try {
            this.initFuture = new CompletableFuture<>();

            this.workspaceClient = new ApiClient();
            this.workspaceClient.setBasePath(this.workspaceUrl);
            this.workspaceClient.addDefaultHeader("x-api-key", this.apiKey);

            this.sessionApi = new SessionApi(this.workspaceClient);
            this.voiceApi = new VoiceApi(this.workspaceClient);

            String authorization = token != null ? "Bearer " + token : null;
            final ApiResponse<ApiSuccessResponse> response =
                    this.sessionApi.initializeWorkspaceWithHttpInfo(authCode, redirectUri, authorization);
            this.extractSessionCookie(response);

            this.initializeCometd();
            return initFuture;

        } catch (ApiException e) {
            throw new WorkspaceApiException("initialize failed.", e);
        }
    }

    /**
     * Logout the agent and cleanup resources.
     */
    public void destroy() throws WorkspaceApiException {
        try {
            if (this.workspaceInitialized) {
                this.cometdClient.disconnect();
                this.cometdHttpClient.stop();
                this.sessionApi.logout();
            }
        } catch (Exception e) {
            throw new WorkspaceApiException("destroy failed.", e);
        } finally {
            this.workspaceInitialized = false;
        }
    }

    /**
     * Initializes the voice channel using the specified resources.
     * @param agentId - AgentId to be used for login
     * @param dn - DN to be used for login
     */
    public void activateChannels(String agentId, String dn) throws WorkspaceApiException {
        try {
            this.debug("Activating channels with agentId [" + agentId + "] and Dn [" + dn + "]...");
            ActivatechannelsData data = new ActivatechannelsData();
            data.setAgentId(agentId);
            data.setDn(dn);

            ChannelsData channelsData = new ChannelsData();
            channelsData.data(data);

            ApiSuccessResponse response = this.sessionApi.activateChannels(channelsData);
            if(response.getStatus().getCode() != 0) {
                throw new WorkspaceApiException(
                        "activateChannels failed with code: " +
                                response.getStatus().getCode());
            }
        } catch (ApiException e) {
            throw new WorkspaceApiException("activateChannels failed.", e);
        }
    }

    public Dn getDn() {
        return this.dn;
    }

    /** 
      * Gets the list of active calls.
      * @return list of active calls
      */
    public Collection<Call> getCalls() {
        return this.calls.values();
    }

    /**
      * Returns the current user.
      * @return the current user.
      */
    public User getUser() {
        return this.user;
    }

    /**
     * Set the agent state to ready.
     */
    public void setAgentReady() throws WorkspaceApiException {
        try {
            ReadyData data = new ReadyData();

            ApiSuccessResponse response = this.voiceApi.setAgentStateReady(data);
            throwIfNotOk("setAgentReady", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("setAgentReady failed.", e);
        }
    }

    /**
     * Set the agent state to not ready.
     */
    public void setAgentNotReady() throws WorkspaceApiException {
        this.setAgentNotReady(null, null);
    }

    /**
     * Set the agent state to not ready.
     * @param workMode - optional workMode to use in the request.
     * @param reasonCode - optional reasonCode to use in the request.
     */
    public void setAgentNotReady(String workMode, String reasonCode) throws WorkspaceApiException{
        try {
            NotReadyData data = new NotReadyData();

            if (workMode != null || reasonCode != null) {
                VoicenotreadyData notReadyData = new VoicenotreadyData();
                data.data(notReadyData);

                if (workMode != null) {
                    notReadyData.setAgentWorkMode(VoicenotreadyData.AgentWorkModeEnum.fromValue(workMode));
                }

                if (reasonCode != null) {
                    notReadyData.setReasonCode(reasonCode);
                }

            }

            ApiSuccessResponse response = this.voiceApi.setAgentStateNotReady(data);
            throwIfNotOk("setAgentNotReady", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("setAgentReady failed.", e);
        }
    }

    /**
     * Make a new call to the specified destination.
     * @param destination The destination to call
     */
    public void makeCall(String destination) throws WorkspaceApiException {
        try {
            VoicemakecallData data = new VoicemakecallData();
            data.destination(destination);
            MakeCallData makeCallData = new MakeCallData().data(data);

            ApiSuccessResponse response = this.voiceApi.makeCall(makeCallData);
            throwIfNotOk("makeCall", response);

        } catch (ApiException e) {
            throw new WorkspaceApiException("makeCall failed.", e);
        }
    }

    /**
     * Answer call.
     * @param connId The connId of the call to answer.
     */
    public void answerCall(String connId) throws WorkspaceApiException {
        try {
            AnswerData data = new AnswerData();

            ApiSuccessResponse response = this.voiceApi.answer(connId, data);
            throwIfNotOk("answerCall", response);

        } catch (ApiException e) {
            throw new WorkspaceApiException("answerCall failed.", e);
        }
    }

    /**
     * Place call on hold.
     * @param connId The connId of the call to place on hold. 
     */
    public void holdCall(String connId) throws WorkspaceApiException {
        try {
            HoldData data = new HoldData();

            ApiSuccessResponse response = this.voiceApi.hold(connId, data);
            throwIfNotOk("holdCall", response);

        } catch (ApiException e) {
            throw new WorkspaceApiException("holdCall failed.", e);
        }
    }

    /**
     * Retrieve call from hold.
     * @param connId The connId of the call to retrieve. 
     */
    public void retrieveCall(String connId) throws WorkspaceApiException {
        try {
            RetrieveData data = new RetrieveData();

            ApiSuccessResponse response = this.voiceApi.retrieve(connId, data);
            throwIfNotOk("retrieveCall", response);

        } catch (ApiException e) {
            throw new WorkspaceApiException("retrieveCall failed.", e);
        }
    }

    /**
     * Release call.
     * @param connId The connId of the call to release 
     */
    public void releaseCall(String connId) throws WorkspaceApiException {
        try {
            ReleaseData data = new ReleaseData();

            ApiSuccessResponse response = this.voiceApi.release(connId, data);
            throwIfNotOk("releaseCall", response);

        } catch (ApiException e) {
            throw new WorkspaceApiException("releaseCall failed.", e);
        }
    }

    /**
     * Initiate a conference to the specified destination.
     * @param connId The connId of the call to start the conference from.
     * @param destination The destination
     */
    public void initiateConference(String connId, String destination) throws WorkspaceApiException {
        try {
            VoicecallsidinitiateconferenceData initData = new VoicecallsidinitiateconferenceData();
            initData.setDestination(destination);
            InitiateConferenceData data = new InitiateConferenceData();
            data.data(initData);

            ApiSuccessResponse response = this.voiceApi.initiateConference(connId, data);
            throwIfNotOk("initiateConference", response);

        } catch (ApiException e) {
            throw new WorkspaceApiException("initiateConference failed.", e);
        }
    }

    /**
     * Complete a previously initiated conference identified by the provided ids.
     * @param connId The id of the consule call (established)
     * @param parentConnId The id of the parent call (held).
     */
    public void completeConference(String connId, String parentConnId) throws WorkspaceApiException {
        try {
            VoicecallsidcompletetransferData completeData = new VoicecallsidcompletetransferData();
            completeData.setParentConnId(parentConnId);
            CompleteConferenceData data = new CompleteConferenceData();
            data.data(completeData);

            ApiSuccessResponse response = this.voiceApi.completeConference(connId, data);
            throwIfNotOk("completeConference", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("completeConference failed.", e);
        }
    }

    /**
     * Initiate a transfer to the specified destination.
     * @param connId The connId of the call to be transfered.
     * @param destination The destination of the transfer.
     */
    public void initiateTransfer(String connId, String destination) throws WorkspaceApiException {
        try {
            VoicecallsidinitiatetransferData data = new VoicecallsidinitiatetransferData();
            data.setDestination(destination);
            InitiateTransferData initData = new InitiateTransferData();
            initData.data(data);

            ApiSuccessResponse response = this.voiceApi.initiateTransfer(connId, initData);
            throwIfNotOk("initiateTransfer", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("initiateTransfer failed.", e);
        }
    }

    /**
     * Complete a previously initiated transfer using the provided ids.
     * @param connId The id of the consult call (established)
     * @param parentConnId The id of the parent call (held)
     */
    public void completeTransfer(String connId, String parentConnId) throws WorkspaceApiException {
        try {
            VoicecallsidcompletetransferData completeData = new VoicecallsidcompletetransferData();
            completeData.setParentConnId(parentConnId);
            CompleteTransferData data = new CompleteTransferData();
            data.data(completeData);

            ApiSuccessResponse response = this.voiceApi.completeTransfer(connId, data);
            throwIfNotOk("completeTransfer", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("completeTransfer failed.", e);
        }
    }

    /**
     * Alternate two calls retrieving the held call and placing the established call on hold. This is a 
     * shortcut for doing hold and retrieve separately.
     * @param connId The id of the established call.
     * @param heldConnId The id of the held call.
     */
    public void alternateCalls(String connId, String heldConnId) throws WorkspaceApiException {
        try {
            VoicecallsidalternateData alternateData = new VoicecallsidalternateData();
            alternateData.setHeldConnId(heldConnId);
            AlternateData data = new AlternateData();
            data.data(alternateData);

            ApiSuccessResponse response = this.voiceApi.alternate(connId, data);
            throwIfNotOk("alternateCalls", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("alternateCalls failed.", e);
        }
    }

    /**
     * Delete a dn from a conference call
     * @param connId The connId of the conference
     * @param dnToDrop The dn number to drop from the conference.
     */
    public void deleteFromConference(String connId, String dnToDrop) throws WorkspaceApiException {
        try {
            VoicecallsiddeletefromconferenceData deleteData =
                    new VoicecallsiddeletefromconferenceData();
            deleteData.setDnToDrop(dnToDrop);
            DeleteFromConferenceData data = new DeleteFromConferenceData();
            data.data(deleteData);
            ApiSuccessResponse response = this.voiceApi.deleteFromConference(connId, data);
            throwIfNotOk("deleteFromConference", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("deleteFromConference failed", e);
        }
    }

    /**
     * Perform a single-step transfer to the specified destination.
     * @param connId The id of the call to transfer.
     * @param destination The destination to transfer the call to.
     */
    public void singleStepTransfer(String connId, String destination) throws WorkspaceApiException {
        try {
            VoicecallsidsinglesteptransferData transferData =
                    new VoicecallsidsinglesteptransferData();
            transferData.setDestination(destination);
            SingleStepTransferData data = new SingleStepTransferData();
            data.data(transferData);

            ApiSuccessResponse response = this.voiceApi.singleStepTransfer(connId, data);
            throwIfNotOk("singleStepTransfer", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("singleStepTransfer failed", e);
        }
    }

    /**
     * Perform a single-step conference to the specififed destination. This will effectively add the
     * destination to the existing call, creating a conference if necessary.
     * @param connId The id of the call to conference.
     * @param destination The destination to be added to the call.
     */
    public void singleStepConference(String connId, String destination) throws WorkspaceApiException {
        try {
            VoicecallsidsinglestepconferenceData confData =
                    new VoicecallsidsinglestepconferenceData();
            confData.setDestination(destination);
            SingleStepConferenceData data = new SingleStepConferenceData();
            data.data(confData);

            ApiSuccessResponse response = this.voiceApi.singleStepConference(connId, data);
            throwIfNotOk("singleStepConference", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("singleStepConference failed", e);
        }
    }

    /**
     * Set do-not-disturb on for voice.
     */
    public void dndOn() throws WorkspaceApiException {
        try {
            ApiSuccessResponse response = this.voiceApi.setDNDOn();
            throwIfNotOk("dndOn", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("dndOn failed.", e);
        }
    }

    /**
     * Set do-not-disturb off for voice.
     */
    public void dndOff() throws WorkspaceApiException {
        try {
            ApiSuccessResponse response = this.voiceApi.setDNDOff();
            throwIfNotOk("dndOff", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("dndOff failed.", e);
        }
    }

    /**
     * Login the voice channel.
     */
    public void voiceLogin() throws WorkspaceApiException {
        try {
            ApiSuccessResponse response = this.voiceApi.loginVoice();
            throwIfNotOk("voiceLogin", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("voiceLogin failed", e);
        }
    }

    /**
     * Logout the voice channel.
     */
    public void voiceLogout() throws WorkspaceApiException {
        try {
            ApiSuccessResponse response = this.voiceApi.logoutVoice();
            throwIfNotOk("voiceLogout", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("voiceLogout failed", e);
        }
    }

    /**
     * Set call forwarding to the specififed destination.
     * @param forwardTo - destination to forward calls to.
     */
    public void setForward(String destination) throws WorkspaceApiException {
        try {
            VoicesetforwardData forwardData = new VoicesetforwardData();
            forwardData.setForwardTo(destination);

            ForwardData data = new ForwardData();
            data.data(forwardData);

            ApiSuccessResponse response = this.voiceApi.forward(data);
            throwIfNotOk("setForward", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("setForward failed.", e);
        }
    }

    /**
     * Cancel call forwarding.
     */
    public void cancelForward() throws WorkspaceApiException {
        try {
            ApiSuccessResponse response = this.voiceApi.cancelForward();
            throwIfNotOk("cancelForward", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("cancelForward failed.", e);
        }
    }

    /**
     * Attach the provided data to the call. This adds the data to the call even if data already exists 
     * with the provided keys.
     * @param connId The id of the call to attach data to.
     * @param userData The data to attach to the call. This is an array of objects with the properties key, type, and value.
     */
    public void attachUserData(String connId, List<Kvpair> userData) throws WorkspaceApiException {
        try {
            VoicecallsidcompleteData completeData = new VoicecallsidcompleteData();
            completeData.setUserData(userData);
            UserData data = new UserData();
            data.data(completeData);

            ApiSuccessResponse response = this.voiceApi.attachUserData(connId, data);
            throwIfNotOk("attachUserData", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("attachUserData failed.", e);
        }
    }

    /**
     * Update call data with the provided key/value pairs. This will replace any existing kvpairs with the same keys.
     * @param connId The id of the call to update data for.
     * @param userData The data to update. This is an array of objecvts with the properties key, type, and value.
     */
    public void updateUserData(String connId, List<Kvpair> userData) throws WorkspaceApiException {
        try {
            VoicecallsidcompleteData completeData = new VoicecallsidcompleteData();
            completeData.setUserData(userData);
            UserData data = new UserData();
            data.data(completeData);

            ApiSuccessResponse response = this.voiceApi.updateUserData(connId, data);
            throwIfNotOk("updateUserData", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("updateUserData failed.", e);
        }
    }

    /**
     * Delete data with the specified key from the call.
     * @param connId The call to remove data from.
     * @param key The key to remove.
     */
    public void deleteUserDataPair(String connId, String key) throws WorkspaceApiException {
        try {
            VoicecallsiddeleteuserdatapairData deletePairData =
                    new VoicecallsiddeleteuserdatapairData();
            deletePairData.setKey(key);
            KeyData data = new KeyData();
            data.data(deletePairData);

            ApiSuccessResponse response = this.voiceApi.deleteUserDataPair(connId, data);
            throwIfNotOk("deleteUserDataPair", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("deleteUserDataPair failed.", e);
        }
    }

    /**
     * Send DTMF digits to the specififed call.
     * @param connId The call to send DTMF digits to.
     * @param digits The DTMF digits to send.
     */
    public void sendDtmf(String connId, String digits) throws WorkspaceApiException {
        try {
            VoicecallsidsenddtmfData dtmfData = new VoicecallsidsenddtmfData();
            dtmfData.setDtmfDigits(digits);
            SendDTMFData data = new SendDTMFData();
            data.data(dtmfData);

            ApiSuccessResponse response = this.voiceApi.sendDTMF(connId, data);
            throwIfNotOk("sendDtmf", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("sendDtmf failed", e);
        }
    }

    /**
     * Send EventUserEvent with the provided data. 
     * @param data The data to be sent. This is an array of objects with the properties key, type, and value.
     */
    public void sendUserEvent(List<Kvpair> userData) throws WorkspaceApiException {
        this.sendUserEvent(userData, null);
    }

    /**
     * Send EventUserEvent with the provided data. 
     * @param data The data to be sent. This is an array of objects with the properties key, type, and value.
     * @param callUuid The callUuid that the event will be associated with.
     */
    public void sendUserEvent(List<Kvpair> userData, String callUuid) throws WorkspaceApiException {
        try {
            SendUserEventDataData sendUserEventData = new SendUserEventDataData();
            sendUserEventData.setUserData(userData);
            sendUserEventData.setCallUuid(callUuid);

            SendUserEventData data = new SendUserEventData();
            data.data(sendUserEventData);

            ApiSuccessResponse response = this.voiceApi.sendUserEvent(data);
            throwIfNotOk("sendUserEvent", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("sendUserEvent failed.", e);
        }
    }

    /**
     * Redirect call to the specified destination
     * @param connId The connId of the call to redirect.
     * @param destination The destination to redirect the call to.
     */
    public void redirectCall(String connId, String destination) throws WorkspaceApiException {
        try {
            VoicecallsidredirectData redirectData = new VoicecallsidredirectData();
            redirectData.setDestination(destination);
            RedirectData data = new RedirectData();
            data.data(redirectData);

            ApiSuccessResponse response = this.voiceApi.redirect(connId, data);
            throwIfNotOk("redirectCall", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("redirectCall failed.", e);
        }
    }

    /**
     * Merge the two specified calls.
     * @param connId The id of the first call to be merged.
     * @param otherConnId The id of the second call to be merged.
     */
    public void mergeCalls(String connId, String otherConnId) throws WorkspaceApiException {
        try {
            VoicecallsidmergeData mergeData = new VoicecallsidmergeData();
            mergeData.setOtherConnId(otherConnId);

            MergeData data = new MergeData();
            data.data(mergeData);

            ApiSuccessResponse response = this.voiceApi.merge(connId, data);
            throwIfNotOk("mergeCalls", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("mergeCalls failed.", e);
        }
    }

    /**
     * Reconnect the specified call. Reconnect releases the established call and retrieves the held call
     * in one step.
     * @param connId The id of the established call (will be released)
     * @param heldConnId The id of the held call (will be retrieved)
     */
    public void reconnectCall(String connId, String heldConnId) throws WorkspaceApiException {
        try {
            VoicecallsidreconnectData reconnectData = new VoicecallsidreconnectData();
            reconnectData.setHeldConnId(heldConnId);

            ReconnectData data = new ReconnectData();
            data.data(reconnectData);

            ApiSuccessResponse response = this.voiceApi.reconnect(connId, data);
            throwIfNotOk("reconnectCall", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("reconnectCall failed.", e);
        }
    }

    /**
     * Clear call.
     * @param connId The connId of the call to clear 
     */
    public void clearCall(String connId) throws WorkspaceApiException {
        try {
            VoicecallsidholdData clearData = new VoicecallsidholdData();

            ClearData data = new ClearData();
            data.data(clearData);

            ApiSuccessResponse response = this.voiceApi.clear(connId, data);
            throwIfNotOk("clearCall", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("clearCall failed.", e);
        }
    }

    /**
     * Start call recording
     * @param connId The id of the call to start recording. 
     */
    public void startRecording(String connId) throws WorkspaceApiException {
        try {
            ApiSuccessResponse response = this.voiceApi.startRecording(connId);
            throwIfNotOk("startRecording", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("startRecording failed.", e);
        }
    }

    /**
     * Pause call recording.
     * @param connId The id of the call to pause recording on. 
     */
    public void pauseRecording(String connId) throws WorkspaceApiException {
        try {
            ApiSuccessResponse response = this.voiceApi.pauseRecording(connId);
            throwIfNotOk("pauseRecording", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("pauseRecording failed.", e);
        }
    }

    /**
     * Resume call recording.
     * @param connId The id of the call to resume recording.
     */
    public void resumeRecording(String connId) throws WorkspaceApiException {
        try {
            ApiSuccessResponse response = this.voiceApi.resumeRecording(connId);
            throwIfNotOk("resumeRecording", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("resumeRecording failed.", e);
        }
    }

    /**
     * Stop call recording
     * @param connId The id of the call to stop recording.
     */
    public void stopRecording(String connId) throws WorkspaceApiException {
        try {
            ApiSuccessResponse response = this.voiceApi.stopRecording(connId);
            throwIfNotOk("stopRecording", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("stopRecording failed.", e);
        }
    }

    public boolean debugEnabled() {
        return this.debugEnabled;
    }

    public void setDebugEnabled(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
    }

}
