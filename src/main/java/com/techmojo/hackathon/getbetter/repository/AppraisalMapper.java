package com.techmojo.hackathon.getbetter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.techmojo.hackathon.getbetter.model.Appraisal;
import com.techmojo.hackathon.getbetter.model.Conversation;

public class AppraisalMapper implements RowCallbackHandler {

	private HashMap<Integer, Appraisal> appraisalMap = new HashMap<>();
	private ArrayList<Appraisal> appraisals = new ArrayList<>();

	@Override
	public void processRow(ResultSet rs) throws SQLException {
		int appraisalId = rs.getInt("iAppraisalID");
		Appraisal appraisal = null;
		if (appraisalMap.containsKey(appraisalId)) {
			appraisal = appraisalMap.get(appraisalId);
		} else {
			appraisal = new Appraisal();
			appraisal.setFrom(rs.getInt("iFrom"));
			appraisal.setTo(rs.getInt("iTo"));
			appraisal.setStatus(rs.getInt("iStatus"));
			appraisal.setCreatedDate(rs.getDate("dtCReatedOn"));
			appraisal.setConversations(new ArrayList<>());
			appraisal.setAppraisalId(appraisalId);
			appraisal.setYear(rs.getInt("iYear"));
			appraisal.setMonth(rs.getInt("iMonth"));
			appraisalMap.put(appraisalId, appraisal);
			appraisals.add(appraisal);
		}
		Conversation conversation = new Conversation();
		conversation.setConversation(rs.getString("tConversation"));
		conversation.setCreatedBy(rs.getInt("iCreatedBy"));
		conversation.setConversationId(rs.getString("iConversationID"));
		conversation.setCreatedDate(rs.getDate("dtCreatedOn"));
		appraisal.getConversations().add(conversation);
	}

	public Appraisal getAppraisal(int appraisalId) {
		return appraisalMap.get(appraisalId);
	}

	public ArrayList<Appraisal> getAppraisals() {
		return appraisals;
	}

	
}
