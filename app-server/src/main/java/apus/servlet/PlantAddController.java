package apus.servlet;

import java.io.IOException;
import java.util.List;
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

@WebServlet("/plant/add")
public class PlantAddController extends HttpServlet {
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

      String input = request.getParameter("name");
      int count = 0;
      List<Plant> plantList = plantDao.findAll();
      for (int i = 0; i < plantList.size(); i++) {
        if (plantList.get(i).getOwnerName().getId().equals(member.getId())) {
          if(input.equals(plantList.get(i).getPlantName())) {
            System.out.println("화분 이름이 중복되어 만들 수 없습니다. 다시 입력해 주세요");
            count++;
          }
        }
      }
      if (count == 0) {

        Plant plant = new Plant();
        plant.setOwnerName(member);
        plant.setPlantName(input);
        plant.setLevel(0);
        plant.setExp(0);
        plant.setShape("🌱");

        member.setPoint(member.getPoint()-30);


        plantDao.insert(plant);
        memberDao.update2(member);
        sqlSession.commit();
        response.setHeader("Refresh", "1;url=list");
        request.getRequestDispatcher("PlantAdd.jsp").forward(request, response);
      }
      count = 0;
    }catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }

  }
}
// js 스크립트 처리 or java에서 처리
//        for (int i = 0; i < plantList.size(); i++) {
//          if (plantList.get(i).getOwnerName().getId().equals(AuthLoginHandler.getLoginUser().getId())) {
//            if(input.equals(plantList.get(i).getPlantName())) {
//              System.out.println("화분 이름이 중복되어 만들 수 없습니다. 다시 입력해 주세요");
//              count++;




