package com.wittysingh.app.Services;

import java.util.List;

import com.wittysingh.app.Payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto getCategoryById(Integer categoryId);
	List<CategoryDto> getAllCategory();
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	void deleteCategory(Integer categoryId);
}
