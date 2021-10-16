package pms.handler;

import java.util.List;
import pms.dao.DoctorReportDao;
import pms.dao.ReportDao;
import pms.dao.RequestDao;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.Medicine;

public class AdminListHandler implements Command {

  RequestDao requestDao;
  ReportDao reportDao;
  DoctorReportDao doctorReportDao;

  public AdminListHandler(RequestDao requestDao
      , ReportDao reportDao, DoctorReportDao doctorReportDao) {
    this.requestDao = requestDao;
    this.reportDao = reportDao;
    this.doctorReportDao = doctorReportDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[승인 요청/신고 목록]");
    System.out.println();
    System.out.println("[약품 승인 요청 내역]");

    List<Medicine> requestList = requestDao.findAll();
    if (requestList == null) {
      System.out.println("약품 승인 요청건이 없습니다.");
      return;
    }
    for (Medicine medicine : requestList) {
      System.out.printf("약품명 : %s\n"
          + "효 능 : %s\n", medicine.getName(), medicine.getEffect());
    }

    System.out.println();

    //    requestAgent.request("request.selectList", null);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println("약품 승인 요청내역이 없습니다.");
    //    } else {
    //      Collection<Medicine> requestList = requestAgent.getObjects(Medicine.class);
    //
    //      for (Medicine medicine : requestList) {
    //        System.out.printf("약품명 : %s\n"
    //            + "효 능 : %s\n", medicine.getName(), medicine.getEffect());
    //      }
    //
    //      System.out.println();
    //    }

    System.out.println();
    System.out.println("[게시판 신고 접수 내역]");

    List<FreeBoard> reportList = reportDao.findAll();
    if (reportList == null) {
      System.out.println("자유게시판 신고 접수건이 없습니다.");
    }
    for (FreeBoard freeBoard : reportList) {
      System.out.printf("게시판 번호 : %d\n"
          + "게시판 제목 : %s\n",
          freeBoard.getNo(),
          freeBoard.getTitle());
    }

    //    requestAgent.request("report.selectList", null);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println("자유게시판 신고 접수건이 없습니다.");
    //    } else {
    //      Collection<FreeBoard> reportList = requestAgent.getObjects(FreeBoard.class);
    //      for (FreeBoard freeBoard : reportList) {
    //        System.out.printf("게시판 번호 : %d\n"
    //            + "게시판 제목 : %s\n",
    //            freeBoard.getNo(),
    //            freeBoard.getTitle());
    //      }
    //    }

    List<DoctorBoard> doctorReportList = doctorReportDao.findAll();
    if (doctorReportList == null) {
      System.out.println("자유게시판 신고 접수건이 없습니다.");
    }
    for (DoctorBoard doctorBoard : doctorReportList) {
      System.out.printf("게시판 번호 : %d\n"
          + "게시판 제목 : %s\n",
          doctorBoard.getNo(),
          doctorBoard.getTitle());
    }

    //    requestAgent.request("doctorReport.selectList", null);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println("지식인 게시판 신고 접수건이 없습니다.");
    //    } else {
    //      Collection<DoctorBoard> doctorReportList = requestAgent.getObjects(DoctorBoard.class);
    //      for (DoctorBoard doctorBoard : doctorReportList) {
    //        System.out.printf("게시판 번호 : %d\n"
    //            + "게시판 제목 : %s\n",
    //            doctorBoard.getNo(),
    //            doctorBoard.getTitle());
    //      }
    //
    //
    //    }

  }
}

