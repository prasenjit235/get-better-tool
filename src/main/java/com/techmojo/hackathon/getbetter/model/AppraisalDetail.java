package com.techmojo.hackathon.getbetter.model;

import java.util.Date;
import java.util.List;

public class AppraisalDetail {

	private int weightageId;
	private int score;
	private Parameter parameter;
	private List<Conversation> conversations;
	private Date createdDate;

	public int getWeightageId() {
		return weightageId;
	}

	public void setWeightageId(int weightageId) {
		this.weightageId = weightageId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}
}
