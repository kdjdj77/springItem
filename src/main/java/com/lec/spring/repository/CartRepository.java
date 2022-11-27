package com.lec.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Cart;
import com.lec.spring.domain.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
	List<Cart> findByUser(User u);
}
