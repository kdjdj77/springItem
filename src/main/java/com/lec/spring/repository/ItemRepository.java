package com.lec.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	Page<Item> findByIsvalidAndNameContaining(boolean b, String search, PageRequest of);
	
}
