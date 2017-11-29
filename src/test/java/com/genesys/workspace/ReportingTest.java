package com.genesys.workspace;

import com.genesys.internal.common.ApiException;
import com.genesys.internal.workspace.model.ApiSuccessResponse;
import com.genesys.internal.workspace.model.InlineResponse2001;
import com.genesys.internal.workspace.model.InlineResponse2001Data;
import com.genesys.internal.workspace.model.InlineResponse2002;
import com.genesys.internal.workspace.model.InlineResponse2002Data;
import com.genesys.internal.workspace.model.InlineResponse2002DataStatistics;
import com.genesys.internal.workspace.model.StatisticValueForRegister;
import com.genesys.workspace.common.WorkspaceApiException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportingTest {
    
    @Mock
    com.genesys.internal.workspace.api.ReportingApi internalApi;
    
    final ReportingApi api = new ReportingApi();
    
    @Before
    public void setup() throws ApiException {
        api.initialize(internalApi);
        
        ApiSuccessResponse resp = Objects.makeResponse(0);
		
        InlineResponse2002 resp1 = new InlineResponse2002();
        resp1.setStatus(resp.getStatus());
        InlineResponse2002Data data2002 = new InlineResponse2002Data();
        data2002.setStatistics(new InlineResponse2002DataStatistics());
        resp1.setData(data2002);
		
        InlineResponse2001 resp2 = new InlineResponse2001();
        resp2.setData(new InlineResponse2001Data());
        resp2.setStatus(resp.getStatus());        
        
        Mockito.when(internalApi.peek(Mockito.any())).thenReturn(resp1);        
        Mockito.when(internalApi.register(Mockito.any())).thenReturn(resp2);
        Mockito.when(internalApi.subscribe(Mockito.any())).thenReturn(resp2);
        Mockito.when(internalApi.unsubscribe(Mockito.any())).thenReturn(resp);
    }
    
    @Test
    public void subscribe() throws WorkspaceApiException {
        ArrayList<StatisticValueForRegister> statistics = new ArrayList<>();
        api.subscribe(Objects.CONNECTION_ID, statistics);
    }
    
    @Test
    public void unsubscribe() throws WorkspaceApiException {
        api.unsubscribe(Objects.SUBSCRIPTION_ID);
    }
    
    @Test
    public void peek() throws WorkspaceApiException {
        api.peek(Objects.SUBSCRIPTION_ID);
    }
    
    @Test
    public void register() throws WorkspaceApiException {
        ArrayList<StatisticValueForRegister> statistics = new ArrayList<>();
        api.register(statistics);
    }
    
}