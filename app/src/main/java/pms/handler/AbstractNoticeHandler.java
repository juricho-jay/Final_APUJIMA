package pms.handler;

import java.util.List;
import pms.domain.Notice;

public abstract class AbstractNoticeHandler implements Command {

  protected List<Notice> noticeList;

  public AbstractNoticeHandler(List<Notice> noticeList) {
    this.noticeList = noticeList;
  }


  protected Notice findByNo(int no) {
    for (Notice notice : noticeList) {
      if (notice.getNo() == no) {
        return notice;
      }
    }
    return null;
  }
}

