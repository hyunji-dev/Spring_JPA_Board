package com.cos.board.model;
/*
 * 2020.10.12-1
 * 선행: main\java\com\cos\board\model 폴더 생성 
 * 후행: BoardTest.java
 */
import java.sql.Timestamp;
import java.text.Format;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Data, @Getter, @Setter, @AllArgsConstructor, @NoArgsConstructor, @Builder, @Entity: lombok 관련 어노테이션 
@Data //-> getter, setter가 합쳐져 있음 + toString()도 구현되어 있음  
//@Getter
//@Setter
@AllArgsConstructor // 파라메터 전부를 가진 생성자
@NoArgsConstructor // 디폴트 생성자 
@Builder // 빌더 패턴 
@Entity // ORM(오브젝트 릴레이션 맵핑) 
// ORM 규칙: P.K를 지정해줘야 한다. 
public class Board {
	@Id //P.K라고 알려줌 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 DB 번호증가 전략을 따라감
	private int id;
	
	private String title, content;
	private int readCount;
	
	
	@CreationTimestamp // 데이터 입력 시 자동으로 now()값이 들어감 
	private Timestamp createDate;
	
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	//public Timestamp getCreateDate() {
		
	//	return createDate;
	//}
	
	//public Timestamp getCreateDate() {
		//return Timestamp.valueOf(createDate.toString().substring(0, 10));
	//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//	Timestamp format_createDate = createDate;
	//	return format_createDate;
	//}
}
