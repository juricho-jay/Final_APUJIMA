package apus.web;

import java.util.Collection;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.DateCheckDao;
import apus.dao.MemberDao;
import apus.domain.DateCheck;
import apus.domain.Member;

@Controller
public class AuthController {

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
    ModelAndView mv = new ModelAndView();

    Member member = (Member) session.getAttribute("loginUser");

    if(member == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    } 

    mv.addObject("dateCheckList", dateCheckList);
    mv.addObject("member", member);
    mv.addObject("pageTitle", "내 정보");
    mv.addObject("contentUrl", "auth/UserInfoList.jsp");
    return mv;
  }

}







