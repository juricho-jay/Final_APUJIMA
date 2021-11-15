package apus.servlet;

import java.io.IOException;
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
import apus.dao.ReportDao;
import apus.domain.Board;
import apus.domain.Member;
import apus.domain.Report;

@WebServlet("/board/report")
public class BoardReportController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  BoardDao boardDao;
  MemberDao memberDao;
  SqlSession sqlSession;
  ReportDao reportDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    reportDao = (ReportDao) 웹애플리케이션공용저장소.getAttribute("reportDao");
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession(false);


    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/apus/index.jsp");
      return;
    }

    try {

      Report report = new Report();

      Member requester = (Member) request.getSession(false).getAttribute("loginUser");

      if(requester == null) {
        throw new Exception("로그인 되어 있지 않습니다.");
      }

      int no = Integer.parseInt(request.getParameter("no"));
      Board requestBoard = boardDao.findByNo(no);

      if(requestBoard == null) {
        throw new Exception("해당 번호의 게시글이 없습니다.");
      }
      report.setRequester(requester);
      report.setRequestBoard(requestBoard);

      //신고 검사

      request.setAttribute("report", report);

      // 출력을 담당할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/board/BoardReport.jsp");
      요청배달자.forward(request, response);


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