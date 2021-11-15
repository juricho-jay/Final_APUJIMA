package apus.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/counseling/form")
public class CounselingMemberFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setAttribute("contentUrl", "/counseling/CounselingMemberForm.jsp");
    request.getRequestDispatcher("/darkTemplate.jsp").forward(request, response);      

    // 출력을 담당할 뷰를 호출한다.
    //    request.getRequestDispatcher("/counseling/CounselingMemberForm.jsp").forward(request, response);
  }
}

