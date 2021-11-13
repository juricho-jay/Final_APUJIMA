package apus.servlet;

import java.io.IOException;
import java.util.Date;
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
import apus.dao.DateCheckDao;
import apus.dao.MemberDao;
import apus.domain.DateCheck;
import apus.domain.Member;

@WebServlet("/auth/attendanceCheck")
public class DateCheckAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  DateCheckDao dateCheckDao;
  MemberDao memberDao;
  SqlSession sqlSession;


  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    dateCheckDao = (DateCheckDao) 웹애플리케이션공용저장소.getAttribute("dateCheckDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession(false);


    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/apus/index.jsp");
      return;
    }


    //출석 체크 하는 사람.(LoginUser)
    Member member = (Member) request.getSession(false).getAttribute("loginUser");

    DateCheck dateCheck = new DateCheck();
    Date today = new Date();

    member.setPoint(member.getPoint() + 30);

    dateCheck.setAttendee(member);
    dateCheck.setDate(today);


    try {
      memberDao.insert(member);
      dateCheckDao.insert(dateCheck);


      sqlSession.commit();
      response.setHeader("Refresh", "1;url=/auth/userInfo");
      request.getRequestDispatcher("UserInfoList.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      request.setAttribute("error", e);

      // 오류가 발생하면, 오류 내용을 출력할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
      요청배달자.forward(request, response);

    }
  }
}

