package apus.servlet;

import java.io.IOException;
import java.util.Collection;
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
import apus.dao.LikeDao;
import apus.dao.MemberDao;
import apus.domain.Board;
import apus.domain.Comment;
import apus.domain.Like;
import apus.domain.Member;

@WebServlet("/board/detail")
public class BoardDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  BoardDao boardDao;
  MemberDao memberDao;
  CommentDao commentDao;
  LikeDao likeDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
    commentDao = (CommentDao)웹애플리케이션공용저장소.getAttribute("commentDao");
    likeDao = (LikeDao)웹애플리케이션공용저장소.getAttribute("likeDao");
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
    Member member = (Member) request.getSession(false).getAttribute("loginUser");

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Board board = boardDao.findByNo(no);
      Like like = likeDao.findBoardLike(no, member.getNo());

      if (board == null) {
        throw new Exception("해당 번호의 게시글이 없습니다.");
      }

      //누른 적 없음
      if (like == null) {
        request.setAttribute("likeOrNot", "0");
      } else if (like != null) {
        request.setAttribute("likeOrNot", like.getLikeOrNot());
      }

      Collection<Comment> commentList = commentDao.findBoardComment(board.getNo());
      Collection<Like> likeList = likeDao.findBoardCount(board.getNo());

      //해당 게시물의 좋아요/댓글 갯수
      int likeNo = likeList.size();
      int commentNo = commentList.size();

      boardDao.updateCount(board.getNo());
      sqlSession.commit();

      request.setAttribute("commentList", commentList);
      request.setAttribute("board", board);
      request.setAttribute("likeNo", likeNo);
      request.setAttribute("commentNo", commentNo);

      request.setAttribute("contentUrl", "/board/BoardDetail.jsp");
      request.getRequestDispatcher("/template2.jsp").forward(request, response);
    }  
    catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
