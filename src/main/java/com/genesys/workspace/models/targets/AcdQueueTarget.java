package com.genesys.workspace.models.targets;

public class AcdQueueTarget extends DnTarget {

    public AcdQueueTarget(String name, String number) {
        super(TargetType.ACD_QUEUE, name, number);
    }
}
