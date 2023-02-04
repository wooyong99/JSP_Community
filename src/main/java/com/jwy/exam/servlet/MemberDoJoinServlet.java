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

    String user_id = req.getParameter("user_id");
    String user_pw = req.getParameter("user_pw");
    String pw_confirm = req.getParameter("pw_confirm");
    String name = req.getParameter("name");
    String tel = req.getParameter("tel");

    Config.ClassforName();

    try{
      con = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());

      SecSql sql = SecSql.from("INSERT INTO member SET");
      sql.append("user_id = ?,", user_id);
      sql.append("user_pw = ?,", user_pw);
      sql.append("name = ?,", name);
      sql.append("tel = ?", tel);

      int memberId = DBUtil.insert(con,sql);
    }catch(SQLException e){
      e.printStackTrace();
    }finally {
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
