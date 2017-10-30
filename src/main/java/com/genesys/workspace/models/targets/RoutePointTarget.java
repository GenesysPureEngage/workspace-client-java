package com.genesys.workspace.models.targets;

public class RoutePointTarget extends DnTarget {

    public RoutePointTarget(String name, String number) {
        super(TargetType.ROUTE_POINT, name, number);
    }
}
