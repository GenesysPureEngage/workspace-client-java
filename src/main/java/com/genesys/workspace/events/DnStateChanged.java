package com.genesys.workspace.events;

import com.genesys.workspace.models.DNumber;

public class DnStateChanged {
    private DNumber dn;
    public DnStateChanged(DNumber dn) {
        this.dn = dn;
    }

    public DNumber getDn() {
        return this.dn;
    }
}
