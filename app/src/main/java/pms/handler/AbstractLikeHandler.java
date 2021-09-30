package pms.handler;

import java.util.List;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.Like;
import pms.domain.NoticeBoard;

public abstract class AbstractLikeHandler implements Command {
  List<Like> likeList; 
  List<FreeBoard> freeBoardList;
  List<NoticeBoard> noticeBoardList;
  List<DoctorBoard> doctorBoardList;

  public AbstractLikeHandler(List<Like> likeList, List<FreeBoard> freeBoardList, 
      List<DoctorBoard> doctorBoardList, List<NoticeBoard> noticeBoardList) {
    this.likeList = likeList;
    this.freeBoardList = freeBoardList;
    this.doctorBoardList = doctorBoardList;
    this.noticeBoardList = noticeBoardList;
  }

  protected Like findByLikeNo(int No) {
    for (Like like : likeList) {
      if (like.getLikeNo() == No) {
        return like;
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
