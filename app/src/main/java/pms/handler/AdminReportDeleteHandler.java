package pms.handler;

import java.util.List;
import pms.domain.FreeBoard;
import util.Prompt;

public class AdminReportDeleteHandler implements Command{

  List<FreeBoard> freeBoardList;
  List<FreeBoard> reportList;

  public AdminReportDeleteHandler(List<FreeBoard> freeBoardList, List<FreeBoard> reportList) {
    this.freeBoardList = freeBoardList;
    this.reportList = reportList;
  }

  public void execute() {
    System.out.println();
    System.out.println("[신고 게시판 삭제 허가]");

    if(reportList.size() == 0) {
      System.out.println("게시판 삭제 요청건이 없습니다.");
      return;
    }

    for(int i = 0; i< reportList.size(); i++) {
      System.out.printf("게시글 번호 : %d\n"
          + "게시글 제목 : %s\n"
          + "게시글 내용 : %s\n"
          + " 👍 개수 : %d\n",
          reportList.get(i).getNo(), reportList.get(i).getTitle(), reportList.get(i).getContent(),
          reportList.get(i).getLike() );
      System.out.println();
    }

    System.out.println("삭제할 게시글의 번호를 입력하세요.");
    String input = Prompt.inputString("번호 (뒤로가기 #)> ");
    if(input.equals("#"))
      return;

    int inputNum = Integer.parseInt(input);

    for(int i = 0; i < reportList.size(); i++) {
      if(inputNum == reportList.get(i).getNo()) {
        String input2 = Prompt.inputString("❗ 정말 삭제하시겠습니까? (y/N)> ");
        if(input2.equalsIgnoreCase("y")) {
          freeBoardList.remove(i);
          System.out.println("해당 게시글이 삭제되었습니다.");
          reportList.remove(i);
          break;
        } else {
          System.out.println("삭제가 취소되었습니다.");
          return;
        }
      } else if((i == reportList.size() -1)) {
        System.out.println("입력하신 게시글 번호가 잘못되었습니다.");
        break;
      }
    }
  }




}
