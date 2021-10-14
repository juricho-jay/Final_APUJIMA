package pms.handler;

import java.util.List;
import pms.dao.CommentDao;
import pms.dao.DoctorBoardDao;
import pms.dao.FreeBoardDao;
import pms.dao.NoticeBoardDao;
import pms.domain.Comment;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.NoticeBoard;
import util.Prompt;

public class CommentDeleteHandler implements Command {

  CommentDao commentDao;
  FreeBoardDao freeBoardDao;
  DoctorBoardDao doctorBoardDao;
  NoticeBoardDao noticeBoardDao;

  public CommentDeleteHandler(CommentDao commentDao, FreeBoardDao freeBoardDao,
      DoctorBoardDao doctorBoardDao, NoticeBoardDao noticeBoardDao) {
    this.commentDao = commentDao;
    this.freeBoardDao = freeBoardDao;
    this.doctorBoardDao = doctorBoardDao;
    this.noticeBoardDao = noticeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[댓글 삭제]");

    //댓글 번호 (게시물마다 1번으로 시작되는)
    int commentResetNo = Prompt.inputInt("번호> ");

    List<Comment> commentList = commentDao.findAll();

    if (commentList.size() == 0) {
      System.out.println("등록된 댓글이 없습니다.");
      return;
    }

    int no = (int)request.getAttribute("no"); //게시글 번호

    //    HashMap<String,String> params = new HashMap<>();
    //    params.put("no", String.valueOf(no));

    String whichBoard = (String)request.getAttribute("boardType");

    if (whichBoard.equals("freeBoard")) {

      FreeBoard freeBoard = freeBoardDao.findByNo(no);

      if (freeBoard == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }
      String whichBoard2 = freeBoard.getWhichBoard();

      // 조건: 어느 보드/ 어느 게시글 / 어느 댓글 번호(1로 reset되는 번호)
      for (int i = commentList.size() - 1; i >= 0; i--) {
        if (commentList.get(i).getWhichBoard().equals(whichBoard2) &&
            commentList.get(i).getCommentBoardNo() == freeBoard.getNo() &&
            commentList.get(i).getNo() == commentResetNo &&
            commentList.get(i).getCommenter().equals(AuthLoginHandler.getLoginUser().getId())) {

          //          HashMap<String,String> deleteIndex = new HashMap<>();
          //          deleteIndex.put("deleteIndex", String.valueOf(i));

          commentDao.delete(i);
          System.out.println("댓글이 삭제되었습니다.");
          return;
        } else {
          System.out.println("내가 쓴 댓글이 아닙니다.");
          return;
        }
      }

    } else if (whichBoard.equals("doctorBoard")) {
      DoctorBoard doctorBoard = doctorBoardDao.findByNo(no);

      if (doctorBoard == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }
      String whichBoard2 = doctorBoard.getWhichBoard();


      for (int i = commentList.size() - 1; i >= 0; i--) {
        if (commentList.get(i).getWhichBoard().equals(whichBoard2) &&
            commentList.get(i).getCommentBoardNo() == doctorBoard.getNo() &&
            commentList.get(i).getNo() == commentResetNo &&
            commentList.get(i).getCommenter().equals(AuthLoginHandler.getLoginUser().getId())) {

          //          HashMap<String,String> deleteIndex = new HashMap<>();
          //          deleteIndex.put("deleteIndex", String.valueOf(i));

          commentDao.delete(i);
          System.out.println("댓글이 삭제되었습니다.");
          return;
        } else {
          System.out.println("내가 쓴 댓글이 아닙니다.");
          return;
        }
      }

    } else if (whichBoard.equals("noticeBoard")) {
      NoticeBoard noticeBoard = noticeBoardDao.findByNo(no);

      if (noticeBoard == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }
      String whichBoard2 = noticeBoard.getWhichBoard();


      for (int i = commentList.size() - 1; i >= 0; i--) {
        if (commentList.get(i).getWhichBoard().equals(whichBoard2) &&
            commentList.get(i).getCommentBoardNo() == noticeBoard.getNo() &&
            commentList.get(i).getNo() == commentResetNo &&
            commentList.get(i).getCommenter().equals(AuthLoginHandler.getLoginUser().getId())) {

          //          HashMap<String,String> deleteIndex = new HashMap<>();
          //          deleteIndex.put("deleteIndex", String.valueOf(i));

          commentDao.delete(i);
          System.out.println("댓글이 삭제되었습니다.");
          return;
        } else {
          System.out.println("내가 쓴 댓글이 아닙니다.");
          return;
        }
      }

    }

  }
}
