package pms.handler;

import java.util.List;
import pms.domain.Comment;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.NoticeBoard;
import util.Prompt;

public class CommentAddHandler extends AbstractCommentHandler {

  public CommentAddHandler(List<Comment> commentList, List<FreeBoard> freeBoardList, List<DoctorBoard> doctorBoardList, List<NoticeBoard> noticeBoardList) {
    super(commentList, freeBoardList, doctorBoardList, noticeBoardList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[댓글 달기]");
    System.out.println();
    int no = (int)request.getAttribute("num");

    // int commentTotal = Comment.getCommentTotal(); // comentList.size();
    int commentTotal = commentList.size(); // comentList.size();
    Comment comment = new Comment(); 
    FreeBoard freeBoard = findByFreeBoardNo(no);

    if (commentTotal == 0) {
      comment.setNo(1);
      //  commentTotal++;
      //  Comment.setCommentTotal(commentTotal);
      comment.setCommentBoardNo(freeBoard.getNo());
      comment.setCommentWriter(freeBoard.getWriter().getId());
      comment.setCommenter(AuthLoginHandler.getLoginUser().getId());
      System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
      comment.setCommentContent(Prompt.inputString("댓글 내용> "));

      commentList.add(comment);
      return;
    } else {
      int lastIndex = 0;
      for (int i = 0; i < commentList.size(); i++) {
        if (commentList.get(i).getCommentBoardNo() == freeBoard.getNo()) {
          lastIndex++;
        }
      }

      Comment.setCommentTotal(commentTotal++);
      comment.setNo(++lastIndex);
      comment.setCommentBoardNo(freeBoard.getNo());
      comment.setCommentWriter(freeBoard.getWriter().getId());
      comment.setCommenter(AuthLoginHandler.getLoginUser().getId());
      System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
      comment.setCommentContent(Prompt.inputString("댓글 내용> "));

      commentList.add(comment);
      return;


      //        request.setAttribute("no", no);
      //        request.getRequestDispatcher("/comment/add").forward(request);

    }


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
