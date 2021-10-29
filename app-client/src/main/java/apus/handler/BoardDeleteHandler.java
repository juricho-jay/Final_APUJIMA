package apus.handler;

import org.apache.ibatis.session.SqlSession;
import apus.dao.BoardDao;
import apus.dao.CommentDao;
import apus.dao.LikeDao;
import apus.domain.Board;
import util.Prompt;

public class BoardDeleteHandler implements Command {

  BoardDao boardDao;
  SqlSession sqlSession;
  CommentDao commentDao;
  LikeDao likeDao;

  public BoardDeleteHandler(BoardDao boardDao, SqlSession sqlSession, 
      CommentDao commentDao, LikeDao likeDao) {
    this.boardDao = boardDao;
    this.sqlSession = sqlSession;
    this.commentDao = commentDao;
    this.likeDao = likeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[삭제] 페이지입니다.");
    System.out.println();

    int no = (int)request.getAttribute("no"); // 게시판 번호

    Board board = boardDao.findByNo(no);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    if (!board.getWriter().getId().equals(AuthLoginHandler.getLoginUser().getId()) ) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }

    String input = Prompt.inputString(" ❗ 정말 삭제하시겠습니까? (y/N)> ");
    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }

    boardDao.delete(no);
    commentDao.autoDelete(no);
    likeDao.autoDelete(no);

    sqlSession.commit();

    System.out.println("게시글을 삭제하였습니다.");

  }

}