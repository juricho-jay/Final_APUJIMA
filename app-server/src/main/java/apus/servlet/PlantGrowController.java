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
import apus.dao.MemberDao;
import apus.dao.PlantDao;
import apus.domain.Member;
import apus.domain.Plant;
// Line 61 : null.

@WebServlet("/plant/grow")
public class PlantGrowController extends HttpServlet {
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

  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession(false);


    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/apus/index.jsp");
      return;
    }


    try {
      Member member = (Member) request.getSession(false).getAttribute("loginUser");

      if(member == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      if (member.getPoint() < 30) {
        response.setHeader("Refresh", "1;url=list");
        request.getRequestDispatcher("PlantError.jsp").forward(request, response);
      }


      int no = Integer.parseInt(request.getParameter("no"));
      Plant plant = plantDao.findByNo(no);

      if(plant == null) {
        throw new Exception("해당 식물이 없습니다.");
      }


      int plusExp = 130;


      plant.setExp(plant.getExp() + plusExp);

      if (plant.getExp() > 150 && plant.getExp() < 260) {
        plant.setShape("leaf.png");
        plant.setLevel(1);
      } else if ( plant.getExp() > 260 && plant.getExp() < 500) {
        plant.setShape("blossom.jpg");
        plant.setLevel(2);
      }else if (plant.getExp() >501 && plant.getExp() < 1000) {
        plant.setShape("Tree.jpg");
        plant.setLevel(3);
      } else if (plant.getExp() > 1000) {
        plant.setExp(1000);
        plant.setShape("Tree.jpg");
      }

      plantDao.update(plant);
      sqlSession.commit();

      response.sendRedirect("../plant/detail?no =" + plant.getNo());

    }catch (Exception e) {
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      request.setAttribute("error", e);
      e.printStackTrace();

      // 오류가 발생하면, 오류 내용을 출력할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
      요청배달자.forward(request, response);
    }
  }
}