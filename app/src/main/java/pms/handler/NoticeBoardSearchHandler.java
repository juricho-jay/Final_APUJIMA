package pms.handler;

import java.util.List;
import pms.domain.NoticeBoard;
import util.Prompt;

public class NoticeBoardSearchHandler extends AbstractNoticeBoardHandler {

  public NoticeBoardSearchHandler(List<NoticeBoard> noticeBoardList) {
    super(noticeBoardList);
  }


  @Override
  public void execute() {
    System.out.println("[게시글 검색] 페이지입니다.");
    System.out.println();
    String input = Prompt.inputString("검색어> ");

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
    }
  }
}
