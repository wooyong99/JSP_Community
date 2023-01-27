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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/article/detail")
public class ArticleDetailServlet extends HttpServlet {
  Connection con = null;
  String url = "jdbc:mysql://127.0.0.1:3306/JSP_Community?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
  String id = "jwy";
  String pw = "1234";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("예외 : MySQL에 연결 할 수 없습니다.");
      e.printStackTrace();
    }
    try {
      con = DriverManager.getConnection(url, id, pw);
      SecSql sql = new SecSql();
      sql.append("SELECT * FROM article");
      sql.append("WHERE id = ?", req.getParameter("id"));
      System.out.println(sql);
      Map<String, Object> articleRow = DBUtil.selectRow(con, sql);

      req.setAttribute("articleRow", articleRow);

      req.getRequestDispatcher("/article/detail.jsp").forward(req, resp);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (con.isClosed() && con != null) {
          con.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
