package com.cos.board.repository;
/*
 * 2020.10.13-1 
 * 선행: HomeController.java
 * 후행: BoardController.java
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.board.model.Board;

// 자동 IoC 등록됨(어노테이션 안붙여줘도 됨)  
public interface BoardRepository extends JpaRepository<Board, Integer>{ // JpaRepository<니가 관리하고 있는 모델명, P.K의 타입>

}
