package pms.handler;

import java.util.List;
import pms.domain.NoticeBoard;
import request.RequestAgent;

public class NoticeBoardListHandler implements Command{

  RequestAgent requestAgent;

  public NoticeBoardListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 목록] 페이지입니다.");
    System.out.println();

    requestAgent.request("noticeBoard.selectList", null);


    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("현재 게시판에 작성된 글이 없습니다.!");
      return;
    }

    List<NoticeBoard> noticeBoardList = (List<NoticeBoard>) requestAgent.getObjects(NoticeBoard.class);


    for (NoticeBoard noticeBoard : noticeBoardList) {
      System.out.printf("%d, %s, %s, %s, %d\n",
          //            noticeNumBoardList
          noticeBoard.getNo(),
          noticeBoard.getTitle(),
          noticeBoard.getWriter().getId(),
          noticeBoard.getRegisteredDate(),
          noticeBoard.getViewCount()
          );
    }
  }
}
