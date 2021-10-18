package pms.handler;

import java.util.List;
import pms.dao.BoardDao;
import pms.dao.CommentDao;
import pms.domain.Board;
import pms.domain.Comment;
import util.Prompt;

public class CommentUpdateHandler implements Command {

  CommentDao commentDao;
  BoardDao boardDao;

  public CommentUpdateHandler(CommentDao commentDao, BoardDao boardDao) {
    this.commentDao = commentDao;
    this.boardDao = boardDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    try {
      System.out.println();
      System.out.println("[댓글 변경]");
      System.out.println();

      int commentResetNo = Prompt.inputInt("번호> "); //댓글 번호 (게시물마다 1번으로 시작되는)
      List<Comment> commentList = commentDao.findAll();

      int no = (int)request.getAttribute("no"); // freeBoardDetailHandler에서 입력한 게시판 번호 불러오기

      Board board = boardDao.findByNo(no);

      if (board == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }

      String commentContent = null;
      Comment comment = new Comment();

      for (int i = 0; i < commentList.size(); i++) {
        if (commentList.get(i).getWhichBoard() == board.getWhichBoard() && 
            commentList.get(i).getCommentBoardNo() == board.getNo() &&
            commentList.get(i).getNo() == commentResetNo && 
            commentList.get(i).getCommenter().equals(AuthLoginHandler.getLoginUser().getId())) {
          comment = commentList.get(i);
          commentContent = commentList.get(i).getCommentContent();
          break;
        } else if (commentList.get(i).getWhichBoard() == board.getWhichBoard() && 
            commentList.get(i).getCommentBoardNo() == board.getNo() &&
            commentList.get(i).getNo() == commentResetNo &&
            !commentList.get(i).getCommenter().equals(AuthLoginHandler.getLoginUser().getId())) {
          System.out.println("댓글 변경 권한이 없습니다.");
          return;
        }
      }


      String newCommentContent = Prompt.inputString(String.format("댓글 내용(%s)> ", commentContent));
      String input = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
      if(input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("댓글 변경을 취소하였습니다.");
        return;
      }

      comment.setCommentContent(newCommentContent);
      commentDao.update(comment);
      System.out.println("댓글을 변경하였습니다.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
