package pms.handler;

import java.util.Collection;
import pms.dao.FreeBoardDao;
import pms.domain.FreeBoard;

public class FreeBoardListHandler implements Command{

  FreeBoardDao freeBoardDao;

  public FreeBoardListHandler(FreeBoardDao freeBoardDao) {
    this.freeBoardDao = freeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 목록] 페이지입니다.");
    System.out.println();

    Collection<FreeBoard> freeBoardList = freeBoardDao.findAll();

    //    requestAgent.request("freeBoard.selectList", null);
    //
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println("현재 게시판에 작성된 글이 없습니다.");
    //      return;
    //    }
    //
    //    Collection<FreeBoard> freeBoardList = requestAgent.getObjects(FreeBoard.class);

    for (FreeBoard freeBoard : freeBoardList) {
      System.out.printf("%d, %s, %s, %s, %d\n",
          freeBoard.getNo(),
          freeBoard.getTitle(),
          freeBoard.getWriter().getId(), //우리는 name이 아니라 id를 가져옴
          freeBoard.getRegisteredDate(),
          freeBoard.getViewCount());
    }
  }
}
