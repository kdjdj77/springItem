package com.lec.spring.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.item.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
