package com.genesys.workspace;

import com.genesys._internal.workspace.ApiClient;
import com.genesys._internal.workspace.ApiException;
import com.genesys._internal.workspace.model.TargetsResponse;
import com.genesys._internal.workspace.model.TargetsResponseData;
import com.genesys.workspace.common.WorkspaceApiException;
import com.genesys.workspace.models.targets.Target;
import com.genesys.workspace.models.targets.TargetSearchResult;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TargetsApi {
    public static enum Type {
        AcdQueue("acd-queue"),
        AgentGroup("agent-group"),
        Agent("agent"),
        RoutePoint("route-point"),
        Skill("skill"),
        CustomContact("custom-contact");
        
        private final String value;

        private Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    
    private com.genesys._internal.workspace.api.TargetsApi targetsApi;

    void initialize(ApiClient client) {
        this.targetsApi = new com.genesys._internal.workspace.api.TargetsApi(client);
    }

    public TargetSearchResult search(String searchTerm) throws WorkspaceApiException {
        return search(searchTerm, new TargetsSearchOptions());
    }
    
    public TargetSearchResult search(String searchTerm, TargetsSearchOptions options) throws WorkspaceApiException {
        try {
            TargetsResponse response = this.targetsApi.get(searchTerm, 
                    options.getFilterName(),
                    Arrays.stream(options.getTypes()).map(v -> v.getValue()).collect(Collectors.joining(",")), 
                    options.isDesc()? "desc": null, 
                    options.getLimit() < 1? null: new BigDecimal(options.getLimit()), 
                    options.isExact()? "exact": null);

            TargetsResponseData data = response.getData();

            List<Target> targets = new ArrayList<>();
            if (data.getTargets() != null) {
                for (com.genesys._internal.workspace.model.Target t : data.getTargets()) {
                    targets.add(new Target(t));
                }
            }
            return new TargetSearchResult(data.getTotalMatches(), targets);

        } catch (ApiException e) {
            throw new WorkspaceApiException("searchTargets failed.", e);
        }
    }
}
