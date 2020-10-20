package com.cos.board.repository;
/*
 * 2020.10.13-1 
 * 선행: HomeController.java
 * 후행: BoardController.java
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.board.model.Board;

// 자동 IoC 등록됨(어노테이션 안붙여줘도 됨)  
public interface BoardRepository extends JpaRepository<Board, Integer> { // JpaRepository<니가 관리하고 있는 모델명, P.K의 타입>
	// Naming Query: 간단한 쿼리문의 경우 사용하기 좋지만, 쿼리 알면 굳이 이렇게 쓰지 말고 NativeQuery를 사용할 것 
	
	// SELECT * FROM BOARD WHERE TITLE = ?1 -> 쿼리를 날려줌, ?에 String title이 들어감  
	// Board객체를 리턴받는 레파지토리 
	Board findByTitle(String title); // 규칙: findBy컬럼명(컬럼명이 커멜처럼 꺽여야함)
	
	// SELECT * FROM BOARD WHERE TITLE = ?1 AND CONTENT = ?2
	Board findByTitleAndContent(String title, String content); 
	
	// NativeQuery
	// 여기서는 public abstract가 생략되어 있음
	@Query(value = "SELECT * FROM BOARD WHERE ID = :id", nativeQuery = true)
	Board mFindById(int id); //m: my의 약자, 내가 만든 함수라고 표시해두는 것
	
	@Modifying // 이 어노테이션이 있어야 내가 작성한 NativeQuery가 수정, 삭제, 저장됨
	@Query(value = "DELETE FROM BOARD WHERE ID = :id", nativeQuery = true) // 이렇게 하면 삭제 안됨 
	int mDeleteById(int id); // int로 받는건, 1보다 크면 정상동작이니까 그 값을 받아줌 
}
