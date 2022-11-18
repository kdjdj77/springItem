package com.lec.spring.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QryReviewList extends QryResult{
	
	@JsonProperty("data") // JSON 으로 매핑될 property name 을 지정해줄 수 있음. list를 data라는 이름으로 보이게 함.
	List<Review> list; // 목록데이터
}
