package com.cos.board.model;
/*
 * 2020.10.12-2
 * 테스트하는 방법 
 * 실행: 컨트롤 + F11
 * 선행: Board.java
 * 후행: application.yml 파일 
 * 		pom.xml에 tomcat-embed-jasper 라이브러리 추가
 * 		src 폴더 안에 \main\webapp\WEB-INF\views 폴더 생성 후 home.jsp 파일 생성 
 * 		main\java\com\cos\board\controller 폴더 생성 후 HomeController.java 파일 생성 
 */

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class BoardTest {
	@Test // 함수별 테스트할 수 있음 
	public void 롬복_테스트() {
		System.out.println("===============");
		// 1. Board 모델, 디폴트 생성자로
		Board board1 = new Board();
		board1.setId(1);
		board1.setTitle("제목1");
		System.out.println(board1); // 이렇게 오브젝트를 던지면 toString() 실행됨
		
		// 2. Board 모델, 파라메타 다 받는 생성자로 
		Board board2 = new Board(
				2,
				"제목2",
				"내용2",
				0,
				Timestamp.valueOf(LocalDateTime.now()) // LocalDate를 Timestamp로
			);
		
		System.out.println(board2);
		
		// 경우: id는 추가 안하고 싶음 
		// 경우: 파라메타 넣는 순서가 헷갈림 
		// 해결방법: 빌더패턴 사용 -> 공부해보기.. 패턴같은 건 공부할 가치가 있음
		// 생성자 말고 빌더 패턴을 사용하는 게 좋다 
		Board board3 = Board.builder().title("제목3").content("내용3").build();
		System.out.println(board3);
		System.out.println("===============");
		
		String date = LocalDate.now().toString();
		System.out.println(date); // 2020-10-12
		
		String time = LocalDateTime.now().toString();
		System.out.println(time); // 2020-10-12T10:17:02.191
		
		// DB에 날짜시간 넣을 때 이런 식으로 넣으면 됨 
		Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
		System.out.println(ts); // 2020-10-12 10:17:02.191
	}
}
