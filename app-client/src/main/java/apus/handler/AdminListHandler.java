package apus.handler;

import java.util.List;
import apus.dao.MedicineDao;
import apus.dao.ReportDao;
import apus.domain.Medicine;
import apus.domain.Report;

public class AdminListHandler implements Command {

  MedicineDao medicineDao;
  ReportDao reportDao;

  public AdminListHandler(MedicineDao medicineDao, ReportDao reportDao) {
    this.medicineDao = medicineDao;
    this.reportDao = reportDao;
  }


  // 수정 사항: 1. board 통합 > doctor / free > 각자 조회

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[승인 요청/신고 목록]");
    System.out.println();
    int count = 0;
    String boardType = "";

    //    System.out.println();
    //    System.out.println("[게시판 신고 접수 내역]");

    List<Medicine> requestList = medicineDao.findAll();
    List<Report> reportList = reportDao.findAll();

    if (requestList == null && 
        reportList == null) {
      System.out.println("-[약품 승인 요청 내역]");
      System.out.println("약품 승인 요청건이 없습니다.");
      System.out.println();
      System.out.println("-[게시판 신고 접수 내역]");
      System.out.println("신고 접수건이 없습니다.");
      System.out.println();
      return;

    } 

    if (requestList != null) {
      for (Medicine medicine : requestList) {
        if(medicine.getCheck() == 0) {
          count++;
          System.out.println("-[약품 승인 요청 내역]");
          System.out.printf("약품명 : %s\n"
              + "효 능 : %s\n"
              + "승인 요청자 : %s\n", 
              medicine.getName(), 
              medicine.getEffect(),
              medicine.getRequester().getId());
          System.out.println();
        }
      }
      if(count == 0) {
        System.out.println("약품 승인 요청건이 없습니다.");
      }
    }


    if (reportList == null ) {
      System.out.println("-[게시판 신고 접수 내역]");
      System.out.println("신고 접수건이 없습니다.");
      System.out.println();
    } else {
      System.out.println("-[게시판 신고 접수 내역]");
      for (Report report : reportList) {

        if(report.getRequestBoard().getWhichBoard() == 1) 
          boardType = "자유 게시판";
        else if(report.getRequestBoard().getWhichBoard() == 2) 
          boardType = "지식인 게시판";

        System.out.printf("게시판 번호 : %d\n"
            + "게시판 종류 : %s\n"
            + "게시글 제목 : %s\n"
            + "신고 내용 : %s\n"
            + "신고 요청자 : %s\n",
            report.getRequestBoard().getNo(),
            boardType,
            report.getRequestBoard().getTitle(),
            report.getReason(),
            report.getRequester().getId());
        System.out.println();
      }
    } 

  }
}

