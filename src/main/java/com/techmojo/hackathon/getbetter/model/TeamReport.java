package com.techmojo.hackathon.getbetter.model;

import java.util.List;

public class TeamReport {

	private String teamName;
	private int teamId;
	private int year;
	private int month;
	private double rating;
	private List<IndividualReport> individualReports;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
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

	public List<IndividualReport> getIndividualReports() {
		return individualReports;
	}

	public void setIndividualReports(List<IndividualReport> individualReports) {
		this.individualReports = individualReports;
	}
}
