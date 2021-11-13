package apus.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BoardDao;
import apus.domain.Board;

@WebServlet("/board/update")
public class BoardUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  BoardDao boardDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");

  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {

      int no = Integer.parseInt(request.getParameter("no"));
      Board board = boardDao.findByNo(no);

      if (board == null) {
        throw new Exception("해당 번호의 게시글이 없습니다.");
      }

      String title = request.getParameter("title");
      String content = request.getParameter("content");
      board.setTitle(title);
      board.setContent(content);
      boardDao.update(board);
      sqlSession.commit();
      request.getRequestDispatcher("/board/BoardUpdate.jsp").forward(request,response);
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}







