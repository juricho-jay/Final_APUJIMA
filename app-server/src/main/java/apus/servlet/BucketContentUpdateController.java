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
import apus.dao.BucketDao;
import apus.domain.Bucket;

@WebServlet("/bucket/contentUpdate")
public class BucketContentUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  BucketDao bucketDao;
  SqlSession sqlSession;


  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    bucketDao = (BucketDao) 웹애플리케이션공용저장소.getAttribute("bucketDao");

  }


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {

      int no = Integer.parseInt(request.getParameter("no"));

      Bucket bucket = bucketDao.findByNo(no);

      if (bucket == null) {
        response.sendRedirect("list");
        System.out.println("버킷 null");
        return;
      }

      bucket.setTitle(request.getParameter("title"));
      bucket.setContent(request.getParameter("content"));

      bucketDao.update(bucket);
      sqlSession.commit();

      response.sendRedirect("list");

    } catch (Exception e) {
      e.printStackTrace();
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      request.setAttribute("error", e);

      // 오류가 발생하면, 오류 내용을 출력할 뷰를 호출한다.
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}