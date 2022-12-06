package com.lec.spring.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

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
@Entity(name = "db_item")
@DynamicInsert
@DynamicUpdate
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	@ColumnDefault(value = "true")
	private Boolean isvalid; // false 이면 판매중지, true 이면 판매중
	@Column(nullable = false)
	private Double discount;
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private Long price;
	@Column(nullable = false)
	private Long stock;
	@ColumnDefault(value = "0")
	private Long reviewcnt;
	@ColumnDefault(value = "0")
	private Double avgstar;
	@ColumnDefault(value = "0")
	private	Long sell;
	@ColumnDefault(value = "0")
	private	Long likecnt;
	
	@Transient
	private Long discountPrice;
	
	@ManyToOne
	@ToString.Exclude
	private Tag tag;
	
	@ManyToOne
	@ToString.Exclude
	private Category category;
	
	public Long getDiscountPrice() {
		Long a = Math.round(this.price - (this.price * this.discount / 100));
		
		return a - (a%100); 
	}
	
	@OneToMany(mappedBy ="item" , cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private List<Color> colors = new ArrayList<>();
	public List<Color> getColors() {
		List<Color> result = new ArrayList<>();
		for(Color c : this.colors) if (c.getIsvalid()) result.add(c);
		return result;
	}
	
	@OneToMany(mappedBy ="item" , cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private List<Size> sizes = new ArrayList<>();
	public List<Size> getSizes() {
		List<Size> result = new ArrayList<>();
		for(Size c : this.sizes) if (c.getIsvalid()) result.add(c);
		return result;
	}
	
	@OneToMany(mappedBy ="item" , cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Itemfile> itemfiles = new ArrayList<>();
	
	@OneToMany(mappedBy ="item" , cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Contentfile> contentfiles = new ArrayList<>();
	
	@OneToMany(mappedBy ="item" , cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();
	
	public void addItemFiles(Itemfile... files) {  // xxxToMany 의 경우 만들어두면 편리
		if(files != null) {
			Collections.addAll(this.itemfiles, files);
		}
	}
	public void addContentFiles(Contentfile... files) {  // xxxToMany 의 경우 만들어두면 편리
		if(files != null) {
			Collections.addAll(this.contentfiles, files);
		}
	}
}
