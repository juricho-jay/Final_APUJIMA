package apus.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import apus.dao.MemberDao;
import apus.domain.Member;

@WebServlet("/doctorinfo/list")
public class DoctorMemberListController extends HttpServlet{
  private static final long serialVersionUID = 1L;
  MemberDao memberDao;

  @Override
  public void init() {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      List<Member> memberList = memberDao.findAll();
      request.setAttribute("memberList", memberList);

      request.setAttribute("contentUrl", "/counseling/DoctorInfo.jsp");
      request.getRequestDispatcher("/darkTemplate.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }

}

