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
import apus.dao.MedicineDao;
import apus.domain.Medicine;

@WebServlet("/medicine/delete")
public class MedicineDeleteController extends HttpServlet{
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  MedicineDao medicineDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    medicineDao = (MedicineDao) 웹애플리케이션공용저장소.getAttribute("medicineDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      String name = request.getParameter("name");
      Medicine medicine = medicineDao.findByName(name);
      if (medicine == null) {
        throw new Exception("해당 아이디의 회원이 없습니다.");
      }

      medicineDao.delete(name);
      sqlSession.commit();

      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}