package com.genesys.workspace;

import com.genesys.internal.common.ApiException;
import com.genesys.internal.workspace.model.*;
import com.genesys.workspace.common.StatusCode;
import com.genesys.workspace.common.WorkspaceApiException;
import org.cometd.bayeux.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VoiceTest {
    
    @Mock
    com.genesys.internal.workspace.api.VoiceApi internalApi;
    
    final VoiceApi api = new VoiceApi();

    @Before
    public void before() throws ApiException {
        api.setVoiceApi(internalApi);
        
        ApiSuccessResponse asyncSuccess = Objects.makeResponse(StatusCode.ASYNC_OK);
        
        Mockito.when(internalApi.alternate((String)Mockito.any(), (AlternateData)Mockito.any())).thenReturn(asyncSuccess);        
        Mockito.when(internalApi.answer((String)Mockito.any(), (AnswerData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.attachUserData((String)Mockito.any(), (UserDataOperationId) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.cancelForward((CancelForwardBody) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.clear((String)Mockito.any(), (ClearData) Mockito.any())).thenReturn(asyncSuccess);
        //Mockito.when(internalApi.completeCall(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.completeConference((String)Mockito.any(), (CompleteConferenceData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.completeTransfer((String)Mockito.any(), (CompleteTransferData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.deleteFromConference((String)Mockito.any(), (DeleteFromConferenceData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.deleteUserDataPair((String)Mockito.any(), (KeyData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.forward((ForwardData) Mockito.any())).thenReturn(asyncSuccess);
        //Mockito.when(internalApi.getCalls()).thenReturn(new InlineResponse200());
        Mockito.when(internalApi.hold((String)Mockito.any(), (HoldData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.initiateConference((String)Mockito.any(), (InitiateConferenceData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.initiateTransfer((String)Mockito.any(), (InitiateTransferData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.loginVoice((LoginData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.logoutVoice((DndOnBody) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.makeCall((MakeCallData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.merge((String)Mockito.any(), (MergeData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.pauseRecording((String)Mockito.any(), (PauseRecordingBody)Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.reconnect((String)Mockito.any(), (ReconnectData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.redirect((String)Mockito.any(), (RedirectData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.release((String)Mockito.any(), (ReleaseData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.resumeRecording((String) Mockito.any(), (ResumeRecordingBody) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.retrieve((String)Mockito.any(), (RetrieveData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.sendDTMF((String)Mockito.any(), (SendDTMFData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.sendUserEvent((SendUserEventData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.setAgentStateNotReady((NotReadyData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.setAgentStateReady((ReadyData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.setDNDOff((DndOffBody) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.setDNDOn((DndOnBody1) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.singleStepConference((String)Mockito.any(), (SingleStepConferenceData) Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.singleStepTransfer((String)Mockito.any(), (SingleStepTransferData) Mockito.any())).thenReturn(asyncSuccess);
        //Mockito.when(internalApi.startMonitoring(Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.startRecording((String) Mockito.any(), (StartRecordingBody) Mockito.any())).thenReturn(asyncSuccess);
        //Mockito.when(internalApi.stopMonitoring(Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.stopRecording((String) Mockito.any(), (StopRecordingBody) Mockito.any())).thenReturn(asyncSuccess);
        //Mockito.when(internalApi.switchToBargeIn(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        //Mockito.when(internalApi.switchToCoaching(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        //Mockito.when(internalApi.switchToListenIn(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.updateUserData((String)Mockito.any(), (UserDataOperationId) Mockito.any())).thenReturn(asyncSuccess);
    }
    
    @Test
    public void alternateCalls() throws WorkspaceApiException {
        api.alternateCalls(Objects.CALL_ID, Objects.HELD_CALL_ID);
    }
    
    @Test
    public void answerCall() throws WorkspaceApiException {
        api.answerCall(Objects.CALL_ID);
    }
    
    @Test
    public void answerCallWithData() throws WorkspaceApiException {
        api.answerCall(Objects.CALL_ID, Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void attachUserData() throws WorkspaceApiException {
        api.attachUserData(Objects.CALL_ID, Objects.makeUserData());
    }
    
    @Test
    public void cancelForward() throws WorkspaceApiException {
        api.cancelForward();
    }
    
    @Test
    public void clearCall() throws WorkspaceApiException {
        api.clearCall(Objects.CALL_ID);
    }
    
    @Test
    public void clearCallWithData() throws WorkspaceApiException {
        api.clearCall(Objects.CALL_ID, Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void completeConference() throws WorkspaceApiException {
        api.completeConference(Objects.CALL_ID, Objects.PARENT_CALL_ID);
    }
    
    @Test
    public void completeConferenceWithData() throws WorkspaceApiException {
        api.completeConference(Objects.CALL_ID, Objects.PARENT_CALL_ID, Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void completeTransfer() throws WorkspaceApiException {
        api.completeTransfer(Objects.CALL_ID, Objects.PARENT_CALL_ID);
    }
    
    @Test
    public void completeTransferWithData() throws WorkspaceApiException {
        api.completeTransfer(Objects.CALL_ID, Objects.PARENT_CALL_ID, Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void deleteFromConference() throws WorkspaceApiException {
        api.deleteFromConference(Objects.CALL_ID, "test");
    }
    
    @Test
    public void deleteFromConferenceWithData() throws WorkspaceApiException {
        api.deleteFromConference(Objects.CALL_ID, "test", Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void deleteUserDataPair() throws WorkspaceApiException {
        api.deleteUserDataPair(Objects.CALL_ID, "test");
    }
    
    @Test
    public void dndOff() throws WorkspaceApiException {
        api.dndOff();
    }
    
    @Test
    public void dndOn() throws WorkspaceApiException {
        api.dndOn();
    }
    
    @Test
    public void holdCall() throws WorkspaceApiException {
        api.holdCall(Objects.CALL_ID);
    }
    
    @Test
    public void holdCallWithData() throws WorkspaceApiException {
        api.holdCall(Objects.CALL_ID, Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void initiateConference() throws WorkspaceApiException {
        api.initiateConference(Objects.CALL_ID, Objects.DESTINATION);
    }
    
    @Test
    public void initiateConferenceWithData() throws WorkspaceApiException {
        api.initiateConference(Objects.CALL_ID, Objects.DESTINATION, Objects.LOCATION, Objects.OUTBOUND_CALLER_ID, Objects.makeUserData(), Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void initiateTransfer() throws WorkspaceApiException {
        api.initiateTransfer(Objects.CALL_ID, Objects.DESTINATION);
    }
    
    @Test
    public void initiateTransferWithData() throws WorkspaceApiException {
        api.initiateTransfer(Objects.CALL_ID, Objects.DESTINATION, Objects.LOCATION, Objects.OUTBOUND_CALLER_ID, Objects.makeUserData(), Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void login() throws WorkspaceApiException {
        api.login();
    }
    
    @Test
    public void logout() throws WorkspaceApiException {
        api.logout();
    }
    
    @Test
    public void makeCall() throws WorkspaceApiException {
        api.makeCall(Objects.DESTINATION);
    }
    
    @Test
    public void makeCallWithData() throws WorkspaceApiException {
        api.makeCall(Objects.DESTINATION, Objects.makeUserData(), Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void mergeCalls() throws WorkspaceApiException {
        api.mergeCalls(Objects.CALL_ID, Objects.OTHER_CALL_ID);
    }
    
    @Test
    public void mergeCallsWithData() throws WorkspaceApiException {
        api.mergeCalls(Objects.CALL_ID, Objects.OTHER_CALL_ID, Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void pauseRecording() throws WorkspaceApiException {
        api.pauseRecording(Objects.CALL_ID);
    }
    
    @Test
    public void reconnectCall() throws WorkspaceApiException {
        api.reconnectCall(Objects.CALL_ID, Objects.HELD_CALL_ID);
    }
    
    @Test
    public void reconnectCallWithData() throws WorkspaceApiException {
        api.reconnectCall(Objects.CALL_ID, Objects.HELD_CALL_ID, Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void redirectCall() throws WorkspaceApiException {
        api.redirectCall(Objects.CALL_ID, Objects.DESTINATION);
    }
    
    @Test
    public void redirectCallWithData() throws WorkspaceApiException {
        api.redirectCall(Objects.CALL_ID, Objects.DESTINATION, Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void releaseCall() throws WorkspaceApiException {
        api.releaseCall(Objects.CALL_ID);
    }
    
    @Test
    public void releaseCallWithData() throws WorkspaceApiException {
        api.releaseCall(Objects.CALL_ID, Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void resumeRecording() throws WorkspaceApiException {
        api.resumeRecording(Objects.CALL_ID);
    }
    
    @Test
    public void retrieveCall() throws WorkspaceApiException {
        api.retrieveCall(Objects.CALL_ID);
    }
    
    @Test
    public void retrieveCallWithData() throws WorkspaceApiException {
        api.retrieveCall(Objects.CALL_ID, Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void sendDTMF() throws WorkspaceApiException {
        api.sendDTMF(Objects.CALL_ID, Objects.DIGITS);
    }
    
    @Test
    public void sendDTMFWithData() throws WorkspaceApiException {
        api.sendDTMF(Objects.CALL_ID, Objects.DIGITS, Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void sendUserEvent() throws WorkspaceApiException {
        api.sendUserEvent(Objects.makeUserData());
    }
    
    @Test
    public void sendUserEventWithId() throws WorkspaceApiException {
        api.sendUserEvent(Objects.makeUserData(), Objects.CALL_ID);
    }
    
    @Test
    public void setAgentNotReady() throws WorkspaceApiException {
        api.setAgentNotReady();
    }
    
    @Test
    public void setAgentNotReadyWithData() throws WorkspaceApiException {
        api.setAgentNotReady(Objects.WORKMODE, Objects.REASON_CODE);
    }
    
    @Test
    public void setAgentReady() throws WorkspaceApiException {
        api.setAgentReady(Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void setForward() throws WorkspaceApiException {
        api.setForward(Objects.DESTINATION);
    }
    
    @Test
    public void singleStepConference() throws WorkspaceApiException {
        api.singleStepConference(Objects.CALL_ID, Objects.DESTINATION);
    }
    
    @Test
    public void singleStepConferenceWithData() throws WorkspaceApiException {
        api.singleStepConference(Objects.CALL_ID, Objects.DESTINATION, Objects.LOCATION, Objects.makeUserData(), Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void singleStepTransfer() throws WorkspaceApiException {
        api.singleStepTransfer(Objects.CALL_ID, Objects.DESTINATION);        
    }
    
    @Test
    public void singleStepTransferWithData() throws WorkspaceApiException {
        api.singleStepTransfer(Objects.CALL_ID, Objects.DESTINATION, Objects.LOCATION, Objects.makeUserData(), Objects.makeReasons(), Objects.makeExtensions());
    }
    
    @Test
    public void startRecording() throws WorkspaceApiException {
        api.startRecording(Objects.CALL_ID);
    }
    
    @Test
    public void stopRecording() throws WorkspaceApiException {
        api.stopRecording(Objects.CALL_ID);
    }
    
    @Test
    public void updateUserData() throws WorkspaceApiException {
        api.updateUserData(Objects.CALL_ID, Objects.makeUserData());
    }
    
    @Test
    public void onDnStateChangedMessage() {
        Message msg = Objects.makeMessage(Objects.MessageType.DnStateChanged);
        api.onVoiceMessage(msg.getDataAsMap());
    }
    
    @Test
    public void onCallStateChangedMessage() {
        Message msg = Objects.makeMessage(Objects.MessageType.CallStateChanged);
        api.onVoiceMessage(msg.getDataAsMap());
    }
    
    @Test
    public void onEventErrorMessage() {
        Message msg = Objects.makeMessage(Objects.MessageType.EventError);
        api.onVoiceMessage(msg.getDataAsMap());
    }
}
