package apus.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import apus.dao.CounselingDao;
import apus.dao.MemberDao;
import apus.domain.Counseling;
import apus.domain.Member;

@WebServlet("/counseling/add")
public class CounselingMemberAddController extends HttpServlet{

  private static final long serialVersionUID = 1L;
  CounselingDao counselingDao;
  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init() {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    counselingDao = (CounselingDao) 웹애플리케이션공용저장소.getAttribute("counselingDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Counseling counseling = new Counseling();

      HttpSession session = request.getSession(false);

      if (session.getAttribute("loginUser") == null) {
        response.sendRedirect("/apus/index.jsp");
        return;
      }

      int no = Integer.parseInt(request.getParameter("no")); // counselor.no
      Member client = (Member) request.getSession(false).getAttribute("loginUser");

      if(client == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      Member doctor = memberDao.findByNo(no);

      counseling.setClient(client);
      counseling.setClientTel(client.getTel());
      counseling.setCounselor(doctor);
      counseling.setDisease(request.getParameter("disease"));
      counseling.setContent(request.getParameter("content"));

      counselingDao.insert(counseling);
      sqlSession.commit();
      response.sendRedirect("list");

    } catch (Exception e) {

      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      request.setAttribute("error", e);

      // 오류가 발생하면, 오류 내용을 출력할 뷰를 호출한다.
      request.getRequestDispatcher("/Error.jsp").forward(request, response);

    }




  }
}

