package com.lec.spring.domain.ajax;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lec.spring.domain.Review;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class QryReviewList extends QryResult{
	
	@JsonProperty("data") // JSON 으로 매핑될 property name 을 지정해줄 수 있음. list를 data라는 이름으로 보이게 함.
	List<Review> list; // 목록데이터
}
