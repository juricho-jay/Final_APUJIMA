package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.NoticeBoard;
import util.Prompt;

public class NoticeBoardAddHandler extends AbstractNoticeBoardHandler{


  public NoticeBoardAddHandler(List<NoticeBoard> noticeBoardList) {
    super(noticeBoardList);

    NoticeBoard testNoticeBoard = new NoticeBoard();
    testNoticeBoard.setNo(1);
    testNoticeBoard.setTitle("9월 공지사항입니다.");
    testNoticeBoard.setWriter(AuthLoginHandler.loginUser);
    testNoticeBoard.setContent("욕설, 비방은 자제해주세요.");

    noticeBoardList.add(testNoticeBoard);
  }

  @Override
  public void execute() {
    System.out.println("[글쓰기] 페이지입니다.");
    System.out.println();
    NoticeBoard noticeBoard = new NoticeBoard();

    noticeBoard.setNo(Prompt.inputInt("번호> "));
    noticeBoard.setTitle(Prompt.inputString("제목> "));
    noticeBoard.setContent(Prompt.inputString("내용> "));


    noticeBoard.setWriter(AuthLoginHandler.getLoginUser());
    noticeBoard.setRegisteredDate(new Date(System.currentTimeMillis()));

    noticeBoardList.add(noticeBoard);
  }

}
