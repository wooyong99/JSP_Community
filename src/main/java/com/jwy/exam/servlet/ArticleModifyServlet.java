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
import java.util.Map;

@WebServlet("/article/modify")
public class ArticleModifyServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Rq rq = new Rq(req,resp);

    Connection con = null;

    int id_param = rq.getIntParam("id", 0);

    Config.ClassforName();

    try{
      con = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());

      SecSql sql = SecSql.from("SELECT * FROM article");
      sql.append("WHERE id = ?", id_param);

      Map<String, Object> articleRow = DBUtil.selectRow(con,sql);

      req.setAttribute("articleRow", articleRow);

      rq.jsp("../article/modify");
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
}
