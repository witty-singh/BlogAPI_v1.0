package com.wittysingh.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wittysingh.app.Entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

	
}
