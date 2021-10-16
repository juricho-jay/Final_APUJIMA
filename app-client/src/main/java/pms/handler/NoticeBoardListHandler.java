package pms.handler;

import java.util.List;
import pms.dao.NoticeBoardDao;
import pms.domain.NoticeBoard;

public class NoticeBoardListHandler implements Command {

  NoticeBoardDao noticeBoardDao;

  public NoticeBoardListHandler(NoticeBoardDao noticeBoardDao) {
    this.noticeBoardDao = noticeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 목록] 페이지입니다.");
    System.out.println();

    List<NoticeBoard> noticeBoardList = noticeBoardDao.findAll();


    for (NoticeBoard noticeBoard : noticeBoardList) {
      System.out.printf("%d, %s, %s, %s, %d\n",
          //            noticeNumBoardList
          noticeBoard.getNo(),
          noticeBoard.getTitle(),
          noticeBoard.getWriter().getId(),
          noticeBoard.getRegisteredDate(),
          noticeBoard.getViewCount()
          );
    }
  }
}
