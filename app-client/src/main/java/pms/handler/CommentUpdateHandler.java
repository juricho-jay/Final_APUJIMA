package pms.handler;

import java.util.HashMap;
import java.util.List;
import pms.domain.Comment;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.NoticeBoard;
import request.RequestAgent;
import util.Prompt;

public class CommentUpdateHandler implements Command {

  RequestAgent requestAgent;

  public CommentUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }



  @Override
  public void execute(CommandRequest request) throws Exception {
    try {
      System.out.println();
      System.out.println("[댓글 변경]");
      System.out.println();
      int commentResetNo = Prompt.inputInt("번호> "); //댓글 번호 (게시물마다 1번으로 시작되는)

      requestAgent.request("comment.selectList", null);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("등록된 댓글이 없습니다.");
        return;
      }

      List<Comment> commentList = (List<Comment>) requestAgent.getObjects(Comment.class);

      int no = (int)request.getAttribute("no"); // freeBoardDetailHandler에서 입력한 게시판 번호 불러오기

      HashMap<String,String> params = new HashMap<>();
      params.put("no", String.valueOf(no));

      String whichBoard = (String)request.getAttribute("boardType"); // 어떤 게시판인지 String으로 변수 지정

      if (whichBoard.equals("freeBoard")) { 

        requestAgent.request("freeBoard.selectOne", params);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("해당 번호의 게시글이 없습니다.");
          return;
        }

        FreeBoard freeBoard = requestAgent.getObject(FreeBoard.class);


        String commentContent = null;
        Comment comment = new Comment();

        for (int i = 0; i < commentList.size(); i++) {
          if (commentList.get(i).getWhichBoard().equals(freeBoard.getWhichBoard())&& 
              commentList.get(i).getCommentBoardNo() == freeBoard.getNo() &&
              commentList.get(i).getNo() == commentResetNo && 
              commentList.get(i).getCommenter().equals(AuthLoginHandler.getLoginUser().getId())) {
            comment = commentList.get(i);
            commentContent = commentList.get(i).getCommentContent();
            break;
          } else if (commentList.get(i).getWhichBoard().equals(freeBoard.getWhichBoard())&& 
              commentList.get(i).getCommentBoardNo() == freeBoard.getNo() &&
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
        requestAgent.request("comment.update", comment);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("댓글 변경 실패!");
          System.out.println(requestAgent.getObject(String.class));
          return;
        }

        System.out.println("댓글을 변경하였습니다.");

      } else if (whichBoard.equals("doctorBoard")) {

        requestAgent.request("doctorBoard.selectOne", params);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("해당 번호의 게시글이 없습니다.");
          return;
        }

        DoctorBoard doctorBoard = requestAgent.getObject(DoctorBoard.class); 


        String commentContent = null;
        Comment comment = new Comment();

        for (int i = 0; i < commentList.size(); i++) {
          if (commentList.get(i).getWhichBoard().equals(doctorBoard.getWhichBoard())&& 
              commentList.get(i).getCommentBoardNo() == doctorBoard.getNo() &&
              commentList.get(i).getNo() == commentResetNo && 
              commentList.get(i).getCommenter().equals(AuthLoginHandler.getLoginUser().getId())) {
            comment = commentList.get(i);
            commentContent = commentList.get(i).getCommentContent();
            break;
          } else if (commentList.get(i).getWhichBoard().equals(doctorBoard.getWhichBoard())&& 
              commentList.get(i).getCommentBoardNo() == doctorBoard.getNo() &&
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
        requestAgent.request("comment.update", comment);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("댓글 변경 실패!");
          System.out.println(requestAgent.getObject(String.class));
          return;
        }

        System.out.println("댓글을 변경하였습니다.");

      } else if (whichBoard.equals("noticeBoard")) {

        requestAgent.request("noticeBoard.selectOne", params);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("해당 번호의 게시글이 없습니다.");
          return;
        }

        NoticeBoard noticeBoard = requestAgent.getObject(NoticeBoard.class);


        String commentContent = null;
        Comment comment = new Comment();

        for (int i = 0; i < commentList.size(); i++) {
          if (commentList.get(i).getWhichBoard().equals(noticeBoard.getWhichBoard())&& 
              commentList.get(i).getCommentBoardNo() == noticeBoard.getNo() &&
              commentList.get(i).getNo() == commentResetNo && 
              commentList.get(i).getCommenter().equals(AuthLoginHandler.getLoginUser().getId())) {
            comment = commentList.get(i);
            commentContent = commentList.get(i).getCommentContent();
            break;
          } else if (commentList.get(i).getWhichBoard().equals(noticeBoard.getWhichBoard())&& 
              commentList.get(i).getCommentBoardNo() == noticeBoard.getNo() &&
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
        requestAgent.request("comment.update", comment);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("댓글 변경 실패!");
          System.out.println(requestAgent.getObject(String.class));
          return;
        }

        System.out.println("댓글을 변경하였습니다.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
