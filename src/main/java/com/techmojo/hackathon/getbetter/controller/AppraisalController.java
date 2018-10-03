package com.techmojo.hackathon.getbetter.controller;

import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techmojo.hackathon.getbetter.model.Appraisal;
import com.techmojo.hackathon.getbetter.model.SearchFilterType;
import com.techmojo.hackathon.getbetter.model.SearchTypeDetail;
import com.techmojo.hackathon.getbetter.services.AppraisalService;

@RestController
public class AppraisalController {
	
	@Autowired
	private AppraisalService service;

	@GetMapping("appraisal/search/filter/types")
	public ArrayList<SearchFilterType> getSearchFilterTypes() {
		/*
		 * happy path
		 */
		SearchFilterType filterType1 = new SearchFilterType("1", "Individual");
		SearchFilterType filterType2 = new SearchFilterType("2", "Projects");
		ArrayList<SearchFilterType> filterTypes = new ArrayList<>();
		filterTypes.add(filterType1);
		filterTypes.add(filterType2);

		return filterTypes;
	}
	
	@GetMapping("appraisal/search/filter/type/{id}")
	public ArrayList<SearchTypeDetail> getSearchFilterType(@PathVariable("id") String typeId) {
		/*
		 * happy path
		 */
		ArrayList<SearchTypeDetail> filterDetails = new ArrayList<>();
		if (typeId.equalsIgnoreCase("1")) {
			SearchTypeDetail detail1 = new SearchTypeDetail("1", "Ranjith");
			SearchTypeDetail detail2 = new SearchTypeDetail("2", "Dileep");
			filterDetails.add(detail1);
			filterDetails.add(detail2);
		} else {
			SearchTypeDetail detail3 = new SearchTypeDetail("3", "ROKA");
			SearchTypeDetail detail4 = new SearchTypeDetail("4", "Dalberry");
			filterDetails.add(detail3);
			filterDetails.add(detail4);
		}

		return filterDetails;
	}
	
	@GetMapping("appraisals/search")
	public void getAppraisals() {
		/**
		 * @todo
		 */
		
	}

	@PostMapping("appraisals/")
	public ResponseEntity<Appraisal> addAppraisal(@RequestBody Appraisal appraisal) {
		HttpStatus status = HttpStatus.CREATED;
		Appraisal createdAppraisal = null;
		try {
			createdAppraisal = service.addAppraisal(appraisal);
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return new ResponseEntity<Appraisal>(createdAppraisal,status);
	}
	
	@GetMapping("appraisals/employees/{employee-id}")
	public ResponseEntity<ArrayList<Appraisal>> getAppraisalForEmployee(@PathVariable("employee-id") int employeeId, 
			@RequestParam("financial-year") int year, @RequestParam("month") int month) {
		ArrayList<Appraisal> appraisals = null;
		HttpStatus status = HttpStatus.OK;
		try {
			appraisals = service.getAppraisalForEmployee(employeeId, year, month);
			if (appraisals == null) {
				appraisals =  new ArrayList<>();
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return new ResponseEntity<ArrayList<Appraisal>>(appraisals,status);
	}
	
	@GetMapping("appraisals/employees/{employee-id}/reportees")
	public ResponseEntity<ArrayList<Appraisal>> getAppraisalForEmployeeReportees(@PathVariable("employee-id") int employeeId,
			@RequestParam("financial-year") int year, @RequestParam("month") int month) {
		ArrayList<Appraisal> appraisals = null;
		HttpStatus status = HttpStatus.OK;
		try {
			appraisals = service.getAppraisalForEmployeeReportees(employeeId, year, month);
			if (appraisals == null) {
				appraisals = new ArrayList<>();
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return new ResponseEntity<ArrayList<Appraisal>>(appraisals,status);
	}
	
	@GetMapping("appraisals/{appraisal-id}")
	public ResponseEntity<Appraisal> getAppraisal(@PathVariable("appraisal-id") int appraisalId) {
		Appraisal appraisal = null;
		HttpStatus status = HttpStatus.OK;
		try {
			appraisal = service.getAppraisal(appraisalId);
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return new ResponseEntity<Appraisal>(appraisal,status);
	}

	@PutMapping("appraisals/{appraisal-id}")
	public ResponseEntity<Appraisal> modifyAppraisal(@PathVariable("appraisal-id") int appraisalId, @RequestBody Appraisal appraisal) {
		HttpStatus status = HttpStatus.OK;
		try {
			service.modifyAppraisal(appraisalId, appraisal);
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return new ResponseEntity<Appraisal>(status);
	}
	
	@DeleteMapping("appraisals/{appraisal-id}")
	public void deleteAppraisal(@PathVariable("appraisal-id") int appraisalId) {
		
	}
	
	
}
