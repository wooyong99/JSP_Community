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

    Config.ClassforName();
    try{
      con = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());
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
      boolean isLogined ;
      int loginMemberId ;

      HttpSession session = req.getSession();

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

      rq.jsp("../article/list");
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
}
