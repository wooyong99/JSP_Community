package com.jwy.exam.dto;

import lombok.Data;

import java.util.Map;
@Data
public class Member {
  public int id;
  public String regDate;
  public String updateDate;
  public String user_id;
  public String user_pw;
  public String name ;
  public String tel;

  public Member(Map<String, Object>row) {
    this.id = (int) row.get("id");
    this.regDate = (String) row.get("regDate");
    this.updateDate = (String) row.get("updateDate");
    this.user_id = (String) row.get("user_id");
    this.user_pw = (String) row.get("user_pw");
    this.name = (String) row.get("name");
    this.tel = (String) row.get("tel");
  }
}

