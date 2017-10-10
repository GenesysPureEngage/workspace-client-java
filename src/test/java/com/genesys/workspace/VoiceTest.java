package com.genesys.workspace;

import com.genesys._internal.workspace.ApiException;
import com.genesys._internal.workspace.model.ApiSuccessResponse;
import com.genesys.workspace.common.StatusCode;
import com.genesys.workspace.common.WorkspaceApiException;
import com.genesys.workspace.models.KeyValueCollection;
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
    com.genesys._internal.workspace.api.VoiceApi internalApi;
    
    final VoiceApi api = new VoiceApi();

    @Before
    public void before() throws ApiException {
        api.setVoiceApi(internalApi);
        
        ApiSuccessResponse asyncSuccess = Objects.makeResponse(StatusCode.ASYNC_OK);
        
        Mockito.when(internalApi.alternate(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);        
        Mockito.when(internalApi.answer(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.attachUserData(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.cancelForward()).thenReturn(asyncSuccess);
        Mockito.when(internalApi.clear(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        //Mockito.when(internalApi.completeCall(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.completeConference(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.completeTransfer(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.deleteFromConference(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.deleteUserDataPair(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.forward(Mockito.any())).thenReturn(asyncSuccess);
        //Mockito.when(internalApi.getCalls()).thenReturn(new InlineResponse200());
        Mockito.when(internalApi.hold(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.initiateConference(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.initiateTransfer(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.loginVoice()).thenReturn(asyncSuccess);
        Mockito.when(internalApi.logoutVoice()).thenReturn(asyncSuccess);
        Mockito.when(internalApi.makeCall(Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.merge(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.pauseRecording(Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.reconnect(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.redirect(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.release(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.resumeRecording(Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.retrieve(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.sendDTMF(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.sendUserEvent(Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.setAgentStateNotReady(Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.setAgentStateReady(Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.setDNDOff()).thenReturn(asyncSuccess);
        Mockito.when(internalApi.setDNDOn()).thenReturn(asyncSuccess);
        Mockito.when(internalApi.singleStepConference(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.singleStepTransfer(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        //Mockito.when(internalApi.startMonitoring(Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.startRecording(Mockito.any())).thenReturn(asyncSuccess);
        //Mockito.when(internalApi.stopMonitoring(Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.stopRecording(Mockito.any())).thenReturn(asyncSuccess);
        //Mockito.when(internalApi.switchToBargeIn(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        //Mockito.when(internalApi.switchToCoaching(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        //Mockito.when(internalApi.switchToListenIn(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(internalApi.updateUserData(Mockito.any(), Mockito.any())).thenReturn(asyncSuccess);
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
        api.attachUserData(Objects.CALL_ID, new KeyValueCollection());
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
