package pms.handler;

import java.util.List;
import pms.domain.FreeBoard;
import pms.domain.Medicine;

public class AdminListHandler implements Command {

  List<Medicine> requestList;
  List<Medicine> medicineList;
  List<FreeBoard> reportList;
  List<FreeBoard> freeBoardList;


  public AdminListHandler(List<Medicine> requestList, List<Medicine> medicineList, List<FreeBoard> reportList, List<FreeBoard> freeBoardList) {
    this.requestList = requestList;
    this.medicineList = medicineList;
    this.reportList = reportList;
    this.freeBoardList = freeBoardList;

  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("[승인 요청/신고 목록]");

    for(int i = 0; i < requestList.size(); i++) {
      System.out.printf("약품명 : %s\n 효과 : %s\n", requestList.get(i).getName(), requestList.get(i).getEffect());
    }
    for(int i = 0; i < reportList.size(); i++) {
      System.out.printf("신고 게시판 번호: %d\n 게시판 제목: %s\n", reportList.get(i).getNo(), requestList.get(i).getName());
    }

  }

}

