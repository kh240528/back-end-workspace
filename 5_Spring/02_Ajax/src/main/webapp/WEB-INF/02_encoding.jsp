<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
	<h2>Encoding 처리하기</h2>
	닉네임 : <input type="text" id="nick">
	<input type="button" value="닉네임 보내기" id="btn">
	<div id="result"></div>
	
	<!-- 자바스크립트 방식 -->
	<!-- get 방식으로 /encoding <-- 닉네임 보내서 : 요청
		해당 닉네임 받아서 result에 보여주기 
	 -->
	<!-- <script>
	 	let xhr;
	 	function startRequest() {
	 		const nickname = document.querySelector("#nick").value;
	 		xhr = new XMLHttpRequest();
	 		xhr.onreadystatechange = callback;
	 		xhr.open('get', '/encoding?nickname=' + encodeURI(encodeURIComponent(nickname)));
	 		xhr.send(null);
	 	}
	 	function callback() {
	 		if(xhr.readyState === 4) {
	 			if(xhr.status === 200) {
	 				const result = xhr.responseText;
	 				document.querySelector("#result").innerText = decodeURI(decodeURIComponent(result));
	 			}
	 		}
	 	}
	 </script>  -->
	 
	 <!-- jQuery -->
	 <script>
	 	$("#btn").click(() => {
	 		const nickname = $("#nick").val();
	 		$.ajax({
	 			// 요청
	 			type: "get",
	 			url: "/encoding",
	 			data: "nickname=" + encodeURIComponent(nickname),
	 			
	 			// 응답
	 			success: function(result) {
	 				$("#result").text(result);
	 			}
	 		});
	 	});
	 </script>
</body>
</html>









