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
      <td><%= articleRow.get("id")%></td>
      <td><%= articleRow.get("regDate")%></td>
      <td><%= articleRow.get("updateDate")%></td>
      <td><%= articleRow.get("title")%></td>
      <td><%= articleRow.get("body")%></td>
      <td>
        <a href="doDelete?id=<%=articleRow.get("id")%>">삭제하기</a> / <a href="modify?id=<%=articleRow.get("id")%>">수정하기</a>
      </td>
    </tr>
  </table>
  <a href="list">리스트 바로가기</a>
</body>
</html>