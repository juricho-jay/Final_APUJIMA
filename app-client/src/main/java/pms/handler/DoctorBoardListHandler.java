package pms.handler;

import java.util.Collection;
import pms.domain.DoctorBoard;
import request.RequestAgent;

public class DoctorBoardListHandler implements Command {

  RequestAgent requestAgent;

  public DoctorBoardListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 목록] 페이지입니다.");
    System.out.println();

    requestAgent.request("doctorBoard.selectList", null);


    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("현재 게시판에 작성된 글이 없습니다.");
      return;
    }

    Collection<DoctorBoard> doctorBoardList = requestAgent.getObjects(DoctorBoard.class);

    for (DoctorBoard doctorBoard : doctorBoardList) {
      System.out.printf("%d, %s, %s, %s, %d\n",
          doctorBoard.getNo(),
          doctorBoard.getTitle(),
          doctorBoard.getWriter().getId(), //우리는 name이 아니라 id를 가져옴
          doctorBoard.getRegisteredDate(),
          doctorBoard.getViewCount());
    }
  }
}
