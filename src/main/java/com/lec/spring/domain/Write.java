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
@Entity(name="t6_write")
@DynamicInsert // INSERT 동작 시 null 인 필드는 SQL문에서 제외
@DynamicUpdate // UPDATE 동작 시 null 인 필드는 SQL문에서 제외
public class Write extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private Long id; // 글의 id (PK)
	
	@Column(nullable = false)
	private String subject; // 글 제목
	@Column(nullable = false)
	private String content; // 글 내용
	
	@ColumnDefault(value = "0") // 컬럼의 디폴트 값이 0 으로 들어간다
	private int viewCnt; // 글 조회수 , 프리미티브 타입으로 넣으면 not null 이 붙는다. DB 결과창 보면 암
	
	// Write : User = N:1
	@ManyToOne
	@ToString.Exclude
	private User user;
	
	@OneToMany(mappedBy = "write", cascade = CascadeType.ALL) // 삭제등의 동작 발생 시 child도 함께 삭제됨.
	@ToString.Exclude
	@Builder.Default
	private List<Comment> comments = new ArrayList<>();
	
	@OneToMany(mappedBy = "write", cascade = CascadeType.ALL)
	@ToString.Exclude
	@Builder.Default  // 아래와 같이 초깃값 있으면 @Builder.Default 처리  (builder 에서 제공안함)
	private List<FileDTO> files = new ArrayList<>();  // NPE 방지

	public void addFiles(FileDTO... files) {  // xxxToMany 의 경우 만들어두면 편리
		if(files != null) {
			Collections.addAll(this.files, files);
		}
	}

}
