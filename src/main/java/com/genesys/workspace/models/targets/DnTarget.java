package com.genesys.workspace.models.targets;

import com.genesys.workspace.models.targets.availability.DnAvailability;
import com.genesys.workspace.models.targets.availability.TargetAvailability;
import com.google.gson.internal.LinkedTreeMap;

public abstract class DnTarget extends Target {

    public DnTarget(TargetType type, String name, String number) {
        super(type, name, number);
    }

    @Override
    protected TargetAvailability extractAvailability(LinkedTreeMap<String, Object> data) {
        Integer waitingCalls = ((Double)data.get("waitingCalls")).intValue();
        DnAvailability availability = new DnAvailability(waitingCalls);
        return availability;
    }
}
