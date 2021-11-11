package apus.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import apus.dao.MemberDao;
import apus.domain.Member;

@WebServlet("/member/check")
public class MemberIdCheckController extends HttpServlet{

  private static final long serialVersionUID = 1L;
  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    try {
      // 클라이언트 요청을 처리하는데 필요한 데이터 준비
      String id = request.getParameter("id");
      Member member = memberDao.findById(request.getParameter("id"));
      // Collection<Member> memberList = memberDao.findAll();
      // 뷰 컴포넌트가 준비한 데이터를 사용할 수 있도록 저장소에 보관한다.

      request.setAttribute("inputId", id);
      if (member != null) {

        request.setAttribute("member", member);
      }
      //   request.setAttribute("memberList", memberList);

      // 출력을 담당할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/member/idCheckPro.jsp");
      요청배달자.forward(request, response);

    } catch (Exception e) {
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      request.setAttribute("error", e);

      // 오류가 발생하면, 오류 내용을 출력할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
      요청배달자.forward(request, response);
    }
  }

}
