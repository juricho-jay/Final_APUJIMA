package apus.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BucketDao;
import apus.dao.MemberDao;
import apus.domain.Bucket;
import apus.domain.Member;

@WebServlet("/bucket/add")
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
    try {
      Bucket bucket = new Bucket();

      String id = request.getParameter("id");

      Member member = memberDao.findById(id);




      // id > member 타입 : 지금은 객체 생성하지만 이후에 멤버 불러올 수 있도록 하기
      // 버킷 리스트 완료하는 순간 > 완료 시간 저장 > update로 변경 add 아님
      // null 허용이라 처음 버킷 작성할 때 null
      // String year = request.getParameter("bucketRegisteredYear");
      // String month = request.getParameter("bucketRegisteredMonth");
      // String day = request.getParameter("bucketRegisteredDay");
      //
      // Date bucketCompletedDt = Date.valueOf(year+"-"+month+"-"+day);


      bucket.setTitle(request.getParameter("title"));
      bucket.setContent(request.getParameter("content"));
      bucket.setWriter(member);



      try {
        bucketDao.insert(bucket);
        sqlSession.commit();
        response.setHeader("Refresh", "0;url=list");
        request.getRequestDispatcher("BucketList.jsp").forward(request, response);

      } catch (Exception e) {
        e.printStackTrace();
        // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
        request.setAttribute("error", e);

        // 오류가 발생하면, 오류 내용을 출력할 뷰를 호출한다.
        RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
        요청배달자.forward(request, response);


      }
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }
}
