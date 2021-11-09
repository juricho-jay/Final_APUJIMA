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
import apus.dao.MemberDao;
import apus.domain.Bucket;

@WebServlet("/bucket/delete")
public class BucketDeleteController extends HttpServlet{
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  MemberDao memberDao;
  BucketDao bucketDao;

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
      //      String stringNo = request.getParameter("no");
      //      int no = Integer.parseInt(stringNo);
      //
      //      Bucket bucket = bucketDao.findByNo(no);
      //      if (bucket == null) {
      //        throw new Exception("해당 아이디의 회원이 없습니다.");
      //      }
      //
      //      bucketDao.delete(no);
      //      sqlSession.commit();
      //
      //      response.sendRedirect("list");
      Bucket bucket = null;
      String stringNo = "";
      int no = 0;
      String[] noList = request.getParameterValues("no");

      for(int i=0;i<noList.length;i++) {
        //        System.out.println(noList[i]);
        stringNo = noList[i];
        no = Integer.parseInt(stringNo);
        System.out.println(no);
        bucket = bucketDao.findByNo(no);

        if (bucket == null) {
          throw new Exception("해당 아이디의 회원이 없습니다.");
        }

        bucketDao.delete(no);
        sqlSession.commit();
      }

      response.sendRedirect("list");

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}