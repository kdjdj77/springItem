package com.lec.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
