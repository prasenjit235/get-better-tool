package com.techmojo.hackathon.getbetter.model;

import java.util.Date;

public class Category {

	private int id;
	private String name;
	private Date creationDate;
	private Date updationDate;
	private int status;

	public Category() {
		super();
	}

	public Category(int id, String name, Date creationDate, Date updationDate, int status) {
		super();
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
		this.updationDate = updationDate;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdationDate() {
		return updationDate;
	}

	public void setUpdationDate(Date updationDate) {
		this.updationDate = updationDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
