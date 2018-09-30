package com.techmojo.hackathon.getbetter.model;

public class Designation {

	private int designationId;
	private String designation;

	public Designation(int designationId, String designation) {
		super();
		this.designationId = designationId;
		this.designation = designation;
	}

	public int getDesignationId() {
		return designationId;
	}

	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
