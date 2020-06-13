package com.genesys.workspace.events;

import com.genesys.workspace.models.Interaction;

public class InteractionStateChanged<InteractionT extends Interaction> {
    private String id;
    private InteractionT interactionState;
    private String notificationType;

    public InteractionStateChanged(String id, InteractionT interactionState, String notificationType) {
        this.id = id;
        this.interactionState = interactionState;
        this.notificationType = notificationType;
    }

    public String getId() {
        return id;
    }

    public InteractionT getInteractionState() {
        return interactionState;
    }

    public String getNotificationType() {
        return notificationType;
    }
}