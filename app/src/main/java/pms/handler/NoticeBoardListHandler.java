package pms.handler;

import java.util.List;
import pms.domain.NoticeBoard;

public class NoticeBoardListHandler extends AbstractNoticeBoardHandler{


  public NoticeBoardListHandler(List<NoticeBoard> noticeBoardList) {
    super(noticeBoardList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[게시글 목록] 페이지입니다.");
    System.out.println();

    if(noticeBoardList.size() == 0) {
      System.out.println("현재 게시판에 작성된 글이 없습니다.");
      return;
    } else {
      for (NoticeBoard noticeBoard : noticeBoardList) {
        System.out.printf("%d, %s, %s, %s, %d, %d\n",
            //            noticeNumBoardList
            noticeBoard.getNo(),
            noticeBoard.getTitle(),
            noticeBoard.getWriter().getId(),
            //     AuthLoginHandler.getLoginUser().getId(), //우리는 name이 아니라 id를 가져옴
            noticeBoard.getRegisteredDate(),
            noticeBoard.getViewCount(),
            noticeBoard.getLike());
      }
    }
  }
}
