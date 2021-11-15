package apus.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSession;
import apus.dao.MemberDao;
import apus.domain.Doctor;
import apus.domain.Member;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/member/add")
public class MemberAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //    System.out.println();
    //    System.out.println("[회원가입] 페이지입니다.");
    Member member = new Member();

    String year = request.getParameter("birthyy");
    String month = request.getParameter("birthmm");
    String day = request.getParameter("birthdd");

    Date birthday = Date.valueOf(year+"-"+month+"-"+day);

    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoPart.write(getServletContext().getRealPath("/upload/member") + "/" + filename);
      member.setPhoto(filename);

      Thumbnails.of(getServletContext().getRealPath("/upload/member") + "/" + filename)
      .size(20, 20)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_20x20";
        }
      });

      Thumbnails.of(getServletContext().getRealPath("/upload/member") + "/" + filename)
      .size(100, 100)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_100x100";
        }
      });
    }



    member.setName(request.getParameter("name"));
    member.setId(request.getParameter("id"));
    member.setPassword(request.getParameter("password"));
    member.setEmail(request.getParameter("email"));
    member.setBirthday(birthday);
    member.setTel(request.getParameter("tel"));
    member.setPhoto(request.getParameter("photo"));
    member.setSex(request.getParameter("sex"));
    member.setPoint(1000);
    String grade = request.getParameter("grade");
    member.setDoctorOrNot(Integer.parseInt(grade));
    member.setNickname(request.getParameter("nickname"));
    member.setActive(1);

    if(member.getDoctorOrNot() == 2) {
      Doctor doctor = new Doctor();
      doctor.setLicense(request.getParameter("major"));
      doctor.setMajor(request.getParameter("lisence"));
      doctor.setHomepage(request.getParameter("homepage"));
      doctor.setIntroduction(request.getParameter("introduce"));
      member.setDoctor(doctor);
    }



    try {
      memberDao.insert(member);

      if(member.getDoctorOrNot() == 2) {
        memberDao.insert2(member);
      }

      sqlSession.commit();
      response.setHeader("Refresh", "1;url=../index.jsp");
      request.getRequestDispatcher("MemberAdd.jsp").forward(request, response);

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
