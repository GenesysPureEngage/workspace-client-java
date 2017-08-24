package com.genesys.workspace.models.targets;

public class Target {
    private String name;
    private String number;
    private TargetType type;

    public Target(com.genesys._internal.workspace.model.Target target) {
        this.name = target.getName();
        this.number = target.getNumber();

        switch (target.getType()) {
            case AGENT:
                this.type = TargetType.AGENT;
                break;
            case ACD_QUEUE:
                this.type = TargetType.ACD_QUEUE;
                break;
            case AGENT_GROUP:
                this.type = TargetType.AGENT_GROUP;
                break;
            case ROUTE_POINT:
                this.type = TargetType.ROUTE_POINT;
                break;
            case SKILL:
                this.type = TargetType.SKILL;
                break;
            case CUSTOM_CONTACT:
                this.type = TargetType.CUSTOM_CONTACT;
                break;
        }
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public TargetType getType() {
        return this.type;
    }
}

