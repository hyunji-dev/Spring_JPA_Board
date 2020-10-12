package com.cos.board.model;
/*
 * 2020.10.12-1
 */
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data //-> getter, setter가 합쳐져 있음 + toString()도 구현되어 있음  
//@Getter
//@Setter
@AllArgsConstructor // 파라메터 전부를 가진 생성자
@NoArgsConstructor // 디폴트 생성자 
@Builder // 빌더 패턴 
public class Board {
	private int id;
	private String title, content;
	private int readCount;
	private Timestamp createDate;
}
