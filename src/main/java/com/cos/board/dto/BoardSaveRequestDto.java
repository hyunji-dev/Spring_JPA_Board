package com.cos.board.dto;
/*
 * 2020.10.13-4
 * 선행: saveForm.jsp
 * 후행: list.jsp
 * 		jstl 가져오기 
 * 		<%@ taglib prefix="c"... list.jsp에 추가 
 */

import com.cos.board.model.Board;

import lombok.Data;

@Data
public class BoardSaveRequestDto {
	private String title, content;
	
	public static Board toEntity(BoardSaveRequestDto dto) { // static: 바로 호출할 수 있도록 해줌 
		Board board = Board.builder().title(dto.getTitle()).content(dto.getContent()).build();
		return board;
	}
	
}
