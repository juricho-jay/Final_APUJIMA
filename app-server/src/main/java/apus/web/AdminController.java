package apus.web;

import java.util.Collection;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.MemberDao;
import apus.domain.Member;

public class AdminController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired MemberDao memberDao;

  @GetMapping("/admin/list")
  public ModelAndView list() throws Exception {
    Collection<Member> memberList = memberDao.findAll();
    ModelAndView mv = new ModelAndView();

    mv.addObject("memberList", memberList);
    mv.addObject("pageTitle", "관리자게시판");
    mv.setViewName("member/MemberList");
    return mv;
  }
}
