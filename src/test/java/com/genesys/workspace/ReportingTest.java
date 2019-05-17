package com.genesys.workspace;

import com.genesys.internal.common.ApiException;
import com.genesys.internal.workspace.model.*;
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

        SubscriptionSuccessResponse resp1 = new SubscriptionSuccessResponse();
        resp1.setStatus(resp.getStatus());
        SubscriptionSuccessResponseData subscriptionSuccessResponseData = new SubscriptionSuccessResponseData();
        subscriptionSuccessResponseData.setStatistics(new ArrayList<StatisticValue>());
        resp1.setData(subscriptionSuccessResponseData);

        SubscriptionSuccessResponse resp2 = new SubscriptionSuccessResponse();
        resp2.setData(new SubscriptionSuccessResponseData());
        resp2.setStatus(resp.getStatus());

        Mockito.when(internalApi.peek((String) Mockito.any())).thenReturn(resp1);
        Mockito.when(internalApi.subscribe((StatisticsSubscribeData) Mockito.any())).thenReturn(resp2);
        Mockito.when(internalApi.unsubscribe((String) Mockito.any())).thenReturn(resp);
    }

    @Test
    public void subscribe() throws WorkspaceApiException {
        ArrayList<Statistic> statistics = new ArrayList<>();
        api.subscribe(statistics);
    }

    @Test
    public void unsubscribe() throws WorkspaceApiException {
        api.unsubscribe(Objects.SUBSCRIPTION_ID);
    }

    @Test
    public void peek() throws WorkspaceApiException {
        api.peek(Objects.SUBSCRIPTION_ID);
    }

}