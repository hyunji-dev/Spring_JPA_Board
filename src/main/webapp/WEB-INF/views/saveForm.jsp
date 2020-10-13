<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	2020.10.13-3
	선행: BoardController.java
	후행: BoardSaveRequestDto.java
-->
<%@ include file="layout/header.jsp" %>	

	<main>
		<h1>글쓰기 페이지</h1>
		<hr />
		<form action="/save" method="post">
			<input type="text" name="title" />
			<input type="text" name="content" />
			<button>글쓰기 완료</button>
		</form>
	</main>
	
<%@ include file="layout/footer.jsp" %>	