package com.genesys.workspace.models;

import com.genesys.internal.workspace.model.StatisticValue;
import java.util.List;

public class SubscribeData {
    String subscriptionId;
    private List<StatisticValue> statistics;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public void setStatistics(List<StatisticValue> statistics) {
        this.statistics = statistics;
    }

    public List<StatisticValue> getStatistics() {
        return statistics;
    }
}
