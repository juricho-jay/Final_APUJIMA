package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.NoticeBoard;
import util.Prompt;

public class NoticeBoardAddHandler extends AbstractNoticeBoardHandler{


  public NoticeBoardAddHandler(List<NoticeBoard> noticeBoardList) {
    super(noticeBoardList);
  }

  @Override
  public void execute(CommandRequest request) {

    System.out.println("[글쓰기] 페이지입니다.");
    System.out.println();
    NoticeBoard noticeBoard = new NoticeBoard();

    noticeBoard.setNo(NoticeBoard.lastIndex++);
    noticeBoard.setTitle(Prompt.inputString("제목> "));
    noticeBoard.setContent(Prompt.inputString("내용> "));
    noticeBoard.setWriter(AuthLoginHandler.getLoginUser());
    noticeBoard.setRegisteredDate(new Date(System.currentTimeMillis()));

    noticeBoardList.add(noticeBoard);

    System.out.println("공지사항 게시판에 등록되었습니다.");
  }

}
