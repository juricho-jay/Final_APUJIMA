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
import apus.domain.Bucket;

@WebServlet("/bucket/update")
public class BucketUpdateController extends HttpServlet {
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
      Bucket bucket = new Bucket();

      String stringComplete = request.getParameter("complete");

      if (stringComplete == null) {
        response.setHeader("Refresh", "0;url=list");
        request.getRequestDispatcher("BucketList.jsp").forward(request, response);
      }

      int complete = Integer.parseInt(stringComplete);

      String stringNo = request.getParameter("no");
      int no = Integer.parseInt(stringNo);
      bucket = bucketDao.findByNo(no);


      System.out.println(complete);
      System.out.println(no);


      if (bucket == null) {
        System.out.println("번호 없음");
        response.setHeader("Refresh", "0;url=list");
        request.getRequestDispatcher("BucketList.jsp").forward(request, response);
      }

      try {
        if (complete == 0) {
          bucketDao.completeUpdate(bucket);
          sqlSession.commit();
          response.setHeader("Refresh", "0;url=list");
          request.getRequestDispatcher("BucketList.jsp").forward(request, response);

        } else if (complete == 1) {
          bucket.setComplete(0);
          bucketDao.cancelCompleteUpdate(bucket);
          sqlSession.commit();
          response.setHeader("Refresh", "0;url=list");
          request.getRequestDispatcher("BucketList.jsp").forward(request, response);
        }
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
