package pms.handler;

import java.util.HashMap;
import java.util.List;
import pms.domain.Comment;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.NoticeBoard;
import pms.domain.XBoard;
import request.RequestAgent;
import util.Prompt;

public class CommentAddHandler implements Command {

  RequestAgent requestAgent;

  public CommentAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[댓글 달기]");
    System.out.println();
    int no = (int)request.getAttribute("no");
    // xxxBoard디테일 핸들러에서 입력한 게시판 번호


    //xxxBoard 명령어 사용하기 위에 params에 게시판 번호 넣기
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));



    Comment comment = new Comment(); 

    String whichBoard = (String)request.getAttribute("boardType");

    XBoard xxxBoard = new XBoard();

    if (whichBoard.equals("freeBoard")) {
      requestAgent.request("freeBoard.selectOne", params);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 번호의 게시판이 없습니다!");
        return;
      }

      FreeBoard freeBoard = requestAgent.getObject(FreeBoard.class);
      comment.setWhichBoard("free");
      xxxBoard = freeBoard;
    } else if (whichBoard.equals("doctorBoard")) {
      requestAgent.request("doctorBoard.selectOne", params);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 번호의 게시판이 없습니다!");
        return;
      }

      DoctorBoard doctorBoard = requestAgent.getObject(DoctorBoard.class);
      comment.setWhichBoard("doctor");
      xxxBoard = doctorBoard;
    } else if (whichBoard.equals("noticeBoard")) {
      requestAgent.request("noticeBoard.selectOne", params);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 번호의 게시판이 없습니다!");
        return;
      }

      NoticeBoard noticeBoard = requestAgent.getObject(NoticeBoard.class);
      comment.setWhichBoard("notice");
      xxxBoard = noticeBoard;
    }

    requestAgent.request("comment.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      comment.setNo(1);
      comment.setCommentNo(0);
      comment.setCommentBoardNo(xxxBoard.getNo());
      comment.setCommentWriter(xxxBoard.getWriter().getId());
      comment.setCommenter(AuthLoginHandler.getLoginUser().getId());
      System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
      comment.setCommentContent(Prompt.inputString("댓글 내용> "));

      requestAgent.request("comment.insert", comment);
      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("댓글 저장 실패!");
        return;
      }

      System.out.println("댓글이 등록되었습니다.");

      return;
    }

    List<Comment> commentList = (List<Comment>) requestAgent.getObjects(Comment.class);


    int count = 0; //기존에 존재하는 댓글 갯수
    int conditionLast = 0; //마지막 댓글의 번호(게시물마다 1로 리셋되는 번호)
    for (int i = 0; i < commentList.size(); i++) {
      if (commentList.get(i).getCommentBoardNo() == xxxBoard.getNo() 
          && commentList.get(i).getWhichBoard().equals(xxxBoard.getWhichBoard())) {
        count++;
        conditionLast = commentList.get(i).getNo();
      }
    }

    if (conditionLast != count) {
      comment.setNo(++conditionLast);
    } else {
      comment.setNo(++count);
    }
    comment.setCommentNo(comment.getNo() - 1);
    comment.setCommentBoardNo(xxxBoard.getNo());
    comment.setCommentWriter(xxxBoard.getWriter().getId());
    comment.setCommenter(AuthLoginHandler.getLoginUser().getId());
    System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
    comment.setCommentContent(Prompt.inputString("댓글 내용> "));

    requestAgent.request("comment.insert", comment);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("댓글 저장 실패!");
      return;
    }

    System.out.println("댓글이 등록되었습니다.");
    return;
  }
}
