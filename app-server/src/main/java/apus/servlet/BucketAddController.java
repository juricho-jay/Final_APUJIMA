package apus.servlet;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BucketDao;
import apus.dao.MemberDao;
import apus.domain.Bucket;
import apus.domain.Member;

public class BucketAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  BucketDao bucketDao;
  MemberDao memberDao;
  SqlSession sqlSession;


  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    bucketDao = (BucketDao) 웹애플리케이션공용저장소.getAttribute("bucketDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Bucket bucket = new Bucket();
    Member member = new Member();

    member.setId(request.getParameter("id"));

    String year = request.getParameter("bucketRegisteredYear");
    String month = request.getParameter("bucketRegisteredMonth");
    String day = request.getParameter("bucketRegisteredDay");

    Date bucketRegistered = Date.valueOf(year+"-"+month+"-"+day);


    bucket.setNo(Integer.parseInt(request.getParameter("no")));
    bucket.setTitle(request.getParameter("title"));
    bucket.setContent(request.getParameter("content"));
    bucket.setComplete(Integer.parseInt(request.getParameter("completedDate")));
    bucket.setRegisteredDate(bucketRegistered);
    bucket.setCompletedDate(null);
    bucket.setWriter(member);


    try {
      bucketDao.insert(bucket);
      sqlSession.commit();
      //      response.setHeader("Refresh", "1;url=list");
      request.getRequestDispatcher("BucketList.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      request.setAttribute("error", e);

      // 오류가 발생하면, 오류 내용을 출력할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
      요청배달자.forward(request, response);


    }
  }
}
