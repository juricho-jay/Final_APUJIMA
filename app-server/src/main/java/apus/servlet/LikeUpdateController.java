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
import apus.dao.LikeDao;
import apus.domain.Board;
import apus.domain.Like;
import apus.domain.Member;

@WebServlet("/like/update")
public class LikeUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  LikeDao likeDao;
  BoardDao boardDao;
  SqlSession sqlSession;


  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    likeDao = (LikeDao) 웹애플리케이션공용저장소.getAttribute("likeDao");
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");

  }


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {

      HttpSession session = request.getSession(false);
      if (session.getAttribute("loginUser") == null) {
        response.sendRedirect("/apus/index.jsp");
        return;
      }
      Member member = (Member) request.getSession(false).getAttribute("loginUser");
      int boardNo = Integer.parseInt(request.getParameter("no"));
      Board board = boardDao.findByNo(boardNo);

      // 라이크 누른적 있나 체크
      Like likeCheck = likeDao.findBoardLike(boardNo, member.getNo());


      // 누른 적 없음 - 리스트에 새로 추가
      if (likeCheck == null) {

        Like like = new Like();
        like.setLikeBoard(board);
        like.setLiker(member);
        //likeOrNot은 insert시 자동으로 1로 부여


        likeDao.insert(like);
        sqlSession.commit();
        request.setAttribute("contentUrl", "/board/detail");
        request.getRequestDispatcher("/board/detail").forward(request, response);

        // 누른 적 있음 - 리스트에서 삭제
      } else if (likeCheck != null) {

        likeDao.delete(boardNo, member.getNo());
        sqlSession.commit();
        request.setAttribute("contentUrl", "/board/detail");
        request.getRequestDispatcher("/board/detail").forward(request, response);
      }

    } catch (Exception e) {
      e.printStackTrace();
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      request.setAttribute("error", e);

      // 오류가 발생하면, 오류 내용을 출력할 뷰를 호출한다.
      request.getRequestDispatcher("/Error.jsp").forward(request, response);

    }
  }
}
