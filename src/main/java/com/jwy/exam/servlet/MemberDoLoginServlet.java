package com.jwy.exam.servlet;

import com.jwy.exam.Config;
import com.jwy.exam.Rq;
import com.jwy.exam.util.DBUtil;
import com.jwy.exam.util.SecSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/member/doLogin")
public class MemberDoLoginServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Rq rq = new Rq(req,resp);
    Connection con = null;

    Config.ClassforName();

    String user_id = req.getParameter("user_id");
    String user_pw = req.getParameter("user_pw");

    try {
      con = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());

      SecSql sql = SecSql.from("SELECT * FROM member");
      sql.append("WHERE user_id = ?", user_id);

      Map<String,Object> memberMap = DBUtil.selectRow(con, sql);
      if(memberMap.isEmpty()){
        rq.appendBody(String.format("<script> alert('%s는(은) 없는 아이디입니다.'); history.back(); </script>", user_id));
      }
      System.out.println(memberMap.get("user_pw").equals(user_pw));
      if(((String)memberMap.get("user_pw")).equals(user_pw) == false){
        rq.appendBody("<script> alert('비밀번호가 틀린 비밀번호입니다.'); history.back(); </script>");
        return ;
      }

      HttpSession session = req.getSession();
      session.setAttribute("loginMemberId", memberMap.get("id"));

      rq.appendBody(String.format("<script> alert('로그인 성공!'); location.replace('../home/main');</script>"));
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try{
        if(con.isClosed() && con != null){
          con.close();
        }
      }catch(SQLException e){
        e.printStackTrace();
      }

    }

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
