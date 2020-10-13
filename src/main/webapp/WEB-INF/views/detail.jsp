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
					<td>${board.title }</td>
					<td>${board.content }</td>
					<td>${board.readCount }</td>
					<td>${board.createDate }</td>
				</tr>
				
			</table>
			
			<button onclick="deleteBoard(${board.id })">삭제</button>
			<button>수정</button>
			
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
		
	</script>
<%@ include file="layout/footer.jsp" %>	