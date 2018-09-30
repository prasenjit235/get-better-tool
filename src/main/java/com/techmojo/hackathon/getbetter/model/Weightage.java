package com.techmojo.hackathon.getbetter.model;

public class Weightage {

	private int weightageId;
	private int weightage;
	private int designationId;
	private int status;

	public Weightage(int weightageId, int weightage, int designationId, int status) {
		super();
		this.weightageId = weightageId;
		this.weightage = weightage;
		this.designationId = designationId;
		this.status = status;
	}

	public int getWeightageId() {
		return weightageId;
	}

	public void setWeightageId(int weightageId) {
		this.weightageId = weightageId;
	}

	public int getWeightage() {
		return weightage;
	}

	public void setWeightage(int weightage) {
		this.weightage = weightage;
	}

	public int getDesignationId() {
		return designationId;
	}

	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
