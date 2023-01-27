<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>

<%
  Map<String,Object> articleRow = (Map<String,Object>)request.getAttribute("articleRow");
%>
<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>게시물 상세보기</title>
</head>
<body>
  <h1>게시물 상세보기</h1>
  <ul>
    <li>번호 : <%= articleRow.get("id")%></li>
    <li>생성날짜 : <%= articleRow.get("regDate")%></li>
    <li>수정날짜 : <%= articleRow.get("updateDate")%></li>
    <li>제목 : <%= articleRow.get("title")%></li>
    <li>내용 : <%= articleRow.get("body")%></li>
  </ul>
  <a href="list">리스트 바로가기</a>
</body>
</html>