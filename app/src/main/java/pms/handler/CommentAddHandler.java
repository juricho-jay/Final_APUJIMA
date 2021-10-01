package pms.handler;

import java.util.List;
import pms.domain.Comment;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.NoticeBoard;
import pms.domain.XBoard;
import util.Prompt;

public class CommentAddHandler extends AbstractCommentHandler {



  public CommentAddHandler(List<Comment> commentList, List<FreeBoard> freeBoardList,
      List<DoctorBoard> doctorBoardList, List<NoticeBoard> noticeBoardList) {
    super(commentList, freeBoardList, doctorBoardList, noticeBoardList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[댓글 달기]");
    System.out.println();
    int no = (int)request.getAttribute("num");

    int commentTotal = commentList.size(); 
    Comment comment = new Comment(); 

    String whichBoard = (String)request.getAttribute("boardType");

    XBoard xxxBoard = new XBoard();

    if (whichBoard.equals("freeBoard")) {
      FreeBoard freeBoard = findByFreeBoardNo(no);
      comment.setWhichBoard("free");
      xxxBoard = freeBoard;
    } else if (whichBoard.equals("doctorBoard")) {
      DoctorBoard doctorBoard = findByDoctorBoardNo(no);
      comment.setWhichBoard("doctor");
      xxxBoard = doctorBoard;
    } else if (whichBoard.equals("noticeBoard")) {
      NoticeBoard noticeBoard = findByNoticeBoardNo(no);
      comment.setWhichBoard("notice");
      xxxBoard = noticeBoard;
    }

    if (commentTotal == 0) {
      comment.setNo(1);
      comment.setCommentNo(0);
      comment.setCommentBoardNo(xxxBoard.getNo());
      comment.setCommentWriter(xxxBoard.getWriter().getId());
      comment.setCommenter(AuthLoginHandler.getLoginUser().getId());
      System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
      comment.setCommentContent(Prompt.inputString("댓글 내용> "));

      commentList.add(comment);
      return;
    } else {
      int lastIndex = 0;
      for (int i = 0; i < commentList.size(); i++) {
        if (commentList.get(i).getCommentBoardNo() == xxxBoard.getNo() 
            && commentList.get(i).getWhichBoard().equals(xxxBoard.getWhichBoard())) {
          lastIndex++;
        }
      }

      comment.setNo(++lastIndex);
      comment.setCommentNo(commentList.size());
      comment.setCommentBoardNo(xxxBoard.getNo());
      comment.setCommentWriter(xxxBoard.getWriter().getId());
      comment.setCommenter(AuthLoginHandler.getLoginUser().getId());
      System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
      comment.setCommentContent(Prompt.inputString("댓글 내용> "));

      commentList.add(comment);
      return;


      //        request.setAttribute("no", no);
      //        request.getRequestDispatcher("/comment/add").forward(request);

    }
  }
}
