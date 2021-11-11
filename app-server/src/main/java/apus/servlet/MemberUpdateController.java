package apus.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import apus.dao.MemberDao;
import apus.domain.Member;

@WebServlet("/member/update")
public class MemberUpdateController extends HttpServlet {
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

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Member member = memberDao.findByNo(no);

      if (member == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      } 

      if (member.getDoctorOrNot() == 1) {

        member.setName(request.getParameter("name"));
        member.setId(request.getParameter("id"));
        member.setEmail(request.getParameter("email"));
        member.setNickname(request.getParameter("nickname"));
        member.setPhoto(request.getParameter("photo"));
        member.setTel(request.getParameter("tel"));
        member.setSex(request.getParameter("sex"));
        String password = request.getParameter("password");
        if (password.length() == 0) {
          memberDao.update2(member);
          sqlSession.commit();
        } else {
          member.setPassword(request.getParameter("password"));
          memberDao.update(member);
          sqlSession.commit();
        }

      } else if (member.getDoctorOrNot() == 2) {

        member.getDoctor().setMajor(request.getParameter("major"));
        member.getDoctor().setLicense(request.getParameter("license"));
        member.getDoctor().setIntroduction(request.getParameter("introduce"));
        member.getDoctor().setHomepage(request.getParameter("homepage"));
        member.setName(request.getParameter("name"));
        member.setId(request.getParameter("id"));
        member.setEmail(request.getParameter("email"));
        member.setNickname(request.getParameter("nickname"));
        member.setPhoto(request.getParameter("photo"));
        member.setTel(request.getParameter("tel"));
        member.setSex(request.getParameter("sex"));
        String password = request.getParameter("password");
        if (password.length() == 0) {
          memberDao.doctorUpdate2(member);
          sqlSession.commit();
        } else {
          member.setPassword(request.getParameter("password"));
          memberDao.doctorUpdate(member);
          sqlSession.commit();
        }




        memberDao.update(member);
        sqlSession.commit();

      }

      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      e.printStackTrace();
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}







