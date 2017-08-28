package com.genesys.workspace;

import com.genesys._internal.workspace.ApiClient;
import com.genesys._internal.workspace.ApiException;
import com.genesys._internal.workspace.model.TargetsResponse;
import com.genesys._internal.workspace.model.TargetsResponseData;
import com.genesys.workspace.common.WorkspaceApiException;
import com.genesys.workspace.models.targets.Target;
import com.genesys.workspace.models.targets.TargetSearchResult;

import java.util.ArrayList;
import java.util.List;

public class TargetsApi {
    private com.genesys._internal.workspace.api.TargetsApi targetsApi;
    public TargetsApi() {
    }

    void initialize(ApiClient client) {
        this.targetsApi = new com.genesys._internal.workspace.api.TargetsApi(client);
    }

    public TargetSearchResult search(String searchTerm) throws WorkspaceApiException {
        try {
            TargetsResponse response = this.targetsApi.get(
                    searchTerm, null, null, null, null, null);

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
