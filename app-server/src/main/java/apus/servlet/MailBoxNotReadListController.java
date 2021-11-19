package apus.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
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

@WebServlet("/mailbox/notreadlist")
public class MailBoxNotReadListController extends HttpServlet{

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
    try {

      Collection<Member> memberList = memberDao.findAll();

      List<MailBox> mailBoxList = mailBoxDao.findByTime();

      request.setAttribute("mailBoxList", mailBoxList);
      request.setAttribute("memberList", memberList);

      request.setAttribute("pageTitle", "안읽은 쪽지함 목록");
      request.setAttribute("contentUrl", "/mailbox/MailBoxNotReadList.jsp");
      request.getRequestDispatcher("/template4.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
