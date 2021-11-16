package apus.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import apus.dao.MemberDao;
import apus.domain.Member;

@WebServlet("/counseling/form")
public class CounselingMemberFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int counselorNo = Integer.parseInt(request.getParameter("counselorNo"));
    try {
      Member counselor = memberDao.findByNo(counselorNo);
      request.setAttribute("counselor", counselor);

      request.setAttribute("contentUrl", "/counseling/CounselingMemberForm.jsp");
      request.getRequestDispatcher("/darkTemplate.jsp").forward(request, response);      
    } catch (Exception e) {
      e.printStackTrace();
    }

    // 출력을 담당할 뷰를 호출한다.
    //    request.getRequestDispatcher("/counseling/CounselingMemberForm.jsp").forward(request, response);
  }
}

