package pms.handler;

import java.util.HashMap;
import pms.domain.DoctorBoard;
import request.RequestAgent;
import util.Prompt;

public class DoctorBoardUpdateHandler implements Command {

  RequestAgent requestAgent;

  public DoctorBoardUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 변경] 페이지입니다.");
    System.out.println();
    int no = (int)request.getAttribute("no");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("doctorBoard.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    DoctorBoard doctorBoard = requestAgent.getObject(DoctorBoard.class);

    String title = Prompt.inputString(String.format("제목(%s)> ", doctorBoard.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s)> ", doctorBoard.getContent()));

    String input = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }

    doctorBoard.setTitle(title);
    doctorBoard.setContent(content);

    requestAgent.request("doctorBoard.update", doctorBoard);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("게시글 변경 실패!");
      return;
    }

    System.out.println("게시글을 변경하였습니다.");

  }
}

