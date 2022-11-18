package com.lec.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Size;

public interface SizeRepository extends JpaRepository<Size, Long> {

}
