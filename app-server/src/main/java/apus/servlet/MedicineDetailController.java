package apus.servlet;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import apus.dao.MedicineDao;
import apus.domain.Medicine;

@WebServlet("/medicine/detail")
public class MedicineDetailController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  MedicineDao medicineDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    medicineDao = (MedicineDao) 웹애플리케이션공용저장소.getAttribute("medicineDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {

      int no = Integer.parseInt(request.getParameter("no"));
      System.out.println("넘어온 넘버 => " + no);

      Medicine medicine = medicineDao.findByNo(no);

      if (medicine == null) {
        throw new Exception("해당 이름의 약품이 없습니다.");
      }

      request.setAttribute("medicine", medicine);
      request.getRequestDispatcher("/medicine/MedicineDetail.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      e.printStackTrace();
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
