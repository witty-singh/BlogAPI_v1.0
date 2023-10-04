package com.wittysingh.app.Services;

import com.wittysingh.app.Payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);
	void delete(Integer commentId);
}
