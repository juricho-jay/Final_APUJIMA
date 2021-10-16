package pms.handler;

import java.util.List;
import pms.dao.NoticeBoardDao;
import pms.domain.NoticeBoard;
import util.Prompt;

public class NoticeBoardSearchHandler implements Command {

  NoticeBoardDao noticeBoardDao;

  public NoticeBoardSearchHandler(NoticeBoardDao noticeBoardDao) {
    this.noticeBoardDao = noticeBoardDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    int count = 0;
    System.out.println("[게시글 검색] 페이지입니다.");
    System.out.println();
    String input = Prompt.inputString("검색어> ");

    List<NoticeBoard> noticeBoardList = noticeBoardDao.findByKeyword(input);

    for (NoticeBoard noticeBoard : noticeBoardList) {
      if (!noticeBoard.getTitle().contains(input) &&
          !noticeBoard.getContent().contains(input) &&
          !noticeBoard.getWriter().getId().contains(input)) {
        continue;
      }
      count ++;
      System.out.printf("%d, %s, %s, %s, %d, %d\n",
          noticeBoard.getNo(),
          noticeBoard.getTitle(),
          noticeBoard.getWriter().getId(),
          noticeBoard.getRegisteredDate(),
          noticeBoard.getViewCount(),
          noticeBoard.getLike());
    }

    if (count == 0) {
      System.out.println("찾는 게시글이 없습니다.");
    }
  }
}
