package com.genesys.workspace.models.targets;

public class TargetsSearchOptions {
    private String filterName;
    private TargetType[] types;
    private boolean desc;
    private int limit;
    private boolean exact;
    private String[] excludeGroups;
    private String[] restrictGroups;
    private String[] excludeFromGroups;
    private String[] restrictToGroups;

    public String[] getRestrictGroups() { return restrictGroups; }

    public void setRestrictGroups(String[] restrictGroups) {
        this.restrictGroups = restrictGroups;
    }

    public String[] getExcludeGroups() { return excludeGroups; }

    public void setExcludeGroups(String[] excludeGroups) {
        this.excludeGroups = excludeGroups;
    }

    public String[] getExcludeFromGroups() {
        return excludeFromGroups;
    }

    public void setExcludeFromGroups(String[] excludeFromGroups) {
        this.excludeFromGroups = excludeFromGroups;
    }

    public String[] getRestrictToGroups() {
        return restrictToGroups;
    }

    public void setRestrictToGroups(String[] restrictToGroups) {
        this.restrictToGroups = restrictToGroups;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public TargetType[] getTypes() {
        return types;
    }

    public void setTypes(TargetType[] types) {
        this.types = types;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isExact() {
        return exact;
    }

    public void setExact(boolean exact) {
        this.exact = exact;
    }
}
