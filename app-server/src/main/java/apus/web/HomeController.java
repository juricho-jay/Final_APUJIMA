package apus.web;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.MailBoxDao;
import apus.domain.MailBox;
import apus.domain.Member;

@Controller
public class HomeController {

  @Autowired MailBoxDao mailBoxDao;

  @GetMapping("/home")
  public ModelAndView home(HttpSession session) throws Exception {

    Member member = ((Member) session.getAttribute("loginUser"));

    //안읽은 메일 체크

    List<MailBox> mailBoxList = mailBoxDao.findAll();

    int count = 0;
    for (int i = 0; i < mailBoxList.size(); i++) {
      if (member.getNickname().equals(mailBoxList.get(i).getReceiver().getNickname())) {
        if (mailBoxList.get(i).getReceivedTime() == null) {
          count++;
        }
      }
    }


    ModelAndView mv = new ModelAndView();
    mv.addObject("uncheckedMail", count);
    mv.addObject("pageTitle", "메인화면");
    mv.addObject("contentUrl", "index2.jsp");
    mv.setViewName("homeTemplate");
    return mv;
  }
}