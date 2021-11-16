package apus.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import apus.dao.BoardDao;
import apus.dao.BucketDao;
import apus.dao.CommentDao;
import apus.dao.CounselingDao;
import apus.dao.DateCheckDao;
import apus.dao.LikeDao;
import apus.dao.MailBoxDao;
import apus.dao.MedicineDao;
import apus.dao.MemberDao;
import apus.dao.PlantDao;
import apus.dao.ReportDao;

@WebListener
public class AppInitListener implements ServletContextListener {

  SqlSession sqlSession = null;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("애플리케이션 시작됨!");

    try {
      // Mybatis의 SqlSession 객체 준비
      sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(
          "apus/conf/mybatis-config.xml")).openSession();


      // SqlSession 객체를 통해 MemberDao 구현체를 자동 생성한다.
      MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      BucketDao bucketDao = sqlSession.getMapper(BucketDao.class);
      MailBoxDao mailBoxDao = sqlSession.getMapper(MailBoxDao.class);
      MedicineDao medicineDao = sqlSession.getMapper(MedicineDao.class);
      CounselingDao counselingDao = sqlSession.getMapper(CounselingDao.class);
      CommentDao commentDao = sqlSession.getMapper(CommentDao.class);
      DateCheckDao dateCheckDao = sqlSession.getMapper(DateCheckDao.class);
      PlantDao plantDao = sqlSession.getMapper(PlantDao.class);
      LikeDao likeDao = sqlSession.getMapper(LikeDao.class);
      ReportDao reportDao = sqlSession.getMapper(ReportDao.class);

      // 모든 웹 애플리케이션의 컴포넌트(서블릿, 리스너, 필터)가 공유할 객체를 두는 저장소
      ServletContext 웹애플리케이션공용저장소 = sce.getServletContext();


      // 웹 애플리케이션 공용 저장소에 DAO 객체를 보관한다.
      // => 이 저장소에 보관된 객체는 서블릿에서 사용할 것이다.
      웹애플리케이션공용저장소.setAttribute("memberDao", memberDao);
      웹애플리케이션공용저장소.setAttribute("boardDao", boardDao);
      웹애플리케이션공용저장소.setAttribute("bucketDao", bucketDao);
      웹애플리케이션공용저장소.setAttribute("mailBoxDao", mailBoxDao);
      웹애플리케이션공용저장소.setAttribute("medicineDao", medicineDao);
      웹애플리케이션공용저장소.setAttribute("counselingDao", counselingDao);
      웹애플리케이션공용저장소.setAttribute("commentDao", commentDao);
      웹애플리케이션공용저장소.setAttribute("dateCheckDao", dateCheckDao);      
      웹애플리케이션공용저장소.setAttribute("plantDao", plantDao);
      웹애플리케이션공용저장소.setAttribute("likeDao", likeDao);
      웹애플리케이션공용저장소.setAttribute("reportDao", reportDao);
      웹애플리케이션공용저장소.setAttribute("sqlSession", sqlSession);     

      ServletContext sc = sce.getServletContext();
      sc.setAttribute("contextPath", sc.getContextPath());

      ServletContext sc = sce.getServletContext();
      sc.setAttribute("contextPath", sc.getContextPath());

    } catch (Exception e) {
      System.out.println("DAO 객체 준비 중 오류 발생!");
    }

  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("애플리케이션 종료됨!");

    sqlSession.close();
  }
}
