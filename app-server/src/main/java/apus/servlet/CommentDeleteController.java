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
import apus.dao.BoardDao;
import apus.dao.CommentDao;
import apus.dao.MemberDao;
import apus.domain.Board;
import apus.domain.Comment;
import apus.domain.Member;

@WebServlet("/board/comment/delete")
public class CommentDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  BoardDao boardDao;
  MemberDao memberDao;
  CommentDao commentDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
    commentDao = (CommentDao)웹애플리케이션공용저장소.getAttribute("commentDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");

  }


  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession(false);


    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/apus/index.jsp");
      return;
    }



    try {
      Member writer = (Member) request.getSession(false).getAttribute("loginUser");

      if(writer == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }
      int no = Integer.parseInt(request.getParameter("no"));
      int no1 = Integer.parseInt(request.getParameter("board_no"));

      Board board = boardDao.findByNo(no1);
      Comment comment = commentDao.findByNo(no);
      if (comment == null) {
        throw new Exception("해당 댓글이 없습니다.");
      }

      commentDao.delete(no);
      sqlSession.commit();
      response.sendRedirect("../../board/detail?no=" +board.getNo());
    }  
    catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
