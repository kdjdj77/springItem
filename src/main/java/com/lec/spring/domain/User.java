package com.lec.spring.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@EqualsAndHashCode(callSuper = true)
@Entity(name = "db_user")
@DynamicInsert
@DynamicUpdate
public class User extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // PK
	
	@Column(unique = true, nullable = false) // 유니크, 널허용 X
	private String username; // 회원 id
	@Column(nullable = false)
	private String password; // 회원 pw
	@Column(nullable = false)
	private String name; // 회원 이름
	@Column(nullable = false)
	private String phonenum;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String address;
	@ColumnDefault(value="true")
	private Boolean isvalid; // true이면 회원, false이면 탈퇴한 회원
	@ColumnDefault(value="0")
	private Long point;
	@Transient
	private String provider;
	
	@OneToMany(mappedBy ="user" , cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Buy> buies = new ArrayList<>();
	
	@OneToMany(mappedBy ="user" , cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Cart> carts = new ArrayList<>();
	
	@OneToMany(mappedBy ="user" , cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();
	
	@OneToMany(mappedBy ="user" , cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Like> likes = new ArrayList<>();

	
	@Transient // javax.persistence : DB에는 반영안하는 필드
	@ToString.Exclude
	private String re_password; // 바인딩만 하기위한 필드로 만듬.
	
	@ManyToMany(fetch = FetchType.EAGER)
	@ToString.Exclude
	@Builder.Default
	private List<Authority> authorities = new ArrayList<>(); // NPE 방지 (Null Point Exception)
	
	public void addAuthority(Authority... authorities) {
		if(authorities != null) {
			Collections.addAll(this.authorities, authorities);
		}
	}
}
