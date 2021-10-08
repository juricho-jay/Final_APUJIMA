package pms.handler;

import java.util.List;
import pms.domain.Comment;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.NoticeBoard;

public class CommentAutoDeleteHandler extends AbstractCommentHandler {



  public CommentAutoDeleteHandler(List<Comment> commentList, List<FreeBoard> freeBoardList,
      List<DoctorBoard> doctorBoardList, List<NoticeBoard> noticeBoardList) {
    super(commentList, freeBoardList, doctorBoardList, noticeBoardList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    int no = (int)request.getAttribute("num");
    int commentSize = commentList.size(); 
    String whichBoard = (String)request.getAttribute("boardType");


    if (whichBoard.equals("freeBoard")) {
      FreeBoard freeBoard = findByFreeBoardNo(no);
      String whichBoard2 = freeBoard.getWhichBoard();

      if (commentSize == 0) {
        return;
      }

      for (int i = commentSize - 1; i >= 0; i--) {
        if (commentList.get(i).getWhichBoard().equals(whichBoard2) &&
            commentList.get(i).getCommentBoardNo() == freeBoard.getNo()) {
          commentList.remove(i);
        }
      }

    } else if (whichBoard.equals("doctorBoard")) {
      DoctorBoard doctorBoard = findByDoctorBoardNo(no);
      String whichBoard2 = doctorBoard.getWhichBoard();

      if (commentSize == 0) {
        return;
      }

      for (int i = commentSize - 1; i >= 0; i--) {
        if (commentList.get(i).getWhichBoard().equals(whichBoard2) &&
            commentList.get(i).getCommentBoardNo() == doctorBoard.getNo()) {
          commentList.remove(i);
        }
      }

    } else if (whichBoard.equals("noticeBoard")) {
      NoticeBoard noticeBoard = findByNoticeBoardNo(no);
      String whichBoard2 = noticeBoard.getWhichBoard();

      if (commentSize == 0) {
        return;
      }

      for (int i = commentSize - 1; i >= 0; i--) {
        if (commentList.get(i).getWhichBoard().equals(whichBoard2) &&
            commentList.get(i).getCommentBoardNo() == noticeBoard.getNo()) {
          commentList.remove(i);
        }
      }

    }



  }
}
