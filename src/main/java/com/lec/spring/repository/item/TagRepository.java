package com.lec.spring.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.item.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
