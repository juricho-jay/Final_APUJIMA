package pms.handler;

import java.util.List;
import pms.domain.NoticeBoard;
import util.Prompt;

public class NoticeBoardDeleteHandler extends AbstractNoticeBoardHandler {

  public NoticeBoardDeleteHandler(List<NoticeBoard> noticeBoardList) {
    super(noticeBoardList);
  }


  @Override
  public void execute(CommandRequest request) {
    System.out.println("[삭제] 페이지입니다.");
    System.out.println();
    int no = Prompt.inputInt("게시글 번호> ");

    NoticeBoard noticeBoard = findByNo(no);

    if (noticeBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    //    if (!noticeBoard.getWriter().getId().equals(AuthLoginHandler.getLoginUser().getId())) {
    //      System.out.println("삭제 권한이 없습니다.");
    //    }
    // 애초에 자신의 글이 아니면 삭제 기능이 뜨지 않음



    String input = Prompt.inputString(" ❗ 정말 삭제하시겠습니까? (y/N)> ");
    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }
    noticeBoardList.remove(noticeBoard);
    System.out.println("게시글을 삭제하였습니다.");
    //    }
    //  }
  }
}
