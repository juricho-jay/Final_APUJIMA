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
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "새 약품");
    mv.addObject("contentUrl", "medicine/MedicineForm.jsp");
    mv.setViewName("template4");
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
    mv.addObject("pageTitle", "약품목록");
    mv.addObject("contentUrl", "medicine/MedicineList.jsp");
    mv.setViewName("template4");
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
    mv.addObject("pageTitle", "약품 상세");
    mv.addObject("contentUrl", "medicine/MedicineDetail.jsp");
    mv.setViewName("template4");
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
    mv.addObject("contentUrl", "medicine/MedicineRequestForm.jsp");
    mv.setViewName("template4");
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


  //  @PostMapping("/board/updateForm")
  //  public ModelAndView updateForm(String no) throws Exception {
  //    System.out.println("----------helo updateForm-----------");
  //
  //    Board board = boardDao.findByNo(Integer.parseInt(no));
  //    if (board == null) {
  //      throw new Exception("해당 번호의 게시글이 없습니다.");
  //    } 
  //
  //    ModelAndView mv = new ModelAndView();
  //    mv.addObject("board", board);
  //    mv.addObject("contentUrl", "board/UpdateForm.jsp");
  //    mv.setViewName("template3");
  //    return mv;
  //  }
  //
  //  @PostMapping("/board/update")
  //  public ModelAndView update(Board board, String title, String content) throws Exception {
  //    System.out.println("----------helo update-----------");
  //
  //    if (board == null) {
  //      throw new Exception("해당 번호의 게시글이 없습니다.");
  //    } 
  //    board.setTitle(title);
  //    board.setContent(content);
  //
  //    boardDao.update(board);
  //    sqlSessionFactory.openSession().commit();
  //
  //
  //    ModelAndView mv = new ModelAndView();
  //    mv.addObject("board", board);
  //    mv.addObject("contentUrl", "board/BoardUpdate.jsp");
  //    mv.setViewName("template3");
  //    return mv;
  //  }
  //
  //  @GetMapping("/board/delete")
  //  public ModelAndView delete(int no) throws Exception {
  //
  //    Board board = boardDao.findByNo(no);
  //    if (board == null) {
  //      throw new Exception("해당 번호의 게시글이 없습니다.");
  //    }
  //
  //    boardDao.delete(no);
  //    sqlSessionFactory.openSession().commit();
  //
  //    ModelAndView mv = new ModelAndView();
  //    mv.setViewName("redirect:list");
  //    return mv;
  //  }
  //
  //  @GetMapping("/board/freeBoardList")
  //  public ModelAndView freeBoardList() throws Exception {
  //    Collection<Board> boardList = boardDao.findFreeBoard();
  //
  //    ModelAndView mv = new ModelAndView();
  //    mv.addObject("boardList", boardList);
  //    mv.addObject("pageTitle", "자유게시판목록");
  //    mv.addObject("contentUrl", "board/FreeBoardList.jsp");
  //    //mv.setViewName("board/BoardList");
  //    mv.setViewName("template3");
  //    // mv.setViewName("template1");
  //    return mv;
  //  }
  //
  //  @GetMapping("/board/doctorBoardList")
  //  public ModelAndView doctorBoardList() throws Exception {
  //    Collection<Board> boardList = boardDao.findDoctorBoard();
  //
  //    ModelAndView mv = new ModelAndView();
  //    mv.addObject("boardList", boardList);
  //    mv.addObject("pageTitle", "자유게시판목록");
  //    mv.addObject("contentUrl", "board/DoctorBoardList.jsp");
  //    //mv.setViewName("board/BoardList");
  //    mv.setViewName("template3");
  //    // mv.setViewName("template1");
  //    return mv;
  //  }
  //
  //  @GetMapping("/board/noticeBoardList")
  //  public ModelAndView noticeBoardList() throws Exception {
  //    Collection<Board> boardList = boardDao.findNoticeBoard();
  //
  //    ModelAndView mv = new ModelAndView();
  //    mv.addObject("boardList", boardList);
  //    mv.addObject("pageTitle", "자유게시판목록");
  //    mv.addObject("contentUrl", "board/NoticeBoardList.jsp");
  //    //mv.setViewName("board/BoardList");
  //    mv.setViewName("template3");
  //    // mv.setViewName("template1");
  //    return mv;
  //  }


}