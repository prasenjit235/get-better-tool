package com.techmojo.hackathon.getbetter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.techmojo.hackathon.getbetter.model.Category;

public class CategoryMapper  implements RowMapper<Category>{

	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category category = new Category(
					rs.getInt("iCategoryID"),
					rs.getString("cCategoryName"),
					rs.getDate("dtCreatedOn"),
					rs.getDate("dtUpdatedOn"),
					rs.getInt("iStatus")
				);
		
		return category;
	}

}
