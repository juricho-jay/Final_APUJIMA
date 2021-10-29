package apus.handler;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BoardDao;
import apus.dao.CommentDao;
import apus.domain.Board;
import apus.domain.Comment;

public class CommentAutoDeleteHandler implements Command {

  CommentDao commentDao;
  BoardDao boardDao;
  SqlSession sqlSession;

  public CommentAutoDeleteHandler(CommentDao commentDao, BoardDao boardDao,
      SqlSession sqlSession) {
    this.commentDao = commentDao;
    this.boardDao = boardDao;
    this.sqlSession = sqlSession;
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



    for (int i = commentList.size() - 1; i >= 0; i--) {
      if ( commentList.get(i).getCommentBoard().getNo() == board.getNo()) {

        commentDao.delete(commentList.get(i).getNo());

      }
    }
    sqlSession.commit();
  } 
}

