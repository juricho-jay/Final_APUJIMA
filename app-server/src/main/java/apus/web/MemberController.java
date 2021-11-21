package apus.web;


import java.sql.Date;
import java.util.Collection;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.MemberDao;
import apus.domain.Doctor;
import apus.domain.Member;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class MemberController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired MemberDao memberDao;
  @Autowired ServletContext sc;

  @GetMapping("/member/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "새회원");
    // mv.addObject("contentUrl", "member/MemberForm.jsp");
    mv.setViewName("member/MemberForm");
    return mv;
  }

  @PostMapping("/member/add")
  public ModelAndView add(Member member, Part photoPart, String grade, String birthyy, String birthmm, 
      String birthdd, String major, Part license, String homepage, String introduce) throws Exception {
    if (photoPart.getSize() > 0) {
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
    }

    Date birthday = Date.valueOf(birthyy+"-"+birthmm+"-"+birthdd);
    member.setBirthday(birthday);
    member.setPoint(1000);
    member.setActive(1);

    member.setDoctorOrNot(Integer.parseInt(grade));

    if(member.getDoctorOrNot() == 2) {
      Doctor doctor = new Doctor();
      doctor.setHomepage(homepage);
      doctor.setIntroduction(introduce);
      doctor.setMajor(major);

      if (license.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        license.write(sc.getRealPath("/upload/member") + "/" + filename);
        doctor.setLicense(filename);

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
      }

      member.setDoctor(doctor);
    }

    memberDao.insert(member);

    if(member.getDoctorOrNot() == 2) {
      memberDao.insert2(member);
    }

    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("refresh", "2;../../url=list");
    mv.addObject("pageTitle", "회원등록");
    mv.addObject("contentUrl", "index2.jsp");
    //mv.setViewName("template1");
    //mv.setViewName("member/MemberAdd");
    mv.setViewName("homeTemplate");
    return mv;
  }

  @GetMapping("/member/list")
  public ModelAndView list() throws Exception {

    Collection<Member> memberList = memberDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("memberList", memberList);
    mv.addObject("pageTitle", "회원목록");
    //  mv.addObject("contentUrl", "member/MemberList.jsp");
    mv.setViewName("member/MemberList");
    // mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/member/detail")
  public ModelAndView detail(int no) throws Exception {
    Member member = memberDao.findByNo(no);
    if (member == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("member", member);
    mv.addObject("pageTitle", "회원정보");
    //  mv.addObject("contentUrl", "member/MemberDetail.jsp");
    mv.setViewName("member/MemberDetail");
    // mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/member/update")
  public ModelAndView update(Member member, Part photoPart, 
      String major, Part license, String homepage, String introduce) throws Exception {
    Member oldMember = memberDao.findByNo(member.getNo());

    if (oldMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    } 

    member.setPhoto(oldMember.getPhoto());
    member.setRegisteredDate(oldMember.getRegisteredDate());

    if (photoPart.getSize() > 0) {
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
    }
    member.setDoctorOrNot(oldMember.getDoctorOrNot());
    member.setBirthday(oldMember.getBirthday());
    member.setActive(1);

    if (oldMember.getDoctorOrNot() == 2) {
      Doctor doctor = oldMember.getDoctor();
      doctor.setHomepage(homepage);
      doctor.setMajor(major);
      doctor.setIntroduction(introduce);

      if (license.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        license.write(sc.getRealPath("/upload/member") + "/" + filename);
        doctor.setLicense(filename);

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
      }

      member.setDoctor(doctor);
    }


    if (member.getPassword().length() == 0) {
      memberDao.update2(member);
    } else {
      memberDao.update(member);
    }


    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/member/delete")
  public ModelAndView delete(String id) throws Exception {
    Member member = memberDao.findById(id);
    if (member == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    memberDao.delete(member.getId());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @PostMapping("/member/idCheck")
  public ModelAndView idCheck(String id) throws Exception {
    Member member = memberDao.findById(id);
    if (member == null) {
      throw new Exception("해당 id의 회원이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("inputId", id);
    if (member != null) {
      mv.addObject("member", member);
    }
    mv.addObject("pageTitle", "ID중복확인");
    //  mv.addObject("contentUrl", "member/MemberDetail.jsp");
    mv.setViewName("member/idCheckPro");
    // mv.setViewName("template1");

    return mv;
  }

  @PostMapping("/member/nicknameCheck")
  public ModelAndView nicknameCheck(String nickname) throws Exception {
    Member member = memberDao.findByNickname(nickname);
    System.out.println("---------------------------------");
    System.out.println("---------------------------------");
    if (member == null) {
      throw new Exception("해당 별명의 회원이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("inputNickname", nickname);
    if (member != null) {
      mv.addObject("member", member);
    }
    mv.addObject("pageTitle", "닉네임중복확인");
    //  mv.addObject("contentUrl", "member/NicknameCheckPro.jsp");
    mv.setViewName("member/NicknameCheckPro");
    // mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/member/checkNickname")
  @ResponseBody
  public String checkNickname(String nickname) throws Exception {
    Member member = memberDao.findByNickname(nickname);

    if (member == null) {
      return "false";
    }else {
      return "true";
    }
  }

  @GetMapping("/member/checkId")
  @ResponseBody
  public String checkId(String id) throws Exception {
    Member member = memberDao.findById(id);
    if (member == null) {
      return "false";
    }else {
      return "true";
    }
  }
}