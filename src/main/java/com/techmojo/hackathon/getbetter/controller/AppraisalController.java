package com.techmojo.hackathon.getbetter.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.techmojo.hackathon.getbetter.model.SearchFilterType;
import com.techmojo.hackathon.getbetter.model.SearchTypeDetail;

@RestController
public class AppraisalController {

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
}
