<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기</title>
</head>
<body>

<h1>공지사항 글쓰기</h1>
<hr>
<form action="insert?pageNum=${param.pageNum}" method="post" enctype="multipart/form-data">
	<input type="hidden" name="nt_num">
	<input type="hidden" name="mb_ID">
	<input type="hidden" name="nt_writeDate">
	<input type="text" name="nt_subject" placeholder="글 제목을 입력하세요."><br>
	<textarea name="nt_content" rows="10" cols="50" placeholder="글 내용을 입력하세요."></textarea><br>
	<input type="file" name="fileName"><br>
	<input type="submit" value="글쓰기">
	<input type="button" value="글목록" onclick="list?pageNum=${param.pageNum}">
</form>

</body>
</html>