package com.genesys.workspace;

import com.genesys.internal.common.ApiClient;
import com.genesys.internal.common.ApiException;
import com.genesys.internal.workspace.api.SessionApi;
import com.genesys.internal.workspace.model.ApiSuccessResponse;
import com.genesys.internal.workspace.model.ChannelsData;
import com.genesys.workspace.common.StatusCode;
import com.genesys.workspace.common.WorkspaceApiException;
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
         
        Mockito.when(sessionApi.activateChannels((ChannelsData)Mockito.any())).thenReturn(asyncSuccess);
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
}
