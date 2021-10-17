package pms.handler;

import java.util.HashMap;
import java.util.List;
import pms.dao.CommentDao;
import pms.dao.DoctorBoardDao;
import pms.dao.FreeBoardDao;
import pms.dao.NoticeBoardDao;
import pms.domain.Comment;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.NoticeBoard;

public class CommentAutoDeleteHandler implements Command {

  CommentDao commentDao;
  FreeBoardDao freeBoardDao;
  DoctorBoardDao doctorBoardDao;
  NoticeBoardDao noticeBoardDao;


  public CommentAutoDeleteHandler(CommentDao commentDao, FreeBoardDao freeBoardDao,
      DoctorBoardDao doctorBoardDao, NoticeBoardDao noticeBoardDao) {
    this.commentDao = commentDao;
    this.freeBoardDao = freeBoardDao;
    this.doctorBoardDao = doctorBoardDao;
    this.noticeBoardDao = noticeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    //게시글 번호
    int no = (int)request.getAttribute("no");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    List<Comment> commentList = commentDao.findAll();

    String whichBoard = (String)request.getAttribute("boardType");

    if (commentList == null) {
      return;
    }


    if (whichBoard.equals("freeBoard")) {
      FreeBoard freeBoard = freeBoardDao.findByNo(no);
      String whichBoard2 = freeBoard.getWhichBoard();


      for (int i = commentList.size() - 1; i >= 0; i--) {
        if (commentList.get(i).getWhichBoard().equals(whichBoard2) &&
            commentList.get(i).getCommentBoardNo() == freeBoard.getNo()) {

          //          HashMap<String,String> deleteIndex = new HashMap<>();
          //          deleteIndex.put("deleteIndex", String.valueOf(i));
          //          requestAgent.request("comment.delete", deleteIndex);
          commentDao.delete(i);

        }
      }

    } else if (whichBoard.equals("doctorBoard")) {
      DoctorBoard doctorBoard = doctorBoardDao.findByNo(no);
      String whichBoard2 = doctorBoard.getWhichBoard();


      for (int i = commentList.size() - 1; i >= 0; i--) {
        if (commentList.get(i).getWhichBoard().equals(whichBoard2) &&
            commentList.get(i).getCommentBoardNo() == doctorBoard.getNo()) {

          //          HashMap<String,String> deleteIndex = new HashMap<>();
          //          deleteIndex.put("deleteIndex", String.valueOf(i));
          //          requestAgent.request("comment.delete", deleteIndex);

          commentDao.delete(i);
        }
      }

    } else if (whichBoard.equals("noticeBoard")) {
      //      requestAgent.request("noticeBoard.selectOne", params);
      //      NoticeBoard noticeBoard = requestAgent.getObject(NoticeBoard.class);
      NoticeBoard noticeBoard = noticeBoardDao.findByNo(no);
      String whichBoard2 = noticeBoard.getWhichBoard();


      for (int i = commentList.size() - 1; i >= 0; i--) {
        if (commentList.get(i).getWhichBoard().equals(whichBoard2) &&
            commentList.get(i).getCommentBoardNo() == noticeBoard.getNo()) {

          //          HashMap<String,String> deleteIndex = new HashMap<>();
          //          deleteIndex.put("deleteIndex", String.valueOf(i));

          commentDao.delete(i);
        }
      }

    }

  }
}
