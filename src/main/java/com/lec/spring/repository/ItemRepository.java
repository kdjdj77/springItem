package com.lec.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Item;
import com.lec.spring.domain.User;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
}
