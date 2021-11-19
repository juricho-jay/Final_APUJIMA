package apus.servlet;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
  public void init() {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    mailBoxDao = (MailBoxDao) 웹애플리케이션공용저장소.getAttribute("mailBoxDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      MailBox mailBox = new MailBox();

      HttpSession session = request.getSession(false);

      if (session.getAttribute("loginUser") == null) {
        response.sendRedirect("/apus/index.jsp");
        return;
      }

      Member sender = (Member) request.getSession(false).getAttribute("loginUser");

      if(sender == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      String receiver = request.getParameter("receiver.nickname");
      //String user2 = request.getParameter("sender.nickname");
      Member member = memberDao.findByNickname(receiver);
      //Member member2 = memberDao.findByNickname(user2);
      mailBox.setReceiver(member);
      mailBox.setSender(sender);
      mailBox.setTitle(request.getParameter("title"));
      mailBox.setContent(request.getParameter("content"));
      mailBox.setSentTime(new Date(System.currentTimeMillis()));

      mailBoxDao.insert(mailBox);
      sqlSession.commit();

      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }



  }
}