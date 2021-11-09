package apus.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import apus.dao.BoardDao;
import apus.dao.CommentDao;
import apus.dao.MemberDao;
import apus.domain.Board;
import apus.domain.Comment;

@WebServlet("/board/detail")
public class BoardDetailController extends GenericServlet {
  private static final long serialVersionUID = 1L;
  BoardDao boardDao;
  MemberDao memberDao;
  CommentDao commentDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
    commentDao = (CommentDao)웹애플리케이션공용저장소.getAttribute("commentDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      //Member member = memberDao.findByNo(no);

      Board board = boardDao.findByNo(no);

      if(board == null) {
        throw new Exception("해당 번호의 게시글이 없습니다.");
      }

      Collection<Comment> commentList = commentDao.findBoardComment(board.getNo());




      request.setAttribute("commentList", commentList);

      request.setAttribute("board", board);

      request.getRequestDispatcher("/board/BoardDetail.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
