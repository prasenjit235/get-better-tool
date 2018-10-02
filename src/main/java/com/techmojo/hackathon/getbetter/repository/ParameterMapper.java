package com.techmojo.hackathon.getbetter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.techmojo.hackathon.getbetter.model.Parameter;
import com.techmojo.hackathon.getbetter.model.Weightage;

public class ParameterMapper implements RowCallbackHandler{

	private HashMap<Integer, Parameter> parameterMapping = new HashMap<>();
	private ArrayList<Parameter> parameters = new ArrayList<>();
	
	@Override
	public void processRow(ResultSet rs) throws SQLException {

		int parameterId = rs.getInt("iParameterID");
		Parameter parameter = null;
		if (parameterMapping.containsKey(parameterId)) {
			parameter = parameterMapping.get(parameterId);
		} else {
			parameter = new Parameter(
					rs.getInt("iParameterID"), 
					rs.getString("cParameterName"), 
					rs.getInt("iCategoryID"), 
					rs.getDate("p_createdDate"),
					rs.getInt("p_status"));
			parameter.setWeightages(new ArrayList<Weightage>());
			parameters.add(parameter);
		}
		
		Weightage weightage = new Weightage(
					rs.getInt("iWeightageID"), 
					rs.getInt("iScore"), 
					rs.getInt("iDesignationID"), 
					rs.getInt("w_status"));
				
		parameter.getWeightages().add(weightage);
		parameterMapping.put(parameterId, parameter);
	}

	public ArrayList<Parameter> getParameters() {
		return parameters;
	}

	
}
