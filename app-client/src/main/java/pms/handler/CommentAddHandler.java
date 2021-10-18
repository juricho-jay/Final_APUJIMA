package pms.handler;

import java.util.List;
import pms.dao.BoardDao;
import pms.dao.CommentDao;
import pms.domain.Board;
import pms.domain.Comment;
import util.Prompt;

public class CommentAddHandler implements Command {

  CommentDao commentDao;
  BoardDao boardDao;

  public CommentAddHandler(CommentDao commentDao, BoardDao boardDao) {
    this.commentDao = commentDao;
    this.boardDao = boardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[댓글 달기]");
    System.out.println();
    int no = (int)request.getAttribute("no");
    // xxxBoard디테일 핸들러에서 입력한 게시판 번호

    Board board = boardDao.findByNo(no);

    Comment comment = new Comment(); 

    List<Comment> commentList = commentDao.findAll();

    if (commentList == null) {
      comment.setNo(1);
      comment.setCommentNo(0);
      comment.setWhichBoard(board.getWhichBoard());
      comment.setCommentBoardNo(board.getNo());
      comment.setCommentWriter(board.getWriter().getId());
      comment.setCommenter(AuthLoginHandler.getLoginUser().getId());
      System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
      comment.setCommentContent(Prompt.inputString("댓글 내용> "));

      commentDao.insert(comment);
      System.out.println("댓글이 등록되었습니다.");
      return;
    }

    int count = 0; //기존에 존재하는 댓글 갯수
    int conditionLast = 0; //마지막 댓글의 번호(게시물마다 1로 리셋되는 번호)
    for (int i = 0; i < commentList.size(); i++) {
      if (commentList.get(i).getCommentBoardNo() == board.getNo() 
          && commentList.get(i).getWhichBoard() == board.getWhichBoard()) {
        count++;
        conditionLast = commentList.get(i).getNo();
      }
    }

    if (conditionLast != count) {
      comment.setNo(++conditionLast);
    } else {
      comment.setNo(++count);
    }
    comment.setWhichBoard(board.getWhichBoard());
    comment.setCommentNo(comment.getNo() - 1);
    comment.setCommentBoardNo(board.getNo());
    comment.setCommentWriter(board.getWriter().getId());
    comment.setCommenter(AuthLoginHandler.getLoginUser().getId());
    System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
    comment.setCommentContent(Prompt.inputString("댓글 내용> "));

    commentDao.insert(comment);
    System.out.println("댓글이 등록되었습니다.");
    return;
  }
}
