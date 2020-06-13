package com.genesys.workspace.models;

import java.util.List;

public class Chat {
  	
  	private List<ChatParticipant> participants = null;
  	private String startAt = null;
  	private String myParticipantId = null;
  	private String myVisibility = null;
  	private String myUserType = null;
  	private List<TranscriptMessage> messages = null;
  	private Boolean isAsyncMode = null;
  	
  	public Chat() {}
  	
    public void setParticipants(List<ChatParticipant> participants) {
        this.participants = participants;
    }
    
    public List<ChatParticipant> getParticipants() {
    	return this.participants;
    }
  	
    public Chat participants(List<ChatParticipant> participants) {
    	this.participants = participants;
    	return this;
    }
  	
    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }
    
    public String getStartAt() {
    	return this.startAt;
    }
  	
    public Chat startAt(String startAt) {
    	this.startAt = startAt;
    	return this;
    }
  	
    public void setMyParticipantId(String myParticipantId) {
        this.myParticipantId = myParticipantId;
    }
    
    public String getMyParticipantId() {
    	return this.myParticipantId;
    }
  	
    public Chat myParticipantId(String myParticipantId) {
    	this.myParticipantId = myParticipantId;
    	return this;
    }
  	
    public void setMyVisibility(String myVisibility) {
        this.myVisibility = myVisibility;
    }
    
    public String getMyVisibility() {
    	return this.myVisibility;
    }
  	
    public Chat myVisibility(String myVisibility) {
    	this.myVisibility = myVisibility;
    	return this;
    }
  	
    public void setMyUserType(String myUserType) {
        this.myUserType = myUserType;
    }
    
    public String getMyUserType() {
    	return this.myUserType;
    }
  	
    public Chat myUserType(String myUserType) {
    	this.myUserType = myUserType;
    	return this;
    }
  	
    public void setMessages(List<TranscriptMessage> messages) {
        this.messages = messages;
    }
    
    public List<TranscriptMessage> getMessages() {
    	return this.messages;
    }
  	
    public Chat messages(List<TranscriptMessage> messages) {
    	this.messages = messages;
    	return this;
    }
  	
    public void setIsAsyncMode(Boolean isAsyncMode) {
        this.isAsyncMode = isAsyncMode;
    }
    
    public Boolean getIsAsyncMode() {
    	return this.isAsyncMode;
    }
  	
    public Chat isAsyncMode(Boolean isAsyncMode) {
    	this.isAsyncMode = isAsyncMode;
    	return this;
    }
    
    public ChatParticipant getParticipant(String id) {
    	for(ChatParticipant participant: this.participants) {
    		if(id.equals(participant.getId())) return participant;
    	}
    	return null;
    }
    
    public void addMessages(List<TranscriptMessage> messages) {
    	for(TranscriptMessage message: messages) {
    		int index = message.getIndex();
    		if(index == -1) index = 0;
    		if(index < messages.size()) {
    			messages.set(index, message);
    		} else if(index == messages.size()) {
    			messages.add(message);
    		}
    	}
    }
    
    public boolean isMe(ChatParticipant participant) {
    	return myParticipantId == null ? false : myParticipantId.equals(participant.getId());
    }
    
}