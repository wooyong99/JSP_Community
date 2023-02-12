<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
  <title>로그인</title>
</head>
<body>
<h1>로그인</h1>
<script>
  function Login__submit(){
    let Login__submitDone = false;
    if(Login__submitDone == true){
      alert('처리 중입니다.');
      return false;
    }

    frm.user_id.value = frm.user_id.value.trim();
    if( frm.user_id.value.length == 0 ){
      alert('아이디를 입력 해주세요.');
      frm.user_id.focus();
      return false;
    }

    frm.user_pw.value = frm.user_pw.value.trim();
    if(frm.user_pw.value.length==0){
      alert('비밀번호를 입력해주세요.');
      frm.user_pw.focus();
      return false;
    }
    Join__submitDone = true;
  }

</script>
<form action="doLogin" method="POST" onsubmit="return Login__submit();" name="frm" >
  <label>ID : <input type="text" name="user_id" placeholder="ID를 입력해주세요"></label>
  <br>
  <label>PW : <input type="password" name="user_pw" placeholder="비밀번호를 입력해주세요"></label>
  <br>
  <input type="submit" value="login">
</form>
<a href="/usr/home/main">메인페이지로</a><br>
<a href="/usr/article/list">리스트페이지로</a>

</body>
</html>