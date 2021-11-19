package apus.web;

import java.util.Collection;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.BoardDao;
import apus.dao.DateCheckDao;
import apus.dao.MemberDao;
import apus.domain.DateCheck;
import apus.domain.Member;

@Controller
public class DateCheckController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired BoardDao boardDao;
  @Autowired MemberDao memberDao;
  @Autowired DateCheckDao dateCheckDao;


  @GetMapping("/auth/dateCheckList")
  public ModelAndView list() throws Exception {
    ModelAndView mv = new ModelAndView();
    Collection<DateCheck> dateCheckList = dateCheckDao.findAll();

    mv.addObject("dateCheckList", dateCheckList);
    mv.addObject("pageTitle", "출석체크 페이지");
    mv.setViewName("auth/DateCheckForm");
    return mv;
  }

  @GetMapping("/auth/dateCheckFinder")
  public ModelAndView detail(HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();
    Member member = (Member) session.getAttribute("loginUser");

    if (member == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    int memberNo = member.getNo();
    DateCheck dateCheck = dateCheckDao.findByMemberAndDate(memberNo);

    if (dateCheck == null) {
      //출석체크 기록 없으면 DateCheckAddController 역할
      //attendanceCheck로 보냄
      mv.setViewName("redirect:attendanceCheck");
      return mv;
    } else if (dateCheck != null) {
      //출석체크를 이미 한 경우에는 DateCheckPro로 바로 이동
      mv.addObject("dateCheck", dateCheck);
      mv.setViewName("auth/DateCheckPro");
    }
    return mv;
  }


  @GetMapping("/auth/attendanceCheck")
  public ModelAndView add(HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();
    Member member = (Member) session.getAttribute("loginUser");

    member.setPoint(member.getPoint() + 30);

    DateCheck dateCheck = new DateCheck();
    dateCheck.setAttendee(member);

    memberDao.update2(member);
    dateCheckDao.insert(dateCheck);
    sqlSessionFactory.openSession().commit();

    mv.setViewName("auth/DateCheckPro");
    return mv;
  }



}