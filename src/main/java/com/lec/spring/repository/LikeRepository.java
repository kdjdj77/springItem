package com.lec.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Item;
import com.lec.spring.domain.Like;
import com.lec.spring.domain.User;

public interface LikeRepository extends JpaRepository<Like, Long> {

	List<Like> findByUser(User u);

	Like findByUserAndItem(User u, Item i);

}
