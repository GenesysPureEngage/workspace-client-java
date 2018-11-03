package com.genesys.workspace.models.targets;

import com.genesys.internal.workspace.model.Target.TypeEnum;

public enum TargetType {
    AGENT(TypeEnum.AGENT),
    AGENT_GROUP(TypeEnum.AGENT_GROUP),
    ACD_QUEUE(TypeEnum.ACD_QUEUE),
    ROUTE_POINT(TypeEnum.ROUTE_POINT),
    SKILL(TypeEnum.SKILL),
    CUSTOM_CONTACT(TypeEnum.CUSTOM_CONTACT);
    
    private final TypeEnum value;

    private TargetType(TypeEnum value) {
        this.value = value;
    }
    
    public String getValue() {
        return value.getValue();
    }
    
    static TargetType fromType(TypeEnum type) {
        TargetType[] values = TargetType.values();
        for(TargetType t: values) {
            if(t.value == type) {
                return t;
            }
        }
        
        throw new IllegalArgumentException("No such TargetType: " + type);
    }
}
