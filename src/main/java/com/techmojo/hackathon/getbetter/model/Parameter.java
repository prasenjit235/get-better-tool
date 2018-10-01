package com.techmojo.hackathon.getbetter.model;

import java.util.Date;
import java.util.List;

public class Parameter {

	private int parameterId;
	private String parameterName;
	private int categoryId;
	private Date creationDate;
	private int status;
	private List<Weightage> weightages;

	public Parameter() {

	}
	
	public Parameter(int parameterId, String parameterName, int categoryId, Date creationDate, int status) {
		super();
		this.parameterId = parameterId;
		this.parameterName = parameterName;
		this.categoryId = categoryId;
		this.creationDate = creationDate;
		this.status = status;
	}

	public int getParameterId() {
		return parameterId;
	}

	public void setParameterId(int parameterId) {
		this.parameterId = parameterId;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Weightage> getWeightages() {
		return weightages;
	}

	public void setWeightages(List<Weightage> weightages) {
		this.weightages = weightages;
	}

}
