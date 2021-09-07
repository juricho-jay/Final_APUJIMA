package pms.handler;

import java.util.List;
import pms.domain.FreeBoard;
import util.Prompt;

public class FreeBoardAddHandler extends AbstractFreeBoardHandler{

  public FreeBoardAddHandler(List<FreeBoard> freeBoardList) {
    super(freeBoardList);
    // TODO Auto-generated constructor stub
  }

  public void execute() {
    System.out.println("[글쓰기] 페이지입니다.");
    System.out.println();
    FreeBoard freeBoard = new FreeBoard();

    freeBoard.setNo(Prompt.inputInt("번호? "));
    freeBoard.setTitle(Prompt.inputString("제목? "));
    freeBoard.setContent(Prompt.inputString("내용? "));

    freeBoardList.add(freeBoard);
  }

}
