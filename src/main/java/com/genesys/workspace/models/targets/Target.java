package com.genesys.workspace.models.targets;

import com.genesys.workspace.models.targets.availability.*;
import com.google.gson.internal.LinkedTreeMap;


public abstract class Target {
    private long id;
    private final TargetType type;
    private String name;
    private String number;
    private TargetAvailability availability;    

    protected Target(TargetType type, String name, String number) {
        this.type = type;
        this.name = name;
        this.number = number;
    }

    public static Target fromTarget(com.genesys.internal.workspace.model.Target target) {
        Target inst = null;
        
        String name = target.getName();
        String number = target.getNumber();
        
        switch (target.getType()) {
            case AGENT:
                inst = new AgentTarget(name, number);
                break;
            case ACD_QUEUE:
                inst = new AcdQueueTarget(name, number);
                break;
            case AGENT_GROUP:
                inst = new AgentGroupTarget(name, number);
                break;
            case ROUTE_POINT:
                inst = new RoutePointTarget(name, number);
                break;
            case SKILL:
                inst = new SkillTarget(name, number);
                break;
            case CUSTOM_CONTACT:
                inst = new CustomContractTarget(name, number);
                break;
        }
        
        if(inst != null) {
            inst.id = target.getDBID();
            
            Object data = target.getAvailability();
            if(data instanceof LinkedTreeMap) {
                TargetAvailability availability = inst.extractAvailability((LinkedTreeMap<String,Object>)data);
                inst.setAvailability(availability);
            }
        }
        
        return inst;
    }
    
    protected void setAvailability(TargetAvailability availability) {
        this.availability = availability;        
    }
    
    public TargetAvailability getAvailability() {
        return availability;
    }

    public long getId() {
        return id;
    }
    
    protected TargetAvailability extractAvailability(LinkedTreeMap<String,Object> data) {
        return null;
    }
    
    public TargetType getType() {
        return this.type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        String str = "name [" + name + "] type [" + type + "]";

        if (this.number != null) {
            str += " number [" + number + "]\n";
        }

        if (this.availability != null) {
            str += " availability " + availability;
        }

        return str;
    }
}

