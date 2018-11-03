package com.genesys.workspace.events;

import com.genesys.workspace.models.Dn;

public class DnStateChanged {
    private Dn dn;
    public DnStateChanged(Dn dn) {
        this.dn = dn;
    }

    public Dn getDn() {
        return this.dn;
    }
}
