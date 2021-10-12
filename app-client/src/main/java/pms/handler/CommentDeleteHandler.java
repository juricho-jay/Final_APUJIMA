package pms.handler;

import java.util.HashMap;
import java.util.List;
import pms.domain.Comment;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.NoticeBoard;
import request.RequestAgent;
import util.Prompt;

public class CommentDeleteHandler implements Command {

  RequestAgent requestAgent;

  public CommentDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[댓글 삭제]");

    //댓글 번호 (게시물마다 1번으로 시작되는)
    int commentResetNo = Prompt.inputInt("번호> ");

    requestAgent.request("comment.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("등록된 댓글이 없습니다.");
      return;
    }

    List<Comment> commentList = (List<Comment>) requestAgent.getObjects(Comment.class);

    int no = (int)request.getAttribute("no"); //게시글 번호

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    String whichBoard = (String)request.getAttribute("boardType");

    if (whichBoard.equals("freeBoard")) {

      requestAgent.request("freeBoard.selectOne", params);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }

      FreeBoard freeBoard = requestAgent.getObject(FreeBoard.class);
      String whichBoard2 = freeBoard.getWhichBoard();

      // 조건: 어느 보드/ 어느 게시글 / 어느 댓글 번호(1로 reset되는 번호)
      for (int i = commentList.size() - 1; i >= 0; i--) {
        if (commentList.get(i).getWhichBoard().equals(whichBoard2) &&
            commentList.get(i).getCommentBoardNo() == freeBoard.getNo() &&
            commentList.get(i).getNo() == commentResetNo &&
            commentList.get(i).getCommenter().equals(AuthLoginHandler.getLoginUser().getId())) {

          HashMap<String,String> deleteIndex = new HashMap<>();
          deleteIndex.put("deleteIndex", String.valueOf(i));

          requestAgent.request("comment.delete", deleteIndex);

          if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
            return;
          }
          System.out.println("댓글이 삭제되었습니다.");
          return;
        } else {
          System.out.println("내가 쓴 댓글이 아닙니다.");
          return;
        }
      }

    } else if (whichBoard.equals("doctorBoard")) {
      requestAgent.request("doctorBoard.selectOne", params);
      DoctorBoard doctorBoard = requestAgent.getObject(DoctorBoard.class);
      String whichBoard2 = doctorBoard.getWhichBoard();


      for (int i = commentList.size() - 1; i >= 0; i--) {
        if (commentList.get(i).getWhichBoard().equals(whichBoard2) &&
            commentList.get(i).getCommentBoardNo() == doctorBoard.getNo() &&
            commentList.get(i).getNo() == commentResetNo &&
            commentList.get(i).getCommenter().equals(AuthLoginHandler.getLoginUser().getId())) {

          HashMap<String,String> deleteIndex = new HashMap<>();
          deleteIndex.put("deleteIndex", String.valueOf(i));

          requestAgent.request("comment.delete", deleteIndex);

          if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
            return;
          }
          System.out.println("댓글이 삭제되었습니다.");
          return;
        } else {
          System.out.println("내가 쓴 댓글이 아닙니다.");
          return;
        }
      }

    } else if (whichBoard.equals("noticeBoard")) {
      requestAgent.request("noticeBoard.selectOne", params);
      NoticeBoard noticeBoard = requestAgent.getObject(NoticeBoard.class);
      String whichBoard2 = noticeBoard.getWhichBoard();


      for (int i = commentList.size() - 1; i >= 0; i--) {
        if (commentList.get(i).getWhichBoard().equals(whichBoard2) &&
            commentList.get(i).getCommentBoardNo() == noticeBoard.getNo() &&
            commentList.get(i).getNo() == commentResetNo &&
            commentList.get(i).getCommenter().equals(AuthLoginHandler.getLoginUser().getId())) {

          HashMap<String,String> deleteIndex = new HashMap<>();
          deleteIndex.put("deleteIndex", String.valueOf(i));

          requestAgent.request("comment.delete", deleteIndex);

          if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
            return;
          }
          System.out.println("댓글이 삭제되었습니다.");
          return;
        } else {
          System.out.println("내가 쓴 댓글이 아닙니다.");
          return;
        }
      }

    }

  }
}
