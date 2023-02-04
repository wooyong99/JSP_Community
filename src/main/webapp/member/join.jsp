<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  String user_id =null;
  if(request.getParameter("user_id") != null){
    user_id = request.getParameter("user_id");
  }
%>
<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
  <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>회원가입</title>
</head>
<body>
<h1>회원가입</h1>
<form action="doJoin" method="POST" onsubmit="alert('hi');" name="frm">
  <%
    if(user_id != null){
  %>
  <label>ID : <input type="text" name="user_id" value="<%=user_id%>" readonly></label>
  <% }else{%>
  <label>ID : <input type="text" name="user_id" placeholder="ID를 입력해주세요"></label>
  <% } %>
  <button type="button" id="idValidation" >
    <a onclick="if(frm.user_id.value) { location.href='idValidation?user_id='+$(frm.user_id).val(); } return false;">중복확인</a>
  </button>
  <br>
  <label>PW : <input type="password" name="user_pw" placeholder="비밀번호를 입력해주세요"></label>
  <br>
  <label>PW 확인: <input type="password" name="pw_confirm" placeholder="비밀번호를 재입력해주세요."></label>
  <br>
  <label>이름 : <input type="text" name="name" placeholder="이름을 입력해주세요"></label>
  <br>
  <label>TEL : <input type="text" name="tel" placeholder="010-xxxx-xxxx"></label>
  <br>
  <input type="submit" value="join">
</form>

</body>
</html>