package pms.handler;

import java.util.HashMap;
import pms.domain.DoctorBoard;
import request.RequestAgent;
import util.Prompt;

public class DoctorBoardDeleteHandler implements Command {

  RequestAgent requestAgent;

  public DoctorBoardDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[삭제] 페이지입니다.");
    System.out.println();

    int no = (int)request.getAttribute("no"); // 게시판 번호

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("doctorBoard.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("게시글이 없습니다.");
      return;
    }

    requestAgent.request("doctorBoard.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 게시판이 없습니다!");
      return;
    }

    DoctorBoard doctorBoard = requestAgent.getObject(DoctorBoard.class);


    if (!doctorBoard.getWriter().getId().equals(AuthLoginHandler.getLoginUser().getId()) ) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }
    // 사실 이것도 지워도 될 듯 강사님께 여쭤보고 지우기( == 이거는 인스턴스가 달라서
    // 다르게 나오는건지

    String input = Prompt.inputString(" ❗ 정말 삭제하시겠습니까? (y/N)> ");
    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }

    request.getRequestDispatcher("/comment/autoDelete").forward(request);
    request.getRequestDispatcher("/like/autoDelete").forward(request);


    requestAgent.request("doctorBoard.delete", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("게시글 삭제 실패!");
      System.out.println(requestAgent.getObject(String.class));
      return;
    }
    System.out.println("게시글을 삭제하였습니다.");

  }

}