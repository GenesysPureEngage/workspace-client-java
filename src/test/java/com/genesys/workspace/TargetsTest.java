package com.genesys.workspace;

import com.genesys.internal.common.ApiException;
import com.genesys.internal.workspace.model.TargetsResponse;
import com.genesys.workspace.common.WorkspaceApiException;
import com.genesys.workspace.models.targets.SearchResult;
import com.genesys.workspace.models.targets.Target;
import com.genesys.workspace.models.targets.TargetType;
import com.genesys.workspace.models.targets.TargetsSearchOptions;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TargetsTest {
    
    @Mock
    com.genesys.internal.workspace.api.TargetsApi internalApi;
    
    final TargetsApi api = new TargetsApi();
    
    @Before
    public void setup() throws ApiException {
        api.setTargetsApi(internalApi);
        
        TargetsResponse response = Objects.makeTargetsResponse(10);
        Mockito.when(internalApi.get((String)Mockito.any(), 
                (String)Mockito.any(), 
                (String)Mockito.any(), 
                (String)Mockito.any(), 
                (String)Mockito.any(), 
                (String)Mockito.any(), 
                (String)Mockito.any(), 
                (BigDecimal)Mockito.any(), 
                (String)Mockito.any())).thenReturn(response);
    }

    @Test
    public void searchWithTerm() throws WorkspaceApiException {
        SearchResult<Target> result = api.search("test");
        
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getTargets().size() > 0);
        Assert.assertTrue(result.getTotal() > 0);
    }
    
    @Test
    public void searchWithOptions() throws WorkspaceApiException {
        TargetsSearchOptions options = new TargetsSearchOptions();
        options.setDesc(true);
        options.setExact(false);
        options.setLimit(2);
        options.setTypes(new TargetType[]{ TargetType.AGENT, TargetType.SKILL });
        options.setExcludeGroups(new String[] {"test1"});
        options.setExcludeFromGroups(new String[] {"test1"});
        options.setRestrictToGroups(new String[] {"test2"});
        SearchResult<Target> result = api.search("test", options);
        
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getTargets().size() > 0);
        Assert.assertTrue(result.getTotal() > 0);
    }
    
    
}
