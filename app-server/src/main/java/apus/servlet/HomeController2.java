package apus.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BoardDao;
import apus.dao.MemberDao;
import apus.domain.Board;
import apus.domain.Member;


@WebServlet("/home2")
public class HomeController2 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  BoardDao boardDao;
  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");

  }


  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(false);


    if (session.getAttribute("loginUser") == null) {
      request.setAttribute("contentUrl", "/index2.jsp");
      request.getRequestDispatcher("/homeTemplate2.jsp").forward(request, response);
      return;
    }
    try {


      Member member = (Member) request.getSession(false).getAttribute("loginUser");

      Collection<Board> boardList = boardDao.findAll();

      // 뷰 컴포넌트가 준비한 데이터를 사용할 수 있도록 저장소에 보관한다.
      request.setAttribute("boardList", boardList);
      request.setAttribute("member", member);


      request.setAttribute("pageTitle", "메인화면");
      request.setAttribute("contentUrl", "/index2.jsp");
      request.getRequestDispatcher("/homeTemplate2.jsp").forward(request, response);
    } catch (Exception e) {
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      request.setAttribute("error", e);
      e.printStackTrace();

      // 오류가 발생하면, 오류 내용을 출력할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
      요청배달자.forward(request, response);

    }
  }

}