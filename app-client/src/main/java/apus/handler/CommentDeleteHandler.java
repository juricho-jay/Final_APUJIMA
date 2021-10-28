package apus.handler;

import java.util.List;
import apus.dao.BoardDao;
import apus.dao.CommentDao;
import apus.domain.Board;
import apus.domain.Comment;
import util.Prompt;

public class CommentDeleteHandler implements Command {

  CommentDao commentDao;
  BoardDao boardDao;

  public CommentDeleteHandler(CommentDao commentDao, BoardDao boardDao) {
    this.commentDao = commentDao;
    this.boardDao = boardDao;
  }

  // 댓글 1번부터 시작하는데 table에 어떻게 구현? (질문하기!)

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[댓글 삭제]");

    //댓글 번호 (게시물마다 1번으로 시작되는)
    int commentResetNo = Prompt.inputInt("번호> ");

    List<Comment> commentList = commentDao.findAll();

    if (commentList == null) {
      System.out.println("등록된 댓글이 없습니다.");
      return;
    }

    int no = (int)request.getAttribute("no"); //게시글 번호


    Board board = boardDao.findByNo(no);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    int whichBoard = board.getWhichBoard();

    // 조건: 어느 보드/ 어느 게시글 / 어느 댓글 번호(1로 reset되는 번호)
    for (int i = commentList.size() - 1; i >= 0; i--) {
      if (commentList.get(i).getWhichBoard() == whichBoard &&
          commentList.get(i).getCommentBoardNo() == board.getNo() &&
          commentList.get(i).getNo() == commentResetNo &&
          commentList.get(i).getCommenter().equals(AuthLoginHandler.getLoginUser().getId())) {

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

