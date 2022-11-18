package com.lec.spring.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.item.Buy;

public interface BuyRepository extends JpaRepository<Buy, Long> {

}
