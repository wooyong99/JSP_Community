<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>

<%
  List<Map<String,Object>> articleRows = (List<Map<String,Object>>) request.getAttribute("articleRows");
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
    <h1>게시물 리스트</h1>
    <ul>
    <% for(Map<String,Object> articleRow : articleRows) {%>
          <li><div><a href="#"><%=articleRow.get("id")%>, <%=articleRow.get("regDate")%>, <%=articleRow.get("updateDate")%>, <%=articleRow.get("title")%>, <%=articleRow.get("body")%></a></div></li>
    <% } %>
    </ul>
</body>
</html>