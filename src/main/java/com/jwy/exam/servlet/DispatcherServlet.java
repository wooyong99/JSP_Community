package com.jwy.exam.servlet;

import com.jwy.exam.Config;
import com.jwy.exam.Rq;
import com.jwy.exam.controller.ArticleController;
import com.jwy.exam.controller.HomeController;
import com.jwy.exam.controller.MemberController;
import com.jwy.exam.dto.Member;
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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/usr/*")
public class DispatcherServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Rq rq = new Rq(req, resp);

    if(rq.getIsInvalid()){
      rq.appendBody("올바른 요청이 아닙니다.");
    }

    Connection con = null;

    Config.ClassforName();
    try{
      con = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());

      boolean isLogined ;
      int loginMemberId ;

      HttpSession session = req.getSession();

      Map<String, Object> memberMap = null;
      Member member =null;
      if(session.getAttribute("loginMemberId") != null){
        isLogined = true;
        loginMemberId = (int) session.getAttribute("loginMemberId");
        SecSql sql = SecSql.from("SELECT * FROM member");
        sql.append("WHERE id = ?", loginMemberId);

        memberMap = DBUtil.selectRow(con, sql);
        member = new Member(memberMap);
      }else{
        isLogined = false;
        loginMemberId = -1;
      }

      req.setAttribute("isLogined", isLogined);
      req.setAttribute("loginMemberId", loginMemberId);
      req.setAttribute("member", member);

      switch ( rq.getControllerTypeName()) {
        case "usr":
          ArticleController articleController = new ArticleController(con, rq);
          MemberController memberController = new MemberController(con,rq);
          HomeController homeController = new HomeController(con, rq);
          switch (rq.getControllerName()) {
            case "article":
              articleController.performAction(rq);
              break;
            case "member":
              memberController.performAction(rq);
              break;
            case "home":
              homeController.performAction(rq);
              break;
          }
      }
    }catch(SQLException e){
      e.printStackTrace();
    } catch(SQLErrorException e){
      e.getOrigin().printStackTrace();
    } finally{
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
