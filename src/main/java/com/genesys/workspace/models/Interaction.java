package com.genesys.workspace.models;

import java.util.List;

public class Interaction {
  	
  	private KeyValueCollection extension = null;
  	private KeyValueCollection userData = null;
  	private String subject = null;
  	private Boolean isOnline = null;
  	private String interactionType = null;
  	private Boolean isLocked = null;
  	private String id = null;
  	private String state = null;
  	private List<String> capabilities = null;
  	private String interactionSubtype = null;
  	private Boolean isHeld = null;
  	private Boolean isInWorkflow = null;
  	private String workflowState = null;
  	private Chat chat = null;
  	private String mediatype = null;
  	private String queue = null;
  	private String comment = null;
  	private Long ticketId = null;
  	private String contactId = null;
  	private String parentInteractionId = null;
  	private Boolean isAlive = null;
  	private String visibilityMode = null;
  	private String moniterMode = null;
  	private String ucsContent = null;
  	private KeyValueCollection inQueues = null;
  	private KeyValueCollection outQueues = null;
  	private List<Object> attatchments = null;
  	
  	public Interaction() {}
  	
    public void setExtension(KeyValueCollection extension) {
        this.extension = extension;
    }
    
    public KeyValueCollection getExtension() {
    	return this.extension;
    }
  	
    public Interaction extension(KeyValueCollection extension) {
    	this.extension = extension;
    	return this;
    }
  	
    public void setUserData(KeyValueCollection userData) {
        this.userData = userData;
    }
    
    public KeyValueCollection getUserData() {
    	return this.userData;
    }
  	
    public Interaction userData(KeyValueCollection userData) {
    	this.userData = userData;
    	return this;
    }
  	
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getSubject() {
    	return this.subject;
    }
  	
    public Interaction subject(String subject) {
    	this.subject = subject;
    	return this;
    }
  	
    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }
    
    public Boolean getIsOnline() {
    	return this.isOnline;
    }
  	
    public Interaction isOnline(Boolean isOnline) {
    	this.isOnline = isOnline;
    	return this;
    }
  	
    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }
    
    public String getInteractionType() {
    	return this.interactionType;
    }
  	
    public Interaction interactionType(String interactionType) {
    	this.interactionType = interactionType;
    	return this;
    }
  	
    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }
    
    public Boolean getIsLocked() {
    	return this.isLocked;
    }
  	
    public Interaction isLocked(Boolean isLocked) {
    	this.isLocked = isLocked;
    	return this;
    }
  	
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
    	return this.id;
    }
  	
    public Interaction id(String id) {
    	this.id = id;
    	return this;
    }
  	
    public void setState(String state) {
        this.state = state;
    }
    
    public String getState() {
    	return this.state;
    }
  	
    public Interaction state(String state) {
    	this.state = state;
    	return this;
    }
  	
    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }
    
    public List<String> getCapabilities() {
    	return this.capabilities;
    }
  	
    public Interaction capabilities(List<String> capabilities) {
    	this.capabilities = capabilities;
    	return this;
    }
  	
    public void setInteractionSubtype(String interactionSubtype) {
        this.interactionSubtype = interactionSubtype;
    }
    
    public String getInteractionSubtype() {
    	return this.interactionSubtype;
    }
  	
    public Interaction interactionSubtype(String interactionSubtype) {
    	this.interactionSubtype = interactionSubtype;
    	return this;
    }
  	
    public void setIsHeld(Boolean isHeld) {
        this.isHeld = isHeld;
    }
    
    public Boolean getIsHeld() {
    	return this.isHeld;
    }
  	
    public Interaction isHeld(Boolean isHeld) {
    	this.isHeld = isHeld;
    	return this;
    }
  	
    public void setIsInWorkflow(Boolean isInWorkflow) {
        this.isInWorkflow = isInWorkflow;
    }
    
    public Boolean getIsInWorkflow() {
    	return this.isInWorkflow;
    }
  	
    public Interaction isInWorkflow(Boolean isInWorkflow) {
    	this.isInWorkflow = isInWorkflow;
    	return this;
    }
  	
    public void setWorkflowState(String workflowState) {
        this.workflowState = workflowState;
    }
    
    public String getWorkflowState() {
    	return this.workflowState;
    }
  	
    public Interaction workflowState(String workflowState) {
    	this.workflowState = workflowState;
    	return this;
    }
  	
    public void setChat(Chat chat) {
        this.chat = chat;
    }
    
    public Chat getChat() {
    	return this.chat;
    }
  	
    public Interaction chat(Chat chat) {
    	this.chat = chat;
    	return this;
    }
  	
    public void setMediatype(String mediatype) {
        this.mediatype = mediatype;
    }
    
    public String getMediatype() {
    	return this.mediatype;
    }
  	
    public Interaction mediatype(String mediatype) {
    	this.mediatype = mediatype;
    	return this;
    }
  	
    public void setQueue(String queue) {
        this.queue = queue;
    }
    
    public String getQueue() {
    	return this.queue;
    }
  	
    public Interaction queue(String queue) {
    	this.queue = queue;
    	return this;
    }
  	
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getComment() {
    	return this.comment;
    }
  	
    public Interaction comment(String comment) {
    	this.comment = comment;
    	return this;
    }
  	
    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
    
    public Long getTicketId() {
    	return this.ticketId;
    }
  	
    public Interaction ticketId(Long ticketId) {
    	this.ticketId = ticketId;
    	return this;
    }
  	
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
    
    public String getContactId() {
    	return this.contactId;
    }
  	
    public Interaction contactId(String contactId) {
    	this.contactId = contactId;
    	return this;
    }
  	
    public void setParentInteractionId(String parentInteractionId) {
        this.parentInteractionId = parentInteractionId;
    }
    
    public String getParentInteractionId() {
    	return this.parentInteractionId;
    }
  	
    public Interaction parentInteractionId(String parentInteractionId) {
    	this.parentInteractionId = parentInteractionId;
    	return this;
    }
  	
    public void setIsAlive(Boolean isAlive) {
        this.isAlive = isAlive;
    }
    
    public Boolean getIsAlive() {
    	return this.isAlive;
    }
  	
    public Interaction isAlive(Boolean isAlive) {
    	this.isAlive = isAlive;
    	return this;
    }
  	
    public void setVisibilityMode(String visibilityMode) {
        this.visibilityMode = visibilityMode;
    }
    
    public String getVisibilityMode() {
    	return this.visibilityMode;
    }
  	
    public Interaction visibilityMode(String visibilityMode) {
    	this.visibilityMode = visibilityMode;
    	return this;
    }
  	
    public void setMoniterMode(String moniterMode) {
        this.moniterMode = moniterMode;
    }
    
    public String getMoniterMode() {
    	return this.moniterMode;
    }
  	
    public Interaction moniterMode(String moniterMode) {
    	this.moniterMode = moniterMode;
    	return this;
    }
  	
    public void setUcsContent(String ucsContent) {
        this.ucsContent = ucsContent;
    }
    
    public String getUcsContent() {
    	return this.ucsContent;
    }
  	
    public Interaction ucsContent(String ucsContent) {
    	this.ucsContent = ucsContent;
    	return this;
    }
  	
    public void setInQueues(KeyValueCollection inQueues) {
        this.inQueues = inQueues;
    }
    
    public KeyValueCollection getInQueues() {
    	return this.inQueues;
    }
  	
    public Interaction inQueues(KeyValueCollection inQueues) {
    	this.inQueues = inQueues;
    	return this;
    }
  	
    public void setOutQueues(KeyValueCollection outQueues) {
        this.outQueues = outQueues;
    }
    
    public KeyValueCollection getOutQueues() {
    	return this.outQueues;
    }
  	
    public Interaction outQueues(KeyValueCollection outQueues) {
    	this.outQueues = outQueues;
    	return this;
    }
  	
    public void setAttatchments(List<Object> attatchments) {
        this.attatchments = attatchments;
    }
    
    public List<Object> getAttatchments() {
    	return this.attatchments;
    }
  	
    public Interaction attatchments(List<Object> attatchments) {
    	this.attatchments = attatchments;
    	return this;
    }
}