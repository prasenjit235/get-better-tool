package com.techmojo.hackathon.getbetter.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.techmojo.hackathon.getbetter.model.Appraisal;
import com.techmojo.hackathon.getbetter.model.AppraisalDetail;
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
		for (Category category : details) {
			category.setParameters(getParameters(category.getId()));
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
		details.get(0).setParameters(getParameters(category));
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
	
	public Parameter getParameter(int category, int parameter) {
		ParameterMapper mapper= new ParameterMapper();
		jdbcTemplate.query(getQueries("GET_PARAMETERS_BY_PARAM_ID"), 
				new Object[] {parameter}, 
				mapper);
		List<Parameter> details = mapper.getParameters();
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
						+ " p.iCategoryID,"
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
			case "GET_PARAMETERS_BY_PARAM_ID":
				query = "select "
						+ " p.iParameterID,"
						+ " p.cParameterName,"
						+ " p.dtCreatedOn as p_createdDate,"
						+ " p.iStatus as p_status,"
						+ " p.iCategoryID,"
						+ " w.iWeightageID,"
						+ " w.iDesignationID,"
						+ " w.iScore,"
						+ " w.dtCreatedOn as w_createdDate,"
						+ " w.iStatus as w_status "
					 + " from tbl_Parameters p , tbl_Weigtages w "
					 + " where "
					    + " p.iParameterID = ? "
					    + " and p.iParameterID = w.iParameterID ";
				break;
			case "DELETE_WIEGHTAGES_BY_PARAM_ID":
				query = "delete "
					 + " from tbl_Weigtages "
					 + " where "
					    + " iParameterID = ? ";
				break;
			case "DELETE_PARAMETER_BY_PARAM_ID":
				query = "delete "
					 + " from tbl_Parameters "
					 + " where "
					    + " iParameterID = ? ";
				break;
			case "INSERT_CONVERSATION":
				query = "insert "
					+ "  into tbl_Conversations("
								+ " iConversationID,"
								+ " tConversation,"
								+ " iCreatedBy) "
								+ " values(?,?,?)";
				break;
			case "INSERT_APPRAISAL":
				query = "insert "
					+ "  into tbl_Appraisals("
								+ " iFrom,"
								+ " iTo,"
								+ " iConversationID,"
								+ " iStatus,"
								+ " iYear,"
								+ " iMonth) "
								+ " values(?,?,?,?,?,?)";
				break;
			case "INSERT_APPRAISAL_DETAILS":
				query = "insert "
					+ "  into tbl_Appraisal_Details("
								+ " iAppraisalID,"
								+ " iWeigtageID,"
								+ " iScore,"
								+ " iConversationID)"
								+ " values(?,?,?,?)";
				break;
			case "UPDATE_APPRAISAL":
				query = "update "
					+ "        tbl_Appraisals"
					 + "    set  "
					 		   + " iConversationID = ?,"
					 		   + " iStatus = ?"
					 + "    where "
					 		   + " iAppraisalID = ?";
				break;
			case "UPDATE_APPRAISAL_DETAILS":
				query = "update "
					+ "        tbl_Appraisal_Details"
					 + "    set  "
					 		   + " iConversationID = ?,"
					 		   + " iScore = ?"
					 + "    where "
					 		   + " iAppraisalID = ?"
					 		   + " and iWeigtageID = ?";
				break;
			case "GET_APPRAISAL":
				query = "select "
						+ " a.iFrom,"
						+ " a.iTo,"
						+ " a.iConversationID,"
						+ " a.dtCreatedOn,"
						+ " a.iAppraisalID,"
						+ " a.iStatus,"
						+ " a.iYear,"
						+ " a.iMonth,"
						+ " c.tConversation,"
						+ " c.iCreatedBy "
					 + "from tbl_Appraisals a left join tbl_Conversations c "
					 + " on a.iConversationID = c.iConversationID "
					 + "where "
					 + " a.iAppraisalID = ?";
				break;
			case "GET_APPRAISAL_DTLS":
				query = "select "
						+ " d.iWeigtageID,"
						+ " d.iScore,"
						+ " d.iConversationID,"
						+ " d.dtCreatedOn,"
						+ " w.iParameterID,"
						+ " c.tConversation,"
						+ " c.iCreatedBy "
					 + "from tbl_Appraisal_Details d inner join tbl_Weigtages w "
					 + " on w.iWeightageID = d.iWeigtageID left join tbl_Conversations c "
					 + " on d.iConversationID = c.iConversationID "
					 + "where d.iAppraisalID = ?";
				break;
			case "GET_APPRAISAL_FOR_EMPLOYEE_FIN_YEAR_MONTH_ALL":
				query = "select "
						+ " a.iFrom,"
						+ " a.iTo,"
						+ " a.iConversationID,"
						+ " a.dtCreatedOn,"
						+ " a.iAppraisalID,"
						+ " a.iStatus,"
						+ " a.iYear,"
						+ " a.iMonth,"
						+ " c.tConversation,"
						+ " c.iCreatedBy "
					 + "from tbl_Appraisals a left join tbl_Conversations c "
					 + " on a.iConversationID = c.iConversationID "
					 + "where "
					 + " a.iTo = ?"
					 + " and a.iYear = ?";
				break;
			case "GET_APPRAISAL_FOR_EMPLOYEE_FIN_YEAR_MONTH":
				query = "select "
						+ " a.iFrom,"
						+ " a.iTo,"
						+ " a.iConversationID,"
						+ " a.dtCreatedOn,"
						+ " a.iAppraisalID,"
						+ " a.iStatus,"
						+ " a.iYear,"
						+ " a.iMonth,"
						+ " c.tConversation,"
						+ " c.iCreatedBy "
					 + "from tbl_Appraisals a left join tbl_Conversations c "
					 + " on a.iConversationID = c.iConversationID "
					 + "where "
					 + " a.iTo = ?"
					 + " and a.iYear = ?"
					 + " and a.iMonth = ?";
				break;
			case "GET_APPRAISAL_FOR_REPORTEES_FIN_YEAR_MONTH":
				query = "select "
						+ " a.iFrom,"
						+ " a.iTo,"
						+ " a.iConversationID,"
						+ " a.dtCreatedOn,"
						+ " a.iAppraisalID,"
						+ " a.iStatus,"
						+ " a.iYear,"
						+ " a.iMonth,"
						+ " c.tConversation,"
						+ " c.iCreatedBy "
					 + "from tbl_Appraisals a left join tbl_Conversations c "
					 + " on a.iConversationID = c.iConversationID "
					 + "where "
					 + " a.iFrom = ?"
					 + " and a.iYear = ?"
					 + " and a.iMonth = ?";
				break;
			case "GET_APPRAISAL_FOR_REPORTEES_FIN_YEAR_MONTH_ALL":
				query = "select "
						+ " a.iFrom,"
						+ " a.iTo,"
						+ " a.iConversationID,"
						+ " a.dtCreatedOn,"
						+ " a.iAppraisalID,"
						+ " a.iStatus,"
						+ " a.iYear,"
						+ " a.iMonth,"
						+ " c.tConversation,"
						+ " c.iCreatedBy "
					 + "from tbl_Appraisals a left join tbl_Conversations c "
					 + " on a.iConversationID = c.iConversationID "
					 + "where "
					 + " a.iFrom = ?"
					 + " and a.iYear = ?";
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

	public int deleteWeightage(int parameterId) {
		int count = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(getQueries("DELETE_WIEGHTAGES_BY_PARAM_ID"));
				ps.setInt(1, parameterId);
				return ps;
			}
		});
		
		return count;
	}

	public int deleteParameter(int parameterId) {
		int count = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(getQueries("DELETE_PARAMETER_BY_PARAM_ID"));
				ps.setInt(1, parameterId);
				return ps;
			}
		});
		
		return count;
	}

	public int addAppraisal(int from, int to, String conversationId, int status, int year, int month) {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(getQueries("INSERT_APPRAISAL"), Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, from);
				ps.setInt(2, to);
				ps.setString(3, conversationId);
				ps.setInt(4, status);
				ps.setInt(5, year);
				ps.setInt(6, month);
				return ps;
			}
		}, holder);
		return holder.getKey().intValue();
	}

	public void addAppraisalDetails(int weightageId, int score, int appraisalId, String conversationId) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(getQueries("INSERT_APPRAISAL_DETAILS"), Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, appraisalId);
				ps.setInt(2, weightageId);
				ps.setInt(3, score);
				ps.setString(4, conversationId);
				return ps;
			}
		});
	}

	public void addConversation(String conversation, int createdBy, String conversationId) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(getQueries("INSERT_CONVERSATION"), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, conversationId);
				ps.setString(2, conversation);
				ps.setInt(3, createdBy);
				return ps;
			}
		});
	}

	public int modifyAppraisal(int appraisalId, String conversationId, int status) {
		int count = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(getQueries("UPDATE_APPRAISAL"));
				ps.setString(1, conversationId);
				ps.setInt(2, status);
				ps.setInt(3, appraisalId);
				return ps;
			}
		});
		return count;
	}

	public int mofifyAppraisalDetails(int appraisalId, int weightageId, int score, String conversationId) {
		int count = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(getQueries("UPDATE_APPRAISAL_DETAILS"));
				ps.setString(1, conversationId);
				ps.setInt(2, score);
				ps.setInt(3, appraisalId);
				ps.setInt(4, weightageId);
				return ps;
			}
		});
		return count;
	}

	public Appraisal getAppraisal(int appraisalId) {
		AppraisalMapper mapper= new AppraisalMapper();
		jdbcTemplate.query(getQueries("GET_APPRAISAL"), 
				new Object[] {appraisalId}, 
				mapper);
		Appraisal appraisal = mapper.getAppraisal(appraisalId);
		if (appraisal == null) {
			return null;
		}
		AppraisalDetailsMapper dtlsmapper= new AppraisalDetailsMapper();
		jdbcTemplate.query(getQueries("GET_APPRAISAL_DTLS"), 
				new Object[] {appraisalId}, 
				dtlsmapper);
		List<AppraisalDetail> appraisals = dtlsmapper.getAppraisalDetails();
		for (AppraisalDetail appraisalDetail : appraisals) {
			appraisalDetail.setParameter(getParameter(0, appraisalDetail.getParameter().getParameterId()));
		}
		appraisal.setAppraisalDetails(appraisals);
		return appraisal;
	}

	public ArrayList<Appraisal> getAppraisalForEmployee(int employeeId, int year, int month, boolean fromEmpl) {
		AppraisalMapper mapper= new AppraisalMapper();
		if (fromEmpl) {
			if (month > 0) {
				jdbcTemplate.query(getQueries("GET_APPRAISAL_FOR_EMPLOYEE_FIN_YEAR_MONTH"), 
						new Object[] {employeeId, year, month}, 
						mapper);
			} else {
				jdbcTemplate.query(getQueries("GET_APPRAISAL_FOR_EMPLOYEE_FIN_YEAR_MONTH_ALL"), 
						new Object[] {employeeId, year}, 
						mapper);
			}
		} else {
			if (month > 0) {
				jdbcTemplate.query(getQueries("GET_APPRAISAL_FOR_REPORTEES_FIN_YEAR_MONTH"), 
						new Object[] {employeeId, year, month}, 
						mapper);
			} else {
				jdbcTemplate.query(getQueries("GET_APPRAISAL_FOR_REPORTEES_FIN_YEAR_MONTH_ALL"), 
						new Object[] {employeeId, year}, 
						mapper);
			}
		}
		
		ArrayList<Appraisal> appraisals = mapper.getAppraisals();
		if (appraisals == null) {
			return null;
		}
		for (Appraisal appraisal : appraisals) {
			AppraisalDetailsMapper dtlsmapper= new AppraisalDetailsMapper();
			jdbcTemplate.query(getQueries("GET_APPRAISAL_DTLS"), 
					new Object[] {appraisal.getAppraisalId()}, 
					dtlsmapper);
			List<AppraisalDetail> appraisalDtls = dtlsmapper.getAppraisalDetails();
			for (AppraisalDetail appraisalDetail : appraisalDtls) {
				appraisalDetail.setParameter(getParameter(0, appraisalDetail.getParameter().getParameterId()));
			}
			appraisal.setAppraisalDetails(appraisalDtls);
		}
		return appraisals;
	}
}
