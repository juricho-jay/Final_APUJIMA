package pms.handler;

import java.util.HashMap;
import java.util.List;
import pms.domain.Comment;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.NoticeBoard;
import request.RequestAgent;

public class CommentAutoDeleteHandler implements Command {

  RequestAgent requestAgent;

  public CommentAutoDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    //게시글 번호
    int no = (int)request.getAttribute("no");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("comment.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return;
    }

    List<Comment> commentList = (List<Comment>) requestAgent.getObjects(Comment.class);


    String whichBoard = (String)request.getAttribute("boardType");


    if (whichBoard.equals("freeBoard")) {
      requestAgent.request("freeBoard.selectOne", params);
      FreeBoard freeBoard = requestAgent.getObject(FreeBoard.class);
      String whichBoard2 = freeBoard.getWhichBoard();


      for (int i = commentList.size() - 1; i >= 0; i--) {
        if (commentList.get(i).getWhichBoard().equals(whichBoard2) &&
            commentList.get(i).getCommentBoardNo() == freeBoard.getNo()) {

          HashMap<String,String> deleteIndex = new HashMap<>();
          deleteIndex.put("deleteIndex", String.valueOf(i));

          requestAgent.request("comment.delete", deleteIndex);

          if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
            return;
          }
        }
      }

    } else if (whichBoard.equals("doctorBoard")) {
      requestAgent.request("doctorBoard.selectOne", params);
      DoctorBoard doctorBoard = requestAgent.getObject(DoctorBoard.class);
      String whichBoard2 = doctorBoard.getWhichBoard();


      for (int i = commentList.size() - 1; i >= 0; i--) {
        if (commentList.get(i).getWhichBoard().equals(whichBoard2) &&
            commentList.get(i).getCommentBoardNo() == doctorBoard.getNo()) {

          HashMap<String,String> deleteIndex = new HashMap<>();
          deleteIndex.put("deleteIndex", String.valueOf(i));

          requestAgent.request("comment.delete", deleteIndex);

          if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
            return;
          }
        }
      }

    } else if (whichBoard.equals("noticeBoard")) {
      requestAgent.request("noticeBoard.selectOne", params);
      NoticeBoard noticeBoard = requestAgent.getObject(NoticeBoard.class);
      String whichBoard2 = noticeBoard.getWhichBoard();


      for (int i = commentList.size() - 1; i >= 0; i--) {
        if (commentList.get(i).getWhichBoard().equals(whichBoard2) &&
            commentList.get(i).getCommentBoardNo() == noticeBoard.getNo()) {

          HashMap<String,String> deleteIndex = new HashMap<>();
          deleteIndex.put("deleteIndex", String.valueOf(i));

          requestAgent.request("comment.delete", deleteIndex);

          if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
            return;
          }
        }
      }

    }

  }
}
