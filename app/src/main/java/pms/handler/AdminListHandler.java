package pms.handler;

import java.util.List;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.Medicine;

public class AdminListHandler implements Command {

  List<Medicine> requestList;
  List<Medicine> medicineList;
  List<FreeBoard> reportList;
  List<FreeBoard> freeBoardList;
  List<DoctorBoard> doctorReportList;


  public AdminListHandler(List<Medicine> requestList, 
      List<Medicine> medicineList, 
      List<FreeBoard> reportList, 
      List<FreeBoard> freeBoardList, 
      List<DoctorBoard> doctorReportList) {
    this.requestList = requestList;
    this.medicineList = medicineList;
    this.reportList = reportList;
    this.freeBoardList = freeBoardList;
    this.doctorReportList = doctorReportList;

  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("[승인 요청/신고 목록]");
    System.out.println();
    System.out.println("[약품 승인 요청 내역]");
    if(requestList.size() == 0) {
      System.out.println("약품 승인 요청내역이 없습니다.");

    } else {
      for(int i = 0; i < requestList.size(); i++) {
        System.out.printf("약품명 : %s\n"
            + "효  능 : %s\n", requestList.get(i).getName(), requestList.get(i).getEffect());
        System.out.println();
      }
    }
    System.out.println();
    System.out.println("[게시판 신고 접수 내역]");

    if(reportList.size() == 0 && doctorReportList.size() == 0) {
      System.out.println("게시판 신고 접수건이 없습니다.");
    } else {
      for(int i = 0; i < reportList.size(); i++) {
        System.out.printf("자유게시판 신고 번호: %d\n"
            + "게시판 제목: %s\n", reportList.get(i).getNo(), reportList.get(i).getTitle());
      }
      for(int i = 0; i < doctorReportList.size(); i++) {
        System.out.printf("지식인 게시판 신고 번호: %d\n"
            + "게시판 제목: %s\n", doctorReportList.get(i).getNo(), doctorReportList.get(i).getTitle());
      }
    }

  }

}

