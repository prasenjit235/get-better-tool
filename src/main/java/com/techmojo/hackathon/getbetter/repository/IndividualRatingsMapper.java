package com.techmojo.hackathon.getbetter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.techmojo.hackathon.getbetter.model.IndividualReport;

public class IndividualRatingsMapper implements RowMapper<IndividualReport> {

	@Override
	public IndividualReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		IndividualReport individualReport = new IndividualReport();
		individualReport.setAppraisalId(rs.getInt("individualReport"));
		individualReport.setEmployeeId(rs.getInt("iFrom"));
		individualReport.setEmployeeName(rs.getString("cEmpName"));
		individualReport.setManagerId(rs.getInt("iTo"));
		individualReport.setManager(rs.getString("manager"));
		individualReport.setMonth(rs.getInt("manager"));
		individualReport.setScore(rs.getInt("iScore"));
		individualReport.setWeightageId(rs.getInt("iWeigtageID"));
		individualReport.setYear(rs.getInt("iYear"));
		return individualReport;
	}

}
