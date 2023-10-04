package com.wittysingh.app.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wittysingh.app.Payloads.ApiResponse;
import com.wittysingh.app.Payloads.CategoryDto;
import com.wittysingh.app.Services.Impl.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired 
	CategoryServiceImpl categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto cat=categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(cat,HttpStatus.CREATED);
	}
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@Valid @PathVariable Integer catId){
		CategoryDto cat= categoryService.getCategoryById(catId);
		return new ResponseEntity<CategoryDto>(cat, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> categories=categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(categories, HttpStatus.OK);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
		CategoryDto cat=categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(cat, HttpStatus.OK);
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
		categoryService.deleteCategory(catId);
		return new ResponseEntity<>(new ApiResponse("Category is deleted successfully !!", true),HttpStatus.OK);
	}
	
}
