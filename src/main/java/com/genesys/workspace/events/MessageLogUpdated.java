package com.genesys.workspace.events;

import com.genesys.workspace.models.Chat;
import com.genesys.workspace.models.TranscriptMessage;

import java.util.List;

public class MessageLogUpdated {
    private String id;
    private Chat chat;
    private List<TranscriptMessage> messagesUpdated;
    private String notificationType;

    public MessageLogUpdated(String id, Chat chat, List<TranscriptMessage> messagesUpdated, String notificationType) {
        this.id = id;
        this.chat = chat;
        this.messagesUpdated = messagesUpdated;
        this.notificationType = notificationType;
    }

    public String getId() {
        return id;
    }

    public Chat getChat() {
        return chat;
    }

    public List<TranscriptMessage> getMessagesUpdated() {
        return messagesUpdated;
    }

    public String getNotificationType() {
        return notificationType;
    }
}