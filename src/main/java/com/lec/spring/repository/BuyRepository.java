package com.lec.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Buy;

public interface BuyRepository extends JpaRepository<Buy, Long> {

}
