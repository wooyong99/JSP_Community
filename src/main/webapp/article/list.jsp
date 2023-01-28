<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>

<%
  List<Map<String,Object>> articleRows = (List<Map<String,Object>>) request.getAttribute("articleRows");
  int pageStartNum = (int) request.getAttribute("pageStartNum");
  int pageLastNum = (int) request.getAttribute("pageLastNum");
  int totalPagingCnt = (int) request.getAttribute("totalPagingCnt");
%>
<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>게시물 상세보기</title>
  <style>
    .list{
      display:flex;
      flex-direction : column;
    }
    .page{
      list-style:none;
      display:flex;
      justify-content:center;
    }
    .page>li{
      margin:0 10px;
  </style>
</head>
<body>
    <h1>게시물 리스트</h1>
    <ul class="list">
    <% for(Map<String,Object> articleRow : articleRows) {%>
          <li><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("id")%>, <%=articleRow.get("regDate")%>, <%=articleRow.get("updateDate")%>, <%=articleRow.get("title")%>, <%=articleRow.get("body")%></a></li>
    <% } %>
    </ul>
    <ul class="page">
      <% if(pageStartNum > 1 ){ %>
      <li><a href="list?page=<%=pageStartNum-5%>"> 이전 </a></li>
      <% }%>
      <%
      for(int i = pageStartNum; i <= pageLastNum; i++) {
        if(i <= totalPagingCnt){
      %>
      <li><a href="list?page=<%=i%>"><%=i%></a></li>
      <% } } %>
      <% if(pageStartNum +5 < totalPagingCnt) { %>
      <li><a href="list?page=<%=pageStartNum+5%>"> 다음 </a></li>
      <% } %>
    </ul>
</body>
</html>