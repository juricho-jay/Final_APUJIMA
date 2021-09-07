package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.NoticeBoard;
import util.Prompt;

public class NoticeBoardAddHandler extends AbstractNoticeBoardHandler{
  List<NoticeBoard> noticeBoardList;

  public NoticeBoardAddHandler(List<NoticeBoard> noticeBoardList) {
    super(noticeBoardList);
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
