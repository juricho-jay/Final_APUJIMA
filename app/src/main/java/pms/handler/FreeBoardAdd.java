package pms.handler;

import java.util.List;
import pms.domain.FreeBoard;
import util.Prompt;

public class FreeBoardAdd extends AbstractFreeBoard{

  public FreeBoardAdd(List<FreeBoard> freeBoardList) {
    super(freeBoardList);
    // TODO Auto-generated constructor stub
  }

  public void add() {
    System.out.println();
    System.out.println("[새 게시글]");

    FreeBoard freeboard = new FreeBoard();

    freeboard.setNo(Prompt.inputInt("번호? "));
    freeboard.setTitle(Prompt.inputString("제목? "));
    freeboard.setContent(Prompt.inputString("내용? "));

    freeBoardList.add(freeboard);
  }

}
