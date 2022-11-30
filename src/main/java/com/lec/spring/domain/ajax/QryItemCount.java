//댓글의 목록을 담기 위한 용도
package com.lec.spring.domain.ajax;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lec.spring.domain.Cart;
import com.lec.spring.domain.Tag;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class QryItemCount extends QryResult{
	@JsonProperty("data") //Json으로 매핑될 property name
	Long data; //목록데이터
}