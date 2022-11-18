package com.lec.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
