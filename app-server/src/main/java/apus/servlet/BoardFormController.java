package apus.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import apus.dao.BoardDao;
import apus.domain.Member;


@WebServlet("/board/form")
public class BoardFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  BoardDao boardDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
  }
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    HttpSession session = request.getSession(false);
    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/apus/index.jsp");
      return;
    }
    Member member = (Member) request.getSession(false).getAttribute("loginUser");

    // 출력을 담당할 뷰를 호출한다.
    //    request.getRequestDispatcher("/board/BoardForm.jsp").forward(request, response);

    request.setAttribute("contentUrl", "/board/BoardForm.jsp");
    request.getRequestDispatcher("/template3.jsp").forward(request, response);
  }
}
