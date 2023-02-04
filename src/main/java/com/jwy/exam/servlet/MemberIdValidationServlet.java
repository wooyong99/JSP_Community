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

@WebServlet("/member/idValidation")
public class MemberIdValidationServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Rq rq = new Rq(req,resp);
    Connection con = null;

    Config.ClassforName();
    String user_id = rq.getParam("user_id","");
    try{
      con = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());

      SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt FROM member");
      sql.append("WHERE user_id = ?", user_id);

      boolean id_duplicate = DBUtil.selectRowBooleanValue(con, sql);
      if(id_duplicate){
        rq.appendBody("<script> alert('사용할 수 없는 아이디입니다.'); history.back();</script>");
      }else{
        rq.appendBody(String.format("<script> alert('사용 가능한 아이디입니다.');  location.replace('join?user_id=%s');</script>", user_id));
      }
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
    doGet(req,resp);
  }
}
