package apus.servlet;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import apus.dao.MailBoxDao;
import apus.dao.MemberDao;
import apus.domain.MailBox;
import apus.domain.Member;

@WebServlet("/mailbox/send")
public class MailBoxSendController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MailBoxDao mailBoxDao;
  SqlSession sqlSession;
  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    mailBoxDao = (MailBoxDao) 웹애플리케이션공용저장소.getAttribute("mailBoxDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    MailBox mailBox = new MailBox();

    String user = request.getParameter("receiver.nickname");
    String user2 = request.getParameter("sender.nickname");
    try {
      Member member = memberDao.findByNickname(user);
      Member member2 = memberDao.findByNickname(user2);
      mailBox.setReceiver(member);
      mailBox.setSender(member2);
      //      System.out.println("존재하는 회원");
      //      if (member == null) {
      //        System.out.println("존재하지 않는 회원입니다.");
      //        return;
      //      }
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    mailBox.setTitle(request.getParameter("title"));
    mailBox.setContent(request.getParameter("content"));
    mailBox.setSentTime(new Date(System.currentTimeMillis()));

    try {
      mailBoxDao.insert(mailBox);
      sqlSession.commit();
      response.setHeader("Refresh", "2;url=list");
      request.getRequestDispatcher("MailBoxSend.jsp").forward(request, response);

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