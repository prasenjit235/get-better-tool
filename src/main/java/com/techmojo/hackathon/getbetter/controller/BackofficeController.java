package com.techmojo.hackathon.getbetter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techmojo.hackathon.getbetter.model.Category;
import com.techmojo.hackathon.getbetter.model.Parameter;
import com.techmojo.hackathon.getbetter.services.BackofficeService;

@RestController
public class BackofficeController {

	@Autowired
	private BackofficeService service;
	
	@GetMapping("backoffice/appraisal/categories")
	public List<Category> getCategories() {
		return service.getCategories();
	}

	@GetMapping("backoffice/appraisal/categories/{category-id}")
	public ResponseEntity<Category> getCategory(@PathVariable("category-id") int categoryId) {
		HttpStatus status = HttpStatus.OK;
		Category  category = service.getCategory(categoryId);
		if (category == null) {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<Category>(category, status);
	}

	@GetMapping("backoffice/appraisal/categories/{category-id}/parameters")
	public ResponseEntity<List<Parameter>> getParameters(@PathVariable("category-id") int categoryId) {
		HttpStatus status = HttpStatus.OK;
		List<Parameter> parameters = service.getParameters(categoryId);
		if (parameters == null || parameters.isEmpty()) {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<Parameter>>(parameters, status);
	}

	@GetMapping("backoffice/appraisal/categories/{category-id}/parameters/{paramter-id}")
	public ResponseEntity<Parameter> getParamter(@PathVariable("category-id") int categoryId, @PathVariable("paramter-id") int paramterId) {
		HttpStatus status = HttpStatus.OK;
		Parameter parameter = service.getParameter(categoryId, paramterId);
		if (parameter == null) {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<Parameter>(parameter, status);
	}

	@PostMapping("backoffice/appraisal/categories/{category-id}/parameters")
	public ResponseEntity<Parameter> addParameter(@PathVariable("category-id") int categoryId, @RequestBody Parameter parameter) {
		HttpStatus status = HttpStatus.CREATED;
		Parameter createdParameter = null;
		try {
			createdParameter=service.addParameter(categoryId, parameter);
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return new ResponseEntity<Parameter>(createdParameter, status);
	}

	@PutMapping("backoffice/appraisal/categories/{category-id}/parameters/{paramter-id}")
	public ResponseEntity<Parameter> modifyParameter(@PathVariable("category-id") int categoryId,
			@PathVariable("paramter-id") int paramterId, @RequestBody Parameter parameter) {
		// @todo : insert weightage for an existing parameter
		HttpStatus status = HttpStatus.OK;
		int modified = 0;
		try {
			modified = service.modifyParameter(categoryId, paramterId, parameter);
			if (modified == 0) {
				status = HttpStatus.NOT_MODIFIED;
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return new ResponseEntity<Parameter>(parameter, status);
	}

	@DeleteMapping("backoffice/appraisal/categories/{category-id}/parameters/{paramter-id}")
	public ResponseEntity<Parameter> deleteParameter(@PathVariable("category-id") int categoryId,
			@PathVariable("paramter-id") int paramterId) {
		HttpStatus status = HttpStatus.OK;
		int modified = 0;
		try {
			modified = service.deleteParameter(categoryId, paramterId);
			if (modified == 0) {
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return new ResponseEntity<Parameter>(status);
	}
}
