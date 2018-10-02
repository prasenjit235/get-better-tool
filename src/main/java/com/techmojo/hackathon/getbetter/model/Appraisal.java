package com.techmojo.hackathon.getbetter.model;

import java.util.Date;
import java.util.List;

public class Appraisal {

	private int appraisalId;
	private int from;
	private int to;
	private int status; // 1 - started , 2 - pending with reportee , 3 - rejected , 4 - accepted , 5 - completed
	private Date createdDate;// fix this the view issue
	private int year;
	private int month;
	private List<Conversation> conversations;
	private List<AppraisalDetail> appraisalDetails;

	public int getAppraisalId() {
		return appraisalId;
	}

	public void setAppraisalId(int appraisalId) {
		this.appraisalId = appraisalId;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}

	public List<AppraisalDetail> getAppraisalDetails() {
		return appraisalDetails;
	}

	public void setAppraisalDetails(List<AppraisalDetail> appraisalDetails) {
		this.appraisalDetails = appraisalDetails;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
}
