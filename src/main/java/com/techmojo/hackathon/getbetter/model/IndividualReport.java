package com.techmojo.hackathon.getbetter.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class IndividualReport {

	private int appraisalId;
	private int employeeId;
	private String employeeName;
	private String manager;
	private int managerId;
	private int year;
	private int month;
	private double rating;
	private int score;
	private int weightageId;
	

	public int getAppraisalId() {
		return appraisalId;
	}

	public void setAppraisalId(int appraisalId) {
		this.appraisalId = appraisalId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getWeightageId() {
		return weightageId;
	}

	public void setWeightageId(int weightageId) {
		this.weightageId = weightageId;
	}
	
	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String string =bCryptPasswordEncoder.encode("password123");
		System.out.println(string);
	}
}
