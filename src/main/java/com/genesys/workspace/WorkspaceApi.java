package com.genesys.workspace;

import java.net.URI;
import java.net.HttpCookie;
import java.net.CookieManager;

import java.util.Base64;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Optional;

import com.genesys.internal.common.ApiClient;
import com.genesys.internal.common.ApiResponse;
import com.genesys.internal.common.ApiException;
import com.genesys.internal.authorization.api.AuthenticationApi;
import com.genesys.internal.authorization.model.DefaultOAuth2AccessToken;

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
    private String clientId;
    private String clientSecret;
    private String baseUrl;
    private String username;
    private String password;
    private boolean debugEnabled;
    private String workspaceUrl;
    private ApiClient authClient;
    private ApiClient workspaceClient;
    private HttpClient cometdHttpClient;
    private BayeuxClient cometdClient;
    private AuthenticationApi authApi;
    private SessionApi sessionApi;
    private VoiceApi voiceApi;
    private DefaultOAuth2AccessToken accessToken;
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

    public WorkspaceApi(
            String apiKey,
            String clientId,
            String clientSecret,
            String baseUrl,
            String username,
            String password,
            boolean debugEnabled
    ) {
        this.apiKey = apiKey;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
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

    public void addCallEventListener(CallEventListener listener) {
        this.callEventListeners.add(listener);
    }

    public void removeCallEventListener(CallEventListener listener) {
        this.callEventListeners.remove(listener);
    }

    public void addDnEventListener(DnEventListener listener) {
        this.dnEventListeners.add(listener);
    }

    public void removeDnEventListener(DnEventListener listener) {
        this.dnEventListeners.remove(listener);
    }

    public void addErrorEventListener(ErrorEventListener listener) {
        this.errorEventListeners.add(listener);
    }

    public void removeErrorEventListener(ErrorEventListener listener) {
        this.errorEventListeners.remove(listener);
    }

    private void getAccessToken() throws ApiException {
        this.debug("Getting access token...");
        byte[] bytes = (this.clientId + ":" + this.clientSecret).getBytes();
        byte[] encoded = Base64.getEncoder().encode(bytes);
        String authorization = "Basic " + new String(encoded);

        this.accessToken = this.authApi.retrieveToken(
                "password", "webshop", authorization, null, this.clientId, this.username, this.password);
        this.debug("Access token is " + this.accessToken.getAccessToken());
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

    public CompletableFuture<User> initialize() throws WorkspaceApiException {
        try {
            this.initFuture = new CompletableFuture<>();

            this.workspaceClient = new ApiClient();
            this.workspaceClient.setBasePath(this.workspaceUrl);
            this.workspaceClient.addDefaultHeader("x-api-key", this.apiKey);

            this.sessionApi = new SessionApi(this.workspaceClient);
            this.voiceApi = new VoiceApi(this.workspaceClient);

            this.authClient = new ApiClient();
            this.authClient.setBasePath(this.baseUrl);
            this.authClient.addDefaultHeader("x-api-key", this.apiKey);

            this.authApi = new AuthenticationApi(this.authClient);

            this.getAccessToken();

            String authorization = "Bearer " + this.accessToken.getAccessToken();
            final ApiResponse<ApiSuccessResponse> response =
                    this.sessionApi.initializeWorkspaceWithHttpInfo("", "", authorization);
            this.extractSessionCookie(response);

            this.initializeCometd();
            return initFuture;

        } catch (ApiException e) {
            throw new WorkspaceApiException("initialize failed.", e);
        }
    }

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

    public Collection<Call> getCalls() {
        return this.calls.values();
    }

    public User getUser() {
        return this.user;
    }

    public void setAgentReady() throws WorkspaceApiException {
        try {
            ReadyData data = new ReadyData();

            ApiSuccessResponse response = this.voiceApi.setAgentStateReady(data);
            throwIfNotOk("setAgentReady", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("setAgentReady failed.", e);
        }
    }

    public void setAgentNotReady() throws WorkspaceApiException {
        this.setAgentNotReady(null, null);
    }

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

    public void answerCall(String connId) throws WorkspaceApiException {
        try {
            AnswerData data = new AnswerData();

            ApiSuccessResponse response = this.voiceApi.answer(connId, data);
            throwIfNotOk("answerCall", response);

        } catch (ApiException e) {
            throw new WorkspaceApiException("answerCall failed.", e);
        }
    }

    public void holdCall(String connId) throws WorkspaceApiException {
        try {
            HoldData data = new HoldData();

            ApiSuccessResponse response = this.voiceApi.hold(connId, data);
            throwIfNotOk("holdCall", response);

        } catch (ApiException e) {
            throw new WorkspaceApiException("holdCall failed.", e);
        }
    }

    public void retrieveCall(String connId) throws WorkspaceApiException {
        try {
            RetrieveData data = new RetrieveData();

            ApiSuccessResponse response = this.voiceApi.retrieve(connId, data);
            throwIfNotOk("retrieveCall", response);

        } catch (ApiException e) {
            throw new WorkspaceApiException("retrieveCall failed.", e);
        }
    }

    public void releaseCall(String connId) throws WorkspaceApiException {
        try {
            ReleaseData data = new ReleaseData();

            ApiSuccessResponse response = this.voiceApi.release(connId, data);
            throwIfNotOk("releaseCall", response);

        } catch (ApiException e) {
            throw new WorkspaceApiException("releaseCall failed.", e);
        }
    }

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

    public boolean debugEnabled() {
        return this.debugEnabled;
    }

    public void setDebugEnabled(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
    }

}
