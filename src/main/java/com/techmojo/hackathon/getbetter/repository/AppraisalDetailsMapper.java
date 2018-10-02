package com.techmojo.hackathon.getbetter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.techmojo.hackathon.getbetter.model.AppraisalDetail;
import com.techmojo.hackathon.getbetter.model.Conversation;
import com.techmojo.hackathon.getbetter.model.Parameter;

public class AppraisalDetailsMapper implements RowCallbackHandler {

	HashMap<Integer, AppraisalDetail> appraisalDtlMap = new HashMap<>();
	List<AppraisalDetail> appraisalDtl = new ArrayList<>();

	@Override
	public void processRow(ResultSet rs) throws SQLException {
		int weightageId = rs.getInt("iWeigtageID");
		AppraisalDetail appraisalDetail = null;
		if (appraisalDtlMap.containsKey(weightageId)) {
			appraisalDetail = appraisalDtlMap.get(weightageId);
		} else {
			appraisalDetail = new AppraisalDetail();
			appraisalDetail.setCreatedDate(rs.getDate("dtCreatedOn"));
			appraisalDetail.setScore(rs.getInt("iScore"));
			appraisalDetail.setWeightageId(weightageId);
			appraisalDetail.setConversations(new ArrayList<>());
			Parameter parameter = new Parameter();
			parameter.setParameterId(rs.getInt("iParameterID"));
			appraisalDetail.setParameter(parameter);
			appraisalDtlMap.put(weightageId, appraisalDetail);
			appraisalDtl.add(appraisalDetail);
		}
		Conversation conversation = new Conversation();
		conversation.setConversation(rs.getString("tConversation"));
		conversation.setCreatedBy(rs.getInt("iCreatedBy"));
		conversation.setConversationId(rs.getString("iConversationID"));
		conversation.setCreatedDate(rs.getDate("dtCreatedOn"));
		appraisalDetail.getConversations().add(conversation);
	}
	
	public List<AppraisalDetail> getAppraisalDetails() {
		return appraisalDtl;
	}

}
