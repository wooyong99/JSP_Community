package com.jwy.exam.dao;

import com.jwy.exam.Rq;
import com.jwy.exam.dto.Member;
import com.jwy.exam.util.DBUtil;
import com.jwy.exam.util.SecSql;

import java.sql.Connection;
import java.util.Map;

public class MemberDao {
  private Connection con;
  private Rq rq;
  public MemberDao(Connection con, Rq rq) {
    this.con = con;
    this.rq = rq;
  }

  public Member getMemberByUserId(String user_id) {
    SecSql sql = SecSql.from("SELECT * FROM member");
    sql.append("WHERE user_id = ?", user_id);

    Map<String,Object> memberMap = DBUtil.selectRow(con, sql);

    return new Member(memberMap);
  }

  public int memberInsert(String user_id, String user_pw, String name, String tel) {
    SecSql sql = SecSql.from("INSERT INTO member SET");
    sql.append("regDate = NOW(),");
    sql.append("updateDate = NOW(),");
    sql.append("user_id = ?,", user_id);
    sql.append("user_pw = ?,", user_pw);
    sql.append("name = ?,", name);
    sql.append("tel = ?", tel);

    int memberId = DBUtil.insert(con,sql);
    return memberId;
  }

  public boolean idDuplicate(String user_id) {
    SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt FROM member");
    sql.append("WHERE user_id = ?", user_id);

    boolean id_duplicate = DBUtil.selectRowBooleanValue(con, sql);
    return id_duplicate;
  }
}
