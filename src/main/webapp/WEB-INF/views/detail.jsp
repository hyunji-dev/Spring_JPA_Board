<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	2020.10.13-8
	선행: BoardSaveRequestDto.java
	후행: config 폴더 생성 
	ExceptionController.java
-->
<%@ include file="layout/header.jsp" %>

		<main>
			<h1>게시글 상세보기</h1>
			<hr />
			
			<table border="1">
				<tr>
					<td>ID</td>
					<td>TITLE</td>
					<td>CONTENT</td>
					<td>READCOUNT</td>
					<td>CREATEDATE</td>
				</tr>
				
				<tr>
					<td>${board.id }</td>
					<td><input id="title" type="text" value="${board.title }" /> </td>
					<td><input id="content" type="text" value="${board.content }" /></td>
					<td>${board.readCount }</td>
					<td>${board.createDate }</td>
				</tr>
				
			</table>
			
			<button onclick="deleteBoard(${board.id })" >삭제</button>
			<button onclick="updateBoard(${board.id })" >수정</button>
			
		</main>

	<script type="text/javascript">
		function deleteBoard(id) {
			fetch("/board/" + id, {
				method: "delete"
			}).then(res => res.text())
			.then(res => {
				if(res == "ok") {
					alert("삭제성공");
					location.href="/list";
				} else {
					alert("삭제실패");
				}
			});
		}

		function updateBoard(id) {
			let title_el = document.querySelector("#title");
			let content_el = document.querySelector("#content");

			let title = title_el.value;
			let content = content_el.value;
			
			console.log("id", id);
			console.log("title", title);
			console.log("content", content);

			// 자바스크립트 오브젝트 타입
			let board = {
				title: title,
				content: content
			};

			fetch("/board/" + id, {
				method: "put",
				headers: {
					'Content-Type': 'application/json; charset=UTF-8',
				},
				body: JSON.stringify(board)
			})
			.then(res => res.text())
			.then(res => {
				if(res == "ok") {
					alert("수정완료");
					location.reload(); // 새로고침
				} else {
					alert("수정실패");
				}	
			});

			console.log("board", board);			
		}
	</script>
<%@ include file="layout/footer.jsp" %>	