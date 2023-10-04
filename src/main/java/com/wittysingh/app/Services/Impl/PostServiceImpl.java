package com.wittysingh.app.Services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.wittysingh.app.Entities.Category;
import com.wittysingh.app.Entities.Post;
import com.wittysingh.app.Entities.User;
import com.wittysingh.app.Exceptions.ResourceNotFoundException;
import com.wittysingh.app.Payloads.PostDto;
import com.wittysingh.app.Payloads.PostResponse;
import com.wittysingh.app.Repositories.CategoryRepo;
import com.wittysingh.app.Repositories.CommentRepo;
import com.wittysingh.app.Repositories.PostRepo;
import com.wittysingh.app.Repositories.UserRepo;
import com.wittysingh.app.Services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {
		
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id", userId));
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		
		Post post=modelMapper.map(postDto, Post.class);
		post.setAddedDate(new Date());
		post.setImage("default.png");
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=postRepo.save(post);
		return modelMapper.map(newPost, PostDto.class);
		
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {
		
		Sort sort=(sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Pageable p=PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePosts=postRepo.findAll(p);
		List<Post> posts=pagePosts.getContent();
		List<PostDto> postDtos=posts.stream()
				.map((post)-> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse pr=new PostResponse();
		pr.setContent(postDtos);
		pr.setPageNumber(pageNumber);
		pr.setTotalElements(pagePosts.getTotalElements());
		pr.setTotalPages(pagePosts.getTotalPages());
		pr.setPageSize(pageSize);
		pr.setLastPage(pagePosts.isLast());
		return pr;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
		PostDto postDto=modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
		post.setAddedDate(postDto.getAddedDate());
		post.setCategory(modelMapper.map(postDto.getCategory(),Category.class));
		post.setContent(postDto.getContent());
		post.setImage(postDto.getImage());
		post.setTitle(postDto.getTitle());
		post.setUser(modelMapper.map(postDto.getUser(),User.class));
		
		postRepo.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		postRepo.delete(post);
		
	}

	@Override
	public PostResponse getPostByCategory(Integer categoryId,Integer pageNumber, Integer pageSize) {
		
		
		Category cat=categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));

		Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePosts=postRepo.findByCategory(cat,p);
		List<Post> lists=pagePosts.getContent();
		List<PostDto> postDtos=lists.stream()
				.map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse pr=new PostResponse();
		pr.setContent(postDtos);
		pr.setLastPage(pagePosts.isLast());
		pr.setPageNumber(pageNumber);
		pr.setPageSize(pageSize);
		pr.setTotalElements(pagePosts.getTotalElements());
		pr.setTotalPages(pagePosts.getTotalPages());
		return pr;
	}

	@Override
	public PostResponse getPostByUser(Integer userId,Integer pageNumber, Integer pageSize) {
		User user=userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id", userId));
		
		Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePosts=postRepo.findByUser(user,p);
		List<Post> posts=pagePosts.getContent();
		List<PostDto> postDtos=posts.stream()
				.map((post)-> modelMapper.map(post,PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse pr=new PostResponse();
		pr.setContent(postDtos);
		pr.setLastPage(pagePosts.isLast());
		pr.setPageNumber(pageNumber);
		pr.setPageSize(pageSize);
		pr.setTotalElements(pagePosts.getTotalElements());
		pr.setTotalPages(pagePosts.getTotalPages());
		
		return pr;
	}

	@Override
	public List<PostDto> searchByTitle(String keyword) {
		List<Post> posts=postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos=posts.stream()
				.map((post)-> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	
}
