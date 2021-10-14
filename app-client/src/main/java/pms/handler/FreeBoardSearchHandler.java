package pms.handler;

import java.util.List;
import pms.dao.FreeBoardDao;
import pms.domain.FreeBoard;
import util.Prompt;

public class FreeBoardSearchHandler implements Command {

  FreeBoardDao freeBoardDao;

  public FreeBoardSearchHandler(FreeBoardDao freeBoardDao) {
    this.freeBoardDao = freeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int count = 0;
    System.out.println("[게시글 검색] 페이지입니다.");
    System.out.println();
    String input = Prompt.inputString("검색어> ");

    List<FreeBoard> freeBoardList = freeBoardDao.findByKeyword(input);

    for (FreeBoard freeBoard : freeBoardList) {
      if (!freeBoard.getTitle().contains(input) &&
          !freeBoard.getContent().contains(input) &&
          !freeBoard.getWriter().getId().contains(input)) {
        continue;
      }
      count++;
      System.out.printf("%d, %s, %s, %s, %d\n",
          freeBoard.getNo(),
          freeBoard.getTitle(),
          freeBoard.getWriter().getId(),
          freeBoard.getRegisteredDate(),
          freeBoard.getViewCount());
    }

    if (count == 0 ) {
      System.out.println("찾는 게시물이 없습니다.");
    }
  }
}
