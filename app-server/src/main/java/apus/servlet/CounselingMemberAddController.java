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



    //    String user = request.getParameter("client.name");
    //    String user2 = request.getParameter("client.phoneNum");
    //    try {
    //      Member member = memberDao.findByName(user);
    //      Member member2 = memberDao.findByTel(user2);
    //      counseling.setClient(member);
    //      counseling.setClientTel(member2);
    //    } catch (Exception e1) {
    //      // TODO Auto-generated catch block
    //      e1.printStackTrace();
    //    }
    counseling.setDisease(request.getParameter("disease"));
    counseling.setContent(request.getParameter("content"));

    String userGender = request.getParameter("counselor.sex");
    try {
      Collection<Member> memberList1 = memberDao.findDoctor();
      Collection<Member> memberList2 = memberDao.findWoman();
      Collection<Member> memberList3 = memberDao.findMan();
      request.setAttribute("memberList", memberList1);
      request.setAttribute("memberList", memberList2);
      request.setAttribute("memberList", memberList3);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

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
      response.setHeader("Refresh", "1;url=list");
      request.getRequestDispatcher("CounselingMemberAdd.jsp").forward(request, response);

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

