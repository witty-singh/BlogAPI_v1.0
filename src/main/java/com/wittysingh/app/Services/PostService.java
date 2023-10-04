package com.wittysingh.app.Services;

import java.util.List;

import com.wittysingh.app.Payloads.PostDto;
import com.wittysingh.app.Payloads.PostResponse;

public interface PostService {
	
	//CRUD
	PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);
	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy, String sortDir);
	PostDto getPostById(Integer postId);
	PostDto updatePost(PostDto postDto, Integer postId);
	void deletePost(Integer postId);
	
	PostResponse getPostByCategory(Integer categoryId,Integer pageNumber, Integer pageSize);
	PostResponse getPostByUser(Integer userId,Integer pageNumber, Integer pageSize);
	
	List<PostDto> searchByTitle(String keyword);
}
