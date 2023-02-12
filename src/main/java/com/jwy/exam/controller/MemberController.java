package com.jwy.exam.controller;

import com.jwy.exam.Config;
import com.jwy.exam.Rq;
import com.jwy.exam.dto.Member;
import com.jwy.exam.service.MemberService;
import com.jwy.exam.util.DBUtil;
import com.jwy.exam.util.SecSql;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class MemberController extends Controller{
  private Connection con;
  private Rq rq;
  private MemberService memberService;
  private HttpServletResponse resp;
  private HttpServletRequest req;

  public MemberController(Connection con, Rq rq) {
    this.con = con;
    this.rq = rq;
    this.memberService = new MemberService(con ,rq);
    this.req = rq.getReq();
    this.resp = rq.getResp();
  }
  @Override
  public void performAction(Rq rq) {
    switch (rq.getActionMethodName()) {
      case "login":
        actionLogin();
        break;
      case "doLogin":
        actionDoLogin();
        break;
      case "join":
        actionJoin();
        break;
      case "doJoin":
        actionDoJoin();
        break;
      case "doLogout":
        actionDoLogout();
        break;
      case "idValidation":
        actionIdValidation();
        break;
      default:
        rq.appendBody("존재하지 않는 페이지입니다.");
        break;
    }
  }

  public void actionLogin() {
    rq.jsp("/member/login");
  }

  public void actionDoLogin() {
    String user_id = req.getParameter("user_id");
    String user_pw = req.getParameter("user_pw");

    Member member = memberService.getMemberByUserId(user_id);
    if(member == null){
      rq.appendBody(String.format("<script> alert('%s는(은) 없는 아이디입니다.'); history.back(); </script>", user_id));
    }
    if((member.user_pw.equals(user_pw) == false)){
      rq.appendBody("<script> alert('비밀번호가 틀린 비밀번호입니다.'); history.back(); </script>");
      return ;
    }

    HttpSession session = req.getSession();
    session.setAttribute("loginMemberId", member.id);

    rq.appendBody(String.format("<script> alert('로그인 성공!'); location.replace('/usr/home/main');</script>"));
  }
  public void actionDoLogout() {
    HttpSession session = req.getSession();
    session.removeAttribute("loginMemberId");

    rq.appendBody("<script> alert('로그아웃 되었습니다.'); location.replace('/usr/home/main'); </script>");
  }

  public void actionJoin() {
    rq.jsp("/member/join");
  }

  public void actionDoJoin() {
    // 파라미터 값이 default 값을 설정하면 안되기 때문에 req객체를 이용하여 파라미터 값을 받아온다.
    String user_id = req.getParameter("user_id");
    String user_pw = req.getParameter("user_pw");
    String name = req.getParameter("name");
    String tel = req.getParameter("tel");

    int memberId = memberService.memberInsert(user_id,user_pw, name, tel);


    rq.appendBody(String.format("<script> alert('회원가입이 완료되었습니다.'); location.replace('/usr/home/main'); </script>"));
  }

  public void actionIdValidation() {
    String user_id = rq.getParam("user_id","");

    boolean id_duplicate = memberService.idDuplicate(user_id);
    if(id_duplicate){
      rq.appendBody("<script> alert('사용할 수 없는 아이디입니다.'); history.back();</script>");
    }else{
      rq.appendBody(String.format("<script> alert('사용 가능한 아이디입니다.');  location.replace('/usr/member/join?user_id=%s');</script>", user_id));
    }
  }
}
