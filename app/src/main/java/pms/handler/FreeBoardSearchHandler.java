package pms.handler;

import java.util.List;
import pms.domain.FreeBoard;
import util.Prompt;

public class FreeBoardSearchHandler  extends AbstractFreeBoardHandler {

  public FreeBoardSearchHandler(List<FreeBoard> freeBoardList) {
    super(freeBoardList);
  }


  @Override
  public void execute() {
    System.out.println("[게시글 검색] 페이지입니다.");
    System.out.println();
    String input = Prompt.inputString("검색어> ");

    for (FreeBoard freeBoard : freeBoardList) {
      if (!freeBoard.getTitle().contains(input) &&
          !freeBoard.getContent().contains(input) &&
          !freeBoard.getWriter().getId().contains(input)) {
        continue;
      }
      System.out.printf("%d, %s, %s, %s, %d, %d\n",
          freeBoard.getNo(),
          freeBoard.getTitle(),
          freeBoard.getWriter().getId(),
          freeBoard.getRegisteredDate(),
          freeBoard.getViewCount(),
          freeBoard.getLike());
    }
  }
}
