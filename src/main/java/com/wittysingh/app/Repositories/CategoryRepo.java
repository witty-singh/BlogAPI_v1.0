package com.wittysingh.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wittysingh.app.Entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
