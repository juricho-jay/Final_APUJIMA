package apus.web;

import java.util.Collection;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.BoardDao;
import apus.dao.MemberDao;
import apus.dao.ReportDao;
import apus.domain.Report;

public class ReportController {
  @Autowired ReportDao reportDao;
  @Autowired BoardDao boardDao;
  @Autowired MemberDao memberDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @GetMapping("/admin/approvalReport")
  public ModelAndView approve() throws Exception{
    Collection<Report> reportApprovalList = reportDao.findAll();
    ModelAndView mv = new ModelAndView();
    mv.addObject("reportApprovalList",reportApprovalList);
    mv.addObject("pageTite" , "요청 승인 대기 목록");
    mv.addObject("contetnUrl","/admin/AdminReportApprovalList.jsp");
    mv.setViewName("/admin/AdminReportApprovalList");
    return mv;
  }


  @GetMapping("/admin/reportConfirm")
  public ModelAndView confirm(int no, String id) throws Exception{

    Report report = reportDao.findByReport(no, id);
    int boardNo = report.getRequestBoard().getNo();
    reportDao.confirmReport(report.getNo());
    boardDao.delete(boardNo);

    ModelAndView mv = new ModelAndView();
    mv.addObject("report",report);
    mv.addObject("pageTite" , "요청 승인 대기 목록");
    mv.setViewName("redirect:approvalReport");
    return mv;
  }

  @GetMapping("/admin/reportReject")
  public ModelAndView reject(int no, String id) throws Exception{

    Report report = reportDao.findByReport(no, id);

    int boardNo = report.getRequestBoard().getNo();
    reportDao.confirmReport(report.getNo());
    boardDao.delete(boardNo);
    sqlSessionFactory.openSession().commit();


    ModelAndView mv = new ModelAndView();
    mv.addObject("report",report);
    mv.addObject("pageTite" , "요청 승인 대기 목록");
    mv.setViewName("redirect:approvalReport");
    return mv;
  }
}
