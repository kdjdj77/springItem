package com.lec.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {

}
