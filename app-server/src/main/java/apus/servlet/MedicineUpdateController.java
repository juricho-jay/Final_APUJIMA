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
import org.apache.ibatis.session.SqlSession;
import apus.dao.MedicineDao;
import apus.domain.Medicine;

@WebServlet("/medicine/update")
public class MedicineUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MedicineDao medicineDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    medicineDao = (MedicineDao) 웹애플리케이션공용저장소.getAttribute("medicineDao");
  }


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //    System.out.println();
    //    System.out.println("[회원가입] 페이지입니다.");
    try {
      int no = Integer.parseInt(request.getParameter("no"));
      System.out.println(no);
      System.out.println("여기나오나 0");
      Medicine medicine = medicineDao.findByNo(no);
      System.out.println("여기나오나 1");

      medicine.setName(request.getParameter("name"));
      System.out.println("여기나오나 2");
      medicine.setAgeLimit(Integer.parseInt(request.getParameter("age")));
      medicine.setShape(request.getParameter("shape"));
      medicine.setColor(request.getParameter("color"));
      medicine.setEffect(request.getParameter("effect"));
      System.out.println("여기나오나 3");

      medicineDao.update(medicine);
      sqlSession.commit();

      response.sendRedirect("list");


    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
      요청배달자.forward(request, response);


    }
  }
}
