package com.lec.spring.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.item.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {

}
