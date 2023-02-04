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

@WebServlet("/article/doDelete")
public class ArticleDoDeleteServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Config.setEncode(req, resp);

    Rq rq = new Rq(req, resp);

    Connection con = null;

    int id_param = rq.getIntParam("id",0);

    Config.ClassforName();

    try{
      con = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());
      SecSql sql = SecSql.from("DELETE FROM article");
      sql.append("WHERE id = ?", id_param);

      int deleteRow = DBUtil.delete(con,sql);
      if (deleteRow == 0) {
        rq.appendBody("<script> alert('존재하지 않는 게시글입니다.'); location.replace('list'); </script>");
      }else{
        rq.appendBody(String.format("<script> alert('%d번 게시글이 삭제되었습니다.'); location.replace('list'); </script>", id_param));
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
}
