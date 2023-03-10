<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

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
<script>
  function Join__submit(){
    let Join__submitDone = false;
    if(Join__submitDone == true){
      alert('처리 중입니다.');
      return false;
    }
    let dupli_check = document.getElementById('idValidation').value;
    frm.user_id.value = frm.user_id.value.trim();

    if( dupli_check == 'false' ){
      alert('아이디 중복체크를 해주세요.');
      frm.user_id.focus();
      return false;
    }

    frm.user_pw.value = frm.user_pw.value.trim();
    if(frm.user_pw.value.length==0){
      alert('비밀번호를 입력해주세요.');
      frm.user_pw.focus();
      return false;
    }

    frm.pw_confirm.value = frm.pw_confirm.value.trim();
    if(frm.pw_confirm.value.length==0){
      alert('비밀번호 확인을 입력해주세요.');
      frm.pw_confirm.focus();
      return false;
    }

    if(frm.pw_confirm.value != frm.user_pw.value) {
      alert('비밀번호가 일치하지 않습니다.');
      frm.pw_confirm.focus();
      return false;
    }

    frm.name.value= frm.name.value.trim();
    if(frm.name.value.length==0){
      alert('이름을 입력해주세요.');
      frm.name.focus();
      return false;
    }

    frm.tel.value= frm.tel.value.trim();
    if(frm.tel.value.length==0){
      alert('연락처를 입력해주세요.');
      frm.tel.focus();
      return false;
    }

    var telReg = /\d{3}-\d{3,4}-\d{4}$/;
    if(!telReg.test(frm.tel.value)){
      alert('연락처 양식을 확인해주세요.');
      frm.tel.focus();
      return false;
    }

    Join__submitDone = true;
  }

</script>
<form action="doJoin" method="POST" onsubmit="return Join__submit();" name="frm" >
  <c:set var="user_id" value="${param.user_id}"/>

  <c:if test="${user_id != null}">
    <label>ID : <input type="text" name="user_id" value="${user_id}" readonly></label>
    <button type="button" id="idValidation" value="true">
      <a>사용가능</a>
    </button>
  </c:if>

  <c:if test="${user_id == null}">
    <label>ID : <input type="text" name="user_id" placeholder="ID를 입력해주세요"></label>
    <button type="button" id="idValidation" value="false">
      <a onclick="if(frm.user_id.value) { location.href='idValidation?user_id='+$(frm.user_id).val(); } return false;">중복확인</a>
    </button>
  </c:if>


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
<a href="/usr/home/main">메인페이지로</a><br>
<a href="/usr/article/list">리스트페이지로</a>

</body>
</html>