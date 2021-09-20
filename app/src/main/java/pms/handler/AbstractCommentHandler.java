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

  public AbstractCommentHandler(List<Comment> commentList, List<FreeBoard> freeBoardList, List<DoctorBoard> doctorBoardList, List<NoticeBoard> noticeBoardList) {
    this.commentList = commentList;
    this.freeBoardList = freeBoardList;
    this.doctorBoardList = doctorBoardList;
    this.noticeBoardList = noticeBoardList;
  }
  protected Comment findByCommentNo(int commentNo) {
    for (Comment comment : commentList) {
      if (comment.getCommentNo() == commentNo) {
        return comment;
      }
    }
    return null;
  }



}
