package com.genesys.workspace.models;

import com.genesys.internal.workspace.model.StatisticValueForRegisterResponse;
import java.util.List;

public class SubscribeData {
    String subscriptionId;
    private List<StatisticValueForRegisterResponse> statistics;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public void setStatistics(List<StatisticValueForRegisterResponse> statistics) {
        this.statistics = statistics;
    }

    public List<StatisticValueForRegisterResponse> getStatistics() {
        return statistics;
    }
}
