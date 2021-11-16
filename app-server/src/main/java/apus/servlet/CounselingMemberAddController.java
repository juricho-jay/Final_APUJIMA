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
import apus.dao.CounselingDao;
import apus.dao.MemberDao;
import apus.domain.Counseling;
import apus.domain.Member;

@WebServlet("/counseling/add")
public class CounselingMemberAddController extends HttpServlet{

  private static final long serialVersionUID = 1L;
  CounselingDao counselingDao;
  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    counselingDao = (CounselingDao) 웹애플리케이션공용저장소.getAttribute("counselingDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Counseling counseling = new Counseling();

    //    try {
    //      Collection<Member> memberList = memberDao.findAll();
    //      request.setAttribute("memberList", memberList); // 다 불러와서 jsp파일에 이름이랑 번호 불러오기
    //    } catch (Exception e2) {
    //      e2.printStackTrace();
    //    }

    String user = request.getParameter("client.name");
    String user2 = request.getParameter("client.tel");
    String counselorNo = request.getParameter("counselor.no");

    try {
      Member member = memberDao.findById(user);
      Member member2 = memberDao.findById(user2);
      Member counselor = memberDao.findById(counselorNo);
      counseling.setClient(member);
      counseling.setClientTel(member2);
      counseling.setCounselor(counselor);
    } catch (Exception e1) {
      e1.printStackTrace();
    }



    counseling.setDisease(request.getParameter("disease"));
    counseling.setContent(request.getParameter("content"));

    //    try {
    //      Collection<Member> memberList1 = memberDao.findDoctor();
    //      request.setAttribute("memberList", memberList1);
    //      String user1 = request.getParameter("counselor.member.name");
    //      Member member3 = memberDao.findById(user1);
    //      counseling.setCounselor(member3);
    //    } catch (Exception e1) {
    //      e1.printStackTrace();
    //    }

    //    String userGender = request.getParameter("counselor.sex");
    //    try {
    //      Member member3 = memberDao.findByGender(userGender);
    //      counseling.setCounselorGender(member3);
    //    } catch (Exception e1) {
    //      e1.printStackTrace();
    //    }

    // 이건 디테일에 추가
    //    String userName = request.getParameter("counselor.name");
    //    try {
    //      Member member4 = memberDao.findByName(userName);
    //      counseling.setCounselor(member4);
    //    } catch (Exception e1) {
    //      e1.printStackTrace();
    //    }

    try {

      counselingDao.insert(counseling);
      sqlSession.commit();
      //      response.setHeader("Refresh", "1;url=list");
      //      request.getRequestDispatcher("CounselingMemberAdd.jsp").forward(request, response);
      response.sendRedirect("list");

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

