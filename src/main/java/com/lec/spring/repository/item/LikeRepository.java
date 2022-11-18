package com.lec.spring.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.item.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
