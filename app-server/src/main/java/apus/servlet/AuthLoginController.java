package apus.servlet;



import java.io.IOException;
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
import apus.domain.Member;

@WebServlet("/auth/login")
public class AuthLoginController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  public static Member loginUser;

  public static Member getLoginUser() {
    return loginUser;
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) 
      throws  ServletException, IOException {

    try {

      String id = request.getParameter("id");
      String password = request.getParameter("password");
      Member member = memberDao.findByIdPwd(id, password);

      if (member == null) {
        System.out.println("이메일과 암호가 일치하는 회원을 찾을 수 없습니다.");
        request.getRequestDispatcher("/LoginError.jsp").forward(request, response);
      }

      else {
        HttpSession session = request.getSession(); //멀티쓰레드
        session.setAttribute("loginUser", member);

        response.setHeader("Refresh", "2;url=/apus/loginindex.jsp");
        request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
      }


    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/LoginError.jsp").forward(request, response);
    }


  }

}

