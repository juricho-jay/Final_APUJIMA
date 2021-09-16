package pms.handler;

import java.util.List;
import pms.domain.NoticeBoard;

public class NoticeBoardListHandler extends AbstractNoticeBoardHandler{

  public NoticeBoardListHandler(List<NoticeBoard> noticeBoardList) {
    super(noticeBoardList);
  }

  @Override
  public void execute() {
    System.out.println("[게시글 목록] 페이지입니다.");
    System.out.println();
    for (NoticeBoard noticeBoard : noticeBoardList) {
      System.out.printf("%d, %s, %s, %s, %s, %d, %d\n",
          noticeBoard.getNo(),
          noticeBoard.getTitle(),
          noticeBoard.getContent(),
          //          AuthLoginHandler.getLoginUser().getId(), //우리는 name이 아니라 id를 가져옴

          noticeBoard.getRegisteredDate(),
          noticeBoard.getViewCount(),
          noticeBoard.getLike());
    }
  }
}
