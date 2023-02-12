package com.jwy.exam.controller;

import com.jwy.exam.Config;
import com.jwy.exam.Rq;
import com.jwy.exam.dto.Article;
import com.jwy.exam.dto.ResultData;
import com.jwy.exam.exception.SQLErrorException;
import com.jwy.exam.service.ArticleService;
import com.jwy.exam.util.DBUtil;
import com.jwy.exam.util.SecSql;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ArticleController extends Controller{
  private Connection con;
  private Rq rq;
  private ArticleService articleService;
  private HttpServletResponse resp;
  private HttpServletRequest req;
  public ArticleController(Connection con, Rq rq) {
    this.con = con;
    this.rq = rq;
    this.articleService = new ArticleService(con, rq);
    this.req = rq.getReq();
    this.resp = rq.getResp();
  }
  @Override
  public void performAction(Rq rq) {
    switch (rq.getActionMethodName()) {
      case "list":
        actionList();
        break;
      case "write":
        actionWrite();
        break;
      case "doWrite":
        actionDoWrite();
        break;
      case "detail":
        actionDetail();
        break;
      case "modify":
        actionModify();
        break;
      case "doModify":
        actionDoModify();
        break;
      case "doDelete":
        actionDoDelete();
        break;
      default:
        rq.appendBody("존재하지 않는 페이지입니다.");
        break;
    }
  }

  public void actionList() {
    int page = rq.getIntParam("page",1);

    // 한 페이지 당 보여주는 게시글 수
    int onePageArticleCnt = articleService.getOnePageArticleCnt();
    // 총 게시글 개수
    int totalArticleCnt = articleService.getTotalArticleCnt();
    // 총 페이징 개수
    int totalPagingCnt = articleService.getTotalPagingCnt();
    // 페이징 시작 게시글
    int startList = (page-1)*articleService.getOnePageArticleCnt() ;

    //1~5, 6~10, 11~15
    int pageStartNum = (page % 5 != 0) ? ((page/5) * 5) +1 : page / 5 ;
    int pageLastNum = pageStartNum+4;

    List<Article> articles = articleService.getArticles(startList, onePageArticleCnt);


    req.setAttribute("totalPagingCnt", totalPagingCnt);
    req.setAttribute("pageStartNum", pageStartNum);
    req.setAttribute("pageLastNum", pageLastNum);
    req.setAttribute("articles", articles);

    rq.jsp("/article/list");
  }

  public void actionDetail() {
      Article article = articleService.getArticle();

      req.setAttribute("article", article);

      rq.jsp("/article/detail");
  }
  public void actionModify() {
    Article article = articleService.getArticle();

    req.setAttribute("article", article);

    rq.jsp("/article/modify");
  }

  public void actionDoModify() {
    int id_param = rq.getIntParam("id",0);
    String title = rq.getParam("title","");
    String body = rq.getParam("body","");

    if(title.equals("") & body.equals("")){
      rq.appendBody(String.format("<script> alert('제목/내용을 입력해주세요.'); location.replace('/usr/article/modify?id=%d'); </script>",id_param));
      return;
    }else if(title.equals("")){
      rq.appendBody(String.format("<script> alert('제목을 입력해주세요.'); location.replace('/usr/article/modify?id=%d'); </script>",id_param));
      return;
    }else if(body.equals("")){
      rq.appendBody(String.format("<script> alert('내용을 입력해주세요.'); location.replace('/usr/article/modify?id=%d'); </script>",id_param));
      return;
    }
    articleService.articleUpdate(title, body, id_param);
    rq.appendBody(String.format("<script> alert('%d번 게시글이 수정되었습니다.'); location.replace('/usr/article/detail?id=%d'); </script>", id_param,id_param));
  }

  public void actionDoWrite() {
    int loginMemberId  = 0;

    HttpSession session = req.getSession();

    if( session.getAttribute("loginMemberId") == null){
      rq.appendBody("<script> alert('로그인 후 이용해주세요.'); location.replace('../member/login'); </script>");
      return;
    }else{
      loginMemberId = (int) session.getAttribute("loginMemberId");
    }
    String title = rq.getParam("title","");
    String body = rq.getParam("body","");
    if(title.equals("") && body.equals("")){
      rq.appendBody(String.format("<script> alert('제목/내용을 입력해주세요.'); location.replace('/usr/article/write'); </script>"));
      return;
    }else if(title.equals("")){
      rq.appendBody(String.format("<script> alert('제목을 입력해주세요.'); location.replace('/usr/article/write'); </script>"));
      return;
    }else if(body.equals("")){
      rq.appendBody(String.format("<script> alert('내용을 입력해주세요.'); location.replace('/usr/article/write'); </script>"));
      return;
    }

    int id_param = articleService.articleInsert(title, body, loginMemberId);

    rq.appendBody(String.format("<script> alert('%d번 게시글이 등록되었습니다.'); location.replace('/usr/article/detail?id=%d'); </script>", id_param,id_param));
  }

  public void actionWrite() {
    rq.jsp("/article/write");
  }

  public void actionDoDelete() {
    int id_param = rq.getIntParam("id",0);

    int deleteRow = articleService.articleDelete(id_param);
    if (deleteRow == 0) {
      rq.appendBody("<script> alert('존재하지 않는 게시글입니다.'); location.replace('/usr/article/list'); </script>");
    }else{
      rq.appendBody(String.format("<script> alert('%d번 게시글이 삭제되었습니다.'); location.replace('/usr/article/list'); </script>", id_param));
    }
  }
}
