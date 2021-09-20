package pms.handler;

import java.util.List;
import pms.domain.Comment;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.NoticeBoard;

public class CommentAddHandler extends AbstractCommentHandler {

  public CommentAddHandler(List<Comment> commentList, List<FreeBoard> freeBoardList, List<DoctorBoard> doctorBoardList, List<NoticeBoard> noticeBoardList) {
    super(commentList, freeBoardList, doctorBoardList, noticeBoardList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {




  }

  protected FreeBoard findByFreeBoardNo(int freeBoardNo) {
    for (FreeBoard freeBoard : freeBoardList) {
      if (freeBoard.getNo() == freeBoardNo) {
        return freeBoard;
      }
    }
    return null;
  }

  protected DoctorBoard findByDoctorBoardNo(int doctorBoardNo) {
    for (DoctorBoard doctorBoard : doctorBoardList) {
      if (doctorBoard.getNo() == doctorBoardNo) {
        return doctorBoard;
      }
    }
    return null;
  }

  protected NoticeBoard findByNoticeBoardNo(int noticeBoardNo) {
    for (NoticeBoard noticeBoard : noticeBoardList) {
      if (noticeBoard.getNo() == noticeBoardNo) {
        return noticeBoard;
      }
    }
    return null;
  }

}
