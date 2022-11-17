package com.lec.spring.domain.item;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class File {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
