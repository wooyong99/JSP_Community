package com.jwy.exam.service;

import com.jwy.exam.Rq;
import com.jwy.exam.dao.MemberDao;
import com.jwy.exam.dto.Member;

import java.sql.Connection;
import java.util.Map;

public class MemberService {
  private Connection con;
  private Rq rq;
  private MemberDao memberDao;

  public MemberService(Connection con, Rq rq) {
    this.con = con;
    this.memberDao = new MemberDao(con,rq);
  }
  public Member getMemberByUserId(String user_id) {
    return memberDao.getMemberByUserId(user_id);
  }

  public int memberInsert(String user_id, String user_pw, String name, String tel) {
    return memberDao.memberInsert(user_id,user_pw,name,tel);
  }

  public boolean idDuplicate(String user_id) {
    return memberDao.idDuplicate(user_id);

  }
}
