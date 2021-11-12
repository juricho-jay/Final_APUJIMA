package apus.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import apus.dao.MemberDao;
import apus.dao.PlantDao;
import apus.domain.Plant;

@WebServlet("/plant/detail")
public class PlantDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  PlantDao plantDao;
  MemberDao memberDao;
  SqlSession sqlSession;


  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    plantDao = (PlantDao) 웹애플리케이션공용저장소.getAttribute("plantDao");
  }

  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession(false);


    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/apus/index.jsp");
      return;
    }



    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Plant plant = plantDao.findByNo(no);

      if(plant == null) {
        throw new Exception("해당 식물이 없습니다.");
      }



      request.setAttribute("plant", plant);

      request.getRequestDispatcher("/plant/PlantDetail.jsp").forward(request, response);

    }  
    catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

