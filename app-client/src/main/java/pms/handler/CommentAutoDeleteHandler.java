package pms.handler;

import java.util.HashMap;
import java.util.List;
import pms.dao.BoardDao;
import pms.dao.CommentDao;
import pms.domain.Board;
import pms.domain.Comment;

public class CommentAutoDeleteHandler implements Command {

  CommentDao commentDao;
  BoardDao boardDao;

  public CommentAutoDeleteHandler(CommentDao commentDao, BoardDao boardDao) {
    this.commentDao = commentDao;
    this.boardDao = boardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    //게시글 번호
    int no = (int)request.getAttribute("no");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    List<Comment> commentList = commentDao.findAll();

    if (commentList == null) {
      return;
    }

    Board board = boardDao.findByNo(no);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    int whichBoard = board.getWhichBoard();


    for (int i = commentList.size() - 1; i >= 0; i--) {
      if (commentList.get(i).getWhichBoard() == whichBoard &&
          commentList.get(i).getCommentBoardNo() == board.getNo()) {

        commentDao.delete(i);

      }
    }
  } 
}

