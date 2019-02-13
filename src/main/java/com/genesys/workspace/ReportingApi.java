package com.genesys.workspace;

import com.genesys.internal.common.ApiClient;
import com.genesys.internal.common.ApiException;
import com.genesys.internal.workspace.model.ApiSuccessResponse;
import com.genesys.internal.workspace.model.InlineResponse2002;
import com.genesys.internal.workspace.model.InlineResponse2002Data;
import com.genesys.internal.workspace.model.Statistic;
import com.genesys.internal.workspace.model.StatisticValue;
import com.genesys.internal.workspace.model.StatisticsSubscribeData;
import com.genesys.internal.workspace.model.StatisticsSubscribeDataData;
import com.genesys.workspace.common.WorkspaceApiException;
import com.genesys.workspace.models.SubscribeData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReportingApi {
    private com.genesys.internal.workspace.api.ReportingApi api;
    
    void initialize(ApiClient client) {
        initialize(new com.genesys.internal.workspace.api.ReportingApi(client));
    }
    
    void initialize(com.genesys.internal.workspace.api.ReportingApi api) {
        this.api = api;
    }

    /**
     * Get the statistics for the specified subscription ID.
     * @param subscriptionId The unique ID of the subscription.
     */
    public List<StatisticValue> peek(String subscriptionId) throws WorkspaceApiException {
        try {
            InlineResponse2002 resp = api.peek(subscriptionId);
            Util.throwIfNotOk(resp.getStatus());
            
            InlineResponse2002Data data = resp.getData();
            if(data == null) {
                throw new WorkspaceApiException("Response data is empty");
            }
            
            return data.getStatistics();
        }
        catch(ApiException ex) {
            throw new WorkspaceApiException("Cannot peek", ex);
        }        
    }

    /**
     *  Subscribe to a group of statistics. The values are returned when you request them using `peek()`.
     * 
     * @param statistics The collection of statistics you want to include in your subscription.
     * @return
     * @throws WorkspaceApiException 
     */
    public SubscribeData subscribe(Collection<Statistic> statistics) throws WorkspaceApiException {
        try {
            StatisticsSubscribeData subscribeData = new StatisticsSubscribeData();
            StatisticsSubscribeDataData data = new StatisticsSubscribeDataData();
            data.setStatistics(new ArrayList<>(statistics));
            subscribeData.setData(data);
            InlineResponse2002 resp = api.subscribe(subscribeData);
            Util.throwIfNotOk(resp.getStatus());
            
            InlineResponse2002Data respData = resp.getData();
            if(respData == null) {
                throw new WorkspaceApiException("Response data is empty");
            }
            
            SubscribeData result = new SubscribeData();
            result.setSubscriptionId(respData.getSubscriptionId());
            result.setStatistics(respData.getStatistics());
            return result;
        }
        catch(ApiException ex) {
            throw new WorkspaceApiException("Cannot subscribe", ex);
        }
    }

    /**
     * Unsubscribe from the specified group of statistics.
     * @param subscriptionId The unique ID of the subscription.
     */
    public void unsubscribe(String subscriptionId) throws WorkspaceApiException {
        try {
            ApiSuccessResponse resp = api.unsubscribe(subscriptionId);
            Util.throwIfNotOk(resp);
        }
        catch(ApiException ex) {
            throw new WorkspaceApiException("Cannot unsubscribe", ex);
        }
    }
}
