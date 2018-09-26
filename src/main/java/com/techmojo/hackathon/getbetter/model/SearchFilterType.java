package com.techmojo.hackathon.getbetter.model;

public class SearchFilterType {

	private String typeId;
	private String type;

	public SearchFilterType(String typeId, String type) {
		super();
		this.typeId = typeId;
		this.type = type;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SearchFilterType [typeId=" + typeId + ", type=" + type + "]";
	}

}
