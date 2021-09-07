package pms.handler;

import java.util.List;
import pms.domain.FreeBoard;
import util.Prompt;

public class FreeBoardDetailHandler extends AbstractFreeBoardHandler{

  public FreeBoardDetailHandler(List<FreeBoard> freeBoardList) {
    super(freeBoardList);
  }


  public void execute() {
    System.out.println("[상세보기] 페이지입니다.");
    System.out.println();
    int no = Prompt.inputInt("게시글 번호> ");

    FreeBoard freeBoard = findByNo(no);

    if (freeBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    System.out.printf("제목: %s\n", freeBoard.getTitle());
    System.out.printf("내용: %s\n", freeBoard.getContent());
    System.out.printf("작성자: %s\n", freeBoard.getWriter().getId()); // 우리는 익명이기 때문에 Id로
    System.out.printf("등록일: %s\n", freeBoard.getRegisteredDate());

    freeBoard.setViewCount(freeBoard.getViewCount() + 1);
    System.out.printf("조회수: %d\n", freeBoard.getViewCount());
    System.out.printf("좋아요: %d\n", freeBoard.getLike());

    String likeNum = Prompt.inputString("[ 좋아요 (#: 👍🏻) / 넘어가기: Enter ]> ");
    if (likeNum.equals("#")) {
      freeBoard.setLike(freeBoard.getLike() + 1);
    } else {
      return;
    }
  }
}
