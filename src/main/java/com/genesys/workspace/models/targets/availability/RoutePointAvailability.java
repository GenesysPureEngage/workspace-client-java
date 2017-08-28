package com.genesys.workspace.models.targets.availability;

public class RoutePointAvailability {
    private int numberOfWaitingCalls;
    public RoutePointAvailability(int numberOfWaitingCalls) {
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
