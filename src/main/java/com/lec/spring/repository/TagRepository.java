package com.lec.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
