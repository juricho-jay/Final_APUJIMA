package pms.handler;

import java.util.List;
import pms.domain.FreeBoard;
import util.Prompt;

public class FreeBoardDeleteHandler extends AbstractFreeBoardHandler{

  public FreeBoardDeleteHandler(List<FreeBoard> freeBoardList) {
    super(freeBoardList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[삭제] 페이지입니다.");
    System.out.println();
    int temp = 0;
    int no = Prompt.inputInt("게시글 번호> ");

    FreeBoard freeBoard = findByNo(no);




    if (freeBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }


    if (freeBoard.getWriter().getId() != AuthLoginHandler.getLoginUser().getId()) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }





    String input = Prompt.inputString(" ❗ 정말 삭제하시겠습니까? (y/N)> ");
    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }
    if(freeBoard == freeBoardList.get(freeBoardList.size()-1)) {
      temp = freeBoardList.get(freeBoardList.size()-1).getNo() + 1;
      FreeBoard.lastIndex++;
    }

    freeBoardList.remove(freeBoard);
    System.out.println("게시글을 삭제하였습니다.");

  }

}