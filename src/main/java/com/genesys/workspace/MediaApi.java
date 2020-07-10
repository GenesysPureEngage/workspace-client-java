package com.genesys.workspace;

import com.genesys.internal.common.ApiClient;
import com.genesys.internal.common.ApiException;
import com.genesys.internal.workspace.model.ApiSuccessResponse;
import com.genesys.internal.workspace.model.AcceptData3;
import com.genesys.internal.workspace.model.MediamediatypeinteractionsidacceptData;
import com.genesys.internal.workspace.model.MediamediatypeinteractionsidrejectData;
import com.genesys.internal.workspace.model.CompleteData;
import com.genesys.internal.workspace.model.RejectData;
import com.genesys.internal.workspace.model.NotReadyForMediaData;
import com.genesys.internal.workspace.model.MediamediatypenotreadyData;
import com.genesys.internal.workspace.model.ReadyForMediaData;
import com.genesys.internal.workspace.model.MediamediatypelogoutData;
import com.genesys.internal.workspace.model.IxnReasonCode;

import com.genesys.workspace.common.StatusCode;
import com.genesys.workspace.common.WorkspaceApiException;
import com.genesys.workspace.models.KeyValueCollection;
import com.genesys.workspace.models.Channel;
import com.genesys.workspace.models.Interaction;
import com.genesys.workspace.events.ChannelEventListener;
import com.genesys.workspace.events.ChannelStateChanged;

import java.util.*;

public class MediaApi {
    private com.genesys.internal.workspace.api.MediaApi mediaApi;

    private Map<String, Channel> channels;
    private Set<ChannelEventListener> channelEventListeners;

    void initialize(ApiClient client) {
        this.mediaApi = new com.genesys.internal.workspace.api.MediaApi(client);
    }

    void setMediaApi(com.genesys.internal.workspace.api.MediaApi mediaApi) {
        this.mediaApi = mediaApi;
    }


    public MediaApi() {
        this.channels = new HashMap<>();
        this.channelEventListeners = new HashSet<>();
    }

    /**
     * Accept an incoming interaction.
     * Accept the specified interaction.
     *
     * @param mediaType The media channel. (required)
     * @param id        The ID of the interaction to accept. (required)
     * @param extension (optional)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void accept(String mediaType, String id, KeyValueCollection extension) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("accept", mediaApi.accept(mediaType, id,
                    new AcceptData3().data(new MediamediatypeinteractionsidacceptData().extension(Util.toKVList(extension)))));
        } catch (ApiException e) {
            throw new WorkspaceApiException("accept interaction failed.", e);
        }
    }

    /**
     * Reject an incoming interaction.
     * Reject the specified interaction.
     *
     * @param mediaType The media channel. (required)
     * @param id        The ID of the interaction to reject. (required)
     * @param extension (optional)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void reject(String mediaType, String id, KeyValueCollection extension) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("reject", mediaApi.reject(mediaType, id,
                    new RejectData().data(new MediamediatypeinteractionsidrejectData().extension(Util.toKVList(extension)))));
        } catch (ApiException e) {
            throw new WorkspaceApiException("reject interaction failed.", e);
        }
    }
    
    
    /*
    	To Be Implemented:
    	addAttachment
    	addContent
    	addDocument
    	assignContact
    	asyncPutOnHold
    	attachMediaUserData
    	attachments
    	deleteMediaUserData
    	mediaStartMonitoring
    	mediaStopMonitoring
    	mediaSwicthToBargeIn
    	mediaSwicthToCoachCall
    	mediaSwicthToMonitor
    	placeInQueue
    	removeAttachment
    	removeMedia
    	setComment
    	transferAgent
    	updateMediaUserData
    */

    /**
     * Complete the interaction.
     * Marks the specified interaction as complete.
     *
     * @param mediaType The media channel. (required)
     * @param id        The ID of the interaction to complete. (required)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void complete(String mediaType, String id) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("complete", mediaApi.complete(mediaType, id, new CompleteData()));
        } catch (ApiException e) {
            throw new WorkspaceApiException("complete interaction failed.", e);
        }
    }


    /**
     * Set the agent state to Not Ready.
     * Set the current agent&#39;s state to Not Ready on the specified media channel.
     *
     * @param mediaType The media channel. (required)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void notReadyForMedia(String mediaType, KeyValueCollection extension, String reasonCode, IxnReasonCode reason) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("notReadyForMedia", mediaApi.notReadyForMedia(mediaType, new NotReadyForMediaData().data(new MediamediatypenotreadyData()
                    .extension(Util.toKVList(extension))
                    .reasonCode(reasonCode)
                    .reason(reason))));
        } catch (ApiException e) {
            throw new WorkspaceApiException("notReadyForMedia failed.", e);
        }
    }

    /**
     * Set the agent state to Ready.
     * Set the current agent&#39;s state to Ready on the specified media channel.
     *
     * @param mediaType The media channel. (required)
     * @throws WorkspaceApiException if call is unsuccessful
     */
    public void readyForMedia(String mediaType, KeyValueCollection extension, IxnReasonCode reason) throws WorkspaceApiException {
        try {
            throwIfNotOkAsync("notReadyForMedia", mediaApi.readyForMedia(mediaType, new ReadyForMediaData().data(new MediamediatypelogoutData()
                    .extension(Util.toKVList(extension))
                    .reason(reason))));
        } catch (ApiException e) {
            throw new WorkspaceApiException("notReadyForMedia failed.", e);
        }
    }

    /**
     * Get channel info
     * Gets channel information (ex. agentState, dnd) for a specific mediaType.
     *
     * @param mediaType The media channel. (required)
     */
    public Channel getChannel(String mediaType) {
        return channels.get(mediaType);
    }

    /**
     * Example
     *
     * {
     *    "media":{
     *       "channels":[
     *          {
     *             "name":"chat",
     *             "state":"NotReady",
     *             "dnd":false
     *          }
     *       ]
     *    },
     *    "messageType":"ChannelStateChanged"
     * }
     *
     */
    void onMediaMessage(Map<String, Object> data) {
        System.out.println("Media message: " + data);
        String messageType = (String) data.get("messageType");

        if ("ChannelStateChanged".equals(messageType)) {
            Map<String, Object> media = (Map<String, Object>) data.get("media");
            Object[] channels = (Object[]) media.get("channels");
            List<String> channelsUpdated = new ArrayList();
            for (Object ch : channels) {

                Map<String, Object> channel = (Map<String, Object>) ch;
                String mediaType = (String) channel.get("name");
                channelsUpdated.add(mediaType);
                if (this.channels.containsKey(mediaType)) {
                    Channel updatedChannel = this.channels.get(mediaType);
                    updatedChannel.setAgentState(Util.parseAgentState((String) channel.get("state")));
                    updatedChannel.setDnd((Boolean) channel.get("dnd"));
                } else {
                    Channel newChannel = new Channel();
                    newChannel.setAgentState(Util.parseAgentState((String) channel.get("state")));
                    newChannel.setDnd((Boolean) channel.get("dnd"));
                    this.channels.put(mediaType, newChannel);
                }

            }
            ChannelStateChanged event = new ChannelStateChanged(this.channels, channelsUpdated);
            for (ChannelEventListener listener : channelEventListeners) {
                listener.handleChannelStateChanged(event);
            }
        }
    }

    public void addChannelEventListener(ChannelEventListener listener) {
        this.channelEventListeners.add(listener);
    }

    public void removeChannelEventListener(ChannelEventListener listener) {
        this.channelEventListeners.remove(listener);
    }

    static void parseInteractionData(Interaction interaction, Map<String, Object> interactionData) {
        if (interactionData.containsKey("extension"))
            interaction.setExtension(Util.extractKeyValueData((Object[]) interactionData.get("extension")));
        if (interactionData.containsKey("userData"))
            interaction.setUserData(Util.extractKeyValueData((Object[]) interactionData.get("userData")));
        if (interactionData.containsKey("subject")) interaction.setSubject((String) interactionData.get("subject"));
        if (interactionData.containsKey("isOnline")) interaction.setIsOnline((Boolean) interactionData.get("isOnline"));
        if (interactionData.containsKey("interactionType"))
            interaction.setInteractionType((String) interactionData.get("interactionType"));
        if (interactionData.containsKey("isLocked")) interaction.setIsLocked((Boolean) interactionData.get("isLocked"));
        if (interactionData.containsKey("state")) interaction.setState((String) interactionData.get("state"));
        if (interactionData.containsKey("isHeld")) interaction.setIsHeld((Boolean) interactionData.get("isHeld"));
        if (interactionData.containsKey("capabilities")) {
            Object[] capabilities = (Object[]) interactionData.get("capabilities");
            interaction.setCapabilities(Arrays.asList(Arrays.copyOf(capabilities, capabilities.length, String[].class)));
        }
        if (interactionData.containsKey("interactionSubtype"))
            interaction.setInteractionSubtype((String) interactionData.get("interactionSubtype"));
        if (interactionData.containsKey("isInWorkflow"))
            interaction.setIsInWorkflow((Boolean) interactionData.get("isInWorkflow"));
        if (interactionData.containsKey("workflowState"))
            interaction.setWorkflowState((String) interactionData.get("workflowState"));
        if (interactionData.containsKey("mediatype"))
            interaction.setMediatype((String) interactionData.get("mediatype"));
        if (interactionData.containsKey("queue")) interaction.setQueue((String) interactionData.get("queue"));
        if (interactionData.containsKey("comment")) interaction.setComment((String) interactionData.get("comment"));
        if (interactionData.containsKey("ticketId")) interaction.setTicketId((Long) interactionData.get("ticketId"));
        if (interactionData.containsKey("contactId"))
            interaction.setContactId((String) interactionData.get("contactId"));
        if (interactionData.containsKey("parentInteractionId"))
            interaction.setParentInteractionId((String) interactionData.get("parentInteractionId"));
        if (interactionData.containsKey("isAlive")) interaction.setIsAlive((Boolean) interactionData.get("isAlive"));
        if (interactionData.containsKey("visibilityMode"))
            interaction.setVisibilityMode((String) interactionData.get("visibilityMode"));
        if (interactionData.containsKey("moniterMode"))
            interaction.setMoniterMode((String) interactionData.get("moniterMode"));
        if (interactionData.containsKey("ucsContent"))
            interaction.setUcsContent((String) interactionData.get("ucsContent"));
        if (interactionData.containsKey("inQueues"))
            interaction.setInQueues(Util.extractKeyValueData((Object[]) interactionData.get("inQueues")));
        if (interactionData.containsKey("outQueues"))
            interaction.setOutQueues(Util.extractKeyValueData((Object[]) interactionData.get("outQueues")));
        if (interactionData.containsKey("attatchments"))
            interaction.setAttatchments(Arrays.asList(interactionData.get("attatchments")));
    }

    private void throwIfNotOkAsync(String requestName, ApiSuccessResponse response) throws WorkspaceApiException {
        if (response.getStatus().getCode() != StatusCode.ASYNC_OK) {
            throw new WorkspaceApiException(
                    requestName + " failed with code: " + response.getStatus().getCode());
        }
    }

}