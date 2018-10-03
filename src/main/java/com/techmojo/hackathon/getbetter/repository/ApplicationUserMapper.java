package com.techmojo.hackathon.getbetter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.techmojo.hackathon.getbetter.model.ApplicationUser;

public class ApplicationUserMapper implements RowMapper<ApplicationUser> {

	@Override
	public ApplicationUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUsername(String.valueOf(rs.getInt("iEmpID")));
		applicationUser.setPassword(rs.getString("cPassword"));
		return applicationUser;
	}

}
