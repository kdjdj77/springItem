package com.lec.spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "db_authority")
public class Authority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // PK
	
	@Column(length = 40, nullable = false, unique = true) // length 옵션 안주면 varchar 기본 255자
	private String name; // 권한명 ex) "ROLE_MEMBER", "ROLE_ADMIN" ...
	
}
