package apus.handler;

import java.sql.Date;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BoardDao;
import apus.dao.CommentDao;
import apus.dao.MemberDao;
import apus.domain.Board;
import apus.domain.Comment;
import apus.domain.Member;
import util.Prompt;

public class CommentAddHandler implements Command {
  MemberDao memberDao;
  CommentDao commentDao;
  BoardDao boardDao;
  SqlSession sqlSession;

  public CommentAddHandler(CommentDao commentDao, BoardDao boardDao,SqlSession sqlSession,  MemberDao memberDao) {
    this.commentDao = commentDao;
    this.boardDao = boardDao;
    this.sqlSession =sqlSession;
    this.memberDao = memberDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[댓글 달기]");
    System.out.println();
    int no = (int)request.getAttribute("no");

    Member loginUser = memberDao.findById(AuthLoginHandler.getLoginUser().getId());
    Board board = boardDao.findByNo(no);

    Comment comment = new Comment(); 

    //  List<Comment> commentList = commentDao.findAll();


    comment.setCommentBoard(board);
    comment.setCommenter(loginUser);
    comment.setRegisteredDate(new Date(System.currentTimeMillis()));
    System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
    comment.setContent(Prompt.inputString("댓글 내용> "));

    commentDao.insert(comment);
    sqlSession.commit();
    System.out.println("댓글이 등록되었습니다.");
    return;
  }
}
