package com.genesys.workspace.models.targets;

public class CustomContractTarget extends Target {

    public CustomContractTarget(String name, String number) {
        super(TargetType.CUSTOM_CONTACT, name, number);
    }

}
