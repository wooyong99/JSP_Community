package com.jwy.exam.servlet;

import com.jwy.exam.Rq;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/home/main")
public class HomeMainServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Rq rq = new Rq(req, resp);

    HttpSession session = req.getSession();

    boolean isLogined ;
    int loginMemberId ;

    if(session.getAttribute("loginMemberId") != null){
      isLogined = true;
      loginMemberId = (int) session.getAttribute("loginMemberId");
    }else{
      isLogined = false;
      loginMemberId = -1;
    }

    req.setAttribute("isLogined", isLogined);
    req.setAttribute("loginMemberId", loginMemberId);

    rq.jsp("../home/main");
  }
}
