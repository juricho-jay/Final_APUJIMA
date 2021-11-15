package apus.servlet;

import java.io.IOException;
import java.util.UUID;
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
@WebServlet("/member/update")
public class MemberUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  MemberDao memberDao;

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      System.out.println("업데이트에 넘어온 멤버 no => " + no);

      Member member = memberDao.findByNo(no);
      System.out.println("doctor or not => " + member.getDoctorOrNot());

      member.setName(request.getParameter("name"));
      member.setId(request.getParameter("id"));
      member.setEmail(request.getParameter("email"));
      member.setNickname(request.getParameter("nickname"));
      member.setTel(request.getParameter("tel"));
      member.setSex(request.getParameter("sex"));

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

      if (member.getDoctorOrNot() == 2) {
        Doctor doctor = member.getDoctor();
        doctor.setMajor(request.getParameter("major"));
        doctor.setHomepage(request.getParameter("homepage"));
        doctor.setIntroduction(request.getParameter("introduce"));

        Part licensePart = request.getPart("license");
        if (licensePart.getSize() > 0) {
          String filename = UUID.randomUUID().toString();
          licensePart.write(getServletContext().getRealPath("/upload/member") + "/" + filename);
          doctor.setLicense(filename);

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
        member.setDoctor(doctor);

      }

      String password = request.getParameter("password");
      System.out.println("넘어온 password => " + password);
      if (password.length() == 0) {
        memberDao.update2(member);
        sqlSession.commit();
      } else {
        member.setPassword(request.getParameter("password"));
        memberDao.update(member);
        sqlSession.commit();
      }


      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      e.printStackTrace();
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}







