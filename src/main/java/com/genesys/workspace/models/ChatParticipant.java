package com.genesys.workspace.models;

import java.util.List;

public class ChatParticipant {
  	
  	private String id = null;
  	private String type = null;
  	private String nickname = null;
  	private String visibility = null;
  	private String personId = null;
  	private List<String> capabilities = null;
  	
  	public ChatParticipant() {}
  	
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
    	return this.id;
    }
  	
    public ChatParticipant id(String id) {
    	this.id = id;
    	return this;
    }
  	
    public void setType(String type) {
        this.type = type;
    }
    
    public String getType() {
    	return this.type;
    }
  	
    public ChatParticipant type(String type) {
    	this.type = type;
    	return this;
    }
  	
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getNickname() {
    	return this.nickname;
    }
  	
    public ChatParticipant nickname(String nickname) {
    	this.nickname = nickname;
    	return this;
    }
  	
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
    
    public String getVisibility() {
    	return this.visibility;
    }
  	
    public ChatParticipant visibility(String visibility) {
    	this.visibility = visibility;
    	return this;
    }
  	
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    
    public String getPersonId() {
    	return this.personId;
    }
  	
    public ChatParticipant personId(String personId) {
    	this.personId = personId;
    	return this;
    }
  	
    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }
    
    public List<String> getCapabilities() {
    	return this.capabilities;
    }
  	
    public ChatParticipant capabilities(List<String> capabilities) {
    	this.capabilities = capabilities;
    	return this;
    }
}