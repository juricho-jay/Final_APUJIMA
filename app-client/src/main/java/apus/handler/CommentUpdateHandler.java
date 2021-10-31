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

public class CommentUpdateHandler implements Command {

  CommentDao commentDao;
  BoardDao boardDao;
  MemberDao memberDao;
  SqlSession sqlSession;

  public CommentUpdateHandler(CommentDao commentDao, BoardDao boardDao,MemberDao memberDao,SqlSession sqlSession) {
    this.commentDao = commentDao;
    this.boardDao = boardDao;
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;
  }



  @Override
  public void execute(CommandRequest request) throws Exception {
    try {
      System.out.println();
      System.out.println("[댓글 변경]");
      System.out.println();

      Member loginUser = memberDao.findById(AuthLoginHandler.getLoginUser().getId());
      List<Comment> commentList = commentDao.findAll();

      int no = (int)request.getAttribute("no"); // freeBoardDetailHandler에서 입력한 게시판 번호 불러오기

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
      int updateNo = Prompt.inputInt("변경할 댓글 번호> "); 

      Comment updateComment = commentDao.findByNo(updateNo);

      if (updateComment == null) {
        System.out.println("해당 번호의 댓글을 찾을 수 없습니다.");
        return;
      }

      if (!updateComment.getCommenter().getId().equals(loginUser.getId())) {
        System.out.println("내가 작성한 댓글이 아닙니다.");
        return;
      }

      String newContent = Prompt.inputString(String.format("댓글 내용(%s)> ", updateComment.getContent()));

      String input = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
      if(input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("댓글 변경을 취소하였습니다.");
        return;
      }

      updateComment.setContent(newContent);

      commentDao.update(updateComment);
      sqlSession.commit();
      System.out.println("댓글을 변경하였습니다.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
