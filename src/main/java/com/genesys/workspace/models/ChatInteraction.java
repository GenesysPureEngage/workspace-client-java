package com.genesys.workspace.models;

import java.util.List;

public class ChatInteraction extends Interaction {
	
	private Chat chat;
	
	public ChatInteraction() {}
	
	public void setChat(Chat chat) {
		this.chat = chat;
	}
	
	public Chat getChat() {
		return chat;
	}
	
}