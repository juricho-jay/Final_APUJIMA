package pms.handler;

import java.util.List;
import pms.domain.FreeBoard;

public class FreeBoardList extends AbstractFreeBoard{

  public FreeBoardList(List<FreeBoard> freeBoardList) {
    super(freeBoardList);
  }

  public void list() {
    System.out.println("[게시글 목록]");

    for (FreeBoard freeBoard : freeBoardList) {
      System.out.printf("%d, %s, %s ,\n", 
          freeBoard.getNo(),
          freeBoard.getTitle(),
          freeBoard.getContent()
          );
    }
  }

}
