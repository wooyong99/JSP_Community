<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.jwy.exam.dto.Article" %>

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
  <%@ include file="../part/topbar.jspf"%>
  <table border="1" style="text-align:center;">
    <colgroup>
      <col width="100">
      <col width="200">
      <col width="200">
      <col width="100">
      <col width="100">
    </colgroup>
    <tr>
      <th>번호</th>
      <th>생성날짜</th>
      <th>수정날짜</th>
      <th>제목</th>
      <th>내용</th>
      <th>비고</th>
    </tr>
    <tr>
      <td>${article.id}</td>
      <td>${article.regDate}</td>
      <td>${article.updateDate}</td>
      <td>${article.title}</td>
      <td>${article.body}</td>
      <td>
        <a href="doDelete?id=${article.id}">삭제하기</a> / <a href="modify?id=${article.id}">수정하기</a>
      </td>
    </tr>
  </table>
  <a href="list">리스트 바로가기</a>
</body>
</html>