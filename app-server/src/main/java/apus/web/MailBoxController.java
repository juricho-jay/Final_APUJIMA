package apus.web;

import java.util.Collection;
import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.MailBoxDao;
import apus.dao.MemberDao;
import apus.domain.MailBox;
import apus.domain.Member;

@Controller
public class MailBoxController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired MailBoxDao mailBoxDao;
  @Autowired MemberDao memberDao;

  @GetMapping("/mailbox/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "쪽지함");
    mv.addObject("contentUrl", "mailbox/MailBoxForm.jsp");
    mv.setViewName("template4");
    return mv;
  }

  @GetMapping("/mailbox/list")
  public ModelAndView list() throws Exception {
    List<Member> memberList = memberDao.findAll();
    List<MailBox> mailBoxList = mailBoxDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("mailBoxList", mailBoxList);
    mv.addObject("memberList", memberList);
    mv.addObject("pageTitle", "쪽지함목록");
    mv.addObject("contentUrl", "/mailbox/MailBoxList.jsp");
    mv.setViewName("template4");
    return mv;
  }

  @GetMapping("/mailbox/detail")
  public ModelAndView detail(int no) throws Exception {
    MailBox mailBox = mailBoxDao.findByNo(no);

    if (mailBox == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "쪽지함상세");
    mv.addObject("mailBox", mailBox);
    mv.addObject("contentUrl", "/mailbox/MailBoxDetail.jsp");
    mv.setViewName("template4");
    return mv;
  }

  @GetMapping("/mailbox/readlist")
  public ModelAndView readlist() throws Exception {
    Collection<Member> memberList = memberDao.findAll();
    List<MailBox> mailBoxList = mailBoxDao.findByCheckedTime();

    ModelAndView mv = new ModelAndView();
    mv.addObject("mailBoxList", mailBoxList);
    mv.addObject("memberList", memberList);
    mv.addObject("pageTitle", "읽은쪽지함");
    mv.addObject("contentUrl", "/mailbox/MailBoxReadList.jsp");
    mv.setViewName("template4");
    return mv;
  }

  @GetMapping("/mailbox/notreadlist")
  public ModelAndView notreadlist() throws Exception {
    Collection<Member> memberList = memberDao.findAll();
    List<MailBox> mailBoxList = mailBoxDao.findByTime();

    ModelAndView mv = new ModelAndView();
    mv.addObject("mailBoxList", mailBoxList);
    mv.addObject("memberList", memberList);
    mv.addObject("pageTitle", "읽은쪽지함");
    mv.addObject("contentUrl", "/mailbox/MailBoxReadList.jsp");
    mv.setViewName("template4");
    return mv;
  }


}
