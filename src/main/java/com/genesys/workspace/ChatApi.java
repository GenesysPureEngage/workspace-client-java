package com.genesys.workspace;

import com.genesys.internal.common.ApiClient;
import com.genesys.internal.common.ApiException;
import com.genesys.internal.workspace.model.ApiSuccessResponse;
import com.genesys.internal.workspace.model.AcceptData;
import com.genesys.internal.workspace.model.MediachatinteractionsidacceptData;
import com.genesys.internal.workspace.model.AcceptData1;
import com.genesys.internal.workspace.model.MediamediatypeinteractionsidsendmessageData;
import com.genesys.internal.workspace.model.LeaveData;
import com.genesys.internal.workspace.model.CompleteData;
import com.genesys.internal.workspace.model.MediamediatypeinteractionsidleaveData;
import com.genesys.internal.workspace.model.TypingStartedData;
import com.genesys.internal.workspace.model.MediamediatypeinteractionsidsendtypingstartedData;
import com.genesys.internal.workspace.model.TypingStoppedData;
import com.genesys.internal.workspace.model.MediamediatypeinteractionsidsendtypingstoppedData;
import com.genesys.internal.workspace.model.CancelConsultData;
import com.genesys.internal.workspace.model.ConsultData;
import com.genesys.internal.workspace.model.MediamediatypeinteractionsidconsultData;
import com.genesys.internal.workspace.model.ConsultData1;
import com.genesys.internal.workspace.model.MediamediatypeinteractionsidconsultbyqueueData;
import com.genesys.internal.workspace.model.InviteData;
import com.genesys.internal.workspace.model.MediamediatypeinteractionsidinviteData;
import com.genesys.internal.workspace.model.InviteData1;
import com.genesys.internal.workspace.model.MediamediatypeinteractionsidinvitebyqueueData;
import com.genesys.internal.workspace.model.RemoveFromConferenceData;
import com.genesys.internal.workspace.model.MediamediatypeinteractionsidremovefromconferenceData;
import com.genesys.internal.workspace.model.CustomNotificationData;
import com.genesys.internal.workspace.model.MediamediatypeinteractionsidsendcustomnotificationData;
import com.genesys.internal.workspace.model.AcceptData2;
import com.genesys.internal.workspace.model.MediamediatypeinteractionsidsendurlData;

import com.genesys.workspace.common.StatusCode;
import com.genesys.workspace.common.WorkspaceApiException;
import com.genesys.workspace.models.KeyValueCollection;
import com.genesys.workspace.models.Message;
import com.genesys.workspace.models.ChatInteraction;
import com.genesys.workspace.models.Chat;
import com.genesys.workspace.models.ChatParticipant;
import com.genesys.workspace.models.TranscriptMessage;
import com.genesys.workspace.events.InteractionEventListener;
import com.genesys.workspace.events.InteractionStateChanged;
import com.genesys.workspace.events.ChatMessageEventListener;
import com.genesys.workspace.events.MessageLogUpdated;

import java.util.*;

import java.util.List;

public class ChatApi {
    private com.genesys.internal.workspace.api.ChatApi chatApi;
    private com.genesys.internal.workspace.api.MediaApi mediaApi; //NOTE: for completeChat call

    private Map<String, ChatInteraction> chats = new HashMap();

    private Set<InteractionEventListener<ChatInteraction>> interactionEventListeners = new HashSet();
    private Set<ChatMessageEventListener> messageEventListeners = new HashSet();

    public ChatApi() {
        this.chats = new HashMap<>();
        this.interactionEventListeners = new HashSet<>();
        this.messageEventListeners = new HashSet<>();
    }

    void initialize(ApiClient client) {
        this.chatApi = new com.genesys.internal.workspace.api.ChatApi(client);
        this.mediaApi = new com.genesys.internal.workspace.api.MediaApi(client);
    }

    void setChatApi(com.genesys.internal.workspace.api.ChatApi chatApi) {
        this.chatApi = chatApi;
    }

    /**
     * Accept the specified chat.
     * Accept the specified chat interaction.
     *
     * @param id       The ID of the chat interaction. (required)
     * @param nickname The agent's nickname, as displayed to the chat participants. (optional)
     * @throws WorkspaceApiException if call is unsuccessful.
     */
    public void acceptChat(String id, String nickname) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("acceptChat", chatApi.acceptChat(id,
                    new AcceptData().data(new MediachatinteractionsidacceptData().nickname(nickname))));
        } catch (ApiException e) {
            throw new WorkspaceApiException("acceptChat failed.", e);
        }
    }


    /**
     * Leave a chat.
     * Leave the specified chat or conference. If the agent is in a conference, the chat  session stays open for the customer. If the agent is not in a conference, the chat   ends for the customer but the agent can still update user data and set disposition.
     *
     * @param id          The ID of the chat interaction. (required)
     * @param mediaType   The chat media channel. (required)
     * @param afterAction afterAction. (optional)
     * @param message     message. (optional)
     * @throws WorkspaceApiException if call is unsuccessful.
     */
    public void leaveChat(String id, String mediaType, String afterAction, Message message) throws WorkspaceApiException {
        try {
            MediamediatypeinteractionsidleaveData data = new MediamediatypeinteractionsidleaveData();
            data.setAfterAction(afterAction == null ? null : MediamediatypeinteractionsidleaveData.AfterActionEnum.valueOf(afterAction));
            if (message != null) data.message(message.getMessage())
                    .messageType(message.getMessageType())
                    .treatAs(message.getTreatAs() == null ? null : MediamediatypeinteractionsidleaveData.TreatAsEnum.valueOf(message.getTreatAs().toString()));

            throwIfNotOkAsync("leaveChat", chatApi.leaveChat(mediaType, id,
                    new LeaveData().data(new MediamediatypeinteractionsidleaveData())));
        } catch (ApiException e) {
            throw new WorkspaceApiException("leaveChat failed.", e);
        }
    }

    /**
     * Leave a chat.
     * Leave the specified chat or conference. If the agent is in a conference, the chat  session stays open for the customer. If the agent is not in a conference, the chat   ends for the customer but the agent can still update user data and set disposition.
     *
     * @param id        The ID of the chat interaction. (required)
     * @param mediaType The chat media channel. (required)
     * @throws WorkspaceApiException if call is unsuccessful.
     */
    public void leaveChat(String id, String mediaType) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("leaveChat", chatApi.leaveChat(mediaType, id,
                    new LeaveData().data(new MediamediatypeinteractionsidleaveData())));
        } catch (ApiException e) {
            throw new WorkspaceApiException("leaveChat failed.", e);
        }
    }

    /**
     * Complete the chat.
     * Marks the specified chat as complete.
     *
     * @param id The ID of the chat interaction to complete. (required)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void completeChat(String id) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("completeChat", mediaApi.complete("chat", id, new CompleteData()));
        } catch (ApiException e) {
            throw new WorkspaceApiException("completeChat failed.", e);
        }
    }

    /**
     * Cancel a chat consultation request.
     * Cancel a chat consultation request that was initialized by calling [/media/chat/interactions/{id}/consult-by-queue](/reference/workspace/Media/index.html#consultByQueue). If the agent has already accepted the invitation, the Workspace API can&#39;t cancel the consultation.
     *
     * @param id        The ID of the chat interaction. (required)
     * @param mediaType The chat media channel. (required)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void cancelConsultationChat(String id, String mediaType) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("cancelConsultationChat", chatApi.cancelConsultationChat(id, mediaType, new CancelConsultData()));
        } catch (ApiException e) {
            throw new WorkspaceApiException("cancelConsultationChat failed.", e);
        }
    }

    /**
     * Consult with another agent during a chat.
     * A consult occurs in the context of the specified chat, but the customer is not aware of the consulting agent. Messages and notifications from the consulting agent are only visible to other agents in the chat, not to the customer. After the consulting agent accepts the consultation, the originating agent can either transfer the chat to the consulting agent ([/media/{mediatype}/interactions/{id}/transfer-agent](/reference/workspace/Media/index.html#transferAgent)), add them in a conference ([/media/chat/interactions/{id}/invite](/reference/workspace/Media/index.html#invite)) or the consulting agent can leave the chat ([/media/chat/interactions/{id}/leave](/reference/workspace/Media/index.html#leaveChat)).
     *
     * @param id        The ID of the chat interaction. (required)
     * @param mediaType The chat media channel. (required)
     * @param agentId   (required)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void consult(String id, String mediaType, String agentId) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("consult", chatApi.consult(id, mediaType, new ConsultData().data(new MediamediatypeinteractionsidconsultData().agentId(agentId))));
        } catch (ApiException e) {
            throw new WorkspaceApiException("consult failed.", e);
        }
    }

    /**
     * Consult with another agent via a queue.
     * Consult with another agent during a chat by sending an consult invitation to the specified queue. A consult occurs in the context of the specified chat, but the customer is not aware of the consulting agent. Messages and notifications from the consulting agent are only visible to other agents in the cat, not to the customer. After the consulting agent accepts the consultation, the originating agent can either transfer the chat to the consulting agent ([/media/{mediatype}/interactions/{id}/transfer-agent](/reference/workspace/Media/index.html#transferAgent)), add them in a conference ([/media/chat/interactions/{id}/invite](/reference/workspace/Media/index.html#invite)) or the consulting agent can leave the chat ([/media/chat/interactions/{id}/leave](/reference/workspace/Media/index.html#leaveChat)).
     *
     * @param id        The ID of the chat interaction. (required)
     * @param mediaType The chat media channel. (required)
     * @param queue     (required)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void consultByQueue(String id, String mediaType, String queue) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("consultByQueue", chatApi.consultByQueue(mediaType, id, new ConsultData1().data(new MediamediatypeinteractionsidconsultbyqueueData().queue(queue))));
        } catch (ApiException e) {
            throw new WorkspaceApiException("consultByQueue failed.", e);
        }
    }

    /**
     * Send notification that the agent is typing.
     * Send notification that the agent is typing to the other participants in the specified chat.
     *
     * @param id        The ID of the chat interaction. (required)
     * @param mediaType The chat media channel. (required)
     * @param message   Message parameters. (optional)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void sendTypingStarted(String id, String mediaType, Message message) throws WorkspaceApiException {
        try {
            TypingStartedData data = message == null ? null : new TypingStartedData().data(new MediamediatypeinteractionsidsendtypingstartedData()
                    .visibility(MediamediatypeinteractionsidsendtypingstartedData.VisibilityEnum.valueOf(message.getVisibility().toString()))
                    .message(message.getMessage()));
            throwIfNotOkAsync("sendTypingStarted", chatApi.sendTypingStarted(id, mediaType, data));
        } catch (ApiException e) {
            throw new WorkspaceApiException("sendTypingStarted failed.", e);
        }
    }

    /**
     * Send notification that the agent is typing.
     * Send notification that the agent is typing to the other participants in the specified chat.
     *
     * @param id        The ID of the chat interaction. (required)
     * @param mediaType The chat media channel. (required)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void sendTypingStarted(String id, String mediaType) throws WorkspaceApiException {
        sendTypingStarted(id, mediaType, null);
    }

    /**
     * Send notification that the agent stopped typing.
     * Send notification that the agent stopped typing to the other participants in the specified chat.
     *
     * @param id        The ID of the chat interaction. (required)
     * @param mediaType The chat media channel. (required)
     * @param message   Message parameters. (optional)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void sendTypingStopped(String id, String mediaType, Message message) throws WorkspaceApiException {
        try {
            TypingStoppedData data = message == null ? null : new TypingStoppedData().data(new MediamediatypeinteractionsidsendtypingstoppedData()
                    .visibility(MediamediatypeinteractionsidsendtypingstoppedData.VisibilityEnum.valueOf(message.getVisibility().toString()))
                    .message(message.getMessage()));
            throwIfNotOkAsync("sendTypingStopped", chatApi.sendTypingStopped(id, mediaType, data));
        } catch (ApiException e) {
            throw new WorkspaceApiException("sendTypingStopped failed.", e);
        }
    }

    /**
     * Send notification that the agent stopped typing.
     * Send notification that the agent stopped typing to the other participants in the specified chat.
     *
     * @param id        The ID of the chat interaction. (required)
     * @param mediaType The chat media channel. (required)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void sendTypingStopped(String id, String mediaType) throws WorkspaceApiException {
        sendTypingStopped(id, mediaType, null);
    }

    /**
     * Invite another agent to the chat conference.
     * Invite another agent to join the specified chat conference. The customer is notified when the invited agent joins the chat. The agents can communicate with the customer or they can communicate with each other without the customer seeing their messages, depending on the value you set for the **visibility** parameter when you call [/media/chat/interactions/{id}/send-message](/reference/workspace/Media/index.html#sendMessage).
     *
     * @param id        The ID of the chat interaction. (required)
     * @param mediaType The chat media channel. (required)
     * @param agentId   (required)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void invite(String id, String mediaType, String agentId) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("invite", chatApi.invite(id, mediaType, new InviteData().data(new MediamediatypeinteractionsidinviteData().agentId(agentId))));
        } catch (ApiException e) {
            throw new WorkspaceApiException("invite failed.", e);
        }
    }

    /**
     * Invite another agent to the chat conference via a queue.
     * Invite another agent to the chat conference by sending an invitation to the specified queue. The next available agent in the queue is then sent an invite to join the chat. The customer is notified when the invited agent joins the chat. The agents can communicate with the customer or they can communicate with each other without the customer seeing their messages, depending on the value you set for the **visibility** parameter when you call [/media/chat/interactions/{id}/send-message](/reference/workspace/Media/index.html#sendMessage).
     *
     * @param id        The ID of the chat interaction. (required)
     * @param mediaType The chat media channel. (required)
     * @param queue     (required)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void inviteByQueue(String id, String mediaType, String queue) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("inviteByQueue", chatApi.inviteByQueue(id, mediaType, new InviteData1().data(new MediamediatypeinteractionsidinvitebyqueueData().queue(queue))));
        } catch (ApiException e) {
            throw new WorkspaceApiException("inviteByQueue failed.", e);
        }
    }

    /**
     * Remove an agent from a chat conference.
     * Remove the specified agent from the chat conference.
     *
     * @param id        The ID of the chat interaction. (required)
     * @param mediaType The chat media channel. (required)
     * @param agentId   (required)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void removeFromConference(String id, String mediaType, String agentId) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("removeFromConference", chatApi.removeFromConference(id, mediaType, new RemoveFromConferenceData().data(new MediamediatypeinteractionsidremovefromconferenceData().agentId(agentId))));
        } catch (ApiException e) {
            throw new WorkspaceApiException("removeFromConference failed.", e);
        }
    }

    /**
     * Send a custom notification.
     * Send a custom notification to the specified chat. The notification is typically used as a trigger for some custom behavior on the recipient&#39;s end.
     *
     * @param id        The ID of the chat interaction. (required)
     * @param mediaType The chat media channel. (required)
     * @param message   message parameters. (optional)
     * @param userData  user data. (optional)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void sendCustomNotification(String id, String mediaType, Message message, KeyValueCollection userData) throws WorkspaceApiException {
        try {
            MediamediatypeinteractionsidsendcustomnotificationData data = new MediamediatypeinteractionsidsendcustomnotificationData();
            if (message != null)
                data.message(message.getMessage()).visibility(MediamediatypeinteractionsidsendcustomnotificationData.VisibilityEnum.valueOf(message.getVisibility().toString()));
            if (userData != null) data.userData(Util.toKVList(userData));
            throwIfNotOkAsync("sendCustomNotification", chatApi.sendCustomNotification(mediaType, id, new CustomNotificationData().data(data)));
        } catch (ApiException e) {
            throw new WorkspaceApiException("sendCustomNotification failed.", e);
        }
    }

    //NOTE: Visibility may be set to enum type later

    /**
     * Send a URL.
     * Send a URL to participants in the specified chat.
     *
     * @param id         The ID of the chat interaction. (required)
     * @param mediaType  The chat media channel. (required)
     * @param visibility Visibility of message. (required)
     * @param url        Url to be sent. (required)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void sendUrlData(String id, String mediaType, String visibility, String url) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("sendUrlData", chatApi.sendUrlData(id, mediaType, new AcceptData2().data(new MediamediatypeinteractionsidsendurlData()
                    .visibility(MediamediatypeinteractionsidsendurlData.VisibilityEnum.valueOf(visibility))
                    .url(url))));
        } catch (ApiException e) {
            throw new WorkspaceApiException("sendUrlData failed.", e);
        }
    }

    //NOTE: InlineResponse2003 has no status code.

    /**
     * Get chat transcript.
     * Get a transcript for the specified chat interaction.
     *
     * @param id        The ID of the chat interaction. (required)
     * @param mediaType The chat media channel. (required)
     * @return List<Object> List of messages.
     * @throws WorkspaceApiException if call is unsuccessful.
     */
    public List<TranscriptMessage> chatMessages(String id, String mediaType) throws WorkspaceApiException {
        try {
            Chat chat = chats.containsKey(id) ? chats.get(id).getChat() : new Chat();
            Object resp = chatApi.chatMessages(id, mediaType);
            Object data = ((Map<String, Object>) resp).get("data");

            return parseMessages((List<Object>) ((Map<String, Object>) data).get("messages"), chat);
        } catch (ApiException e) {
            throw new WorkspaceApiException("chatMessages failed.", e);
        }
    }

    /**
     * Send a message.
     * Send a message to participants in the specified chat.
     *
     * @param id        The ID of the chat interaction. (required)
     * @param mediaType The chat media channel. (required)
     * @param message   The message data. (required)
     * @throws WorkspaceApiException if call is unsuccessful.
     */
    public void sendMessage(String id, String mediaType, Message message) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("sendMessage", chatApi.sendMessage(id, mediaType,
                    new AcceptData1().data(new MediamediatypeinteractionsidsendmessageData()
                            .message(message.getMessage())
                            .messageType(message.getMessageType())
                            .treatAs(message.getTreatAs() == null ? null : MediamediatypeinteractionsidsendmessageData.TreatAsEnum.fromValue(message.getTreatAs().toString()))
                            .visibility(message.getVisibility() == null ? null : MediamediatypeinteractionsidsendmessageData.VisibilityEnum.fromValue(message.getVisibility().toString()))
                    )));
        } catch (ApiException e) {
            throw new WorkspaceApiException("sendMessage failed.", e);
        }
    }


    synchronized void onChatMessage(Map<String, Object> data) {

        String messageType = (String) data.get("messageType");
        if ("InteractionStateChanged".equals(messageType)) {
            Map<String, Object> interactionData = (Map<String, Object>) data.get("interaction");
            String id = (String) interactionData.get("id");
            if (!this.chats.containsKey(id)) this.chats.put(id, (ChatInteraction) (new ChatInteraction().id(id)));

            ChatInteraction chatInteraction = this.chats.get(id);

            MediaApi.parseInteractionData(chatInteraction, interactionData);
            if (chatInteraction.getChat() == null) chatInteraction.setChat(new Chat());

            parseChatData(chatInteraction.getChat(), (Map<String, Object>) interactionData.get("chat"));

            String notificationType = (String) data.get("notificationType");

            InteractionStateChanged<ChatInteraction> event = new InteractionStateChanged<ChatInteraction>(id, chatInteraction, notificationType);

            for (InteractionEventListener<ChatInteraction> listener : interactionEventListeners) {
                listener.handleInteractionStateChanged(event);
            }
        } else if ("MessageLogUpdated".equals(messageType)) {

            String id = (String) data.get("interactionId");

            String notificationType = (String) data.get("notificationType");
            Chat chat = chats.get(id).getChat();

            List<TranscriptMessage> messages = parseMessages((Object[]) data.get("messages"), chat);
            chat.addMessages(messages);

            MessageLogUpdated event = new MessageLogUpdated(id, chat, messages, notificationType);
            for (ChatMessageEventListener listener : messageEventListeners) {
                listener.handleMessageLogUpdated(event);
            }

        } else {

        }

    }

    private static ChatParticipant parseChatParticipant(ChatParticipant chatParticipant, Map<String, Object> chatParticipantData) {
        if (chatParticipantData.containsKey("id")) chatParticipant.setId((String) chatParticipantData.get("id"));
        if (chatParticipantData.containsKey("type")) chatParticipant.setType((String) chatParticipantData.get("type"));
        if (chatParticipantData.containsKey("nickname"))
            chatParticipant.setNickname((String) chatParticipantData.get("nickname"));
        if (chatParticipantData.containsKey("visibility"))
            chatParticipant.setVisibility((String) chatParticipantData.get("visibility"));
        if (chatParticipantData.containsKey("personId"))
            chatParticipant.setPersonId((String) chatParticipantData.get("personId"));
        if (chatParticipantData.containsKey("capabilities")) {
            Object[] capabilities = (Object[]) chatParticipantData.get("capabilities");
            chatParticipant.setCapabilities(Arrays.asList(Arrays.copyOf(capabilities, capabilities.length, String[].class)));
        }
        return chatParticipant;
    }

    private static Chat parseChatData(Chat chat, Map<String, Object> chatData) {

        if (chatData.containsKey("participants")) {
            List<ChatParticipant> chatParticipants = new ArrayList();
            for (Object o : (Object[]) chatData.get("participants")) {
                chatParticipants.add(parseChatParticipant(new ChatParticipant(), (Map<String, Object>) o));
            }
            chat.setParticipants(chatParticipants);
        }
        if (chatData.containsKey("startAt")) chat.setStartAt((String) chatData.get("startAt"));
        if (chatData.containsKey("myParticipantId")) chat.setMyParticipantId((String) chatData.get("myParticipantId"));
        if (chatData.containsKey("myVisibility")) chat.setMyVisibility((String) chatData.get("myVisibility"));
        if (chatData.containsKey("myUserType")) chat.setMyUserType((String) chatData.get("myUserType"));
        if (chat.getMessages() == null) if (chatData.containsKey("messages"))
            chat.setMessages(parseMessages((Object[]) chatData.get("messages"), chat));
        if (chatData.containsKey("isAsyncMode")) chat.setIsAsyncMode((Boolean) chatData.get("isAsyncMode"));
        return chat;
    }

    private static List<TranscriptMessage> parseMessages(List<Object> messagesData, Chat chat) {
        List<TranscriptMessage> messages = new ArrayList();
        for (Object o : messagesData) {
            messages.add(parseMessage((Map<String, Object>) o, chat));
        }
        return messages;
    }

    private static List<TranscriptMessage> parseMessages(Object[] messagesData, Chat chat) {
        List<TranscriptMessage> messages = new ArrayList();
        for (Object o : messagesData) {
            messages.add(parseMessage((Map<String, Object>) o, chat));
        }
        return messages;
    }

    private static TranscriptMessage parseMessage(Map<String, Object> messageData, Chat chat) {
        TranscriptMessage message = new TranscriptMessage();
        if (messageData.containsKey("index")) message.setIndex((int) (long) messageData.get("index"));
        if (messageData.containsKey("type")) message.setType((String) messageData.get("type"));
        if (messageData.containsKey("text")) message.setText((String) messageData.get("text"));
        if (messageData.containsKey("from")) {
            Map<String, Object> participantData = ((Map<String, Object>) messageData.get("from"));

            String participantId = (String) participantData.get("participantId");
            ChatParticipant participant = chat.getParticipant(participantId);
            if (participant == null) {
                participant = new ChatParticipant();
            }
            participant.setId(participantId);
            message.setFrom(parseChatParticipant(participant, participantData));
        }
        if (messageData.containsKey("visibility")) message.setVisibility((String) messageData.get("visibility"));
        if (messageData.containsKey("timestamp")) message.setTimestamp((String) messageData.get("timestamp"));
        if (messageData.containsKey("timestampSeconds"))
            message.setTimestampSeconds((Long) messageData.get("timestampSeconds"));
        if (messageData.containsKey("userData")) message.setUserData((Object) messageData.get("userData"));
        return message;
    }

    public void addInteractionEventListener(InteractionEventListener<ChatInteraction> listener) {
        interactionEventListeners.add(listener);
    }

    public void removeInteractionEventListener(InteractionEventListener<ChatInteraction> listener) {
        interactionEventListeners.remove(listener);
    }

    public void addMessageEventListener(ChatMessageEventListener listener) {
        messageEventListeners.add(listener);
    }

    public void removeMessageEventListener(ChatMessageEventListener listener) {
        messageEventListeners.remove(listener);
    }

    private void throwIfNotOkAsync(String requestName, ApiSuccessResponse response) throws WorkspaceApiException {
        if (response.getStatus().getCode() != StatusCode.ASYNC_OK) {
            throw new WorkspaceApiException(
                    requestName + " failed with code: " + response.getStatus().getCode());
        }
    }
}