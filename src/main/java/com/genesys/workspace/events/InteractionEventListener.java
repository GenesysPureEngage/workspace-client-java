package com.genesys.workspace.events;

import com.genesys.workspace.models.Interaction;

public interface InteractionEventListener<InteractionT extends Interaction> {
    void handleInteractionStateChanged(InteractionStateChanged<InteractionT> event);
}
