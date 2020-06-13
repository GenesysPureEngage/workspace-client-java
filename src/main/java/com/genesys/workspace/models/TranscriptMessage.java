package com.genesys.workspace.models;

public class TranscriptMessage {
  	
  	private Integer index = null;
  	private String type = null;
  	private String text = null;
  	private ChatParticipant from = null;
  	private String visibility = null;
  	private String timestamp = null;
  	private Long timestampSeconds = null;
  	private Object userData = null;
  	
  	public TranscriptMessage() {}
  	
    public void setIndex(Integer index) {
        this.index = index;
    }
    
    public Integer getIndex() {
    	return this.index;
    }
  	
    public TranscriptMessage index(Integer index) {
    	this.index = index;
    	return this;
    }
  	
    public void setType(String type) {
        this.type = type;
    }
    
    public String getType() {
    	return this.type;
    }
  	
    public TranscriptMessage type(String type) {
    	this.type = type;
    	return this;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public String getText() {
    	return this.text;
    }
  	
    public TranscriptMessage text(String text) {
    	this.text = text;
    	return this;
    }
  	
    public void setFrom(ChatParticipant from) {
        this.from = from;
    }
    
    public ChatParticipant getFrom() {
    	return this.from;
    }
  	
    public TranscriptMessage from(ChatParticipant from) {
    	this.from = from;
    	return this;
    }
  	
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
    
    public String getVisibility() {
    	return this.visibility;
    }
  	
    public TranscriptMessage visibility(String visibility) {
    	this.visibility = visibility;
    	return this;
    }
  	
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getTimestamp() {
    	return this.timestamp;
    }
  	
    public TranscriptMessage timestamp(String timestamp) {
    	this.timestamp = timestamp;
    	return this;
    }
  	
    public void setTimestampSeconds(Long timestampSeconds) {
        this.timestampSeconds = timestampSeconds;
    }
    
    public Long getTimestampSeconds() {
    	return this.timestampSeconds;
    }
  	
    public TranscriptMessage timestampSeconds(Long timestampSeconds) {
    	this.timestampSeconds = timestampSeconds;
    	return this;
    }
  	
    public void setUserData(Object userData) {
        this.userData = userData;
    }
    
    public Object getUserData() {
    	return this.userData;
    }
  	
    public TranscriptMessage userData(Object userData) {
    	this.userData = userData;
    	return this;
    }
}