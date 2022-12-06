package com.lec.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.spring.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	// 로그인한 사용자 username 으로 찾기
	User findByUsername(String username);

	List<User> findByIsvalid(boolean b);
}
