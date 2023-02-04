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

@WebServlet("/article/doModify")
public class ArticleDoModifyServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Config.setEncode(req, resp);

    Rq rq = new Rq(req, resp);

    Connection con = null;

    int id_param = rq.getIntParam("id",0);
    String title = rq.getParam("title","");
    String body = rq.getParam("body","");

    if(title.equals("") & body.equals("")){
      rq.appendBody(String.format("<script> alert('제목/내용을 입력해주세요.'); location.replace('modify?id=%d'); </script>",id_param));
      return;
    }else if(title.equals("")){
      rq.appendBody(String.format("<script> alert('제목을 입력해주세요.'); location.replace('modify?id=%d'); </script>",id_param));
      return;
    }else if(body.equals("")){
      rq.appendBody(String.format("<script> alert('내용을 입력해주세요.'); location.replace('modify?id=%d'); </script>",id_param));
      return;
    }

    Config.ClassforName();
    try{
      con = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());

      SecSql sql = SecSql.from("UPDATE article SET ");
      sql.append("updateDate = NOW(),");
      sql.append("title = ? ,",title);
      sql.append("body = ? ",body);
      sql.append("WHERE id = ?", id_param);

      DBUtil.update(con, sql);
      rq.appendBody(String.format("<script> alert('%d번 게시글이 수정되었습니다.'); location.replace('detail?id=%d'); </script>", id_param,id_param));
    }catch(SQLException e){
      e.printStackTrace();
    }catch(SQLErrorException e){
      e.getOrigin().printStackTrace();
    } finally {
      try{
        if(con.isClosed() && con!=null){
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
