package pms.handler;

import java.util.List;
import pms.domain.FreeBoard;
import util.Prompt;

public class FreeBoardDeleteHandler extends AbstractFreeBoardHandler{

  public FreeBoardDeleteHandler(List<FreeBoard> freeBoardList) {
    super(freeBoardList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[삭제] 페이지입니다.");
    System.out.println();

    int no = (int)request.getAttribute("num");

    FreeBoard freeBoard = findByNo(no);


    if (!freeBoard.getWriter().getId().equals(AuthLoginHandler.getLoginUser().getId()) ) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }
    // 사실 이것도 지워도 될 듯 강사님께 여쭤보고 지우기( == 이거는 인스턴스가 달라서
    // 다르게 나오는건지

    String input = Prompt.inputString(" ❗ 정말 삭제하시겠습니까? (y/N)> ");
    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }

    request.getRequestDispatcher("/comment/autoDelete").forward(request);
    request.getRequestDispatcher("/like/autoDelete").forward(request);
    freeBoardList.remove(freeBoard);
    System.out.println("게시글을 삭제하였습니다.");

  }

}