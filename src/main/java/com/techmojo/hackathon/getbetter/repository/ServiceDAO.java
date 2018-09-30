package com.techmojo.hackathon.getbetter.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.techmojo.hackathon.getbetter.model.Category;
import com.techmojo.hackathon.getbetter.model.Parameter;

@Repository
public class ServiceDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Category> getCategories() {
		List<Category> details = jdbcTemplate.query(getQueries("GET_CATEGORIES"), 
				new Object[] {}, 
				new CategoryMapper());
		if (details == null || details.isEmpty()) {
			return null;
		}
		
		return details;
	}
	
	public Category getCategory(int category) {
		List<Category> details = jdbcTemplate.query(getQueries("GET_CATEGORY_BY_ID"), 
				new Object[] {category}, 
				new CategoryMapper());
		if (details == null || details.isEmpty()) {
			return null;
		}
		
		return details.get(0);
	}
	
	public List<Parameter> getParameters(int category) {
		ParameterMapper mapper= new ParameterMapper();
		jdbcTemplate.query(getQueries("GET_PARAMETERS_BY_CAT_ID"), 
				new Object[] {category}, 
				mapper);
		List<Parameter> details = mapper.getParameters();
		if (details == null || details.isEmpty()) {
			return null;
		}
		
		return details;
	}
	
	public Category getParameter(int category, int parameter) {
		// @todo:
		List<Category> details = jdbcTemplate.query(getQueries("GET_CATEGORY_BY_ID"), 
				new Object[] {category}, 
				new CategoryMapper());
		if (details == null || details.isEmpty()) {
			return null;
		}
		
		return details.get(0);
	}
	
	private String getQueries(String queryName) {
		String query = "";
		switch (queryName) {
		case "GET_CATEGORIES":
			query = "select "
					+ " iCategoryID,"
					+ " cCategoryName,"
					+ " dtCreatedOn,"
					+ " dtUpdatedOn,"
					+ " iStatus"
				+ " from tbl_Categories";
			break;
		case "GET_CATEGORY_BY_ID":
			query = "select "
					+ " iCategoryID,"
					+ " cCategoryName,"
					+ " dtCreatedOn,"
					+ " dtUpdatedOn,"
					+ " iStatus"
				+ " from tbl_Categories"
				+ " where  iCategoryID = ?";
			break;
		case "INSERT_PARAMETER":
			query = "insert "
				+ " into tbl_Parameters ("
					+ " iCategoryID,"
					+ " cParameterName,"
					+ " iStatus)"
				+ " values(?,?,?)";
			break;
		case "INSERT_WEIGHTAGE":
			query = "insert "
				+ " into tbl_Weigtages ("
					+ " iParameterID,"
					+ " iDesignationID,"
					+ " iScore,"
					+ " iStatus)"
				+ " values(?,?,?,?)";
			break;
		case "UPDATE_PARAMETER":
			query = "update "
				+ " tbl_Parameters set"
					+ " cParameterName = ?,"
					+ " iStatus = ?"
				+ " where iParameterID = ?";
			break;
		case "UPDATE_WEIGHTAGE":
			query = "update "
					+ " tbl_Weigtages set"
						+ " iScore = ?,"
						+ " iStatus = ?"
					+ " where iWeightageID = ?";
			break;
		case "GET_PARAMETERS_BY_CAT_ID":
			query = "select "
					+ " p.iParameterID,"
					+ " p.cParameterName,"
					+ " p.dtCreatedOn as p_createdDate,"
					+ " p.iStatus as p_status,"
					+ " w.iWeightageID,"
					+ " w.iDesignationID,"
					+ " w.iScore,"
					+ " w.dtCreatedOn as w_createdDate,"
					+ " w.iStatus as w_status "
				 + " from tbl_Parameters p , tbl_Weigtages w "
				 + " where "
				    + " p.iCategoryID = ? "
				    + " and p.iParameterID = w.iParameterID ";
			break;
		default:
			break;
		}
		
		return query;
	}

	public int createParameter(int categoryId, String parameterName, int status) {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(getQueries("INSERT_PARAMETER"), Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, categoryId);
				ps.setString(2, parameterName);
				ps.setInt(3, status);
				return ps;
			}
		}, holder);

		return holder.getKey().intValue();
	}

	public int createWeightage(int parameterId, int designationId, int weightage, int status) {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(getQueries("INSERT_WEIGHTAGE"), Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, parameterId);
				ps.setInt(2, designationId);
				ps.setInt(3, weightage);
				ps.setInt(4, status);
				return ps;
			}
		}, holder);

		return holder.getKey().intValue();
	}

	public int modifyParameter(int categoryId, int parameterId, String parameterName, int status) {
		int count = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(getQueries("UPDATE_PARAMETER"), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, parameterName);
				ps.setInt(2, status);
				ps.setInt(3, parameterId);
				return ps;
			}
		});
		
		return count;
	}

	public int modifyWeightage(int weightageId, int designationId, int weightage, int status) {
		int count = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(getQueries("UPDATE_WEIGHTAGE"), Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, weightage);
				ps.setInt(2, status);
				ps.setInt(3, weightageId);
				return ps;
			}
		});
		
		return count;
	}
}
