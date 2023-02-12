package com.jwy.exam.dto;

import lombok.Data;

import java.util.Map;
@Data
public class Article {
  public int id;
  public String regDate;
  public String updateDate;
  public String title;
  public String body;
  public int memberId;

  public Article(Map<String,Object> row) {
    this.id = (int) row.get("id");
    this.regDate = (String) row.get("regDate");
    this.updateDate = (String) row.get("updateDate");
    this.title = (String) row.get("title");
    this.body = (String) row.get("body");
    this.memberId = (int) row.get("memberId");
  }
}
