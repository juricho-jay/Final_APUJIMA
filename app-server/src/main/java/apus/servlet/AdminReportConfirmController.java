package apus.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BoardDao;
import apus.dao.MemberDao;
import apus.dao.ReportDao;
import apus.domain.Report;

@WebServlet("/admin/reportConfirm")
public class AdminReportConfirmController extends HttpServlet {
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
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    try {

      int no = Integer.parseInt(request.getParameter("no"));
      String id = request.getParameter("id");

      Report report = reportDao.findByReport(no, id);
      int boardNo = report.getRequestBoard().getNo();

      //System.out.println("신고 번호 => " + report.getNo());
      reportDao.confirmReport(report.getNo());

      //System.out.println("삭제 게시판 번호 => " + boardNo);
      boardDao.delete(boardNo);

      sqlSession.commit();


      // 출력을 담당할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("approvalReport");
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