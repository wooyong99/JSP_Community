<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String dan_str = request.getParameter("dan");
String limit_str = request.getParameter("limit");

if(limit_str.equals("") || limit_str == null){
limit_str = "0";
}
if(dan_str.equals("") || dan_str == null){
dan_str = "0";
limit_str = "0";
}


int dan = Integer.parseInt(dan_str);
int limit = Integer.parseInt(limit_str);
%>

<h1><%= dan%>단</h1>
<% for(int i = 1; i <= limit; i++){ %>
<div><%= dan%> * <%= i%> = <%= dan*i%></div>
<% } %>
