package com.genesys.workspace.models.targets;

public class TargetsSearchOptions {
    private String filterName;
    private TargetType[] types;
    private boolean desc;
    private int limit;
    private boolean exact;

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
