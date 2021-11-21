package apus.web;

import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.BoardDao;
import apus.dao.MailBoxDao;
import apus.dao.MedicineDao;
import apus.dao.MemberDao;
import apus.dao.ReportDao;
import apus.domain.MailBox;
import apus.domain.Medicine;
import apus.domain.Member;
import apus.domain.Report;

@Controller
public class AdminController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired MedicineDao medicineDao;
  @Autowired BoardDao boardDao;
  @Autowired MemberDao memberDao;
  @Autowired MailBoxDao mailBoxDao;
  @Autowired ReportDao reportDao;


  @GetMapping("/admin/adminMedicineApprovalList")
  public ModelAndView approvalMedicineList(HttpSession session) throws Exception {

    Collection<Medicine> medicineApprovalList = medicineDao.findRequest();
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

    mv.addObject("medicineApprovalList", medicineApprovalList);
    mv.addObject("member", member);
    mv.addObject("contentUrl", "admin/AdminMedicineApprovalList.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }

  @GetMapping("/admin/medicineConfirm")
  public ModelAndView medicineConfirm(HttpSession session, String name) throws Exception {

    Medicine medicine = medicineDao.findByName(name);

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

    medicineDao.requestApprove(medicine);
    sqlSessionFactory.openSession().commit();

    // mv.addObject("medicineList", medicineList);
    mv.setViewName("redirect:adminMedicineApprovalList");
    return mv;
  }

  @GetMapping("/admin/reject")
  public ModelAndView medicineReject(HttpSession session, String name) throws Exception {

    Medicine medicine = medicineDao.findByName(name);

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

    medicineDao.requestCancle(medicine);
    sqlSessionFactory.openSession().commit();

    // mv.addObject("medicineList", medicineList);
    mv.setViewName("redirect:adminMedicineApprovalList");
    return mv;
  }

  @GetMapping("/admin/adminReportApprovalList")
  public ModelAndView approvalReportList(HttpSession session) throws Exception {

    Collection<Report> reportApprovalList = reportDao.findAll();
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

    mv.addObject("reportApprovalList", reportApprovalList);
    mv.addObject("member", member);
    mv.addObject("contentUrl", "admin/AdminReportApprovalList.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }

  @GetMapping("/admin/reportConfirm")
  public ModelAndView reportConfirm(HttpSession session, String id, int no) throws Exception {

    Report report = reportDao.findByReport(no, id);
    int boardNo = report.getRequestBoard().getNo();

    boardDao.delete(boardNo);
    reportDao.delete(report.getNo());
    sqlSessionFactory.openSession().commit();

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

    // mv.addObject("medicineList", medicineList);
    mv.setViewName("redirect:adminReportApprovalList");
    return mv;
  }

  @GetMapping("/admin/reportReject")
  public ModelAndView reportReject(HttpSession session, String id, int no) throws Exception {

    Report report = reportDao.findByReport(no, id);
    reportDao.delete(report.getNo());
    sqlSessionFactory.openSession().commit();

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


    // mv.addObject("medicineList", medicineList);
    mv.setViewName("redirect:adminReportApprovalList");
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