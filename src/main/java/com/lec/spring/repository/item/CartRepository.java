package com.lec.spring.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.item.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
