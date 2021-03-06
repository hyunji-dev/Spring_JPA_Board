package com.cos.board.controller;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
/*
 * 2020.10.13-2
 * 선행: BoardRepository.java
 * 후행: saveForm.jsp
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.board.config.ex.MyArgsNotFound;
import com.cos.board.dto.BoardSaveRequestDto;
import com.cos.board.model.Board;
import com.cos.board.repository.BoardRepository;
import com.cos.board.service.BoardService;

@Controller // 파일 리턴
//@RequestMapping("/board") // 이 주소로 들어옴 // 주소에는 명사만 넣고 동사를 넣지 말 것!
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// http://localhost:8000/board/saveForm : RequestMapping 존재 시 
	// http://localhost:8000/saveForm
	@GetMapping("/saveForm")
	public String writeForm() {
		return "saveForm"; // saveForm.jsp 파일리턴함  
	}
	
	@PostMapping("/save")
	@ResponseBody
	public String save(@RequestBody BoardSaveRequestDto dto) {
		System.out.println(dto);
		
//		Board boardEntity = BoardSaveRequestDto.toEntity(dto);
//		boardRepository.save(boardEntity);
		boardService.글쓰기(dto);
		return "ok"; // 그냥 list로 때리면 목록을 안가지고 가니까 redirect:/ 해줘야 밑에
	}
	
	// @PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable : 5개씩 페이징
	// 스프링에서 Controller의 메서드의 파라메터부분은 자동 DI가 된다. 
	@GetMapping({"", "/", "/list"})
	public String list(Model model, @PageableDefault(size = 4, sort = "id", direction = Direction.DESC) Pageable pageable) { 
//		List<Board> boards = boardRepository.findAll(); // board테이블 목록 가져옴
		model.addAttribute("boards", boardService.글목록보기(pageable));
		return "list";
	}
	
	// http://localhost:8000/list/test/?page=1
	// 페이징 테스트용 -> 제이슨으로 된 데이터 원형을 볼 수 있음 
	// @PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable : 페이징
	@GetMapping("/list/test")
	@ResponseBody
	public Page<Board> listTest(@PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) { 
		return boardService.글목록보기(pageable);
	}
	
	@GetMapping("/board/{id}")
	public String detail(@PathVariable int id, Model model) throws Exception { // @PathVariable: 파라메터 받아줌
		//// Optional<Board>: null값 처리, 안에 Board 자체는 null일 수는 있음 
		//// Optional은 P.K로 찾을 시 못찾을 때 처리할 수 있는 하나의 방법 
		//Optional<Board> opBoard = boardRepository.findById(id); // 게시글 하나 가져오기
		
		//// 못찾으면 opBoard.get() 자체가 아예 오류라서 이렇게 사용해선 안됨 
		//if(opBoard.get() == null) {
		//	System.out.println("못찾았어요"); 
		//} else {
		//	System.out.println("찾았어요"); 
		//}
		
		// null 처리하는 방법 1.빈 객체를 던져줌 
		//Board board = boardRepository.findById(id).orElse(new Board());
		
		//// 2.다른 일을 할 수 있는 함수 넣어줌 -> 자바에서는 함수 못넣으니까 인터페이스를 넣어줌 익명클래스 
		//Board board = boardRepository.findById(id).orElseGet(new Supplier<Board>() {
		//	@Override
		//	public Board get() {	
		//		// TODO Auto-generated method stub
		//		System.out.println("못찾았으면 어떻게 할지 여기다 기술");
		//		return new Board();
		//	}
		//});
		//// 위에를 이렇게 줄일 수 있음 
		//Board board = boardRepository.findById(id).orElseGet(() -> new Board());


//		Board board = boardRepository.findById(id).orElseThrow(new Supplier<Exception>() { 
//			// Supplier<익셉션 타입이 들어감>
//			@Override
//			public Exception get() {
//				// TODO Auto-generated method stub
//				return new Exception("ID값 잘못들어왔어요");
//			}
//		});
		
		model.addAttribute("board", boardService.글상세보기(id));
		return "detail";
	}
	
	@DeleteMapping("/board/{id}")
	@ResponseBody // 데이터 응답으로 변경함 
	public String delete(@PathVariable int id) {
//		boardRepository.deleteById(id); // 글 하나 삭제 
		boardService.글삭제하기(id);
		return "ok";
	}
	
	@PutMapping("/board/{id}")
	@ResponseBody // 데이터 응답으로 변경함 
	public String update(@PathVariable int id, @RequestBody BoardSaveRequestDto dto) {
		boardService.글수정하기(id, dto);
		return "ok";
	}
}
