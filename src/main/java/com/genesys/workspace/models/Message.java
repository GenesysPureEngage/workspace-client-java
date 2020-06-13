package com.genesys.workspace.models;

public class Message {
  	
  	private String message = null;
  	private String messageType = null;
  	private TreatAsEnum treatAs = null;
  	private Visibility visibility = null;
  	
  	public Message() {}
  	
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMessage() {
    	return this.message;
    }
  	
    public Message message(String message) {
    	this.message = message;
    	return this;
    }
  	
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    
    public String getMessageType() {
    	return this.messageType;
    }
  	
    public Message messageType(String messageType) {
    	this.messageType = messageType;
    	return this;
    }
  	
    public void setTreatAs(TreatAsEnum treatAs) {
        this.treatAs = treatAs;
    }
    
    public TreatAsEnum getTreatAs() {
    	return this.treatAs;
    }
  	
    public Message treatAs(TreatAsEnum treatAs) {
    	this.treatAs = treatAs;
    	return this;
    }
  	
    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }
    
    public Visibility getVisibility() {
    	return this.visibility;
    }
  	
    public Message visibility(Visibility visibility) {
    	this.visibility = visibility;
    	return this;
    }
    
    public enum Visibility {
    	ALL("All"),
		AGENT("Agent"),
		SUPERVISOR("Supervisor");

		private String value;

		Visibility(String value) {
		  this.value = value;
		}

		public String getValue() {
		  return value;
		}

		@Override
		public String toString() {
		  return String.valueOf(value);
		}

		public static Visibility fromValue(String text) {
		  for (Visibility b : Visibility.values()) {
			if (String.valueOf(b.value).equals(text)) {
			  return b;
			}
		  }
		  return null;
		}

    }
    
    public enum TreatAsEnum {
    	NORMAL("Normal"),
		SYSTEM("System");

		private String value;

		TreatAsEnum(String value) {
		  this.value = value;
		}

		public String getValue() {
		  return value;
		}
		
		@Override
		public String toString() {
		    return String.valueOf(value);
		}

		public static TreatAsEnum fromValue(String text) {
		    for (TreatAsEnum b : TreatAsEnum.values()) {
			  if (String.valueOf(b.value).equals(text)) {
			      return b;
			  }
		    }
		    return null;
		}
    }
}