<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  boolean isLogined = (boolean) request.getAttribute("isLogined");
  int loginMemberId = (int) request.getAttribute("loginMemberId");
%>
<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>메인 페이지</title>
</head>
<body>
<h1>메인 페이지</h1>
<% if( isLogined ) { %>
  <div><%= loginMemberId%> 회원님 환영합니다.</div>
  <div><a href="../member/doLogout">로그아웃</a></div>
<% } %>
<% if( !isLogined ) { %>
<div><a href="../member/login">로그인</a></div>
<% } %>

<div>
  <a href="../article/list">리스트 페이지</a>
</div>
<div>
  <a href="../member/join">회원 가입</a>
</div>


</body>
</html>