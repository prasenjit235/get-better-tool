package com.techmojo.hackathon.getbetter.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.techmojo.hackathon.getbetter.model.Appraisal;
import com.techmojo.hackathon.getbetter.model.AppraisalDetail;
import com.techmojo.hackathon.getbetter.model.Conversation;
import com.techmojo.hackathon.getbetter.repository.ServiceDAO;

@Component
public class AppraisalService {

	@Autowired
	private ServiceDAO dao;

	@Transactional
	public Appraisal addAppraisal(Appraisal appraisal) {
		/**
		 * - insert into tbl_Conversations if any conversation exists 
		 * - then into tbl_Appraisals
		 * - if any weightage exists with conversations , then
		 * - insert into tbl_Conversations and then into tbl_Appraisal_Details
		 */
		String conversationId = insertConversations(appraisal.getConversations());
		int appraisalId = dao.addAppraisal(appraisal.getFrom(),appraisal.getTo(),
				conversationId,appraisal.getStatus(), appraisal.getYear(), appraisal.getMonth());
		appraisal.setAppraisalId(appraisalId);
		for (AppraisalDetail appraisalDetail : appraisal.getAppraisalDetails()) {		
			conversationId = insertConversations(appraisalDetail.getConversations());
			dao.addAppraisalDetails(appraisalDetail.getWeightageId(),appraisalDetail.getScore(),
					appraisalId, conversationId);
		}
		return appraisal;
	}

	private String insertConversations(List<Conversation> conversations) {
		String conversationId = null;
		if (conversations != null && !conversations.isEmpty()) {
			UUID uuid = UUID.randomUUID();
			conversationId = uuid.toString();
			conversationId = conversationId.replaceAll("-", "");
			for (Conversation conversation : conversations) {
				dao.addConversation(conversation.getConversation(),conversation.getCreatedBy(),
						conversationId);
				conversation.setConversationId(conversationId);
			}
		}
		return conversationId;
	}

	@Transactional
	public void modifyAppraisal(int appraisalId, Appraisal appraisal) {
		/**
		 * - insert or update conversation
		 * - update appraisal
		 * - insert or update conversation of appraisal_detail
		 * - update appraisal_detail
		 * - for tbl_Appraisals table updateable fields are conversationId and status
		 * - for tbl_Appraisal_Details table updateable fields are conversationId,score
		 */
		String conversationId = insertOrUpdateConversations(appraisal.getConversations());
		dao.modifyAppraisal(appraisalId,conversationId,appraisal.getStatus());
		for (AppraisalDetail appraisalDetail : appraisal.getAppraisalDetails()) {	
			conversationId = insertOrUpdateConversations(appraisalDetail.getConversations());
			dao.mofifyAppraisalDetails(appraisalId,appraisalDetail.getWeightageId(),
					appraisalDetail.getScore(),conversationId);
		}
	}

	private String insertOrUpdateConversations(List<Conversation> conversations) {
		/**
		 * if the conversation list does not has any conversation id, then create one
		 * and insert the conversations.
		 * if the conversationid exists , then use the conversation id and insert the ones which
		 * does not have conversationid.
		 * 
		 */
		String conversationId = null;
		if (conversations != null && !conversations.isEmpty()) {
			List<Conversation> conversationsWdotId = new ArrayList<>();
			for (Conversation conversation : conversations) {
				if (StringUtils.isNullOrEmpty(conversation.getConversationId())) {
					conversationsWdotId.add(conversation);
				} else {
					conversationId = conversation.getConversationId();
				}
			}
			if (StringUtils.isNullOrEmpty(conversationId)) {
				UUID uuid = UUID.randomUUID();
				conversationId = uuid.toString();
				conversationId = conversationId.replaceAll("-", "");
			}
			for (Conversation conversation : conversationsWdotId) {
				dao.addConversation(conversation.getConversation(),conversation.getCreatedBy(),
						conversationId);
			}
		}
		
		return conversationId;
	}

	public Appraisal getAppraisal(int appraisalId) {
		return dao.getAppraisal(appraisalId);
	}

	public ArrayList<Appraisal> getAppraisalForEmployee(int employeeId, int year, int month) {
		// validate year , for now default
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(new Date());
		if (year == 0) {
			year = cal.get(Calendar.YEAR);
		}
		
		return dao.getAppraisalForEmployee(employeeId, year, month);
	}
	

}
