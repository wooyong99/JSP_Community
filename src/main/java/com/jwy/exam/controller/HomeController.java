package com.jwy.exam.controller;

import com.jwy.exam.Rq;
import com.jwy.exam.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;

public class HomeController {
  private Connection con;
  private Rq rq;
  private ArticleService articleService;
  private HttpServletResponse resp;
  private HttpServletRequest req;
  public HomeController(Connection con, Rq rq) {
    this.con = con;
    this.rq = rq;
    this.req = rq.getReq();
    this.resp = rq.getResp();
  }

  public void actionMain() {
    rq.jsp("/home/main");
  }
}