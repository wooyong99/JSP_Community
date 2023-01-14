import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");  // 요청되는 데이터를 UTF-8로 지정
    resp.setCharacterEncoding("UTF-8"); // 응답하는 데이터를 UTF-8로 지정
    resp.setContentType("text/html; charset-utf-8");  // 브라우저에 노출되는 결과물을 UTF-8로 지정

    resp.getWriter().append("안녕하세요.1");

  }
}
