package com.cos.board.service;
/*
 * 2020.10.14-1 
 * 선행: detail.jsp
 * 		service폴더 생성 후 BoardService.java 생성함..
 * 후행: 
 */

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.board.dto.BoardSaveRequestDto;
import com.cos.board.model.Board;
import com.cos.board.repository.BoardRepository;

@Service
public class BoardService {
	
	// 의존성 주입 DI
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional // DML(데이터조작어: update, delete, insert)이면 걸어두는 습관을 들여야함 
	public void 글쓰기(BoardSaveRequestDto dto) {
		Board boardEntity = BoardSaveRequestDto.toEntity(dto);
		boardRepository.save(boardEntity);
		
		// 리절트셋 받을 게 없으니까 그냥 이대로 둬도 됨.. void
	}
	
	// select 하는데 트랜잭션이 필요한 이유는? 
	@Transactional(readOnly = true)
	public List<Board> 글목록보기() {
		return boardRepository.findAll(); // board테이블 목록 가져옴
		// 리턴받을 게 있음(목록) -> List<<Board> 로 받아주면 됨 
	}
	
	// RuntimeException 걸려있어야 rollback 할 수 있고, try catch문 걸지 않아도 됨 
	// 서비스에서는 트랙잭션 처리를 하니까... save를 2개 이상할 때 rollback이 꼭 필요함 
	@Transactional
	public Board 글상세보기(int id) throws Exception {
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("ID값 잘못들어왔어요"));
		
		// 더티체킹으로 조회수 증가
		board.setReadCount(board.getReadCount() + 1);
		return board;
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id); // 글 하나 삭제 
	}
	
	@Transactional
	public void 글수정하기(int id, BoardSaveRequestDto dto) {
		// 방법1. 
		// 문제점: 받아오지 않은 값들(readCount, createDate)이 null값이 넘어와서 기존 값들이 날라감
		// 애초에 사용자에게 전제 값을 다 받아오지 않는 이상 이 방법을 사용하지 않음  
		//Board boardEntity = BoardSaveRequestDto.toEntity(dto);
		//boardEntity.setId(id);
		//boardRepository.save(boardEntity);
		
		// 방법2. 더티 체킹: 영속성 컨텍스트의 오브젝트의 변경을 감지하여 update 한다. 
		Board boardEntity = boardRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("ID값 잘못들어왔어요"));
		boardEntity.setTitle(dto.getTitle());
		boardEntity.setContent(dto.getContent());
	}
}
