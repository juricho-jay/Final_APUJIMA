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

      int no = Integer.parseInt(request.getParameter("no"));
      Board requestBoard = boardDao.findByNo(no);
      String id = request.getParameter("reportId");
      Member requester = memberDao.findById(id);

      Report report = reportDao.findByReport(no, id);

      String reason = request.getParameter("reason");
      String reason2 = request.getParameter("reason2");

      if (report == null) {
        Report adminReport = new Report();

        if(reason2.length() == 0) {
          adminReport.setRequester(requester);
          adminReport.setRequestBoard(requestBoard);
          adminReport.setReason(reason);
          adminReport.setCheck(0);
          reportDao.insert(adminReport);
          sqlSession.commit();
          request.setAttribute("report", adminReport);

        } else {
          adminReport.setRequester(requester);
          adminReport.setRequestBoard(requestBoard);
          adminReport.setReason(reason2);
          adminReport.setCheck(0);
          reportDao.insert(adminReport);
          sqlSession.commit();
          request.setAttribute("report", adminReport);
        }

      } 
      // 신고한 적이 있으면..? 이미 신고하셨습니다 어떻게 띄우지..?
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/board/BoardReport.jsp");
      요청배달자.forward(request, response);

      // request.setAttribute("report", report);
      //      response.sendRedirect("list");

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