package com.genesys.workspace;

import com.genesys.workspace.common.WorkspaceApiException;
import org.cometd.client.BayeuxClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NotificationsTest {
    
    @Mock
    BayeuxClient client;

    Notifications notifications = new Notifications();
    
    @Before
    public void before() {
    }
    
    @Test
    public void initialize() throws WorkspaceApiException {
        notifications.initialize("http://localhost/notifications", Objects.API_KEY, "test", client);
    }
    
    @Test
    public void disconnect() throws WorkspaceApiException {
        notifications.disconnect();
    }
}
