package com.lec.spring.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Item;
import com.lec.spring.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByItem(Item itemId, Sort sort);
}
