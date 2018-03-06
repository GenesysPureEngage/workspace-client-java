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
import com.genesys.workspace.events.CallEventListener;
import com.genesys.workspace.events.CallStateChanged;
import com.genesys.workspace.events.DnEventListener;
import com.genesys.workspace.events.DnStateChanged;
import com.genesys.workspace.events.ErrorEventListener;
import com.genesys.workspace.events.EventError;
import com.genesys.workspace.events.NotificationType;
import com.genesys.workspace.models.AgentState;
import com.genesys.workspace.models.AgentWorkMode;
import com.genesys.workspace.models.Call;
import com.genesys.workspace.models.CallState;
import com.genesys.workspace.models.Capability;
import com.genesys.workspace.models.Dn;
import com.genesys.workspace.models.KeyValueCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class VoiceApi {
    private static final Logger logger = LoggerFactory.getLogger(VoiceApi.class);
    
    private com.genesys.internal.workspace.api.VoiceApi voiceApi;
    private Dn dn;
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
        for (CallEventListener callEventListener : this.callEventListeners) {
            try {
                callEventListener.handleCallStateChanged(msg);
            } catch (Exception e) {
                logger.debug("Exception in listener" + e);
            }
        }
    }

    private void publishDnStateChanged(DnStateChanged msg) {
        for(DnEventListener dnEventListener: this.dnEventListeners) {
            try {
                dnEventListener.handleDnStateChanged(msg);
            } catch (Exception e) {
                logger.debug("Exception in listener" + e);
            }
        }
    }

    private void publishErrorEvent(EventError msg) {
        for (ErrorEventListener errorEventListener : this.errorEventListeners) {
            try {
                errorEventListener.handleEventError(msg);
            } catch (Exception e) {
                logger.debug("Exception in listener" + e);
            }
        }
    }

    /**
     * Add a listener for CallStateChanged events.
     * @param listener The listener to be added.
     */
    public void addCallEventListener(CallEventListener listener) {
        this.callEventListeners.add(listener);
    }

    /**
     * Remove a previously added CallStateChanged listener.
     * @param listener The listener to be removed
     */
    public void removeCallEventListener(CallEventListener listener) {
        this.callEventListeners.remove(listener);
    }

    /**
     * Add a listener for DnStateChanged events.
     * @param listener The listener to be added.
     */
    public void addDnEventListener(DnEventListener listener) {
        this.dnEventListeners.add(listener);
    }

    /**
     * Remove a previously added DnStateChanged listener.
     * @param listener The listener to be removed.
     */
    public void removeDnEventListener(DnEventListener listener) {
        this.dnEventListeners.remove(listener);
    }

    /**
     * Add a listener for EventError.
     * @param listener The listener to be added.
     */
    public void addErrorEventListener(ErrorEventListener listener) {
        this.errorEventListeners.add(listener);
    }

    /**
     * Remove a previously added EventError listener.
     * @param listener The listener to be removed.
     */
    public void removeErrorEventListener(ErrorEventListener listener) {
        this.errorEventListeners.remove(listener);
    }

    public void onDnStateChanged(Map<String, Object> data) {
        if (this.dn == null) {
            this.dn = new Dn();
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
        Object[] capabilities;
        if(callData.get("capabilities") ==  null) {
            capabilities = new Object[]{};
        } else {
            capabilities = (Object[]) callData.get("capabilities");
        }
        Object[] participantData = (Object[])callData.get("participants");
        KeyValueCollection userData = Util.extractKeyValueData((Object[])callData.get("userData"));

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
        List<Capability> capabilityList = new ArrayList<>(10);
        for(Object capability: capabilities){
            String capabilityStringValue = (String) capability;
            Capability capabilityObject = Capability.fromString(capabilityStringValue);
            if(capability != null){
                capabilityList.add(capabilityObject);
            }
        }
        call.setCapabilities(capabilityList.toArray(new Capability[capabilityList.size()]));
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

    public Dn getDn() {
        return this.dn;
    }

    /**
     * Get the list of active calls.
     * @return Collection<Call>
     */
    public Collection<Call> getCalls() {
        return this.calls.values();
    }

    /**
     * Set the current agent's state to Ready on the voice channel.
     */
    public void setAgentReady() throws WorkspaceApiException {
        this.setAgentReady(null, null);
    }

    /**
     * Set the current agent's state to Ready on the voice channel.
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void setAgentReady(KeyValueCollection reasons, KeyValueCollection extensions) throws WorkspaceApiException {
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
     * Set the current agent's state to NotReady on the voice channel.
     */
    public void setAgentNotReady() throws WorkspaceApiException {
        this.setAgentNotReady(null, null, null, null);
    }

    /**
     * Set the current agent's state to NotReady on the voice channel.
     * @param workMode The agent workmode. Possible values are AfterCallWork, AuxWork, LegalGuard, NoCallDisconnect, WalkAway. (optional)
     * @param reasonCode The reason code representing why the agent is not ready. These codes are a business-defined set of categories, such as "Lunch" or "Training". (optional)
     */
    public void setAgentNotReady(String workMode, String reasonCode) throws WorkspaceApiException {
        this.setAgentNotReady(workMode, reasonCode, null, null);
    }

    /**
     * Set the current agent's state to NotReady on the voice channel.
     * @param workMode The agent workmode. Possible values are AfterCallWork, AuxWork, LegalGuard, NoCallDisconnect, WalkAway. (optional)
     * @param reasonCode The reason code representing why the agent is not ready. These codes are a business-defined set of categories, such as "Lunch" or "Training". (optional)
     * @param reasons reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void setAgentNotReady(
            String workMode,
            String reasonCode,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * Set the current agent's state to do-not-disturb on the voice channel.
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
     * Turn off do-not-disturb for the current agent on the voice channel.
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
     * Login the current agent on the voice channel. When you make this request, Workspace uses the parameters you 
     * provided in `activateChannels()`. For most applications, you don't need to worry about logging in 
     * the agent on the voice channel because it's handled by the Workspace API when you call `activateChannels()`. 
     * However, if you `logout()`, you can then use `login()` to login the agent on the voice channel. Note: This 
     * login/logout flow only applies to the voice channel, not to the agent's session.
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
     * Logout the current agent on the voice channel. This request is typically paired with `login()` - together 
     * they let you login/logout an agent on the voice channel without logging out of the entire session.
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
     * Set call forwarding on the current agent's DN to the specified destination.
     * @param destination The number where Workspace should forward calls.
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
     * Cancel call forwarding for the current agent.
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
     * @param destination The number to call.
     */
    public void makeCall(String destination) throws WorkspaceApiException {
        this.makeCall(destination, null, null, null);
    }

    /**
     * Make a new call to the specified destination.
     * @param destination The number to call.
     * @param userData A key/value pairs list of the user data that should be attached to the call.
     */
    public void makeCall(
            String destination,
            KeyValueCollection userData
    ) throws WorkspaceApiException {
        this.makeCall(destination, userData, null, null);
    }

    /**
     * Make a new call to the specified destination.
     * @param destination The number to call.
     * @param userData A key/value pairs list of the user data that should be attached to the call.
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void makeCall(
            String destination,
            KeyValueCollection userData,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * Answer a call.
     * @param connId The connection ID of the call. 
     */
    public void answerCall(String connId) throws WorkspaceApiException {
        this.answerCall(connId, null, null);
    }

    /**
     * Answer a call.
     * @param connId The connection ID of the call. 
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void answerCall(
            String connId,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * Place a call on hold.
     * @param connId The connection ID of the call. 
     */
    public void holdCall(String connId) throws WorkspaceApiException {
        this.holdCall(connId, null, null);
    }

    /**
     * Place a call on hold.
     * @param connId The connection ID of the call. 
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void holdCall(
            String connId,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * Retrieve a call from hold.
     * @param connId The connection ID of the call. 
     */
    public void retrieveCall(String connId) throws WorkspaceApiException {
        this.retrieveCall(connId, null, null);
    }

    /**
     * Retrieve a call from hold.
     * @param connId The connection ID of the call. 
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void retrieveCall(
            String connId,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * Release a call.
     * @param connId The connection ID of the call. 
     */
    public void releaseCall(String connId) throws WorkspaceApiException {
        this.releaseCall(connId, null, null);
    }

    /**
     * Release a call.
     * @param connId The connection ID of the call. 
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void releaseCall(
            String connId,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * Initiates a two-step conference to the specified destination. This places the existing call on
     * hold and creates a new call in the dialing state (step 1). After initiating the conference you can use 
     * `completeConference()` to complete the conference and bring all parties into the same call (step 2).
     * @param connId The connection ID of the call to start the conference from. This call will be placed on hold.
     * @param destination The number to be dialed.
     */
    public void initiateConference(String connId, String destination) throws WorkspaceApiException {
        this.initiateConference(connId, destination, null, null, null, null, null);
    }

    /**
     * Initiates a two-step conference to the specified destination. This places the existing call on
     * hold and creates a new call in the dialing state (step 1). After initiating the conference you can use 
     * `completeConference()` to complete the conference and bring all parties into the same call (step 2).
     * @param connId The connection ID of the call to start the conference from. This call will be placed on hold.
     * @param destination The number to be dialed.
     * @param userData Key/value data to include with the call. (optional)
     */
    public void initiateConference(
            String connId,
            String destination,
            KeyValueCollection userData
    ) throws WorkspaceApiException {
        this.initiateConference(connId, destination, null, null, userData, null, null);
    }

    /**
     * Initiates a two-step conference to the specified destination. This places the existing call on
     * hold and creates a new call in the dialing state. After initiating the conference you can use 
     * completeConference to complete the conference and bring all parties into the same call.
     * @param connId The connection ID of the call to start the conference from.
     * @param destination The number to be dialed.
     * @param location Name of the remote location in the form of <SwitchName> or <T-ServerApplicationName>@<SwitchName>. This value is used by Workspace to set the location attribute for the corresponding T-Server requests. (optional)
     * @param outboundCallerId The caller ID information to display on the destination party's phone. The value should be set as CPNDigits. For more information about caller ID, see the SIP Server Deployment Guide. (optional)
     * @param userData Key/value data to include with the call. (optional)
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void initiateConference(
            String connId,
            String destination,
            String location,
            String outboundCallerId,
            KeyValueCollection userData,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * Complete a previously initiated two-step conference identified by the provided IDs. Once completed, 
     * the two separate calls are brought together so that all three parties are participating in the same call.
     * @param connId The connection ID of the consult call (established).
     * @param parentConnId The connection ID of the parent call (held).
     */
    public void completeConference(String connId, String parentConnId) throws WorkspaceApiException {
        this.completeConference(connId, parentConnId, null, null);
    }

    /**
     * Complete a previously initiated conference identified by the provided IDs. Once completed, the 
     * two separate calls are brought together so that all three parties are participating in the same call.
     * @param connId The connection ID of the consult call (established).
     * @param parentConnId The connection ID of the parent call (held).
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void completeConference(
            String connId,
            String parentConnId,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * Initiates a two-step transfer by placing the first call on hold and dialing the destination number (step 1). 
     * After initiating the transfer, you can use `completeTransfer()` to complete the transfer (step 2).
     * @param connId The connection ID of the call to be transferred. This call will be placed on hold.
     * @param destination The number where the call will be transferred.
     */
    public void initiateTransfer(String connId, String destination) throws WorkspaceApiException {
        this.initiateTransfer(connId, destination, null, null, null, null, null);
    }

    /**
     * Initiates a two-step transfer to the specified destination. After initiating the transfer, you can 
     * use completeTransfer to complete the transfer.
     * @param connId The connection ID of the call to be transferred. This call will be placed on hold.
     * @param destination The number where the call will be transferred.
     * @param userData Key/value data to include with the call. (optional)
     */
    public void initiateTransfer(
            String connId,
            String destination,
            KeyValueCollection userData
    ) throws WorkspaceApiException {
        this.initiateTransfer(connId, destination, null, null, userData, null, null);
    }

    /**
     * Initiates a two-step transfer to the specified destination. After initiating the transfer, you can 
     * use completeTransfer to complete the transfer.
     * @param connId The connection ID of the call to be transferred. This call will be placed on hold.
     * @param destination The number where the call will be transferred.
     * @param location Name of the remote location in the form of <SwitchName> or <T-ServerApplicationName>@<SwitchName>. This value is used by Workspace to set the location attribute for the corresponding T-Server requests. (optional)
     * @param outboundCallerId The caller ID information to display on the destination party's phone. The value should be set as CPNDigits. For more information about caller ID, see the SIP Server Deployment Guide. (optional)
     * @param userData Key/value data to include with the call. (optional)
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void initiateTransfer(
            String connId,
            String destination,
            String location,
            String outboundCallerId,
            KeyValueCollection userData,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * Complete a previously initiated transfer using the provided IDs.
     * @param connId The connection ID of the consult call (established).
     * @param parentConnId The connection ID of the parent call (held).
     */
    public void completeTransfer(String connId, String parentConnId) throws WorkspaceApiException {
        this.completeTransfer(connId, parentConnId, null, null);
    }

    /**
     * Complete a previously initiated transfer using the provided IDs.
     * @param connId The connection ID of the consult call (established).
     * @param parentConnId The connection ID of the parent call (held).
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void completeTransfer(
            String connId,
            String parentConnId,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * Alternate two calls so that you retrieve a call on hold and place the established call on hold instead. 
     * This is a shortcut for doing `holdCall()` and `retrieveCall()` separately.
     * @param connId The connection ID of the established call that should be placed on hold.
     * @param heldConnId The connection ID of the held call that should be retrieved.
     */
    public void alternateCalls(String connId, String heldConnId) throws WorkspaceApiException {
        this.alternateCalls(connId, heldConnId, null, null);
    }

    /**
     * Alternate two calls so that you retrieve a call on hold and place the established call on hold instead. 
     * This is a shortcut for doing `holdCall()` and `retrieveCall()` separately.
     * @param connId The connection ID of the established call that should be placed on hold.
     * @param heldConnId The connection ID of the held call that should be retrieved.
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void alternateCalls(
            String connId,
            String heldConnId,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * Delete the specified DN from the conference call. This operation can only be performed by the owner of the conference call.
     * @param connId The connection ID of the conference.
     * @param dnToDrop The DN of the party to drop from the conference.
     */
    public void deleteFromConference(String connId, String dnToDrop) throws WorkspaceApiException {
        this.deleteFromConference(connId, dnToDrop, null, null);
    }

    /**
     * Delete the specified DN from the conference call. This operation can only be performed by the owner of the conference call.
     * @param connId The connection ID of the conference.
     * @param dnToDrop The DN of the party to drop from the conference.
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void deleteFromConference(
            String connId,
            String dnToDrop,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * @param connId The connection ID of the call to transfer.
     * @param destination The number where the call should be transferred.
     */
    public void singleStepTransfer(String connId, String destination) throws WorkspaceApiException {
        this.singleStepTransfer(connId, destination, null, null, null, null);
    }

    /**
     * Perform a single-step transfer to the specified destination.
     * @param connId The connection ID of the call to transfer.
     * @param destination The number where the call should be transferred.
     * @param userData Key/value data to include with the call. (optional)
     */
    public void singleStepTransfer(
            String connId,
            String destination,
            KeyValueCollection userData
    ) throws WorkspaceApiException {
        this.singleStepTransfer(connId, destination, null, userData, null, null);
    }

    /**
     * Perform a single-step transfer to the specified destination.
     * @param connId The connection ID of the call to transfer.
     * @param destination The number where the call should be transferred.
     * @param location Name of the remote location in the form of <SwitchName> or <T-ServerApplicationName>@<SwitchName>. This value is used by Workspace to set the location attribute for the corresponding T-Server requests. (optional)
     * @param userData Key/value data to include with the call. (optional)
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void singleStepTransfer(
            String connId,
            String destination,
            String location,
            KeyValueCollection userData,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * Perform a single-step conference to the specified destination. This adds the destination to the 
     * existing call, creating a conference if necessary.
     * @param connId The connection ID of the call to conference.
     * @param destination The number to add to the call.
     */
    public void singleStepConference(String connId, String destination) throws WorkspaceApiException {
        this.singleStepConference(connId, destination, null, null, null, null);

    }

    /**
     * Perform a single-step conference to the specified destination. This adds the destination to the 
     * existing call, creating a conference if necessary.
     * @param connId The connection ID of the call to conference.
     * @param destination The number to add to the call.
     * @param userData Key/value data to include with the call. (optional)
     *
     */
    public void singleStepConference(
            String connId,
            String destination,
            KeyValueCollection userData
    ) throws WorkspaceApiException {
        this.singleStepConference(connId, destination, null, userData, null, null);
    }

    /**
     * Perform a single-step conference to the specified destination. This adds the destination to the 
     * existing call, creating a conference if necessary.
     * @param connId The connection ID of the call to conference.
     * @param destination The number to add to the call.
     * @param location Name of the remote location in the form of <SwitchName> or <T-ServerApplicationName>@<SwitchName>. This value is used by Workspace to set the location attribute for the corresponding T-Server requests. (optional)
     * @param userData Key/value data to include with the call. (optional)
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void singleStepConference(
            String connId,
            String destination,
            String location,
            KeyValueCollection userData,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * @param connId The connection ID of the call.
     * @param userData The data to attach to the call. This is an array of objects with the properties key, type, and value.
     */
    public void attachUserData(String connId, KeyValueCollection userData) throws WorkspaceApiException {
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
     * Update call data with the provided key/value pairs. This replaces any existing key/value pairs with the same keys.
     * @param connId The connection ID of the call.
     * @param userData The data to update. This is an array of objects with the properties key, type, and value.
     */
    public void updateUserData(String connId, KeyValueCollection userData) throws WorkspaceApiException {
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
     * Delete data with the specified key from the call's user data.
     * @param connId The connection ID of the call. 
     * @param key The key of the data to remove.
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
     * Send DTMF digits to the specified call. You can send DTMF digits individually with multiple requests or together with multiple digits in one request. 
     * @param connId The connection ID of the call.
     * @param digits The DTMF digits to send to the call.
     */
    public void sendDTMF(String connId, String digits) throws WorkspaceApiException {
        this.sendDTMF(connId, digits, null, null);
    }

    /**
     * Send DTMF digits to the specified call. You can send DTMF digits individually with multiple requests or together with multiple digits in one request. 
     * @param connId The connection ID of the call.
     * @param digits The DTMF digits to send to the call.
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void sendDTMF(
            String connId,
            String digits,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * Send EventUserEvent to T-Server with the provided attached data. For details about EventUserEvent, refer to the 
     * [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System).
     * @param userData The data to send. This is an array of objects with the properties key, type, and value.
     */
    public void sendUserEvent(KeyValueCollection userData) throws WorkspaceApiException {
        this.sendUserEvent(userData, null, null);
    }

    /**
     * Send EventUserEvent to T-Server with the provided attached data. For details about EventUserEvent, refer to the 
     * [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System).
     * @param userData The data to send. This is an array of objects with the properties key, type, and value.
     * @param callUuid The universally unique identifier for the call that the event will be associated with. (optional)
     */
    public void sendUserEvent(KeyValueCollection userData, String callUuid) throws WorkspaceApiException {
        this.sendUserEvent(userData, callUuid, null);
    }

    /**
     * Send EventUserEvent to T-Server with the provided attached data. For details about EventUserEvent, refer to the 
     * [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System).
     * @param userData The data to send. This is an array of objects with the properties key, type, and value.
     * @param callUuid The universally unique identifier for the call that the event will be associated with. (optional)
     * @param connId The connectionId for the call that the event will be associated with. (optional)
     */
    public void sendUserEvent(KeyValueCollection userData, String callUuid, String connId) throws WorkspaceApiException {
        try {
            SendUserEventDataData sendUserEventData = new SendUserEventDataData();
            sendUserEventData.setUserData(Util.toKVList(userData));
            sendUserEventData.setCallUuid(callUuid);
            sendUserEventData.setConnId(connId);

            SendUserEventData data = new SendUserEventData();
            data.data(sendUserEventData);

            ApiSuccessResponse response = this.voiceApi.sendUserEvent(data);
            throwIfNotOk("sendUserEvent", response);
        } catch (ApiException e) {
            throw new WorkspaceApiException("sendUserEvent failed.", e);
        }
    }

    /**
     * Redirect a call to the specified destination.
     * @param connId The connection ID of the call to redirect.
     * @param destination The number where Workspace should redirect the call.
     */
    public void redirectCall(String connId, String destination) throws WorkspaceApiException {
        this.redirectCall(connId, destination, null, null);
    }

    /**
     * Redirect call to the specified destination
     * @param connId The connection ID of the call to redirect.
     * @param destination The number where Workspace should redirect the call.
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void redirectCall(
            String connId,
            String destination,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * @param connId The connection ID of the first call to be merged.
     * @param otherConnId The connection ID of the second call to be merged.
     */
    public void mergeCalls(String connId, String otherConnId) throws WorkspaceApiException {
        this.mergeCalls(connId, otherConnId, null, null);
    }

    /**
     * Merge the two specified calls.
     * @param connId The connection ID of the first call to be merged.
     * @param otherConnId The connection ID of the second call to be merged.
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void mergeCalls(
            String connId,
            String otherConnId,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * Reconnect the specified call. This releases the established call and retrieves the held call
     * in one step. This is a quick way to to do `releaseCall()` and `retrieveCall()`.
     * @param connId The connection ID of the established call (will be released).
     * @param heldConnId The ID of the held call (will be retrieved).
     */
    public void reconnectCall(String connId, String heldConnId) throws WorkspaceApiException {
        this.reconnectCall(connId, heldConnId, null, null);
    }

    /**
     * Reconnect the specified call. This releases the established call and retrieves the held call
     * in one step. This is a quick way to to do `releaseCall()` and `retrieveCall()`.
     * @param connId The connection ID of the established call (will be released).
     * @param heldConnId The ID of the held call (will be retrieved).
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void reconnectCall(
            String connId,
            String heldConnId,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * End the conference call for all parties. This can be performed by any agent participating in the conference.
     * @param connId The connection ID of the call to clear.
     */
    public void clearCall(String connId) throws WorkspaceApiException {
        this.clearCall(connId, null, null);
    }

    /**
     * End the conference call for all parties. This can be performed by any agent participating in the conference.
     * @param connId The connection ID of the call to clear.
     * @param reasons A collection of key/value pairs. For details about reasons, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     * @param extensions A collection of key/value pairs. For details about extensions, refer to the [Genesys Events and Models Reference Manual](https://docs.genesys.com/Documentation/System). (optional)
     */
    public void clearCall(
            String connId,
            KeyValueCollection reasons,
            KeyValueCollection extensions
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
     * Start recording the specified call. Recording stops when the call is completed or you send `stopRecording()`
     * on either the call or the DN.
     * @param connId The connection ID of the call.
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
     * Pause recording on the specified call.
     * @param connId The connection ID of the call.
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
     * Resume recording the specified call.
     * @param connId The connection ID of the call.
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
     * Stop recording the specified call.
     * @param connId The connection ID of the call.
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
