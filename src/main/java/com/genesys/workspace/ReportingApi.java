package com.genesys.workspace;

import com.genesys.internal.common.ApiClient;
import com.genesys.internal.common.ApiException;
import com.genesys.internal.workspace.model.ApiSuccessResponse;
import com.genesys.internal.workspace.model.InlineResponse2001;
import com.genesys.internal.workspace.model.InlineResponse2002;
import com.genesys.internal.workspace.model.ReportingunsubscribeData;
import com.genesys.internal.workspace.model.StatisticValueForRegister;
import com.genesys.internal.workspace.model.StatisticsRegisterData;
import com.genesys.internal.workspace.model.StatisticsRegisterDataData;
import com.genesys.internal.workspace.model.StatisticsSubscribeData;
import com.genesys.internal.workspace.model.StatisticsSubscribeDataData;
import com.genesys.internal.workspace.model.UnsubscribeData;
import com.genesys.workspace.common.WorkspaceApiException;
import java.util.ArrayList;
import java.util.Collection;

public class ReportingApi {
    private com.genesys.internal.workspace.api.ReportingApi api;
    
    void initialize(ApiClient client) {
        initialize(new com.genesys.internal.workspace.api.ReportingApi(client));
    }
    
    void initialize(com.genesys.internal.workspace.api.ReportingApi api) {
        this.api = api;
    }

    public void peek(String subscriptionId) throws WorkspaceApiException {
        try {
            InlineResponse2002 resp = api.peek(subscriptionId);
            Util.throwIfNotOk(resp.getStatus());
        }
        catch(ApiException ex) {
            throw new WorkspaceApiException("Cannot peek", ex);
        }        
    }

    public void register(Collection<StatisticValueForRegister> statistics) throws WorkspaceApiException {
        try {
            StatisticsRegisterData statisticsData = new StatisticsRegisterData();
            StatisticsRegisterDataData data = new StatisticsRegisterDataData();
            data.setStatistics(new ArrayList<>(statistics));
            statisticsData.setData(data);
            InlineResponse2001 resp = api.register(statisticsData);
            Util.throwIfNotOk(resp.getStatus());
        }
        catch(ApiException ex) {
            throw new WorkspaceApiException("Cannot register", ex);
        }
    }

    public void subscribe(String connectionId, Collection<StatisticValueForRegister> statistics) throws WorkspaceApiException {
        try {
            StatisticsSubscribeData subscribeData = new StatisticsSubscribeData();
            StatisticsSubscribeDataData data = new StatisticsSubscribeDataData();
            data.setConnectionId(connectionId);
            data.setStatistics(new ArrayList<>(statistics));
            subscribeData.setData(data);
            InlineResponse2001 resp = api.subscribe(subscribeData);
            Util.throwIfNotOk(resp.getStatus());
        }
        catch(ApiException ex) {
            throw new WorkspaceApiException("Cannot subscribe", ex);
        }
    }

    public void unsubscribe(String subscriptionId) throws WorkspaceApiException {
        try {
            UnsubscribeData unsubscribe = new UnsubscribeData();
            ReportingunsubscribeData data = new ReportingunsubscribeData();
            data.setSubscriptionId(subscriptionId);
            unsubscribe.setData(data);
            ApiSuccessResponse resp = api.unsubscribe(unsubscribe);
            Util.throwIfNotOk(resp);
        }
        catch(ApiException ex) {
            throw new WorkspaceApiException("Cannot unsunscribe", ex);
        }
    }
}
