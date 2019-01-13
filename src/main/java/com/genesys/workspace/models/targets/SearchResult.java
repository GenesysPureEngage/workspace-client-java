package com.genesys.workspace.models.targets;

import java.util.Collection;

public class SearchResult <T> {
    private final long total;
    private final Collection<T> targets;

    public SearchResult(long total, Collection<T> targets) {
        this.total = total;
        this.targets = targets;
    }

    public long getTotal() {
        return total;
    }

    public Collection<T> getTargets() {
        return targets;
    }
}
