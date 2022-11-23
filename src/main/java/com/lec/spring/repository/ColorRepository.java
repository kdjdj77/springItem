package com.lec.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Color;
import com.lec.spring.domain.Item;

public interface ColorRepository extends JpaRepository<Color, Long> {

	List<Color> findByItem(Item item);

}
