package com.techmojo.hackathon.getbetter.model;

import java.util.List;

public class SearchData {

	private String type;
	private List<TeamReport> teamReports;
	private List<IndividualReport> individualReports;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<TeamReport> getTeamReports() {
		return teamReports;
	}

	public void setTeamReports(List<TeamReport> teamReports) {
		this.teamReports = teamReports;
	}

	public List<IndividualReport> getIndividualReports() {
		return individualReports;
	}

	public void setIndividualReports(List<IndividualReport> individualReports) {
		this.individualReports = individualReports;
	}

}
