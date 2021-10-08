package pms.handler;

import java.util.Collection;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.Medicine;
import request.RequestAgent;

public class AdminListHandler implements Command {

  RequestAgent requestAgent;

  public AdminListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[승인 요청/신고 목록]");
    System.out.println();
    System.out.println("[약품 승인 요청 내역]");

    requestAgent.request("medicine.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("약품 승인 요청내역이 없습니다.");
    } else {
      Collection<Medicine> requestList = requestAgent.getObjects(Medicine.class);

      for (Medicine medicine : requestList) {
        System.out.printf("약품명 : %s\n"
            + "효 능 : %s\n", medicine.getName(), medicine.getEffect());
      }

      System.out.println();
    }

    System.out.println();
    System.out.println("[게시판 신고 접수 내역]");

    requestAgent.request("report.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("자유게시판 신고 접수건이 없습니다.");
    } else {
      Collection<FreeBoard> reportList = requestAgent.getObjects(FreeBoard.class);
      for (FreeBoard freeBoard : reportList) {
        System.out.printf("게시판 번호 : %d\n"
            + "게시판 제목 : %s\n",
            freeBoard.getNo(),
            freeBoard.getTitle());
      }
    }

    requestAgent.request("doctorReport.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("지식인 게시판 신고 접수건이 없습니다.");
    } else {
      Collection<DoctorBoard> doctorReportList = requestAgent.getObjects(DoctorBoard.class);
      for (DoctorBoard doctorBoard : doctorReportList) {
        System.out.printf("게시판 번호 : %d\n"
            + "게시판 제목 : %s\n",
            doctorBoard.getNo(),
            doctorBoard.getTitle());
      }


    }

  }
}

