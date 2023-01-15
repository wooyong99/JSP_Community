package com.jwy.exam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Rq {
  private final HttpServletRequest req;
  private final HttpServletResponse resp;

  public Rq(HttpServletRequest req, HttpServletResponse resp) {
    this.req = req;
    this.resp = resp;

    try {
      req.setCharacterEncoding("UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    resp.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html; charset-utf-8");
  }

  public int getIntParam(String paramName, int defaultValue){
    String paramValue = req.getParameter(paramName);

    if(paramValue == null){
      return defaultValue;
    }else{
      try{
        return Integer.parseInt(paramValue);
      }catch(NumberFormatException e){
        return defaultValue;
      }
    }
  }

  public void appendBody(String str){
    try {
      resp.getWriter().append(str);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
