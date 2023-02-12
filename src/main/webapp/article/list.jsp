<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jwy.exam.dto.Article" %>

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
     }
     table > tbody > tr > td.articleTitle{
      display:block;
      width:200px;
      white-space:nowrap;
      overflow:hidden;
      text-overflow:ellipsis;
     }
  </style>
</head>
<body>
    <h1>게시물 리스트</h1>

    <%@ include file="../part/topbar.jspf"%>

    <button onclick="window.location.href='write'">게시물 작성</button>
    <table border="1" style="text-align:center;">
      <colgroup>
        <col width="100">
        <col width="200">
        <col width="100">
      </colgroup>
      <thead>
        <tr>
          <td>번호</td>
          <td>생성날짜</td>
          <td>제목</td>
          <td>비고</td>
        </tr>
      </thead>
      <tbody>

      <c:forEach items="${articles}" var="article">
        <tr>
          <td><a href="detail?id=${article.id}"><c:out value="${article.id}"/></a></td>
          <td><a href="detail?id=${article.id}">${article.regDate}</a></td>
          <td class="articleTitle"><a href="detail?id=${article.id}">${article.title}</td></td>
          <td><a href="doDelete?id=${article.id}">삭제하기</a> / <a href="modify?id=${article.id}">수정하기</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <ul class="page">
      <c:set var="pageStartNum" value="${pageStartNum}"/>
      <c:set var="pageLastNum" value="${pageLastNum}"/>
      <c:set var="totalPagingCnt" value="${totalPagingCnt}"/>

      <c:if test="${pageStartNum > 1}">
        <li><a href="list?page=${pageStartNum-5}"> 이전 </a></li>
      </c:if>

      <c:forEach var="i" begin="${pageStartNum}" end="${pageLastNum}" step="1">
        <c:if test="${i <= totalPagingCnt}">
          <li><a href="list?page=${i}">${i}</a></li>
        </c:if>
      </c:forEach>

      <c:if test="${pageStartNum+5 < totalPagingCnt}">
        <li><a href="list?page=${pageStartNum+5}"> 다음 </a></li>
      </c:if>
    </ul>
</body>
</html>