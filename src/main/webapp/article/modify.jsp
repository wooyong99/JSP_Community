<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.jwy.exam.dto.Article" %>

<%
  Article article = (Article) request.getAttribute("article");
%>

<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>게시글 수정</title>
</head>
<body>
<h1>게시글 수정</h1>
<form action="doModify" method="POST">
  <input type="hidden" name="id" value="${param.id}">
  <div>번호 : ${param.id}</div>
  <div>생성 날짜 : <%= article.regDate%></div>
  <label>제목 : <input type="text" name="title" placeholder="제목을 입력해주세요." value="<%= article.title%>"></label><br>
  <label>내용 : <textarea type="text" name="body" placeholder="내용을 입력해주세요." cols="40" rows="10"><%= article.body%>></textarea></label><br>
  <button type="submit">작성</button>
</form>
<a href="list">리스트 페이지로</a>
</body>
</html>