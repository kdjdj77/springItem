package com.lec.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
