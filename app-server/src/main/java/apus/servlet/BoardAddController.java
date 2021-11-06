package apus.servlet;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BoardDao;
import apus.dao.MemberDao;
import apus.domain.Board;
import apus.domain.Member;

@WebServlet("/board/add")
public class BoardAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  BoardDao boardDao;
  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");

  }


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException { 
    Member writer = memberDao.findById(AuthLoginController.getLoginUser().getId());
    Board board = new Board();
    int whichBoard = Integer.parseInt(request.getParameter("whichBoard"));
    //    String convertDate = request.getParameter("birthDay");
    //    SimpleDateFormat convertDate2 = new SimpleDateFormat("yyyy-MM-dd");
    //
    //    Date bDate = convertDate2.parse(convertDate);

    String year = request.getParameter("birthyy");
    String month = request.getParameter("birthmm");
    String day = request.getParameter("birthdd");
    Date birthday = Date.valueOf(year+"-"+month+"-"+day);

    board.setTitle(request.getParameter("title"));
    board.setWriter(writer);
    board.setContent(request.getParameter("content"));
    board.setWhichBoard(whichBoard);
    board.setRegisteredDate(birthday);


    /*
     board.setComment/like.. detail에서 보여주는게 맞지않나../
     */



    try {
      boardDao.insert(board);
      sqlSession.commit();
      response.setHeader("Refresh", "1;url=list");
      request.getRequestDispatcher("MemberAdd.jsp").forward(request, response);

    } catch (Exception e) {
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      request.setAttribute("error", e);

      // 오류가 발생하면, 오류 내용을 출력할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
      요청배달자.forward(request, response);







    }
  }
}
