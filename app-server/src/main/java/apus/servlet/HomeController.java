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
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BoardDao;
import apus.dao.MailBoxDao;
import apus.dao.MemberDao;
import apus.domain.Board;
import apus.domain.MailBox;
import apus.domain.Member;


@WebServlet("/home")
public class HomeController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  BoardDao boardDao;
  MemberDao memberDao;
  MailBoxDao mailBoxDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    mailBoxDao = (MailBoxDao) 웹애플리케이션공용저장소.getAttribute("mailBoxDao");

  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      HttpSession session = request.getSession(false);

      if (session.getAttribute("loginUser") != null) {
        Member member = (Member) request.getSession(false).getAttribute("loginUser");

        Collection<Board> boardList = boardDao.findAll();


        //안읽은 메일 체크
        List<MailBox> mailBoxList = mailBoxDao.findAll();
        int count = 0;
        for (int i = 0; i < mailBoxList.size(); i++) {
          if (member.getNickname().equals(mailBoxList.get(i).getReceiver().getNickname())) {
            if (mailBoxList.get(i).getReceivedTime() == null) {
              count++;
            }
          }
        }
        request.setAttribute("uncheckedMail", count);


        request.setAttribute("boardList", boardList);
        request.setAttribute("member", member);


        request.setAttribute("pageTitle", "메인화면");
        request.setAttribute("contentUrl", "/index2.jsp");
        request.getRequestDispatcher("/homeTemplate.jsp").forward(request, response);
        return;
      }

      Collection<Board> boardList = boardDao.findAll();

      request.setAttribute("boardList", boardList);
      request.setAttribute("pageTitle", "메인화면");
      request.setAttribute("contentUrl", "/index2.jsp");
      request.getRequestDispatcher("/homeTemplate.jsp").forward(request, response);





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