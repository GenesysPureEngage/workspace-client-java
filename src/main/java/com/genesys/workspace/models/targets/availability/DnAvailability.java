package com.genesys.workspace.models.targets.availability;

public class DnAvailability extends TargetAvailability {
    private final int numberOfWaitingCalls;
    public DnAvailability(int numberOfWaitingCalls) {
        this.numberOfWaitingCalls = numberOfWaitingCalls;
    }

    public int getNumberOfWaitingCalls() {
        return this.numberOfWaitingCalls;
    }

    @Override
    public String toString() {
        String str = "waitingCalls [" + this.numberOfWaitingCalls + "]";
        return str;
    }
}
