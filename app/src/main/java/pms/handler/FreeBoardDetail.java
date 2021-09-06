package pms.handler;

import java.util.List;
import pms.domain.FreeBoard;
import util.Prompt;

public class FreeBoardDetail extends AbstractFreeBoard{

  public FreeBoardDetail(List<FreeBoard> freeBoardList) {
    super(freeBoardList);
  }

  FreeBoardUpdate freeBoardUpdate;
  FreeBoardDelete freeBoardDelete;

  public void detail() {
    System.out.println();
    System.out.println("[게시글 상세보기]");
    int no = Prompt.inputInt("번호? ");

    FreeBoard freeboard = findByNo(no);

    if (freeboard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    while(true) {
      System.out.printf("제목: %s\n", freeboard.getTitle());
      System.out.printf("내용: %s\n", freeboard.getContent());
      System.out.println();
      if(freeboard.getLike() == 0) {
        System.out.printf("[1.♡ : %s )] ", freeboard.getLike());
      } else {
        System.out.printf("[1.❤ : %s )] ", freeboard.getLike());
      }
      System.out.print("[2.수정] ");
      System.out.print("[3.삭제] ");
      System.out.println("[0.뒤로가기]");
      int select = Prompt.inputInt("선택> ");
      System.out.println();
      if (select == 1) {
        freeboard.setLike(freeboard.getLike() +1);
        continue;
      } else if (select == 2) {
        freeBoardUpdate.update();
      } else if (select == 3) {
        freeBoardDelete.delete();
        return;
      } else if (select == 0) {
        System.out.println();
        return;
      }
    }

  }

}
