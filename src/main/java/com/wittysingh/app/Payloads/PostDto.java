package com.wittysingh.app.Payloads;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.wittysingh.app.Entities.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	private Integer postId;

	private String title;
	
	private String content;
	
	private String image;
	
	private Date addedDate;
	
	private UserDto user;
	
	private CategoryDto category;
	
	private Set<Comment> comments=new HashSet<>();
}
