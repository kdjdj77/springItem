package com.lec.spring.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@Entity(name = "db_category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy ="category" , cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Tag> tags = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy ="category" , cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Item> items = new ArrayList<>();
	public List<Item> getItems() {
		List<Item> result = new ArrayList<>();
		for(Item c : this.items) if (c.getIsvalid()) result.add(c);
		return result;
	}
}
