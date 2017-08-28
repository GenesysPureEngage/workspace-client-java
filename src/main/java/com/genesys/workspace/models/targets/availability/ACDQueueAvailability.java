package com.genesys.workspace.models.targets.availability;

public class ACDQueueAvailability {
    private int numberOfWaitingCalls;
    public ACDQueueAvailability(int numberOfWaitingCalls) {
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
