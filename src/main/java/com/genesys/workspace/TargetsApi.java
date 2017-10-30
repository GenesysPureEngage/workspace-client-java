package com.genesys.workspace;

import com.genesys.internal.common.ApiClient;
import com.genesys.internal.common.ApiException;
import com.genesys.internal.common.ApiResponse;
import com.genesys.workspace.models.targets.TargetsSearchOptions;
import com.genesys.internal.workspace.model.ApiSuccessResponse;
import com.genesys.internal.workspace.model.PersonalFavoriteData;
import com.genesys.internal.workspace.model.RecentData;
import com.genesys.internal.workspace.model.RecentTargetData;
import com.genesys.internal.workspace.model.TargetsResponse;
import com.genesys.internal.workspace.model.TargetsResponseData;
import com.genesys.internal.workspace.model.TargetInformation;
import com.genesys.internal.workspace.model.TargetspersonalfavoritessaveData;
import com.genesys.internal.workspace.model.TargetsrecentsaddData;
import com.genesys.workspace.common.WorkspaceApiException;
import com.genesys.workspace.models.targets.AgentTarget;
import com.genesys.workspace.models.targets.Target;
import com.genesys.workspace.models.targets.SearchResult;
import com.genesys.workspace.models.targets.TargetType;
import java.math.BigDecimal;
import java.time.Instant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TargetsApi {
    private com.genesys.internal.workspace.api.TargetsApi targetsApi;

    void initialize(ApiClient client) {
        this.targetsApi = new com.genesys.internal.workspace.api.TargetsApi(client);
    }

    void setTargetsApi(com.genesys.internal.workspace.api.TargetsApi targetsApi) {
        this.targetsApi = targetsApi;
    }
    
    public SearchResult<Target> search(String searchTerm) throws WorkspaceApiException {
        return search(searchTerm, new TargetsSearchOptions());
    }
    
    public SearchResult<Target> search(String searchTerm, TargetsSearchOptions options) throws WorkspaceApiException {
        try {
            String types = options.getTypes() !=null? Arrays.stream(options.getTypes()).map(v -> v.getValue()).collect(Collectors.joining(",")): null;
            String excludeGroups = options.getExcludeGroups() !=null? Arrays.stream(options.getExcludeGroups()).collect(Collectors.joining(",")): null;
            String excludeFromGroups = options.getExcludeFromGroups() !=null? Arrays.stream(options.getExcludeFromGroups()).collect(Collectors.joining(",")): null;
            String restrictToGroups = options.getRestrictToGroups() !=null? Arrays.stream(options.getRestrictToGroups()).collect(Collectors.joining(",")): null;
            TargetsResponse response = this.targetsApi.get(searchTerm, 
                    options.getFilterName(),
                    types,
                    excludeGroups,
                    excludeFromGroups,
                    restrictToGroups,
                    options.isDesc()? "desc": null, 
                    options.getLimit() < 1? null: new BigDecimal(options.getLimit()), 
                    options.isExact()? "exact": null);

            TargetsResponseData data = response.getData();

            List<Target> targets = new ArrayList<>();
            if (data.getTargets() != null) {
                for (com.genesys.internal.workspace.model.Target t : data.getTargets()) {
                    Target target = Target.fromTarget(t);
                    targets.add(target);
                }
            }
            return new SearchResult<>(data.getTotalMatches(), targets);

        } catch (ApiException e) {
            throw new WorkspaceApiException("searchTargets failed.", e);
        }
    }
    
    public void addRecentTarget(Target target, String media) throws WorkspaceApiException {
        addRecentTarget(target, media, Instant.now().toEpochMilli());
    }

    public void addRecentTarget(Target target, String media, long timestamp) throws WorkspaceApiException {
        try {
            TargetsrecentsaddData data = new TargetsrecentsaddData();
            data.setTarget(toInformation(target));
            RecentData recentData = new RecentData();
            recentData.setMedia(media);
            recentData.setTimeStamp(new BigDecimal(timestamp));
            data.setRecentInformation(recentData);
            RecentTargetData recentTargetData = new RecentTargetData();
            recentTargetData.setData(data);
            ApiSuccessResponse resp = targetsApi.addRecentTarget(recentTargetData);
            Util.throwIfNotOk(resp);
        }
        catch(ApiException ex) {
            throw new WorkspaceApiException("Cannot add recent target", ex);
        }
    }
    
    private TargetInformation toInformation(Target target) {
        TargetInformation information = new TargetInformation();
        if(target instanceof AgentTarget) {
            information.setFirstName(((AgentTarget)target).getFirstName());
            information.setLastName(((AgentTarget)target).getLastName());
            information.setEmailAddresses(Arrays.asList(((AgentTarget)target).getEmailAddress()));
        }
        
        information.setNumbers(Arrays.asList(target.getNumber()));
        information.setType(target.getType().getValue());
        
        return information;
    }
    
    public SearchResult<Target> getRecentTargets() throws WorkspaceApiException {
        return getRecentTargets(0);
    }

    public SearchResult<Target> getRecentTargets(int limit) throws WorkspaceApiException {
        try {
            TargetsResponse resp = targetsApi.getRecentTargets(limit > 0? new BigDecimal(limit): null);
            Util.throwIfNotOk(resp.getStatus());
            
            TargetsResponseData data = resp.getData();

            int total = 0;
            List<Target> list = new ArrayList<>();
            if(data != null) {
                total = data.getTotalMatches();
                if(data.getTargets() != null) {
                    for (com.genesys.internal.workspace.model.Target t : data.getTargets()) {
                        Target target = Target.fromTarget(t);
                        list.add(target);
                    }
                }
            }

            return new SearchResult<>(total, list);
        }
        catch(ApiException ex) {
            throw new WorkspaceApiException("Cannot get recent targets", ex);
        }
    }

    public Target getTarget(long id, TargetType type) throws WorkspaceApiException {
        try {
            TargetsResponse resp = targetsApi.getTarget(new BigDecimal(id), type.getValue());
            Util.throwIfNotOk(resp.getStatus());
            
            Target target = null;
            if(resp.getData() != null) {
                List<com.genesys.internal.workspace.model.Target> targets = resp.getData().getTargets();
                if(targets != null && targets.size() > 0) {
                   target = Target.fromTarget(targets.get(0));
                }
            }
            
            return target;
            
        }
        catch(ApiException ex) {
            throw new WorkspaceApiException("Cannot get target", ex);
        }
    }
    
    public void deletePersonalFavorite(Target target) throws WorkspaceApiException {
        try {
            ApiSuccessResponse resp = targetsApi.deletePersonalFavorite(String.valueOf(target.getId()), target.getType().getValue());
            Util.throwIfNotOk(resp);
        }
        catch(ApiException ex) {
            throw new WorkspaceApiException("Cannot delete personal favorite", ex);
        }
    }
    
    public SearchResult<Target> getPersonalFavorites() throws WorkspaceApiException {
        return getPersonalFavorites(0);
    }

    public SearchResult<Target> getPersonalFavorites(int limit) throws WorkspaceApiException {
        try {
            TargetsResponse resp = targetsApi.getPersonalFavorites(limit > 0? new BigDecimal(limit): null);
            Util.throwIfNotOk(resp.getStatus());
            
            TargetsResponseData data = resp.getData();
            int total = 0;
            List<Target> list = new ArrayList<>();
            if(data != null) {
                total = data.getTotalMatches();
                if(data.getTargets() != null) {
                    for (com.genesys.internal.workspace.model.Target t : data.getTargets()) {
                        Target target = Target.fromTarget(t);
                        list.add(target);
                    }
                }
            }

            return new SearchResult<>(total, list);
        }
        catch(ApiException ex) {
            throw new WorkspaceApiException("Cannot personal favorites", ex);
        }
    }

    public void savePersonalFavorite(Target target, String category) throws WorkspaceApiException {
        TargetspersonalfavoritessaveData data = new TargetspersonalfavoritessaveData();
        data.setCategory(category);
        data.setTarget(toInformation(target));
        PersonalFavoriteData favData = new PersonalFavoriteData();
        favData.setData(data);
        
        try {
            ApiSuccessResponse resp = targetsApi.savePersonalFavorite(favData);
            Util.throwIfNotOk(resp);
        }
        catch(ApiException ex) {
            throw new WorkspaceApiException("Cannot save personal favorites", ex);
        }
    }

    public void ackRecentMissedCalls() throws WorkspaceApiException {
        try {
            ApiSuccessResponse resp = targetsApi.ackRecentMissedCalls();
            Util.throwIfNotOk(resp);
        }
        catch(ApiException ex) {
            throw new WorkspaceApiException("Cannot ack recent missed calls", ex);
        }
    }
}
