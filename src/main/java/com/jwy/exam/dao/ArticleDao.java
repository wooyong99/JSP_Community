package com.jwy.exam.dao;

import com.jwy.exam.Rq;
import com.jwy.exam.dto.Article;
import com.jwy.exam.util.DBUtil;
import com.jwy.exam.util.SecSql;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleDao {
  private Connection con;
  private Rq rq ;
  private HttpServletRequest req;
  private HttpServletResponse resp;

  public ArticleDao(Connection con, Rq rq) {
    this.con = con;
    this.rq = rq;
    this.req = rq.getReq();
    this.resp = rq.getResp();
  }

  public List<Article> getArticles(int startList, int onePageArticleCnt) {
    SecSql sql = SecSql.from("SELECT * FROM article");
    sql.append("ORDER BY id DESC");
    sql.append("LIMIT ?, ?", startList, onePageArticleCnt);

    List<Map<String, Object>> articleRows =  DBUtil.selectRows(con,sql);
    List<Article> articles = new ArrayList<>();
    for(Map<String, Object> article : articleRows){
      articles.add(new Article(article));
    }
    return articles;
  }

  public int getTotalArticleCnt() {
    SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt FROM article");
    Map<String,Object> article_cnt = DBUtil.selectRow(con,sql);
    return (int) article_cnt.get("cnt");
  }

  public Article getArticle() {
    int id_param = rq.getIntParam("id", 0);
    SecSql sql = new SecSql();
    sql.append("SELECT * FROM article");
    sql.append("WHERE id = ?", id_param);

    Map<String, Object> articleRow = DBUtil.selectRow(con, sql);

    return new Article(articleRow);
  }

  public void articleUpdate(String title, String body, int id_param) {
    SecSql sql = SecSql.from("UPDATE article SET ");
    sql.append("updateDate = NOW(),");
    sql.append("title = ? ,",title);
    sql.append("body = ? ",body);
    sql.append("WHERE id = ?", id_param);

    DBUtil.update(con, sql);
  }

  public int articleInsert(String title, String body, int loginMemberId) {
    SecSql sql = SecSql.from("INSERT INTO article SET");
    sql.append("regDate = NOW(), ");
    sql.append("updateDate = NOW(), ");
    sql.append("title = ? ,", title);
    sql.append("body = ? ,", body);
    sql.append("memberId = ?", loginMemberId);

    int id_param = DBUtil.insert(con, sql);

    return id_param;
  }

  public int articleDelte(int id_param) {
    SecSql sql = SecSql.from("DELETE FROM article");
    sql.append("WHERE id = ?", id_param);

    int deleteRow = DBUtil.delete(con,sql);
    return deleteRow;
  }
}
