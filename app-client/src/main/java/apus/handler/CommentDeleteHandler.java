package apus.handler;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BoardDao;
import apus.dao.CommentDao;
import apus.dao.MemberDao;
import apus.domain.Board;
import apus.domain.Comment;
import apus.domain.Member;

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


    for(int i = 0; i < commentList.size(); i++) {
      if (commentList.get(i).getCommentBoard() == board &&
          commentList.get(i).getCommenter().equals(loginUser)) {

        commentDao.delete(i);
        sqlSession.commit();
        System.out.println("댓글이 삭제되었습니다.");
        return;
      }
    } 
    System.out.println("내가 쓴 댓글이 아닙니다.");
    return;

  } 
}

