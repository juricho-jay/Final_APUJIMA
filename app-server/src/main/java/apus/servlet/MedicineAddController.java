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

@WebServlet("/medicine/add")
public class MedicineAddController extends HttpServlet {
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
      Medicine medicine = new Medicine();

      medicine.setName(request.getParameter("name"));
      medicine.setAgeLimit(Integer.parseInt(request.getParameter("age")));
      medicine.setShape(request.getParameter("shape"));
      medicine.setColor(request.getParameter("color"));
      medicine.setEffect(request.getParameter("effect"));
      medicine.setActive(1);
      medicine.setCheck(1);

      medicineDao.insert(medicine);
      sqlSession.commit();

      request.setAttribute("refresh", "0;url=list");
      request.getRequestDispatcher("list").forward(request, response);

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
