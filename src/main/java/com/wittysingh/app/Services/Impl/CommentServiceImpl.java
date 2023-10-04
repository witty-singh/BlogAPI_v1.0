package com.wittysingh.app.Services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittysingh.app.Entities.Comment;
import com.wittysingh.app.Entities.Post;
import com.wittysingh.app.Entities.User;
import com.wittysingh.app.Exceptions.ResourceNotFoundException;
import com.wittysingh.app.Payloads.CommentDto;
import com.wittysingh.app.Repositories.CommentRepo;
import com.wittysingh.app.Repositories.PostRepo;
import com.wittysingh.app.Repositories.UserRepo;
import com.wittysingh.app.Services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
		
		Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		Comment cmnt=modelMapper.map(commentDto, Comment.class);
		cmnt.setPost(post);
		cmnt.setUser(user);
		Comment savedComment = commentRepo.save(cmnt);
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void delete(Integer commentId) {
		Comment cmnt=commentRepo.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("Comment", "Id", commentId));
		commentRepo.delete(cmnt);
		
	}

}
