package apus.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import apus.dao.BoardDao;
import apus.domain.Board;

@WebServlet("/board/doctorBoardList")
public class BoardDoctorBoardListController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  BoardDao boardDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    try {
      // 클라이언트 요청을 처리하는데 필요한 데이터 준비
      Collection<Board> boardList = boardDao.findDoctorBoard();

      // 뷰 컴포넌트가 준비한 데이터를 사용할 수 있도록 저장소에 보관한다.
      request.setAttribute("boardList", boardList);

      // 출력을 담당할 뷰를 호출한다.
      //      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/board/BoardList.jsp");
      //      요청배달자.forward(request, response);
      request.setAttribute("contentUrl", "/board/DoctorBoardList.jsp");
      request.getRequestDispatcher("/template3.jsp").forward(request, response);

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
