package apus.servlet;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/auth/logout")
public class AuthLogoutController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) 
      throws  ServletException, IOException {
    System.out.println("-------------------");

    request.getSession().invalidate();

    //    response.setHeader("Refresh", "1;url=/apus/index.jsp");
    //    request.getRequestDispatcher("Logout.jsp").forward(request, response);

    response.sendRedirect("/apus/home");


    //    HttpSession session = request.getSession(false);
    //
    //    if (session.getAttribute("loginUser") == null) {
    //      response.sendRedirect("/drinker/login/menu");
    //      return;
    //    }
    //
    //    try {
    //      Member writer = (Member) request.getSession(false).getAttribute("loginUser");

  }
}