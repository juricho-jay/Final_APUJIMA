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
    int commentTotal = Comment.getCommentTotal();
    Comment comment = new Comment(); 

    if (commentTotal == 0) {
      comment.setNo(1);
      commentTotal++;
      Comment.setCommentTotal(commentTotal);
      comment.setCommentBoardNo(freeBoard.getNo());
      comment.setCommentWriter(freeBoard.getWriter().getId());
      comment.setCommenter(AuthLoginHandler.getLoginUser().getId());
      System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
      comment.setCommentContent(Prompt.inputString("댓글 내용> "));

      commentList.add(comment);
      break;
    } else {
      int lastIndex = 0;
      for (int i = 0; i < commentList.size(); i++) {
        if (commentList.get(i).getCommentBoardNo() == freeBoard.getNo()) {
          lastIndex++;
        }
      }


      // 그러니까아... 입력한 숫자 num > freeBoard 찾아서 그 번호의 넘버를 넣고 ㅇㅅㅇ....
      // 흠~~

      Comment.setCommentTotal(commentTotal++);
      comment.setNo(++lastIndex);
      comment.setCommentBoardNo(freeBoard.getNo());
      comment.setCommentWriter(freeBoard.getWriter().getId());
      comment.setCommenter(AuthLoginHandler.getLoginUser().getId());
      System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
      comment.setCommentContent(Prompt.inputString("댓글 내용> "));

      commentList.add(comment);
      break;

    }


  }



}
