package pms.handler;

import java.util.Collection;
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

    Collection<FreeBoard> freeBoardList = freeBoardDao.findByKeyword(input);

    //    HashMap<String,String> params = new HashMap<>();
    //    params.put("keyword", String.valueOf(input));
    //
    //    requestAgent.request("freeBoard.selectListByKeyword", params);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println("목록 조회 실패!");
    //      return;
    //    }
    //
    //    Collection<FreeBoard> freeBoardList = requestAgent.getObjects(FreeBoard.class);
    //

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
