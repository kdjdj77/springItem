package com.lec.spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "db_buy")
@DynamicInsert
@DynamicUpdate
public class Buy extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@ToString.Exclude
	private Item item;
	
	@ManyToOne
	@ToString.Exclude
	private User user;
	
	@ManyToOne
	@ToString.Exclude
	private Color color;
	
	@ManyToOne
	@ToString.Exclude
	private Size size;
	
	@ColumnDefault(value = "true")
	private Boolean isOrder; // true이면 주문, false이면 주문취소
	
	@ColumnDefault(value = "1")
	private Long count;
}
