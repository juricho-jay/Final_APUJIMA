package apus.web;

import java.util.Collection;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.DateCheckDao;
import apus.dao.MemberDao;
import apus.domain.DateCheck;
import apus.domain.Member;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class AuthController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired MemberDao memberDao;
  @Autowired DateCheckDao dateCheckDao;
  @Autowired ServletContext sc;


  @GetMapping("/auth/loginForm")
  public ModelAndView loginForm() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "로그인");
    mv.addObject("contentUrl", "auth/LoginForm.jsp");
    //mv.setViewName("auth/template1");
    mv.setViewName("auth/LogIn");
    return mv;
  }

  @PostMapping("/auth/login")
  public ModelAndView login(String id, String password, String saveEmail, HttpServletResponse response, HttpSession session) throws Exception {
    Cookie cookie = null;
    if (saveEmail != null) {
      cookie = new Cookie("id", id);
      cookie.setMaxAge(60 * 60 * 24 * 7);
      //cookie.setPath(sc.getContextPath() + "/app/auth"); // 예) http://localhost:8080/pms/app/auth

    } else {
      cookie = new Cookie("id", "");
      cookie.setMaxAge(0); // 유효기간을 0으로 설정하면 웹브라우저가 받는 즉시 무효한 쿠기가 된다.
    }
    response.addCookie(cookie);

    Member member = memberDao.findByIdPwd(id, password);

    ModelAndView mv = new ModelAndView();

    if (member != null) {
      session.setAttribute("loginUser", member);
      mv.setViewName("redirect:../home");

    } else {
      mv.addObject("refresh", "2;url=loginForm");
      mv.addObject("pageTitle", "로그인오류!");
      mv.addObject("contentUrl", "LoginError.jsp");
      //mv.setViewName("template1");
      mv.setViewName("homeTemplate");
    }
    return mv;
  }

  @GetMapping("/auth/logout")
  public ModelAndView logout(HttpSession session) throws Exception {
    session.invalidate();
    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../home");
    return mv;
  }

  @GetMapping("/auth/userInfoList")
  public ModelAndView list(HttpSession session) throws Exception {
    Collection<DateCheck> dateCheckList = dateCheckDao.findAll();
    Member member = (Member) session.getAttribute("loginUser");


    ModelAndView mv = new ModelAndView();
    if(member == null) {
      mv.setViewName("redirect:../home");
      return mv;
    } 

    mv.addObject("dateCheckList", dateCheckList);
    mv.addObject("member", member);
    mv.addObject("pageTitle", "내 정보");
    mv.setViewName("auth/UserInfoList");
    return mv;
  }

  @GetMapping("/auth/updatePhotoForm")
  public ModelAndView Plist(HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginUser");


    ModelAndView mv = new ModelAndView();
    if(member == null) {
      mv.setViewName("redirect:../home");
      return mv;
    } 

    mv.setViewName("auth/UpdatePhotoForm");
    return mv;
  }

  @PostMapping("/auth/changePhoto")
  public ModelAndView PClist(HttpSession session, Part photoPart) throws Exception {
    Member member = (Member) session.getAttribute("loginUser");

    String filename = UUID.randomUUID().toString();
    photoPart.write(sc.getRealPath("/upload/member") + "/" + filename);
    member.setPhoto(filename);

    Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
    .size(20, 20)
    .outputFormat("jpg")
    .crop(Positions.CENTER)
    .toFiles(new Rename() {

      @Override
      public String apply(String name, ThumbnailParameter param) {
        return name + "_20x20";
      }
    });

    Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
    .size(100, 100)
    .outputFormat("jpg")
    .crop(Positions.CENTER)
    .toFiles(new Rename() {
      @Override
      public String apply(String name, ThumbnailParameter param) {
        return name + "_100x100";
      }
    });

    member.setPhoto(filename);

    memberDao.update2(member);

    sqlSessionFactory.openSession().commit();


    ModelAndView mv = new ModelAndView();

    mv.setViewName("auth/ChangePhotoFin");
    return mv;
  }

}







