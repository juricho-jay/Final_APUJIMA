package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.NoticeBoard;
import util.Prompt;

public class NoticeBoardAddHandler extends AbstractNoticeBoardHandler{

  NoticeBoardListHandler noticeBoardListHandler;
  int size;

  public NoticeBoardAddHandler(List<NoticeBoard> noticeBoardList
      ,NoticeBoardListHandler noticeBoardListHandler) {
    super(noticeBoardList);
    this.noticeBoardListHandler = noticeBoardListHandler;
  }

  @Override
  public void execute(CommandRequest request) {
    //    int lastIndex = (noticeBoardListHandler.noticeBoardList.size() - 1);
    int lastIndex = 1;
    System.out.println("[글쓰기] 페이지입니다.");
    System.out.println();
    NoticeBoard noticeBoard = new NoticeBoard();

    for (int i = 0; i < noticeBoardList.size(); i++) {
      if (noticeBoardList.get(noticeBoardList.size() - 1).getNo() == 0) {
        lastIndex = 1;
      } else if (noticeBoardList.get(noticeBoardList.size() - 1).getNo() != 0){
        lastIndex++;
      }
    }

    //    noticeBoard.no = lastIndex;
    //    if (noticeBoard.no > noticeBoardListHandler.noticeBoardList.size()) {
    //    noticeBoard.setNo(noticeBoard.no);
    //    }
    noticeBoard.setNo(lastIndex);
    noticeBoard.setTitle(Prompt.inputString("제목> "));
    noticeBoard.setContent(Prompt.inputString("내용> "));
    noticeBoard.setWriter(AuthLoginHandler.getLoginUser());
    noticeBoard.setRegisteredDate(new Date(System.currentTimeMillis()));

    //    size++;
    //    noticeBoard.no++;
    //    noticeNumBoardList.add(noticeBoard);
    noticeBoardList.add(noticeBoard);

    System.out.println("공지사항 게시판에 등록되었습니다.");
  }

}
