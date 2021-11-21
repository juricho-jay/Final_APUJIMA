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
  public ModelAndView reportConfirm(MailBox mailBox, HttpSession session, String id, int no, String reportId) throws Exception {

    Report report = reportDao.findByReport(no, id);
    int boardNo = report.getRequestBoard().getNo();
    String reportedBoardWriter = report.getRequestBoard().getWriter().getId();

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



    Member reportedMember = memberDao.findById(reportId);
    Member reportMember = memberDao.findById(reportedBoardWriter);
    String sendTitle = "게시판 신고 결과";
    String sendContent = "게시판 신고 접수가 승인되어 게시글이 삭제됨을 알려드립니다!";

    mailBox.setSender(member);
    mailBox.setReceiver(reportedMember);
    mailBox.setTitle(sendTitle);
    mailBox.setContent(sendContent);

    mailBox.setReceiver(reportMember);
    mailBox.setTitle(sendTitle);
    mailBox.setContent(sendContent);
    mailBoxDao.insert(mailBox);
    boardDao.delete(boardNo);
    reportDao.delete(report.getNo());
    sqlSessionFactory.openSession().commit();


    // mv.addObject("medicineList", medicineList);
    mv.setViewName("redirect:adminReportApprovalList");
    return mv;
  }

  @GetMapping("/admin/reportReject")
  public ModelAndView reportReject(MailBox mailBox, HttpSession session, String id, int no, String reportId) throws Exception {

    Report report = reportDao.findByReport(no, id);

    String reportedBoardWriter = report.getRequestBoard().getWriter().getId();
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

    Member reportedMember = memberDao.findById(reportId);
    Member reportMember = memberDao.findById(reportedBoardWriter);
    String sendTitle = "게시판 신고 결과";
    String sendContent = "게시판 신고 접수가 거절되어 게시글이 유지됨을 알려드립니다!";

    mailBox.setSender(member);
    mailBox.setReceiver(reportedMember);
    mailBox.setTitle(sendTitle);
    mailBox.setContent(sendContent);

    mailBox.setReceiver(reportMember);
    mailBox.setTitle(sendTitle);
    mailBox.setContent(sendContent);

    mailBoxDao.insert(mailBox);
    reportDao.delete(report.getNo());
    sqlSessionFactory.openSession().commit();

    // mv.addObject("medicineList", medicineList);
    mv.setViewName("redirect:adminReportApprovalList");
    return mv;
  }




}