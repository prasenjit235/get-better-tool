package com.techmojo.hackathon.getbetter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.techmojo.hackathon.getbetter.model.Weightage;

public class IndividualWeightageMapper implements RowCallbackHandler {

	private HashMap<Integer, Weightage> weightageMap = new HashMap<>();

	@Override
	public void processRow(ResultSet rs) throws SQLException {
		int weightageId = rs.getInt("iWeightageID");
		Weightage weightage = new Weightage(weightageId, rs.getInt("iScore"), rs.getInt("designationId"),
				rs.getInt("status"));

		weightageMap.put(weightageId, weightage);
	}

	public HashMap<Integer, Weightage> getWeightageMap() {
		return weightageMap;
	}
}
