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
    testNoticeBoard.setRegisteredDate(new Date(System.currentTimeMillis()));
    testNoticeBoard.setContent("욕설, 비방은 자제해주세요.");

    noticeBoardList.add(testNoticeBoard);

    testNoticeBoard = new NoticeBoard();
    testNoticeBoard.setNo(2);
    testNoticeBoard.setTitle("8월 공지사항입니다.");
    testNoticeBoard.setWriter(AuthLoginHandler.loginUser);
    testNoticeBoard.setRegisteredDate(new Date(System.currentTimeMillis()));
    testNoticeBoard.setContent("안녕하세요. 우리 조는 천재입니다. 감사합니다.");

    noticeBoardList.add(testNoticeBoard);

    testNoticeBoard = new NoticeBoard();
    testNoticeBoard.setNo(3);
    testNoticeBoard.setTitle("7월 공지사항입니다.");
    testNoticeBoard.setWriter(AuthLoginHandler.loginUser);
    testNoticeBoard.setRegisteredDate(new Date(System.currentTimeMillis()));
    testNoticeBoard.setContent("APUJIMA 사이트를 OPEN 했습니다. 많은 관심 부탁드립니다.");

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
