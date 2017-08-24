package com.genesys.workspace.models.targets;

import java.util.Collection;

public class TargetSearchResult {
    private long totalMatches;
    private Collection<Target> targets;

    public TargetSearchResult(long totalMatches, Collection<Target> targets) {
        this.totalMatches = totalMatches;
        this.targets = targets;
    }

    public long getTotalMatches() {
        return this.totalMatches;
    }

    public Collection<Target> getTargets() {
        return this.targets;
    }
}
