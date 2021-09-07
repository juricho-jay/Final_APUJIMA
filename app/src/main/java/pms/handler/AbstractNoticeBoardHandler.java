package pms.handler;

import java.util.List;
import pms.domain.NoticeBoard;

public abstract class AbstractNoticeBoardHandler implements Command {

  protected List<NoticeBoard> noticeBoardList;

  public AbstractNoticeBoardHandler(List<NoticeBoard> noticeBoardList) {
    this.noticeBoardList = noticeBoardList;
  }


  protected NoticeBoard findByNo(int no) {
    for (NoticeBoard noticeBoard : noticeBoardList) {
      if (noticeBoard.getNo() == no) {
        return noticeBoard;
      }
    }
    return null;
  }
}

