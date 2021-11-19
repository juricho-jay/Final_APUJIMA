package apus.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import apus.dao.CounselingDao;
import apus.dao.MemberDao;
import apus.domain.Counseling;

@WebServlet("/counseling/doctordetail")
public class CounselingMemberDoctorDetailController extends HttpServlet{

  private static final long serialVersionUID = 1L;
  CounselingDao counselingDao;
  MemberDao memberDao;

  @Override
  public void init() {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    counselingDao = (CounselingDao) 웹애플리케이션공용저장소.getAttribute("counselingDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Counseling counseling = counselingDao.findByNo(no);

      if (counseling == null) {
        throw new Exception("해당 번호의 상담이 없습니다.");
      }

      counseling.getClient();
      counseling.getClientTel();
      counseling.getDisease();
      counseling.getContent();
      counseling.getRegisteredDate();

      request.setAttribute("counseling", counseling);
      request.setAttribute("contentUrl", "/counseling/CounselingDoctorDetail.jsp");
      request.getRequestDispatcher("/darkTemplate.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }


  }

}
