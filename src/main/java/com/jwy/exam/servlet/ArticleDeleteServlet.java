package com.jwy.exam.servlet;

import com.jwy.exam.util.DBUtil;
import com.jwy.exam.util.SecSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/article/doDelete")
public class ArticleDeleteServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html; charset-utf-8");
    Connection con = null;
    String url = "jdbc:mysql://127.0.0.1:3306/JSP_Community?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
    String id = "jwy";
    String pw = "1234";
    int id_param = Integer.parseInt(req.getParameter("id"));
    try{
      Class.forName("com.mysql.jdbc.Driver");
    }catch(ClassNotFoundException e){
      System.out.println("예외 : MySQL에 연결할 수 없습니다.");
      e.printStackTrace();
      return;
    }
    try{
      con = DriverManager.getConnection(url, id, pw);
      SecSql sql = SecSql.from("DELETE FROM article");
      sql.append("WHERE id = ?", id_param);

      DBUtil.delete(con,sql);

      resp.getWriter().append("<script> alert('"+id_param+"번 게시글이 삭제되었습니다.'); location.replace('list'); </script>");
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
}
