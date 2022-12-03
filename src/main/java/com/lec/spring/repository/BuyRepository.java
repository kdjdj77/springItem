package com.lec.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Buy;
import com.lec.spring.domain.User;

public interface BuyRepository extends JpaRepository<Buy, Long> {
	List<Buy> findByUser(User u);
}
