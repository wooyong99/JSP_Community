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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/home/main")
public class HomeMainServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Rq rq = new Rq(req, resp);

    HttpSession session = req.getSession();

    Connection con = null;

    Config.ClassforName();
    try{
      con = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());

      boolean isLogined ;
      int loginMemberId ;

      SecSql sql;
      Map<String, Object> memberMap = null;

      if(session.getAttribute("loginMemberId") != null){
        isLogined = true;
        loginMemberId = (int) session.getAttribute("loginMemberId");
        sql = SecSql.from("SELECT * FROM member");
        sql.append("WHERE id = ?", loginMemberId);

        memberMap = DBUtil.selectRow(con, sql);
      }else{
        isLogined = false;
        loginMemberId = -1;
      }

      req.setAttribute("isLogined", isLogined);
      req.setAttribute("loginMemberId", loginMemberId);
      req.setAttribute("memberMap", memberMap);

      rq.jsp("../home/main");
    }catch (SQLException e){
      e.printStackTrace();
    }catch(SQLErrorException e){
      e.getOrigin().printStackTrace();
    } finally {
      try{
        if(con.isClosed() && con != null){
          con.close();
        }
      }catch (SQLException e){
        e.printStackTrace();
      }
    }
  }
}
