package pms.handler;

import java.util.List;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.Like;
import pms.domain.NoticeBoard;

public class LikeAutoDeleteHandler extends AbstractLikeHandler {



  public LikeAutoDeleteHandler(List<Like> likeList, List<FreeBoard> freeBoardList, 
      List<DoctorBoard> doctorBoardList, List<NoticeBoard> noticeBoardList) {
    super(likeList, freeBoardList, doctorBoardList, noticeBoardList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int no = (int)request.getAttribute("num");
    int likeSize = likeList.size(); 
    String whichBoard = (String)request.getAttribute("boardType");


    if (whichBoard.equals("freeBoard")) {
      FreeBoard freeBoard = findByFreeBoardNo(no);
      String whichBoard2 = freeBoard.getWhichBoard();

      if (likeSize == 0) {
        return;
      }

      for (int i = likeSize - 1; i >= 0; i--) {
        if (likeList.get(i).getWhichBoard().equals(whichBoard2) &&
            likeList.get(i).getLikeBoardNo() == freeBoard.getNo()) {
          likeList.remove(i);
        }
      }

    } else if (whichBoard.equals("doctorBoard")) {
      DoctorBoard doctorBoard = findByDoctorBoardNo(no);
      String whichBoard2 = doctorBoard.getWhichBoard();

      if (likeSize == 0) {
        return;
      }

      for (int i = likeSize - 1; i >= 0; i--) {
        if (likeList.get(i).getWhichBoard().equals(whichBoard2) &&
            likeList.get(i).getLikeBoardNo() == doctorBoard.getNo()) {
          likeList.remove(i);
        }
      }

    } else if (whichBoard.equals("noticeBoard")) {
      NoticeBoard noticeBoard = findByNoticeBoardNo(no);
      String whichBoard2 = noticeBoard.getWhichBoard();

      if (likeSize == 0) {
        return;
      }

      for (int i = likeSize - 1; i >= 0; i--) {
        if (likeList.get(i).getWhichBoard().equals(whichBoard2) &&
            likeList.get(i).getLikeBoardNo() == noticeBoard.getNo()) {
          likeList.remove(i);
        }
      }

    }


  } 
}




