import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Rq rq = new Rq(req, resp);

    int dan = rq.getIntParam("dan", 0);
    int limit = rq.getIntParam("limit", 0);
    rq.appendBody("<h1>%d단</h1>".formatted(dan));

    for(int i=1; i <= limit; i++){
      rq.appendBody("<div>%d * %d  = %d</div>".formatted(dan, i, dan*i));
    }

  }
}
