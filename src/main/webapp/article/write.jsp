<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>게시글 작성하기</title>
</head>
<body>
<h1>게시글 작성하기</h1>
<form action="doWrite" method="POST">
  <label for="title">제목 : </label>
  <input type="text" name="title" id="title" placeholder="제목을 입력해주세요."><br>
  <div>내용</div>
  <textarea name="body" id="body" cols="30" rows="10" placeholder="내용을 입력해주세요"></textarea>
  <br>
  <input type="submit" value="작성완료">
  <input type="reset" value="초기화">
</form>
<button onclick="location.href='list'">리스트로 돌아가기</button>

</body>
</html>