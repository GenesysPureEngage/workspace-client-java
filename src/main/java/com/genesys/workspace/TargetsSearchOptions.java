package com.genesys.workspace;

public class TargetsSearchOptions {
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
    
    private String filterName;
    private Type[] types;
    private boolean desc;
    private int limit;
    private boolean exact;

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public Type[] getTypes() {
        return types;
    }

    public void setTypes(Type[] types) {
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
