package com.techmojo.hackathon.getbetter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techmojo.hackathon.getbetter.model.Category;
import com.techmojo.hackathon.getbetter.model.Parameter;
import com.techmojo.hackathon.getbetter.model.Weightage;
import com.techmojo.hackathon.getbetter.repository.ServiceDAO;

@Component
public class BackofficeService {

	@Autowired
	private ServiceDAO dao;
	
	public List<Category> getCategories() {
		return dao.getCategories();
	}
	
	public Category getCategory(int category) {
		// @todo: 
		return dao.getCategory(category);
	}
	
	@Transactional
	public Parameter addParameter(int categoryId, Parameter parameter) {
		// add parameter
		int parameterId = dao.createParameter(categoryId, 
				parameter.getParameterName(), parameter.getStatus());
		// add weigtages
		List<Weightage> weightages = parameter.getWeightages();
		for (Weightage weightage : weightages) {
			int weigtageId = dao.createWeightage(parameterId, 
					weightage.getDesignationId(), weightage.getWeightage(),
					weightage.getStatus());
			weightage.setWeightageId(weigtageId);
		}
		parameter.setParameterId(parameterId);
		return parameter;
	}
	
	@Transactional
	public int modifyParameter(int categoryId, int parameterId, Parameter parameter) {
		int modified = 0;
		// modify parameter -> modifiable parameters : name , status
		modified = dao.modifyParameter(categoryId, parameterId, parameter.getParameterName(), parameter.getStatus());
		// modify wieghtages
		List<Weightage> weightages = parameter.getWeightages();
		int updated = 0;
		for (Weightage weightage : weightages) {
			updated = dao.modifyWeightage(weightage.getWeightageId(),
					weightage.getDesignationId(), weightage.getWeightage(),
					weightage.getStatus());
			if (modified == 0 && updated > 0) {
				modified = updated;
			}
		}
		
		return modified;
	}

	public List<Parameter> getParameters(int categoryId) {
		return dao.getParameters(categoryId);
	}

	public Parameter getParameter(int categoryId, int paramterId) {
		return dao.getParameter(categoryId, paramterId);
	}

	@Transactional
	public int deleteParameter(int categoryId, int parameterId) {
		int deleted = 0;
		// remove weightages for the parameter
		dao.deleteWeightage(parameterId);
		// remove parameter
		deleted = dao.deleteParameter(parameterId);
		return deleted;
	}
	
}
