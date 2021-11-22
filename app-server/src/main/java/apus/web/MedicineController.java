package apus.web;

import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.CommentDao;
import apus.dao.LikeDao;
import apus.dao.MailBoxDao;
import apus.dao.MedicineDao;
import apus.dao.MemberDao;
import apus.domain.MailBox;
import apus.domain.Medicine;
import apus.domain.Member;

@Controller
public class MedicineController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired MedicineDao medicineDao;
  @Autowired MemberDao memberDao;
  @Autowired CommentDao commentDao;
  @Autowired LikeDao likeDao;
  @Autowired MailBoxDao mailBoxDao;

  @GetMapping("/medicine/form")
  public ModelAndView form(HttpSession session) throws Exception{
    ModelAndView mv = new ModelAndView();
    Member member = ((Member) session.getAttribute("loginUser"));

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


    mv.addObject("pageTitle", "새 약품");
    mv.addObject("member", member);
    mv.addObject("contentUrl", "medicine/MedicineForm.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }

  @PostMapping("/medicine/add")
  public ModelAndView add(Medicine medicine, HttpSession session, String age) throws Exception {
    medicine.setActive(1);
    medicine.setAgeLimit(Integer.parseInt(age));
    medicine.setCheck(1);
    medicineDao.insert(medicine);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/medicine/list")
  public ModelAndView list(HttpSession session) throws Exception {
    Collection<Medicine> medicineList = medicineDao.findActive();

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

    mv.addObject("medicineList", medicineList);
    mv.addObject("member", member);
    mv.addObject("pageTitle", "약품목록");
    mv.addObject("contentUrl", "medicine/MedicineList.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }

  @GetMapping("/medicine/detail")
  public ModelAndView detail(int no, HttpSession session) throws Exception {
    Medicine medicine = medicineDao.findByNo(no);

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

    mv.addObject("medicine", medicine);
    mv.addObject("member", member);
    mv.addObject("pageTitle", "약품 상세");
    mv.addObject("contentUrl", "medicine/MedicineDetail.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }

  @GetMapping("/medicine/requestForm")
  public ModelAndView requestForm(HttpSession session) throws Exception {

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

    mv.addObject("pageTitle", "약품 등록 요청폼");
    mv.addObject("member", member);
    mv.addObject("contentUrl", "medicine/MedicineRequestForm.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }

  @GetMapping("/medicine/request")
  public ModelAndView request(Medicine medicine, HttpSession session, String age) throws Exception {



    Member requester = ((Member) session.getAttribute("loginUser"));
    medicine.setRequester(requester);
    medicine.setActive(0);
    medicine.setCheck(0);
    medicine.setAgeLimit(Integer.parseInt(age));

    medicineDao.insert(medicine);
    sqlSessionFactory.openSession().commit();
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "약품 등록 요청");
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/medicine/delete")
  public ModelAndView delete(String name) throws Exception {

    medicineDao.delete(name);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @PostMapping("/medicine/update")
  public ModelAndView update(Medicine medicine, String age) throws Exception {

    Medicine inputMedicine = medicineDao.findByNo(medicine.getNo());
    if (inputMedicine == null) {
      throw new Exception("해당 번호의 의약품이 없습니다.");
    }
    medicine.setAgeLimit(Integer.parseInt(age));

    medicineDao.update(medicine);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/medicine/checkName")
  @ResponseBody
  public String checkName(String name) throws Exception {
    Medicine medicine = medicineDao.findByName(name);
    if (medicine == null) {
      return "false";
    } else {
      return "true";
    }
  }
}