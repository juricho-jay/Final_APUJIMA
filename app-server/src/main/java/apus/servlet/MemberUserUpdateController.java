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
import org.apache.ibatis.session.SqlSession;
import apus.dao.MemberDao;
import apus.domain.Doctor;
import apus.domain.Member;

@WebServlet("/auth/userUpdate")
public class MemberUserUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
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
    Doctor doctor = member.getDoctor();

    try {

      member.setNickname(request.getParameter("nickname"));
      member.setEmail(request.getParameter("email"));
      member.setPhoto(request.getParameter("photo"));
      member.setTel(request.getParameter("tel"));

      if (member.getDoctorOrNot() == 2) {
        //        doctor.setMajor(request.getParameter("major"));
        doctor.setHomepage(request.getParameter("homepage"));
        doctor.setLicense(request.getParameter("license"));
        doctor.setIntroduction(request.getParameter("introduction"));
        member.setDoctor(doctor);
      }

      memberDao.update2(member);
      sqlSession.commit();

      request.getRequestDispatcher("userInfoList").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}







