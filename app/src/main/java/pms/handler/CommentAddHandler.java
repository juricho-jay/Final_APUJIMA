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
    System.out.println("[댓글달기]");
    + "댓글달기(@: 💬) / 넘어가기: Enter ]> ");
    if (status.equals("#")) {
      freeBoard.setLike(freeBoard.getLike() + 1);
      System.out.println("게시글 좋아요를 눌렀습니다.");
      break;
    } else if (status.equals("@")) {
      System.out.println("[댓글 달기]");
      Comment comment = new Comment(); 
      int commentTotal = Comment.getCommentTotal();
      int no = comment.getNo();

      if (commentTotal == 0 && no == 0) {
        comment.setNo(1);
        commentTotal++;
        comment.setCommentNo(commentTotal);
      } else {
        comment.setNo(no + 1);
        commentTotal++;
        comment.setCommentNo(commentTotal);
      } 


      comment.setCommentBoardNo(freeBoard.getNo());
      comment.setCommentWriter(AuthLoginHandler.getLoginUser().getId());




      request.setAttribute("no", no);
      request.getRequestDispatcher("/comment/add").forward(request);







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
