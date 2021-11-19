package apus.web;

import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.CounselingDao;
import apus.dao.MemberDao;
import apus.domain.Counseling;
import apus.domain.Member;

@Controller
public class CounselingController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired CounselingDao counselingDao;
  @Autowired MemberDao memberDao;


  @GetMapping("/counseling/form")
  public ModelAndView form(int counselorNo) throws Exception {
    //    System.out.println("form");

    Member counselor = memberDao.findByNo(counselorNo);
    ModelAndView mv = new ModelAndView();
    mv.addObject("counselor", counselor);
    mv.addObject("contentUrl", "/counseling/CounselingMemberForm.jsp");
    mv.setViewName("counseling/CounselingMemberForm");
    return mv;
  }

  @GetMapping("/doctorinfo/list")
  public ModelAndView list() throws Exception {
    List<Member> memberList = memberDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("memberList", memberList);
    mv.addObject("pageTitle", "힐러상담사");
    mv.addObject("contentUrl", "/counseling/DoctorInfo.jsp");
    //mv.setViewName("board/BoardList");
    mv.setViewName("darkTemplate");
    // mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/counseling/list")
  public ModelAndView list2() throws Exception {
    Collection<Member> memberList = memberDao.findAll();
    Collection<Counseling> counselingList = counselingDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("counselingList", counselingList);
    mv.addObject("memberList", memberList);
    mv.addObject("pageTitle", "나의상담신청목록");
    mv.addObject("contentUrl", "/counseling/CounselingList.jsp");
    //mv.setViewName("board/BoardList");
    mv.setViewName("darkTemplate");
    // mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/counseling/add")
  public ModelAndView add(Counseling counseling, int counselorNo, HttpSession session) throws Exception {

    Member doctor = memberDao.findByNo(counselorNo);
    //int no = Integer.parseInt(request.getParameter("no")); // counselor.no


    counseling.setClient((Member) session.getAttribute("loginUser"));
    counseling.setCounselor(doctor);

    counselingDao.insert(counseling);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/counseling/detail")
  public ModelAndView detail(int no) throws Exception {
    Counseling counseling = counselingDao.findByNo(no);

    if (counseling == null) {
      throw new Exception("해당 번호의 상담이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "상담신청상세");
    mv.addObject("counseling", counseling);
    mv.addObject("contentUrl", "/counseling/CounselingMemberDetail.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }

  @GetMapping("/counseling/doctorlist")
  public ModelAndView doctorlist() throws Exception {
    Collection<Member> memberList = memberDao.findAll();
    List<Counseling> counselingList= counselingDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("counselingList", counselingList);
    mv.addObject("memberList", memberList);
    mv.addObject("pageTitle", "나의상담요청목록");
    mv.addObject("contentUrl", "/counseling/CounselingDoctorList.jsp");
    //mv.setViewName("board/BoardList");
    mv.setViewName("darkTemplate");
    // mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/counseling/doctordetail")
  public ModelAndView doctordetail(int no) throws Exception {
    Counseling counseling = counselingDao.findByNo(no);

    if (counseling == null) {
      throw new Exception("해당 번호의 상담이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "상담요청상세");
    mv.addObject("counseling", counseling);
    mv.addObject("contentUrl", "/counseling/CounselingDoctorDetail.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }


}
