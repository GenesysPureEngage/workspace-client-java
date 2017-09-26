package com.genesys.workspace;

import com.genesys.internal.common.ApiClient;
import com.genesys.internal.common.ApiException;
import com.genesys.internal.workspace.api.SessionApi;
import com.genesys.internal.workspace.model.ApiSuccessResponse;
import com.genesys.workspace.common.StatusCode;
import com.genesys.workspace.common.WorkspaceApiException;
import com.genesys.workspace.models.User;
import java.util.concurrent.CompletableFuture;
import org.cometd.bayeux.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WorkspaceTest {
    
    @Mock
    ApiClient client;
    
    @Mock
    SessionApi sessionApi;
    
    @Mock
    VoiceApi voiceApi;
    
    @Mock
    TargetsApi targetsApi;
    
    @Mock
    Notifications notifications;
    
    WorkspaceApi api;
    
    @Before
    public void setup() throws ApiException {
        api = new WorkspaceApi(Objects.API_KEY, Objects.BASE_URL, voiceApi, targetsApi, sessionApi, notifications);
         
        ApiSuccessResponse asyncSuccess = Objects.makeResponse(StatusCode.OK);
         
        Mockito.when(sessionApi.activateChannels(Mockito.any())).thenReturn(asyncSuccess);
        Mockito.when(sessionApi.initializeWorkspaceWithHttpInfo(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(Objects.makeHttpResponse());
    }
    
    @Test
    public void activateChannels() throws WorkspaceApiException {
        api.activateChannels(Objects.AGENT_ID, Objects.PLACE_NAME);
    }
    
    @Test
    public void activateChannelsWithData() throws WorkspaceApiException {
        api.activateChannels(Objects.AGENT_ID, Objects.AGENT_DN, Objects.PLACE_NAME, Objects.QUEUE_NAME, null);
    }
    
    @Test
    public void destroy() throws WorkspaceApiException {
        api.destroy();
    }
    
    @Test
    public void initializeWithToken() throws WorkspaceApiException {
        api.initialize(Objects.TOKEN);
    }
    
    @Test
    public void initializeWithAuthCode() throws WorkspaceApiException {
        api.initialize(Objects.AUTH_CODE, Objects.REDIRECT_URI);
    }
    
    @Test
    public void onInitMessage() {
        CompletableFuture<User> future = new CompletableFuture<>();
        Message msg = Objects.makeMessage(Objects.MessageType.WorkspaceInitializationComplete);
        api.onInitMessage(msg.getDataAsMap(), future);
        Assert.assertTrue(future.isDone());
    }

}
