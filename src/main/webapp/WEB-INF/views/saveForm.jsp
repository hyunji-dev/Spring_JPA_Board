<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	2020.10.13-3
	선행: BoardController.java
	후행: BoardSaveRequestDto.java
-->

<!-- 썸머노트 -->
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	
	<!-- include summernote css/js-->
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
<!-- /썸머노트 끝 -->

<%@ include file="layout/header.jsp" %>	

	<main>
		<h1>글쓰기 페이지</h1>
		<hr />
		
			<h3>제목 <input id="title" type="text" name="title" /></h3>
			<hr />
			<textarea id="content" name="content" ></textarea>
			<button onclick="saveBoard()">글쓰기 완료</button>
		
	</main>
	
	<script type="text/javascript">
		// 썸머노트 스타일 지정
		$(document).ready(function() {
		     $('#content').summernote({
		             height: 300,                 // set editor height
		             minHeight: null,             // set minimum height of editor
		             maxHeight: null,             // set maximum height of editor
		             focus: true                  // set focus to editable area after initializing summernote
		     });
		});

		// 글쓰기완료
		function saveBoard() {
			let title = document.querySelector("#title").value;
			let content = document.querySelector("#content").value;

			let board = {
				title: title,
				content: content
			};

			fetch("/save", {
				method: "post",
				headers:  {
					'Content-Type': 'application/json; charset=UTF-8',
				},
				body: JSON.stringify(board)
			})
			.then(res => res.text())
			.then(res => {
				if(res == "ok") {
					alert(res);
				} else {
					alert("ㅠㅠ");
				}	
			});
		}
			
	</script>
	
<%@ include file="layout/footer.jsp" %>	