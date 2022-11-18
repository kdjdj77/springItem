package com.lec.spring.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.item.Size;

public interface SizeRepository extends JpaRepository<Size, Long> {

}
