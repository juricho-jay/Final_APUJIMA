package apus.servlet;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.ibatis.session.SqlSession;
import apus.dao.MailBoxDao;
import apus.domain.MailBox;

@WebServlet("/mailbox/detail")
public class MailBoxDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MailBoxDao mailBoxDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    mailBoxDao = (MailBoxDao) 웹애플리케이션공용저장소.getAttribute("mailBoxDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      MailBox mailBox = mailBoxDao.findByNo(no);

      if (mailBox == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      mailBox.getSender();
      mailBox.getTitle();
      mailBox.getContent();
      mailBox.getSentTime();
      mailBox.setReceivedTime(new Date(System.currentTimeMillis()));

      mailBoxDao.update(mailBox);
      sqlSession.commit();
      request.setAttribute("mailBox", mailBox);

      request.setAttribute("contentUrl", "/mailbox/MailBoxDetail.jsp");
      request.getRequestDispatcher("/darkTemplate.jsp").forward(request, response);

    } catch (Exception e) {
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}