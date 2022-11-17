package com.lec.spring.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
