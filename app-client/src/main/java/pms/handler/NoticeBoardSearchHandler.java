package pms.handler;

import java.util.Collection;
import java.util.HashMap;
import pms.domain.NoticeBoard;
import request.RequestAgent;
import util.Prompt;

public class NoticeBoardSearchHandler implements Command{

  RequestAgent requestAgent;

  public NoticeBoardSearchHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }





  @Override
  public void execute(CommandRequest request) throws Exception {

    Collection<NoticeBoard> noticeBoardList = requestAgent.getObjects(NoticeBoard.class);

    System.out.println("[게시글 검색] 페이지입니다.");
    System.out.println();
    String input = Prompt.inputString("검색어> ");


    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", input);
    requestAgent.request("noticeBoard.search", params);


    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("현재 게시판에 작성된 글이 없습니다.!");
      return;
    }

    int count = 0;

    for (NoticeBoard noticeBoard : noticeBoardList) {
      if (!noticeBoard.getTitle().contains(input) &&
          !noticeBoard.getContent().contains(input) &&
          !noticeBoard.getWriter().getId().contains(input)) {
        continue;
      }
      System.out.printf("%d, %s, %s, %s, %d, %d\n",
          noticeBoard.getNo(),
          noticeBoard.getTitle(),
          noticeBoard.getWriter().getId(),
          noticeBoard.getRegisteredDate(),
          noticeBoard.getViewCount(),
          noticeBoard.getLike());
      count ++;
    }

    if (count == 0) {
      System.out.println("찾는 게시글이 없습니다.");
    }
  }
}
