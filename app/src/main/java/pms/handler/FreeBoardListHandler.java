package pms.handler;

import java.util.List;
import pms.domain.FreeBoard;

public class FreeBoardListHandler extends AbstractFreeBoardHandler{

  public FreeBoardListHandler(List<FreeBoard> freeBoardList) {
    super(freeBoardList);
  }

  public void execute() {
    System.out.println("[게시글 목록] 페이지입니다.");
    System.out.println();

    if(freeBoardList.size() == 0) {
      System.out.println("현재 게시판에 작성된 글이 없습니다.");
      return;
    } else {
      for (FreeBoard freeBoard : freeBoardList) {
        System.out.printf("%d, %s, %s, %s, %d, %d\n",
            freeBoard.getNo(),
            freeBoard.getTitle(),
            freeBoard.getWriter().getId(), //우리는 name이 아니라 id를 가져옴
            freeBoard.getRegisteredDate(),
            freeBoard.getViewCount(),
            freeBoard.getLike());
      }
    }
  }
}
