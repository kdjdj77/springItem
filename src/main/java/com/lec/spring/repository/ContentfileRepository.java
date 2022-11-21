package com.lec.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Contentfile;

public interface ContentfileRepository extends JpaRepository<Contentfile, Long> {

}
