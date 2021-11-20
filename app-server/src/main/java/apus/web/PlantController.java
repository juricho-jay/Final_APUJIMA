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
import apus.dao.MailBoxDao;
import apus.dao.MemberDao;
import apus.dao.PlantDao;
import apus.domain.MailBox;
import apus.domain.Member;
import apus.domain.Plant;

@Controller
public class PlantController {


  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired MemberDao memberDao;
  @Autowired PlantDao plantDao;
  @Autowired MailBoxDao mailBoxDao;


  @GetMapping("/plant/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "새 글");
    mv.addObject("contentUrl", "plant/PlantForm.jsp");
    // mv.setViewName("template1");
    mv.setViewName("plant/PlantForm");
    return mv;
  }

  @PostMapping("/plant/add")
  public ModelAndView add(Member member, HttpSession session,Plant plant) throws Exception {
    ModelAndView mv = new ModelAndView();
    int point = 30;

    plant.setOwnerName((Member) session.getAttribute("loginUser"));
    plant.setShape("saessak.png");

    plantDao.insert(plant);
    member.setPoint(member.getPoint()-point);
    sqlSessionFactory.openSession().commit();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/plant/list")
  public ModelAndView list(HttpSession session) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");
    Collection<Plant> plantList = plantDao.findMyPlant(member.getNo());

    ModelAndView mv = new ModelAndView();

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

    mv.addObject("plantList",plantList);
    mv.addObject("pageTitle","화분 목록");
    mv.addObject("contentUrl", "plant/PlantList.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }

  @GetMapping("/plant/detail")
  public ModelAndView detail(int no, HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginUser");
    Plant plant = plantDao.findByNo(no);

    if (plant == null) {
      throw new Exception ("해당 화분이 없습니다.");
    }
    ModelAndView mv = new ModelAndView();

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

    mv.addObject("plant",plant);
    mv.addObject("pageTitle","화분 목록");
    mv.addObject("contentUrl", "plant/PlantDetail.jsp");

    mv.setViewName("darkTemplate");
    return mv;
  }

  @GetMapping("/plant/grow")
  public ModelAndView grow(String no,Member member, HttpSession session) throws Exception {
    Plant plant  = plantDao.findByNo(Integer.parseInt(no));

    plant.setOwnerName((Member) session.getAttribute("loginUser"));

    int point = 30;
    int plusExp = 130;

    plant.setExp(plant.getExp() + plusExp);

    if (plant.getExp() > 120 && plant.getExp() < 400) {
      plant.setShape("leaf.png");
      plant.setLevel(1);
    } else if ( plant.getExp() > 400 && plant.getExp() < 700) {
      plant.setShape("flower.png");
      plant.setLevel(2);
    }else if (plant.getExp() >700 && plant.getExp() < 1000) {
      plant.setShape("Tree.jpg");
      plant.setLevel(3);
    } else if (plant.getExp() > 1000) {
      plant.setExp(1000);
      plant.setShape("Tree.jpg");
    }
    plantDao.update(plant);
    member.setPoint(member.getPoint()-point);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("plant", plant);

    mv.setViewName("redirect: /apus/PlantWater.html");
    return mv;
  }

  @GetMapping("/plant/updateForm")
  public ModelAndView updateForm(String no) throws Exception {

    Plant plant  = plantDao.findByNo(Integer.parseInt(no));
    if (plant == null) {
      throw new Exception ("해당 화분이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("plant",plant);
    mv.addObject("pagetitle","화분 이름 수정하기");
    mv.addObject("contentUrl", "plant/UpdateForm.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }

  @GetMapping("/plant/update")
  public ModelAndView update(String no, String plantName ) throws Exception {

    Plant plant  = plantDao.findByNo(Integer.parseInt(no));
    if (plant == null) {
      throw new Exception ("해당 화분이 없습니다.");
    }

    plant.setPlantName(plantName);
    plantDao.update(plant);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("plant",plant);
    mv.addObject("contentUrl", "plant/PlantUpdate.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }

  @GetMapping("/plant/delete")
  public ModelAndView delete(int no) throws Exception {

    Plant plant = plantDao.findByNo(no);
    if (plant == null) {
      throw new Exception("해당 번호의 화분이 없습니다.");
    }

    plantDao.delete(no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }


}