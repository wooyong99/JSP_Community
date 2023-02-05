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
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/article/doWrite")
public class ArticleDoWriteServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Config.setEncode(req, resp);

    Rq rq = new Rq(req,resp);

    HttpSession session = req.getSession();

    int loginMemberId  = 0;

    if( session.getAttribute("loginMemberId") == null){
      rq.appendBody("<script> alert('로그인 후 이용해주세요.'); location.replace('../member/login'); </script>");
      return;
    }else{
      loginMemberId = (int) session.getAttribute("loginMemberId");
    }

    Connection con = null;

    Config.ClassforName();
    try{
      String title = rq.getParam("title","");
      String body = rq.getParam("body","");
      if(title.equals("") && body.equals("")){
        rq.appendBody(String.format("<script> alert('제목/내용을 입력해주세요.'); location.replace('write'); </script>"));
        return;
      }else if(title.equals("")){
        rq.appendBody(String.format("<script> alert('제목을 입력해주세요.'); location.replace('write'); </script>"));
        return;
      }else if(body.equals("")){
        rq.appendBody(String.format("<script> alert('내용을 입력해주세요.'); location.replace('write'); </script>"));
        return;
      }
      con = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());

      SecSql sql = SecSql.from("INSERT INTO article SET");
      sql.append("regDate = NOW(), ");
      sql.append("updateDate = NOW(), ");
      sql.append("title = ? ,", title);
      sql.append("body = ? ,", body);
      sql.append("memberId = ?", loginMemberId);

      int id_param = DBUtil.insert(con, sql);

      sql = SecSql.from("SELECT id FROM article");
      sql.append("ORDER BY id DESC");
      sql.append("LIMIT 1");

      rq.appendBody(String.format("<script> alert('%d번 게시글이 등록되었습니다.'); location.replace('detail?id=%d'); </script>", id_param,id_param));
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
