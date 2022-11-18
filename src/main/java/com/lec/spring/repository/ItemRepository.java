package com.lec.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
