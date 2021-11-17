package apus.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import apus.dao.MailBoxDao;
import apus.dao.MemberDao;
import apus.domain.MailBox;
import apus.domain.Member;

@WebServlet("/mailbox/list")
public class MailBoxListController extends HttpServlet{

  private static final long serialVersionUID = 1L;
  MailBoxDao mailBoxDao;
  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    mailBoxDao = (MailBoxDao) 웹애플리케이션공용저장소.getAttribute("mailBoxDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //    HttpSession session = request.getSession(false);
    //
    //
    //    if (session.getAttribute("loginUser") == null) {
    //      request.setAttribute("contentUrl", "/index2.jsp");
    //      request.getRequestDispatcher("/homeTemplate2.jsp").forward(request, response);
    //      return;
    //    }
    try {

      //      Member loginMember = (Member) request.getSession(false).getAttribute("loginUser");

      Collection<Member> memberList = memberDao.findAll();


      // 클라이언트 요청을 처리하는데 필요한 데이터 준비
      List<MailBox> mailBoxList = mailBoxDao.findAll();



      // 뷰 컴포넌트가 준비한 데이터를 사용할 수 있도록 저장소에 보관한다.
      request.setAttribute("mailBoxList", mailBoxList);
      request.setAttribute("memberList", memberList);
      // 출력을 담당할 뷰를 호출한다.
      //      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/mailbox/MailBoxList.jsp");
      //      요청배달자.forward(request, response);

      request.setAttribute("contentUrl", "/mailbox/MailBoxList.jsp");
      request.getRequestDispatcher("/darkTemplate.jsp").forward(request, response);


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
