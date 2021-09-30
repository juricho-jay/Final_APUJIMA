package pms.handler;

import java.util.List;
import pms.domain.Comment;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.NoticeBoard;

public abstract class AbstractCommentHandler implements Command {
  List<Comment> commentList; 
  List<FreeBoard> freeBoardList;
  List<NoticeBoard> noticeBoardList;
  List<DoctorBoard> doctorBoardList;

  public AbstractCommentHandler(List<Comment> commentList, List<FreeBoard> freeBoardList, 
      List<DoctorBoard> doctorBoardList, List<NoticeBoard> noticeBoardList) {
    this.commentList = commentList;
    this.freeBoardList = freeBoardList;
    this.doctorBoardList = doctorBoardList;
    this.noticeBoardList = noticeBoardList;
  }

  protected Comment findByCommentNo(int CommentNo) {
    for (Comment comment : commentList) {
      if (comment.getCommentNo() == CommentNo) {
        return comment;
      }
    }
    return null;
  }
  protected Comment findByNo(int No) {
    for (Comment comment : commentList) {
      if (comment.getNo() == No) {
        return comment;
      }
    }
    return null;
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
