package com.jwy.exam.service;

import com.jwy.exam.Rq;
import com.jwy.exam.dao.ArticleDao;
import com.jwy.exam.dto.Article;
import com.jwy.exam.util.DBUtil;
import com.jwy.exam.util.SecSql;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ArticleService {
  private ArticleDao articleDao;
  private Connection con;
  private Rq rq;

  public ArticleService(Connection con, Rq rq) {
    this.con = con;
    this.rq = rq;
    this.articleDao = new ArticleDao(con,rq);
  }

  public int getTotalArticleCnt() {
    return articleDao.getTotalArticleCnt();
  }

  public int getOnePageArticleCnt() {
    return 10;
  }

  public int getTotalPagingCnt() {
    return (int) Math.ceil((double)getTotalArticleCnt() / getOnePageArticleCnt());
  }

  public List<Article> getArticles(int startList, int onePageArticleCnt) {
    return articleDao.getArticles(startList, onePageArticleCnt);
  }

  public Article getArticle() {
    return articleDao.getArticle();
  }

  public void articleUpdate(String title, String body, int id_param) {
    articleDao.articleUpdate(title, body, id_param);
  }

  public int articleInsert(String title, String body, int loginMemberId) {
    return articleDao.articleInsert(title, body, loginMemberId);
  }

  public int articleDelete(int id_param) {
    return articleDao.articleDelte(id_param);
  }
}
