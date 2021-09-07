package pms.handler;

import java.util.List;
import pms.domain.NoticeBoard;
import util.Prompt;

public class NoticeBoardDetailHandler extends AbstractNoticeBoardHandler{

  public NoticeBoardDetailHandler(List<NoticeBoard> noticeBoardList) {
    super(noticeBoardList);
  }

  public void execute() {
    System.out.println("[상세보기] 페이지입니다.");
    System.out.println();
    int no = Prompt.inputInt("게시글 번호> ");

    NoticeBoard noticeBoard = findByNo(no);

    if (noticeBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", noticeBoard.getTitle());
    System.out.printf("내용: %s\n", noticeBoard.getContent());
    System.out.printf("작성자: %s\n", noticeBoard.getWriter().getId()); // 우리는 익명이기 때문에 Id로
    System.out.printf("등록일: %s\n", noticeBoard.getRegisteredDate());

    noticeBoard.setViewCount(noticeBoard.getViewCount() + 1);
    System.out.printf("조회수: %d\n", noticeBoard.getViewCount());
    System.out.printf("좋아요: %d\n", noticeBoard.getLike());

    String likeNum = Prompt.inputString("[ 좋아요 (#: 👍🏻) / 넘어가기: Enter ]> ");
    if (likeNum.equals("#")) {
      noticeBoard.setLike(noticeBoard.getLike() + 1);
    } else {
      return;
    }
  }
}



