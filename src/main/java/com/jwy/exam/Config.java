package com.jwy.exam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;

public class Config {
  public static String getDBUrl(){
    return "jdbc:mysql://127.0.0.1:3306/JSP_Community?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
  }
  public static String getDBId(){
    return "jwy";
  }
  public static String getDBPw(){
    return "1234";
  }
  public static void ClassforName() {
    try{
      Class.forName("com.mysql.jdbc.Driver");
    }catch(ClassNotFoundException e){
      System.out.println("예외 : MySQL에 연결할 수 없습니다.");
      e.printStackTrace();
      return;
    }
  }
  public static void setEncode(HttpServletRequest req , HttpServletResponse resp) throws UnsupportedEncodingException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html; chasrset-utf-8");

  }
}
