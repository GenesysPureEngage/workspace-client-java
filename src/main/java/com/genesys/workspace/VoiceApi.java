package com.genesys.workspace;

import com.genesys.internal.common.ApiClient;
import com.genesys.internal.common.ApiException;
import com.genesys.internal.workspace.model.AlternateData;
import com.genesys.internal.workspace.model.AnswerData;
import com.genesys.internal.workspace.model.ApiSuccessResponse;
import com.genesys.internal.workspace.model.ClearData;
import com.genesys.internal.workspace.model.CompleteConferenceData;
import com.genesys.internal.workspace.model.CompleteTransferData;
import com.genesys.internal.workspace.model.DeleteFromConferenceData;
import com.genesys.internal.workspace.model.ForwardData;
import com.genesys.internal.workspace.model.HoldData;
import com.genesys.internal.workspace.model.InitiateConferenceData;
import com.genesys.internal.workspace.model.InitiateTransferData;
import com.genesys.internal.workspace.model.KeyData;
import com.genesys.internal.workspace.model.MakeCallData;
import com.genesys.internal.workspace.model.MergeData;
import com.genesys.internal.workspace.model.NotReadyData;
import com.genesys.internal.workspace.model.ReadyData;
import com.genesys.internal.workspace.model.ReconnectData;
import com.genesys.internal.workspace.model.RedirectData;
import com.genesys.internal.workspace.model.ReleaseData;
import com.genesys.internal.workspace.model.RetrieveData;
import com.genesys.internal.workspace.model.SendDTMFData;
import com.genesys.internal.workspace.model.SendUserEventData;
import com.genesys.internal.workspace.model.SendUserEventDataData;
import com.genesys.internal.workspace.model.SingleStepConferenceData;
import com.genesys.internal.workspace.model.SingleStepTransferData;
import com.genesys.internal.workspace.model.UserData;
import com.genesys.internal.workspace.model.VoicecallsidalternateData;
import com.genesys.internal.workspace.model.VoicecallsidcompleteData;
import com.genesys.internal.workspace.model.VoicecallsidcompletetransferData;
import com.genesys.internal.workspace.model.VoicecallsiddeletefromconferenceData;
import com.genesys.internal.workspace.model.VoicecallsiddeleteuserdatapairData;
import com.genesys.internal.workspace.model.VoicecallsidinitiateconferenceData;
import com.genesys.internal.workspace.model.VoicecallsidinitiatetransferData;
import com.genesys.internal.workspace.model.VoicecallsidmergeData;
import com.genesys.internal.workspace.model.VoicecallsidreconnectData;
import com.genesys.internal.workspace.model.VoicecallsidredirectData;
import com.genesys.internal.workspace.model.VoicecallsidsenddtmfData;
import com.genesys.internal.workspace.model.VoicecallsidsinglestepconferenceData;
import com.genesys.internal.workspace.model.VoicecallsidsinglesteptransferData;
import com.genesys.internal.workspace.model.VoicemakecallData;
import com.genesys.internal.workspace.model.VoicenotreadyData;
import com.genesys.internal.workspace.model.VoicereadyData;
import com.genesys.internal.workspace.model.VoicesetforwardData;
import com.genesys.workspace.common.StatusCode;
import com.genesys.workspace.common.WorkspaceApiException;
import com.genesys.workspace.events.*;
import com.genesys.workspace.models.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoiceApi {
    private static final Logger logger = LoggerFactory.getLogger(VoiceApi.class);
    
    private com.genesys.internal.workspace.api.VoiceApi voiceApi;
    private DNumber dn;
    private Map<String, Call> calls;
    private Set<DnEventListener> dnEventListeners;
    private Set<CallEventListener> callEventListeners;
    private Set<ErrorEventListener> errorEventListeners;

    public VoiceApi() {
        this.calls = new HashMap<>();
        this.dnEventListeners = new HashSet<>();
        this.callEventListeners = new HashSet<>();
        this.errorEventListeners = new HashSet<>();
    }

    void initialize(ApiClient apiClient) {
        this.voiceApi = new com.genesys.internal.workspace.api.VoiceApi(apiClient);
    }

    private void publishCallStateChanged(CallStateChanged msg) {
        this.callEventListeners.forEach(listener -> {
            try {
                listener.handleCallStateChanged(msg);
            } catch (Exception e) {
                logger.debug("Exception in listener" + e);
            }
        });
    }

    private void publishDnStateChanged(DnStateChanged msg) {
        this.dnEventListeners.forEach(listener -> {
            try {
                listener.handleDnStateChanged(msg);
            } catch (Exception e) {
                logger.debug("Exception in listener" + e);
            }
        });
    }

    private void publishErrorEvent(EventError msg) {
        this.errorEventListeners.forEach(listener -> {
            try {
                listener.handleEventError(msg);
            } catch (Exception e) {
                logger.debug("Exception in listener" + e);
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

    public void onDnStateChanged(Map<String, Object> data) {
        if (this.dn == null) {
            this.dn = new DNumber();
        }

        Map<String, Object> dnData = (Map<String, Object>)data.get("dn");

        String number = (String)dnData.get("number");
        String agentId = (String)dnData.get("agentId");
        AgentState agentState = Util.parseAgentState((String)dnData.get("agentState"));
        AgentWorkMode workMode = Util.parseAgentWorkMode((String)dnData.get("agentWorkMode"));
        String forwardTo = (String)dnData.get("forwardTo");
        String dnd = (String)dnData.get("dnd");

        this.dn.setAgentId(agentId);
        this.dn.setNumber(number);
        this.dn.setAgentState(agentState);
        this.dn.setWorkMode(workMode);
        this.dn.setForwardTo(forwardTo);
        this.dn.setDND(dnd != null && "on".equals(dnd));

        logger.debug("Dn updated: state [" + agentState + "] workMode [" + workMode + "]...");
        this.publishDnStateChanged(new DnStateChanged(this.dn));
    }

    private void onCallStateChanged(Map<String, Object> data) {
        Map<String, Object> callData = (Map<String, Object>)data.get("call");
        NotificationType notificationType = Util.parseNotificationType((String)data.get("notificationType"));

        String id = (String)callData.get("id");
        String callUuid = (String)callData.get("callUuid");
        CallState state = Util.parseCallState((String)callData.get("state"));
        String parentConnId = (String)callData.get("parentConnId");
        String previousConnId = (String)callData.get("previousConnId");
        String ani = (String)callData.get("ani");
        String dnis = (String)callData.get("dnis");
        Object[] capabilities = (Object[])callData.getOrDefault("capabilities", new Object[]{});
        Object[] participantData = (Object[])callData.get("participants");
        Map<String,Object> userData = Util.extractKeyValueData((Object[])callData.get("userData"));

        String[] participants = Util.extractParticipants(participantData);

        boolean connIdChanged = false;
        String callType = (String)callData.get("callType");

        Call call = this.calls.get(id);
        if (call == null) {
            call = new Call();
            call.setId(id);
            call.setCallType(callType);
            if (parentConnId != null) {
                call.setParentConnId(parentConnId);
            }

            this.calls.put(id, call);
            logger.debug("Added call " + id + " (" + state + ")");
        }

        if (previousConnId != null && this.calls.containsKey(previousConnId)) {
            call = this.calls.remove(previousConnId);
            call.setId(id);
            call.setPreviousConnId(previousConnId);
            this.calls.put(id, call);
            connIdChanged = true;
        } else if (state == CallState.RELEASED) {
            this.calls.remove(id);
            logger.debug("Removed call " + id + "(" + state + ")");
        }

        call.setState(state);
        call.setANI(ani);
        call.setDNIS(dnis);
        call.setCallUuid(callUuid);
        call.setParticipants(participants);
        call.setUserData(userData);
        call.setCapabilities(Arrays.stream(capabilities).map(v -> Capability.fromString((String)v)).filter(Objects::nonNull).toArray(n -> new Capability[n]));

        this.publishCallStateChanged(new CallStateChanged(call, notificationType, connIdChanged ? previousConnId : null));
    }

    private void onEventError(Map<String, Object> data) {
        Map<String, Object> errorDetails = (Map<String, Object>)data.get("error");
        String msg = (String)errorDetails.get("errorMessage");
        String code = errorDetails.get("errorCode") == null ? "" : errorDetails.get("errorCode").toString();

        this.publishErrorEvent(new EventError(msg, code));
    }

    public void onVoiceMessage(Map<String, Object> data) {
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
                logger.debug("Unexpected messageType: " + messageType);
        }
    }

    private void throwIfNotOk(String requestName, ApiSuccessResponse response) throws WorkspaceApiException {
        if (response.getStatus().getCode() != StatusCode.ASYNC_OK) {
            throw new WorkspaceApiException(
                    requestName + " failed with code: " + response.getStatus().getCode());
        }
    }

    public DNumber getDn() {
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
     * Set the agent state to ready.
     */
    public void setAgentReady() throws WorkspaceApiException {
        this.setAgentReady(null, null);
    }

    /**
     * Set the agent state to ready.
     * @param reasons reasons
     * @param extensions extensions
     */
    public void setAgentReady(Map<String,Object> reasons, Map<String,Object> extensions) throws WorkspaceApiException {
        try {
            VoicereadyData readyData = new VoicereadyData();
            readyData.setReasons(Util.toKVList(reasons));
            readyData.setExtensions(Util.toKVList(extensions));
            ReadyData data = new ReadyData();
            data.data(readyData);

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
        this.setAgentNotReady(null, null, null, null);
    }

    /**
     * Set the agent state to not ready.
     * @param workMode optional workMode to use in the request.
     * @param reasonCode optional reasonCode to use in the request.
     */
    public void setAgentNotReady(String workMode, String reasonCode) throws WorkspaceApiException {
        this.setAgentNotReady(workMode, reasonCode, null, null);
    }

    /**
     * Set the agent state to not ready.
     * @param workMode optional workMode to use in the request.
     * @param reasonCode optional reasonCode to use in the request.
     * @param reasons reasons
     * @param extensions extensions
     */
    public void setAgentNotReady(
            String workMode,
            String reasonCode,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            NotReadyData data = new NotReadyData();

            VoicenotreadyData notReadyData = new VoicenotreadyData();
            notReadyData.setReasonCode(reasonCode);
            notReadyData.setReasons(Util.toKVList(reasons));
            notReadyData.setExtensions(Util.toKVList(extensions));

            if (workMode != null) {
                notReadyData.setAgentWorkMode(VoicenotreadyData.AgentWorkModeEnum.fromValue(workMode));
            }

            data.data(notReadyData);

            ApiSuccessResponse response = this.voiceApi.setAgentStateNotReady(data);
            throwIfNotOk("setAgentNotReady", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("setAgentReady failed.", e);
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
    public void login() throws WorkspaceApiException {
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
    public void logout() throws WorkspaceApiException {
        try {
            ApiSuccessResponse response = this.voiceApi.logoutVoice();
            throwIfNotOk("voiceLogout", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("voiceLogout failed", e);
        }
    }

    /**
     * Set call forwarding to the specififed destination.
     * @param destination - destination to forward calls to.
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
     * Make a new call to the specified destination.
     * @param destination The destination to call
     */
    public void makeCall(String destination) throws WorkspaceApiException {
        this.makeCall(destination, null, null, null);
    }

    /**
     * Make a new call to the specified destination.
     * @param destination The destination to call
     * @param userData userData to be included with the new call
     */
    public void makeCall(
            String destination,
            Map<String,Object> userData
    ) throws WorkspaceApiException {
        this.makeCall(destination, userData, null, null);
    }

    /**
     * Make a new call to the specified destination.
     * @param destination The destination to call
     * @param userData userData to be included with the new call
     * @param reasons reasons
     * @param extensions extensions
     */
    public void makeCall(
            String destination,
            Map<String,Object> userData,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicemakecallData data = new VoicemakecallData();
            data.destination(destination);
            data.setUserData(Util.toKVList(userData));
            data.setExtensions(Util.toKVList(extensions));
            data.setReasons(Util.toKVList(reasons));
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
        this.answerCall(connId, null, null);
    }

    /**
     * Answer call.
     * @param connId The connId of the call to answer.
     * @param reasons reasons
     * @param extensions extensions
     */
    public void answerCall(
            String connId,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicereadyData answerData = new VoicereadyData();
            answerData.setReasons(Util.toKVList(reasons));
            answerData.setExtensions(Util.toKVList(extensions));
            AnswerData data = new AnswerData();
            data.setData(answerData);

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
        this.holdCall(connId, null, null);
    }

    /**
     * Place call on hold.
     * @param connId The connId of the call to place on hold.
     * @param reasons reasons
     * @param extensions extensions
     */
    public void holdCall(
            String connId,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicereadyData holdData = new VoicereadyData();
            holdData.setReasons(Util.toKVList(reasons));
            holdData.setExtensions(Util.toKVList(extensions));
            HoldData data = new HoldData();
            data.data(holdData);

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
        this.retrieveCall(connId, null, null);
    }

    /**
     * Retrieve call from hold.
     * @param connId The connId of the call to retrieve.
     * @param reasons reasons
     * @param extensions extensions
     */
    public void retrieveCall(
            String connId,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicereadyData retrieveData = new VoicereadyData();
            retrieveData.setReasons(Util.toKVList(reasons));
            retrieveData.setExtensions(Util.toKVList(extensions));
            RetrieveData data = new RetrieveData();
            data.data(retrieveData);

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
        this.releaseCall(connId, null, null);
    }

    /**
     * Release call.
     * @param connId The connId of the call to release
     * @param reasons reasons
     * @param extensions extensions
     */
    public void releaseCall(
            String connId,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {

        try {
            VoicereadyData releaseData = new VoicereadyData();
            releaseData.setReasons(Util.toKVList(reasons));
            releaseData.setExtensions(Util.toKVList(extensions));
            ReleaseData data = new ReleaseData();
            data.data(releaseData);

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
        this.initiateConference(connId, destination, null, null, null, null, null);
    }

    /**
     * Initiate a conference to the specified destination.
     * @param connId The connId of the call to start the conference from.
     * @param destination The destination
     * @param userData userdata to be used for the new consult call.
     */
    public void initiateConference(
            String connId,
            String destination,
            Map<String,Object> userData
    ) throws WorkspaceApiException {
        this.initiateConference(connId, destination, null, null, userData, null, null);
    }

    /**
     * Initiate a conference to the specified destination.
     * @param connId The connId of the call to start the conference from.
     * @param destination The destination
     * @param location
     * @param outboundCallerId
     * @param userData userdata to be used for the new consult call.
     * @param reasons reasons
     * @param extensions extensions
     */
    public void initiateConference(
            String connId,
            String destination,
            String location,
            String outboundCallerId,
            Map<String,Object> userData,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicecallsidinitiateconferenceData initData = new VoicecallsidinitiateconferenceData();
            initData.setDestination(destination);
            initData.setLocation(location);
            initData.setOutboundCallerId(outboundCallerId);
            initData.setUserData(Util.toKVList(userData));
            initData.setReasons(Util.toKVList(reasons));
            initData.setExtensions(Util.toKVList(extensions));
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
        this.completeConference(connId, parentConnId, null, null);
    }

    /**
     * Complete a previously initiated conference identified by the provided ids.
     * @param connId The id of the consule call (established)
     * @param parentConnId The id of the parent call (held).
     * @param reasons reasons
     * @param extensions extensions
     */
    public void completeConference(
            String connId,
            String parentConnId,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicecallsidcompletetransferData completeData = new VoicecallsidcompletetransferData();
            completeData.setParentConnId(parentConnId);
            completeData.setReasons(Util.toKVList(reasons));
            completeData.setExtensions(Util.toKVList(extensions));
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
     * @param connId The connId of the call to be transferred.
     * @param destination The destination of the transfer.
     */
    public void initiateTransfer(String connId, String destination) throws WorkspaceApiException {
        this.initiateTransfer(connId, destination, null, null, null, null, null);
    }

    /**
     * Initiate a transfer to the specified destination.
     * @param connId The connId of the call to be transferred.
     * @param destination The destination of the transfer.
     * @param userData userdata to be included with the new consult call
     */
    public void initiateTransfer(
            String connId,
            String destination,
            Map<String,Object> userData
    ) throws WorkspaceApiException {
        this.initiateTransfer(connId, destination, null, null, userData, null, null);
    }

    /**
     * Initiate a transfer to the specified destination.
     * @param connId  The connId of the call to be transferred.
     * @param destination The destination
     * @param location
     * @param outboundCallerId
     * @param userData userdata to be used for the new consult call.
     * @param reasons reasons
     * @param extensions extensions
     */
    public void initiateTransfer(
            String connId,
            String destination,
            String location,
            String outboundCallerId,
            Map<String,Object> userData,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicecallsidinitiatetransferData data = new VoicecallsidinitiatetransferData();
            data.setDestination(destination);
            data.setLocation(location);
            data.setOutboundCallerId(outboundCallerId);
            data.setUserData(Util.toKVList(userData));
            data.setReasons(Util.toKVList(reasons));
            data.setExtensions(Util.toKVList(extensions));
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
        this.completeTransfer(connId, parentConnId, null, null);
    }

    /**
     * Complete a previously initiated transfer using the provided ids.
     * @param connId The id of the consult call (established)
     * @param parentConnId The id of the parent call (held)
     * @param reasons reasons
     * @param extensions extensions
     */
    public void completeTransfer(
            String connId,
            String parentConnId,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicecallsidcompletetransferData completeData = new VoicecallsidcompletetransferData();
            completeData.setParentConnId(parentConnId);
            completeData.setReasons(Util.toKVList(reasons));
            completeData.setExtensions(Util.toKVList(extensions));
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
        this.alternateCalls(connId, heldConnId, null, null);
    }

    /**
     * Alternate two calls retrieving the held call and placing the established call on hold. This is a
     * shortcut for doing hold and retrieve separately.
     * @param connId The id of the established call.
     * @param heldConnId The id of the held call.
     * @param reasons reasons
     * @param extensions extensions
     */
    public void alternateCalls(
            String connId,
            String heldConnId,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicecallsidalternateData alternateData = new VoicecallsidalternateData();
            alternateData.setHeldConnId(heldConnId);
            alternateData.setReasons(Util.toKVList(reasons));
            alternateData.setExtensions(Util.toKVList(extensions));
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
        this.deleteFromConference(connId, dnToDrop, null, null);
    }

    /**
     * Delete a dn from a conference call
     * @param connId The connId of the conference
     * @param dnToDrop The dn number to drop from the conference.
     * @param reasons reasons
     * @param extensions extensions
     */
    public void deleteFromConference(
            String connId,
            String dnToDrop,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicecallsiddeletefromconferenceData deleteData =
                    new VoicecallsiddeletefromconferenceData();
            deleteData.setDnToDrop(dnToDrop);
            deleteData.setReasons(Util.toKVList(reasons));
            deleteData.setExtensions(Util.toKVList(extensions));

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
        this.singleStepTransfer(connId, destination, null, null, null, null);
    }

    /**
     * Perform a single-step transfer to the specified destination.
     * @param connId The id of the call to transfer.
     * @param destination The destination to transfer the call to.
     * @param userData userdata to be included on the transfer
     */
    public void singleStepTransfer(
            String connId,
            String destination,
            Map<String,Object> userData
    ) throws WorkspaceApiException {
        this.singleStepTransfer(connId, destination, null, userData, null, null);
    }

    /**
     * Perform a single-step transfer to the specified destination.
     * @param connId The id of the call to transfer.
     * @param destination The destination to transfer the call to.
     * @param location
     * @param userData userdata to be used for the new consult call.
     * @param reasons reasons
     * @param extensions extensions
     */
    public void singleStepTransfer(
            String connId,
            String destination,
            String location,
            Map<String,Object> userData,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicecallsidsinglesteptransferData transferData =
                    new VoicecallsidsinglesteptransferData();
            transferData.setDestination(destination);
            transferData.setLocation(location);
            transferData.setUserData(Util.toKVList(userData));
            transferData.setReasons(Util.toKVList(reasons));
            transferData.setExtensions(Util.toKVList(extensions));
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
        this.singleStepConference(connId, destination, null, null, null, null);

    }

    /**
     * Perform a single-step conference to the specififed destination. This will effectively add the
     * destination to the existing call, creating a conference if necessary.
     * @param connId The id of the call to conference.
     * @param destination The destination to be added to the call.
     * @param userData userdata to be included with the request
     *
     */
    public void singleStepConference(
            String connId,
            String destination,
            Map<String,Object> userData
    ) throws WorkspaceApiException {
        this.singleStepConference(connId, destination, null, userData, null, null);
    }

    /**
     * Perform a single-step conference to the specififed destination. This will effectively add the
     * destination to the existing call, creating a conference if necessary.
     * @param connId The id of the call to conference.
     * @param destination The destination to be added to the call.
     * @param location
     * @param userData userdata to be included with the request
     * @param reasons reasons
     * @param extensions extensions
     */
    public void singleStepConference(
            String connId,
            String destination,
            String location,
            Map<String,Object> userData,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicecallsidsinglestepconferenceData confData =
                    new VoicecallsidsinglestepconferenceData();
            confData.setDestination(destination);
            confData.setLocation(location);
            confData.setUserData(Util.toKVList(userData));
            confData.setReasons(Util.toKVList(reasons));
            confData.setExtensions(Util.toKVList(extensions));
            SingleStepConferenceData data = new SingleStepConferenceData();
            data.data(confData);

            ApiSuccessResponse response = this.voiceApi.singleStepConference(connId, data);
            throwIfNotOk("singleStepConference", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("singleStepConference failed", e);
        }
    }

    /**
     * Attach the provided data to the call. This adds the data to the call even if data already exists
     * with the provided keys.
     * @param connId The id of the call to attach data to.
     * @param userData The data to attach to the call. This is an array of objects with the properties key, type, and value.
     */
    public void attachUserData(String connId, Map<String,Object> userData) throws WorkspaceApiException {
        try {
            VoicecallsidcompleteData completeData = new VoicecallsidcompleteData();
            completeData.setUserData(Util.toKVList(userData));
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
    public void updateUserData(String connId, Map<String,Object> userData) throws WorkspaceApiException {
        try {
            VoicecallsidcompleteData completeData = new VoicecallsidcompleteData();
            completeData.setUserData(Util.toKVList(userData));
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
    public void sendDTMF(String connId, String digits) throws WorkspaceApiException {
        this.sendDTMF(connId, digits, null, null);
    }

    /**
     * Send DTMF digits to the specififed call.
     * @param connId The call to send DTMF digits to.
     * @param digits The DTMF digits to send.
     * @param reasons reasons
     * @param extensions extensions
     */
    public void sendDTMF(
            String connId,
            String digits,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicecallsidsenddtmfData dtmfData = new VoicecallsidsenddtmfData();
            dtmfData.setDtmfDigits(digits);
            dtmfData.setReasons(Util.toKVList(reasons));
            dtmfData.setExtensions(Util.toKVList(extensions));
            SendDTMFData data = new SendDTMFData();
            data.data(dtmfData);

            ApiSuccessResponse response = this.voiceApi.sendDTMF(connId, data);
            throwIfNotOk("sendDTMF", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("sendDTMF failed", e);
        }
    }

    /**
     * Send EventUserEvent with the provided data.
     * @param userData The data to be sent. This is an array of objects with the properties key, type, and value.
     */
    public void sendUserEvent(Map<String,Object> userData) throws WorkspaceApiException {
        this.sendUserEvent(userData, null);
    }

    /**
     * Send EventUserEvent with the provided data.
     * @param userData The data to be sent. This is an array of objects with the properties key, type, and value.
     * @param callUuid The callUuid that the event will be associated with.
     */
    public void sendUserEvent(Map<String,Object> userData, String callUuid) throws WorkspaceApiException {
        try {
            SendUserEventDataData sendUserEventData = new SendUserEventDataData();
            sendUserEventData.setUserData(Util.toKVList(userData));
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
        this.redirectCall(connId, destination, null, null);
    }

    /**
     * Redirect call to the specified destination
     * @param connId The connId of the call to redirect.
     * @param destination The destination to redirect the call to.
     * @param reasons reasons
     * @param extensions extensions
     */
    public void redirectCall(
            String connId,
            String destination,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicecallsidredirectData redirectData = new VoicecallsidredirectData();
            redirectData.setDestination(destination);
            redirectData.setReasons(Util.toKVList(reasons));
            redirectData.setExtensions(Util.toKVList(extensions));
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
        this.mergeCalls(connId, otherConnId, null, null);
    }

    /**
     * Merge the two specified calls.
     * @param connId The id of the first call to be merged.
     * @param otherConnId The id of the second call to be merged.
     * @param reasons reasons
     * @param extensions extensions
     */
    public void mergeCalls(
            String connId,
            String otherConnId,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicecallsidmergeData mergeData = new VoicecallsidmergeData();
            mergeData.setOtherConnId(otherConnId);
            mergeData.setReasons(Util.toKVList(reasons));
            mergeData.setExtensions(Util.toKVList(extensions));

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
        this.reconnectCall(connId, heldConnId, null, null);
    }

    /**
     * Reconnect the specified call. Reconnect releases the established call and retrieves the held call
     * in one step.
     * @param connId The id of the established call (will be released)
     * @param heldConnId The id of the held call (will be retrieved)
     * @param reasons reasons
     * @param extensions extensions
     */
    public void reconnectCall(
            String connId,
            String heldConnId,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicecallsidreconnectData reconnectData = new VoicecallsidreconnectData();
            reconnectData.setHeldConnId(heldConnId);
            reconnectData.setReasons(Util.toKVList(reasons));
            reconnectData.setExtensions(Util.toKVList(extensions));

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
        this.clearCall(connId, null, null);
    }

    /**
     * Clear call.
     * @param connId The connId of the call to clear
     * @param reasons reasons
     * @param extensions extensions
     */
    public void clearCall(
            String connId,
            Map<String,Object> reasons,
            Map<String,Object> extensions
    ) throws WorkspaceApiException {
        try {
            VoicereadyData clearData = new VoicereadyData();
            clearData.setReasons(Util.toKVList(reasons));
            clearData.setExtensions(Util.toKVList(extensions));

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

    void setVoiceApi(com.genesys.internal.workspace.api.VoiceApi api) {
        voiceApi = api;
    }
}
