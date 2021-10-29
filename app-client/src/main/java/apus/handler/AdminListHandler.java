package apus.handler;

import java.util.List;
import apus.dao.ReportDao;
import apus.dao.RequestDao;
import apus.domain.Medicine;
import apus.domain.Report;

public class AdminListHandler implements Command {

  RequestDao requestDao;
  ReportDao reportDao;

  public AdminListHandler(RequestDao requestDao
      , ReportDao reportDao) {
    this.requestDao = requestDao;
    this.reportDao = reportDao;
  }


  // 수정 사항: 1. board 통합 > doctor / free > 각자 조회

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[승인 요청/신고 목록]");
    System.out.println();

    //    System.out.println();
    //    System.out.println("[게시판 신고 접수 내역]");

    List<Medicine> requestList = requestDao.findAll();
    List<Report> reportList = reportDao.findAll();

    if (requestList == null && 
        reportList == null) {
      System.out.println("-[약품 승인 요청 내역]");
      System.out.println("신고 접수건이 없습니다.");
      System.out.println();
      System.out.println("-[게시판 신고 접수 내역]");
      System.out.println("신고 접수건이 없습니다.");
      System.out.println();
      return;

    } else {
      if (requestList == null) {
        System.out.println("-[약품 승인 요청 내역]");
        System.out.println("약품 승인 요청건이 없습니다.");
        System.out.println();
      } 

      if (reportList == null ) {
        System.out.println("-[자유게시판 신고 접수 내역]");
        System.out.println("신고 접수건이 없습니다.");
        System.out.println();
        System.out.println("-[지식in게시판 신고 접수 내역]");
        System.out.println("신고 접수건이 없습니다.");
        System.out.println();
      } 
    }


    if (requestList != null) {
      System.out.println("-[약품 승인 요청 내역]");
      for (Medicine medicine : requestList) {
        System.out.printf("약품명 : %s\n"
            + "효 능 : %s\n", medicine.getName(), medicine.getEffect());
        System.out.println();
      }
    } 
    //    else if (reportList != null) {
    //      System.out.println("-[게시판 신고 접수 내역]");
    //      for (Report report : reportList) {
    //        // System.out.printf("게시판 번호 : %d"
    //            + ", 제목 : %s\n",
    //            //   freeBoard.getNo(),
    //            //    freeBoard.getTitle());
    //            System.out.println();
    //      }
    //    } 
    /*
     * else if (doctorReportList != null) { for (DoctorBoard doctorBoard : doctorReportList) {
     * System.out.printf("게시판 번호 : %d" + ", 제목 : %s\n", doctorBoard.getNo(),
     * doctorBoard.getTitle()); } return; }
     */

  }
}

