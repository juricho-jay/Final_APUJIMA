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
import apus.dao.ReportDao;
import apus.domain.Board;
import apus.domain.Member;
import apus.domain.Report;

@WebServlet("/board/reportAdd")
public class BoardReportAddController extends HttpServlet {
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

      // 신고리스트 가져와서 중복 신고 못하게 해야하는데 일단 지금 테스트중

      Collection<Report> reportList = reportDao.findAll();

      Report report = new Report();

      int no = Integer.parseInt(request.getParameter("no"));
      Board requestBoard = boardDao.findByNo(no);

      Member requester = (Member) request.getSession(false).getAttribute("loginUser");

      if(requester == null) {
        throw new Exception("로그인 되어 있지 않습니다.");
      }
      String reason = request.getParameter("reason");
      System.out.println("reason => " + reason);
      String reason2 = request.getParameter("reason2");
      System.out.println("reason2 => " + reason2);

      if(reason2.length() == 0) {
        report.setRequester(requester);
        report.setRequestBoard(requestBoard);
        report.setReason(reason);
        report.setCheck(0);
        reportDao.insert(report);
        sqlSession.commit();

      } else {
        report.setRequester(requester);
        report.setRequestBoard(requestBoard);
        report.setReason(reason2);
        report.setCheck(0);
        reportDao.insert(report);
        sqlSession.commit();
      }

      request.setAttribute("report", report);

      response.sendRedirect("list");

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