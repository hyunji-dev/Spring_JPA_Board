package com.cos.board.config;
/*
 * 2020.10.13-9
 * exception을 관리하는 컨트롤러 클래스 
 * 
 * 선행: deatail.jsp
 * 후행: ex폴더 생성 
 * 		MyArgsNotFound.java
 */

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.board.config.ex.MyArgsNotFound;

@ControllerAdvice // 프로젝트 전체를 관리 
@RestController // 데이터 응답 
public class ExceptionController {

	@ExceptionHandler(value=Exception.class) // IllegalArgumentException 이 터지면 자동으로 얘를 찾음
	public String 모든익셉션(Exception e) {
		return e.getMessage();
	}
}
