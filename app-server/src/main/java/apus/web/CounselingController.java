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
import apus.dao.MailBoxDao;
import apus.dao.MemberDao;
import apus.domain.Counseling;
import apus.domain.MailBox;
import apus.domain.Member;

@Controller
public class CounselingController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired CounselingDao counselingDao;
  @Autowired MailBoxDao mailBoxDao;
  @Autowired MemberDao memberDao;


  @GetMapping("/counseling/form")
  public ModelAndView form(int counselorNo, HttpSession session) throws Exception {

    Member counselor = memberDao.findByNo(counselorNo);
    List<MailBox> mailBoxList = mailBoxDao.findAll();
    ModelAndView mv = new ModelAndView();

    Member member = ((Member) session.getAttribute("loginUser"));

    int count = 0;
    if (member != null) {
      for (int i = 0; i < mailBoxList.size(); i++) {
        if (member.getNickname().equals(mailBoxList.get(i).getReceiver().getNickname())) {
          if (mailBoxList.get(i).getReceivedTime() == null) {
            count++;
          }
        }
      }
      mv.addObject("uncheckedMail", count);
    }
    mv.addObject("counselor", counselor);
    mv.addObject("contentUrl", "/counseling/CounselingMemberForm.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }

  @GetMapping("/doctorinfo/list")
  public ModelAndView list(HttpSession session) throws Exception {
    List<Member> memberList = memberDao.findAll();
    ModelAndView mv = new ModelAndView();

    Member member = ((Member) session.getAttribute("loginUser"));

    //안읽은 메일 체크
    if (member != null) {
      List<MailBox> mailBoxList = mailBoxDao.findAll();

      int count = 0;
      for (int i = 0; i < mailBoxList.size(); i++) {
        if (member.getNickname().equals(mailBoxList.get(i).getReceiver().getNickname())) {
          if (mailBoxList.get(i).getReceivedTime() == null) {
            count++;
          }
        }
      }
      mv.addObject("uncheckedMail", count);
    }

    mv.addObject("memberList", memberList);
    mv.addObject("pageTitle", "힐러상담사");
    mv.addObject("contentUrl", "/counseling/DoctorInfo.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }

  @GetMapping("/counseling/list")
  public ModelAndView list2(HttpSession session) throws Exception {
    Collection<Member> memberList = memberDao.findAll();
    Collection<Counseling> counselingList = counselingDao.findAll();
    List<MailBox> mailBoxList = mailBoxDao.findAll();
    ModelAndView mv = new ModelAndView();

    Member member = ((Member) session.getAttribute("loginUser"));

    int count = 0;
    if (member != null) {
      for (int i = 0; i < mailBoxList.size(); i++) {
        if (member.getNickname().equals(mailBoxList.get(i).getReceiver().getNickname())) {
          if (mailBoxList.get(i).getReceivedTime() == null) {
            count++;
          }
        }
      }
      mv.addObject("uncheckedMail", count);
    }

    mv.addObject("counselingList", counselingList);
    mv.addObject("memberList", memberList);
    mv.addObject("pageTitle", "나의상담신청목록");
    mv.addObject("contentUrl", "counseling/CounselingList.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }

  @PostMapping("/counseling/add")
  public ModelAndView add(Counseling counseling, int counselorNo, HttpSession session
      , String disease, String content) throws Exception {

    Member doctor = memberDao.findByNo(counselorNo);
    List<MailBox> mailBoxList = mailBoxDao.findAll();
    ModelAndView mv = new ModelAndView();

    Member member = ((Member) session.getAttribute("loginUser"));

    int count = 0;
    if (member != null) {
      for (int i = 0; i < mailBoxList.size(); i++) {
        if (member.getNickname().equals(mailBoxList.get(i).getReceiver().getNickname())) {
          if (mailBoxList.get(i).getReceivedTime() == null) {
            count++;
          }
        }
      }
      mv.addObject("uncheckedMail", count);
    }

    counseling.setClient((Member) session.getAttribute("loginUser"));
    counseling.setCounselor(doctor);
    counseling.setDisease(disease);
    counseling.setContent(content);

    counselingDao.insert(counseling);
    sqlSessionFactory.openSession().commit();

    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/counseling/detail")
  public ModelAndView detail(String no, HttpSession session) throws Exception {
    Counseling counseling = counselingDao.findByNo(Integer.parseInt(no));
    List<MailBox> mailBoxList = mailBoxDao.findAll();
    ModelAndView mv = new ModelAndView();

    Member member = ((Member) session.getAttribute("loginUser"));

    int count = 0;
    if (member != null) {
      for (int i = 0; i < mailBoxList.size(); i++) {
        if (member.getNickname().equals(mailBoxList.get(i).getReceiver().getNickname())) {
          if (mailBoxList.get(i).getReceivedTime() == null) {
            count++;
          }
        }
      }
      mv.addObject("uncheckedMail", count);
    }

    mv.addObject("pageTitle", "상담신청상세");
    mv.addObject("counseling", counseling);
    mv.addObject("contentUrl", "counseling/CounselingMemberDetail.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }

  @GetMapping("/counseling/doctorlist")
  public ModelAndView doctorlist(HttpSession session) throws Exception {
    Collection<Member> memberList = memberDao.findAll();
    List<Counseling> counselingList= counselingDao.findAll();
    List<MailBox> mailBoxList = mailBoxDao.findAll();
    ModelAndView mv = new ModelAndView();

    Member member = ((Member) session.getAttribute("loginUser"));

    int count = 0;
    if (member != null) {
      for (int i = 0; i < mailBoxList.size(); i++) {
        if (member.getNickname().equals(mailBoxList.get(i).getReceiver().getNickname())) {
          if (mailBoxList.get(i).getReceivedTime() == null) {
            count++;
          }
        }
      }
      mv.addObject("uncheckedMail", count);
    }
    mv.addObject("counselingList", counselingList);
    mv.addObject("memberList", memberList);
    mv.addObject("pageTitle", "나의상담요청목록");
    mv.addObject("contentUrl", "counseling/CounselingDoctorList.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }

  @GetMapping("/counseling/doctordetail")
  public ModelAndView doctordetail(String no, HttpSession session) throws Exception {
    Counseling counseling = counselingDao.findByNo(Integer.parseInt(no));
    List<MailBox> mailBoxList = mailBoxDao.findAll();
    ModelAndView mv = new ModelAndView();

    Member member = ((Member) session.getAttribute("loginUser"));

    int count = 0;
    if (member != null) {
      for (int i = 0; i < mailBoxList.size(); i++) {
        if (member.getNickname().equals(mailBoxList.get(i).getReceiver().getNickname())) {
          if (mailBoxList.get(i).getReceivedTime() == null) {
            count++;
          }
        }
      }
      mv.addObject("uncheckedMail", count);
    }
    mv.addObject("pageTitle", "상담요청상세");
    mv.addObject("counseling", counseling);
    mv.addObject("contentUrl", "counseling/CounselingDoctorDetail.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }


}
