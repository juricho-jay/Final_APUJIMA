package pms.handler;

import java.util.HashMap;
import pms.domain.NoticeBoard;
import request.RequestAgent;
import util.Prompt;

public class NoticeBoardUpdateHandler implements Command {

  RequestAgent requestAgent;

  public NoticeBoardUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[공지사항 게시글 변경] 페이지입니다.");
    System.out.println();
    int no = Prompt.inputInt("게시글 번호> ");

    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("noticeboard.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;

    } 

    // else if (noticeBoard.getWriter().getId().equals(AuthLoginHandler.getLoginUser().getId())) {
    NoticeBoard noticeBoard = requestAgent.getObject(NoticeBoard.class);


    String title = Prompt.inputString(String.format("제목(%s)> ", noticeBoard.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s)> ", noticeBoard.getContent()));

    String input = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }

    noticeBoard.setTitle(title);
    noticeBoard.setContent(content);

    requestAgent.request("noticeBoard.update", noticeBoard);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("게시글 변경 실패");
      System.out.println(requestAgent.getObject(String.class));
      return;
    }
    System.out.println("게시글을 변경하였습니다.");

  }
}
