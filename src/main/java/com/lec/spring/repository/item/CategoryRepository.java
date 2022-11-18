package com.lec.spring.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.item.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
