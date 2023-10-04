package com.wittysingh.app.Repositories;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wittysingh.app.Entities.Category;
import com.wittysingh.app.Entities.Post;
import com.wittysingh.app.Entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	Page<Post> findByCategory(Category cat,Pageable p);

	Page<Post> findByUser(User user,Pageable p);

	List<Post> findByTitleContaining(String keyword);
}
