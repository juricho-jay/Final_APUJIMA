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
import apus.dao.MedicineDao;
import apus.dao.MemberDao;
import apus.domain.Medicine;
import apus.domain.Member;

@WebServlet("/board/report")
public class BoardReportController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MedicineDao medicineDao;
  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    medicineDao = (MedicineDao) 웹애플리케이션공용저장소.getAttribute("medicineDao");
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
      Medicine medicine = new Medicine();
      Member requester = (Member) request.getSession(false).getAttribute("loginUser");

      medicine.setName(request.getParameter("name"));
      String age = request.getParameter("age");
      medicine.setAgeLimit(Integer.parseInt(age));
      medicine.setShape(request.getParameter("shape"));
      medicine.setColor(request.getParameter("color"));
      medicine.setEffect(request.getParameter("effect"));
      medicine.setRequester(requester);
      medicine.setActive(0);
      medicine.setCheck(0);

      medicineDao.insert(medicine);
      sqlSession.commit();


      // 출력을 담당할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/medicine/list");
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