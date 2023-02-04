package com.jwy.exam.servlet;

import com.jwy.exam.Config;
import com.jwy.exam.Rq;
import com.jwy.exam.exception.SQLErrorException;
import com.jwy.exam.util.DBUtil;
import com.jwy.exam.util.SecSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/member/doJoin")
public class MemberDoJoinServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Rq rq = new Rq(req, resp);
    Connection con = null;
    // 파라미터 값이 default 값을 설정하면 안되기 때문에 req객체를 이용하여 파라미터 값을 받아온다.
    String user_id = req.getParameter("user_id");
    String user_pw = req.getParameter("user_pw");
    String name = req.getParameter("name");
    String tel = req.getParameter("tel");

    Config.ClassforName();

    try{
      con = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());

      SecSql sql = SecSql.from("INSERT INTO member SET");
      sql.append("regDate = NOW(),");
      sql.append("updateDate = NOW(),");
      sql.append("user_id = ?,", user_id);
      sql.append("user_pw = ?,", user_pw);
      sql.append("name = ?,", name);
      sql.append("tel = ?", tel);

      int memberId = DBUtil.insert(con,sql);

      rq.appendBody(String.format("<script> alert('회원가입이 완료되었습니다.'); location.replace('/home/main'); </script>"));
    }catch(SQLException e){
      e.printStackTrace();
    }catch(SQLErrorException e){
      e.getOrigin().printStackTrace();
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
