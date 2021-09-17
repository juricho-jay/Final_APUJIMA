package pms.handler;

import java.util.List;
import pms.domain.FreeBoard;
import util.Prompt;

public class FreeBoardUpdateHandler extends AbstractFreeBoardHandler{

  public FreeBoardUpdateHandler(List<FreeBoard> freeBoardList) {
    super(freeBoardList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[게시글 변경] 페이지입니다.");
    System.out.println();
    int no = Prompt.inputInt("게시글 번호> ");

    FreeBoard freeBoard = findByNo(no);

    if (freeBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }else if (freeBoard.getWriter().getId() == AuthLoginHandler.getLoginUser().getId()) {
      String title = Prompt.inputString(String.format("제목(%s)> ", freeBoard.getTitle()));
      String content = Prompt.inputString(String.format("내용(%s)> ", freeBoard.getContent()));

      String input = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
      if(input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("게시글 변경을 취소하였습니다.");
        return;
      }

      freeBoard.setTitle(title);
      freeBoard.setContent(content);
      System.out.println("게시글을 변경하였습니다.");
    } else if (freeBoard.getWriter().getId() != AuthLoginHandler.getLoginUser().getId()) {
      System.out.println("변경 권한이 없습니다.");
    } 

  }
}
