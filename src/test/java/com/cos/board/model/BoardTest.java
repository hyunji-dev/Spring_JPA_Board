package com.cos.board.model;
/*
 * 2020.10.12-2
 * 테스트하는 방법 
 */

import org.junit.jupiter.api.Test;

public class BoardTest {
	@Test // 함수별 테스트할 수 있음 
	public void 롬복_테스트() {
		System.out.println("===============");
		//1. Board 모델 
		Board board1 = new Board();
		board1.setId(1);
		board1.setTitle("제목1");
		System.out.println(board1);
		System.out.println("===============");
	}
}
