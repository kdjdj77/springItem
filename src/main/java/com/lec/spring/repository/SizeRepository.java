package com.lec.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Item;
import com.lec.spring.domain.Size;

public interface SizeRepository extends JpaRepository<Size, Long> {

	List<Size> findByItem(Item item);
}
