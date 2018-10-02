package com.techmojo.hackathon.getbetter.model;

import java.util.Date;

public class Conversation {

	private String conversationId;
	private String conversation;
	private Date createdDate;
	private int createdBy;

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public String getConversation() {
		return conversation;
	}

	public void setConversation(String conversation) {
		this.conversation = conversation;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
}
