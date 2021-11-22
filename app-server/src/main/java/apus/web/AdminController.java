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
import apus.domain.Board;
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


    MailBox mailBox = new MailBox();
    Member receiver = memberDao.findById(medicine.getRequester().getId());
    String sendTitle = "약품 승인 결과";
    String sendContent = "신청하신 " + medicine.getName() + " 약품이 등록되었습니다." ;
    mailBox.setSender(member);
    mailBox.setReceiver(receiver);
    mailBox.setTitle(sendTitle);
    mailBox.setContent(sendContent);

    mailBoxDao.insert(mailBox);
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

    MailBox mailBox = new MailBox();
    Member receiver = memberDao.findById(medicine.getRequester().getId());
    String sendTitle = "약품 승인 결과";
    String sendContent = "신청하신 " + medicine.getName() + " 약품이 거절되었습니다."
        + "\n 관심을 가져주셔서 감사합니다." ;
    mailBox.setSender(member);
    mailBox.setReceiver(receiver);
    mailBox.setTitle(sendTitle);
    mailBox.setContent(sendContent);

    mailBoxDao.insert(mailBox);
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
    Board board = boardDao.findByNo(boardNo);

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

    MailBox mailBox = new MailBox();
    MailBox mailBox1 = new MailBox();

    Member requester = memberDao.findById(report.getRequester().getId());
    Member writer = memberDao.findById(board.getWriter().getId());
    String sendTitle = "게시판 신고 결과";
    String deleteContent = "작성하신 게시글 중 제목이 '" + board.getTitle() +"' 게시글에 대해 신고가 들어왔습니다. 관리자가 판단하기에 부적절 하다고 생각되어 삭제합니다.!";
    String confirmContent = "신고하신 '" + board.getTitle() + "' 게시물을 검토해 보았지만 유해게시글로 판단되지 않습니다. 꾸준히 관심을 가져주셔서 감사합니다.";


    mailBox.setSender(member);
    mailBox.setReceiver(requester);
    mailBox.setTitle(sendTitle);
    mailBox.setContent(confirmContent);

    mailBox1.setSender(member);
    mailBox1.setReceiver(writer);
    mailBox1.setTitle(sendTitle);
    mailBox1.setContent(deleteContent);
    mailBoxDao.insert(mailBox);
    mailBoxDao.insert(mailBox1);
    reportDao.delete(report.getNo());
    boardDao.delete(boardNo);
    sqlSessionFactory.openSession().commit();


    // mv.addObject("medicineList", medicineList);
    mv.setViewName("redirect:adminReportApprovalList");
    return mv;
  }

  @GetMapping("/admin/reportReject")
  public ModelAndView reportReject(HttpSession session, String id, int no) throws Exception {

    Report report = reportDao.findByReport(no, id);
    int boardNo = report.getRequestBoard().getNo();
    Board board = boardDao.findByNo(boardNo);

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

    MailBox mailBox = new MailBox();
    Member requester = memberDao.findById(report.getRequester().getId());
    String sendTitle = "게시판 신고 결과";
    String confirmContent = "신고하신 '" + board.getTitle() + "' 게시물을 검토해 보았지만 유해게시글로 판단되지 않습니다. 꾸준히 관심을 가져주셔서 감사합니다.";

    mailBox.setSender(member);
    mailBox.setReceiver(requester);
    mailBox.setTitle(sendTitle);
    mailBox.setContent(confirmContent);

    mailBoxDao.insert(mailBox);
    reportDao.delete(report.getNo());
    sqlSessionFactory.openSession().commit();

    // mv.addObject("medicineList", medicineList);
    mv.setViewName("redirect:adminReportApprovalList");
    return mv;
  }




}