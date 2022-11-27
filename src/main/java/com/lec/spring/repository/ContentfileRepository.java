package com.lec.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Contentfile;
import com.lec.spring.domain.Item;

public interface ContentfileRepository extends JpaRepository<Contentfile, Long> {
	List<Contentfile> findByItem(Item item);
}
