package apus.servlet;

import java.io.IOException;
import java.util.Collection;
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

@WebServlet("/plant/list")
public class PlantListController  extends HttpServlet {
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

  @Override
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
      Collection<Plant> plantList = plantDao.findMyPlant(member.getNo());

      // 뷰 컴포넌트가 준비한 데이터를 사용할 수 있도록 저장소에 보관한다.
      request.setAttribute("plantList", plantList);

      // 출력을 담당할 뷰를 호출한다.
      //      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/plant/PlantList.jsp");
      //      요청배달자.forward(request, response);

      request.setAttribute("contentUrl", "/plant/PlantList.jsp");
      request.getRequestDispatcher("/darkTemplate.jsp").forward(request, response);


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


/*

    while (true) {
      String input = Prompt.inputString("[검색(S) / 뒤로가기(0)] ");

      switch (input) {
        case "S":
        case "s":
          System.out.println("[화분 상세검색] 페이지입니다. ");
          String find = Prompt.inputString("화분/아이디 검색> ");
          for (Plant findplantList : plantList) {
            if (!findplantList.getOwnerName().getId().contains(find) && 
                !findplantList.getPlantName().contains(find)) {
              continue;
            }

            findCount++;
            System.out.println();
            System.out.println("화분 이름: " + findplantList.getPlantName());
            System.out.println("화분 주인: " + findplantList.getOwnerName().getId());
            System.out.println("화분 누적 경험치: " + findplantList.getExp());
            System.out.println("화분 레벨: " + findplantList.getLevel());
            System.out.println("화분 모양: " + findplantList.getShape());
            System.out.println("화분 생성일: " + findplantList.getRegisteredDate());
            System.out.println();
          } 

          if (findCount == 0) {
            System.out.println("입력한 아이디나 화분이 없습니다.");
            return;
          }
          break;

        case "0":
          return;
        default :
          System.out.println("명령어가 올바르지 않습니다!");
      }
    }


  }



}
 */