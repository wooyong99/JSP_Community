package com.jwy.exam.servlet;

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
import java.util.List;
import java.util.Map;
@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Rq rq = new Rq(req,resp);

    Connection con = null;
    String url = "jdbc:mysql://127.0.0.1:3306/JSP_Community?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
    String id = "jwy";
    String pw = "1234";

    try{
      Class.forName("com.mysql.jdbc.Driver");
    }catch(ClassNotFoundException e){
      System.out.println("예외 : MySQL에 연결할 수 없습니다.");
      e.printStackTrace();
      return;
    }
    try{
      con = DriverManager.getConnection(url, id, pw);
      SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt FROM article");
      Map<String,Object> article_cnt = DBUtil.selectRow(con,sql);
      int page = rq.getIntParam("page",1);

      // 한 페이지 당 보여주는 게시글 수
      int onePageArticleCnt = 30;
      // 총 게시글 개수
      int totalArticleCnt = (int) article_cnt.get("cnt");
      // 총 페이징 개수
      int totalPagingCnt = (int) Math.ceil((double)totalArticleCnt / onePageArticleCnt);
      // 페이징 시작 게시글
      int startList = (page-1)*onePageArticleCnt ;

      //1~5, 6~10, 11~15
      int pageStartNum = (page % 5 != 0) ? ((page/5) * 5) +1 : page / 5 ;
      int pageLastNum = pageStartNum+4;

      sql = SecSql.from("SELECT * FROM article");
      sql.append("ORDER BY id DESC");
      sql.append("LIMIT ?, ?", startList, onePageArticleCnt);

      List<Map<String, Object>> articleRows =  DBUtil.selectRows(con,sql);
      req.setAttribute("totalPagingCnt", totalPagingCnt);
      req.setAttribute("pageStartNum", pageStartNum);
      req.setAttribute("pageLastNum", pageLastNum);
      req.setAttribute("articleRows", articleRows);

      rq.jsp("../article/list");
    }catch(SQLException e){
      e.printStackTrace();
    }finally{
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