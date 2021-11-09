package apus.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import apus.dao.CounselingDao;
import apus.domain.Counseling;

@WebServlet("/counselingdoctor/list")
public class CounselingMemberDoctorListController extends HttpServlet{

  private static final long serialVersionUID = 1L;
  CounselingDao counselingDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    counselingDao = (CounselingDao) 웹애플리케이션공용저장소.getAttribute("counselingDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    try {
      List<Counseling> counselingList= counselingDao.findAll();
      request.setAttribute("counselingList", counselingList);

      // 출력을 담당할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/counseling/CounselingDoctorList.jsp");
      요청배달자.forward(request, response);

    } catch (Exception e) {
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      e.printStackTrace();
      request.setAttribute("error", e);

      // 오류가 발생하면, 오류 내용을 출력할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
      요청배달자.forward(request, response);
    }
  }

}
