package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.FreeBoard;
import util.Prompt;

public class FreeBoardAddHandler extends AbstractFreeBoardHandler{

  public FreeBoardAddHandler(List<FreeBoard> freeBoardList) {
    super(freeBoardList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println();
    System.out.println("[자유게시판 글쓰기] 페이지입니다.");
    System.out.println();
    FreeBoard freeBoard = new FreeBoard();

    if (freeBoardList.size() == 0 && FreeBoard.lastIndex == 0) {
      FreeBoard.setLastIndex(1);
      FreeBoard.setNextNo(1);

    } else if (FreeBoard.lastIndex != (freeBoardList.size() - 1)) {
      FreeBoard.lastIndex = freeBoardList.get(freeBoardList.size() - 1).getNo();
      FreeBoard.setNextNo(freeBoardList.size() - 1);

    } else if (FreeBoard.lastIndex == (freeBoardList.size() - 1)) {
      FreeBoard.setNextNo(FreeBoard.getNextNo() + 1);
      FreeBoard.lastIndex = freeBoardList.size();
    }



    freeBoard.setNo(FreeBoard.lastIndex++);
    freeBoard.setTitle(Prompt.inputString("제목> "));
    freeBoard.setContent(Prompt.inputString("내용> "));


    freeBoard.setWriter(AuthLoginHandler.getLoginUser());
    freeBoard.setRegisteredDate(new Date(System.currentTimeMillis()));

    freeBoardList.add(freeBoard);
    System.out.println("게시판 등록이 완료되었습니다.");
  }

}