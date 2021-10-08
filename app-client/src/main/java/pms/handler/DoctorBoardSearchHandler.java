package pms.handler;

import java.util.Collection;
import java.util.HashMap;
import pms.domain.DoctorBoard;
import request.RequestAgent;
import util.Prompt;

public class DoctorBoardSearchHandler implements Command {

  RequestAgent requestAgent;

  public DoctorBoardSearchHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    int count = 0;
    System.out.println("[지식인 게시글 검색] 페이지입니다.");
    System.out.println();
    String input = Prompt.inputString("검색어> ");

    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", String.valueOf(input));

    requestAgent.request("doctorBoard.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("목록 조회 실패!");
      return;
    }

    Collection<DoctorBoard> doctorBoardList = requestAgent.getObjects(DoctorBoard.class);

    for (DoctorBoard doctorBoard : doctorBoardList) {
      if (!doctorBoard.getTitle().contains(input) &&
          !doctorBoard.getContent().contains(input) &&
          !doctorBoard.getWriter().getId().contains(input)) {
        continue;
      }
      count++;
      System.out.printf("%d, %s, %s, %s, %d\n",
          doctorBoard.getNo(),
          doctorBoard.getTitle(),
          doctorBoard.getWriter().getId(),
          doctorBoard.getRegisteredDate(),
          doctorBoard.getViewCount());
    }

    if (count == 0 ) {
      System.out.println("찾는 게시물이 없습니다.");
    }
  }
}
