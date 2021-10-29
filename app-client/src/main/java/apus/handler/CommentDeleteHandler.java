package apus.handler;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BoardDao;
import apus.dao.CommentDao;
import apus.dao.MemberDao;
import apus.domain.Board;
import apus.domain.Comment;
import apus.domain.Member;
import util.Prompt;

public class CommentDeleteHandler implements Command {

  CommentDao commentDao;
  BoardDao boardDao;
  MemberDao memberDao;
  SqlSession sqlSession;

  public CommentDeleteHandler(CommentDao commentDao, BoardDao boardDao,MemberDao memberDao,SqlSession sqlSession) {
    this.commentDao = commentDao;
    this.boardDao = boardDao;
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;
  }

  // 댓글 1번부터 시작하는데 table에 어떻게 구현? (질문하기!)

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[댓글 삭제]");

    Member loginUser = memberDao.findById(AuthLoginHandler.getLoginUser().getId());
    //Comment comment = new Comment();
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


    for (Comment comment : commentList) {
      if (comment.getCommenter().getId().equals(loginUser.getId())) {
        System.out.printf("%d, %s, %s",
            comment.getNo(),
            comment.getCommenter().getId(),
            comment.getContent());
      }
    }

    System.out.println();
    int deleteNo = Prompt.inputInt("삭제할 댓글 번호> ");

    Comment deleteComment = commentDao.findByNo(deleteNo);

    if (deleteComment == null) {
      System.out.println("해당 번호의 댓글을 찾을 수 없습니다.");
      return;
    }

    if (!deleteComment.getCommenter().getId().equals(loginUser.getId())) {
      System.out.println("내가 작성한 댓글이 아닙니다.");
      return;
    }

    commentDao.delete(deleteNo);
    sqlSession.commit();
    System.out.println("댓글이 삭제되었습니다.");

  } 
}


