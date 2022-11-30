package com.lec.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Category;
import com.lec.spring.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

	List<Tag> findByCategory(Category cat);
}
