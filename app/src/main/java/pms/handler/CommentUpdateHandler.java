package pms.handler;

import java.util.List;
import pms.domain.Comment;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.NoticeBoard;

public class CommentUpdateHandler extends AbstractCommentHandler {

  public CommentUpdateHandler(List<Comment> commentList, List<FreeBoard> freeBoardList,
      List<DoctorBoard> doctorBoardList, List<NoticeBoard> noticeBoardList) {
    super(commentList, freeBoardList, doctorBoardList, noticeBoardList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    //    System.out.println("[댓글 변경]");
    //    System.out.println();
    //    int no = (int)request.getAttribute("num");
    //
    //    String whichBoard = (String)request.getAttribute("boardType"); // 어떤 게시판인지 String으로 변수 지정
    //
    //    if (whichBoard.equals("freeBoard")) { // 그게 만약 자유게시판이면
    //      Comment comment = findByCommentNo(no); // 찾는 번호의 댓글을 지정하고
    //
    //      String commentContent = Prompt.inputString(String.format("댓글 내용(%s)> ", comment.getCommentContent()));
    //      String input = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
    //      if(input.equalsIgnoreCase("n") || input.length() == 0) {
    //        System.out.println("댓글 변경을 취소하였습니다.");
    //        return;
    //      }
    //      comment.setCommentContent(commentContent);
    //      System.out.println("댓글을 변경하였습니다.");
    //
    //    } else if (whichBoard.equals("doctorBoard")) { // 그게 만약 힐러지식인게시판이면
    //      Comment comment = findByCommentNo(commentNo);
    //
    //      String commentContent = Prompt.inputString(String.format("댓글 내용(%s)> ", comment.getCommentContent()));
    //      String input = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
    //      if(input.equalsIgnoreCase("n") || input.length() == 0) {
    //        System.out.println("댓글 변경을 취소하였습니다.");
    //        return;
    //      }
    //      comment.setCommentContent(commentContent);
    //      System.out.println("댓글을 변경하였습니다.");
    //
    //    } else if (whichBoard.equals("noticeBoard")) { // 그게 만약 공지사항이면
    //      Comment comment = findByCommentNo(commentNo);
    //
    //      String commentContent = Prompt.inputString(String.format("댓글 내용(%s)> ", comment.getCommentContent()));
    //      String input = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
    //      if(input.equalsIgnoreCase("n") || input.length() == 0) {
    //        System.out.println("댓글 변경을 취소하였습니다.");
    //        return;
    //      }
    //      comment.setCommentContent(commentContent);
    //      System.out.println("댓글을 변경하였습니다.");
    //    }

    ///////////////////////////////



    //    if (commentTotal == 0) { // 댓글 총 개수가 0이면
    //      comment.setNo(1); // 번호 1번 지정
    //      comment.setCommentBoardNo(xxxBoard.getNo()); // 임시 게시판 중 설정한 번호는 댓글번호로 지정
    //      comment.setCommentWriter(xxxBoard.getWriter().getId()); // 임시 게시판에서 글쓴이를 댓글쓴이로 지정
    //      comment.setCommenter(AuthLoginHandler.getLoginUser().getId()); // 현재 로그인된 회원을 댓글쓴으로 지정
    //      System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
    //      comment.setCommentContent(Prompt.inputString("댓글 내용> "));
    //
    //      commentList.add(comment); // 댓글리스트에 추가
    //      return;
    //    } else { // 댓글 총 개수가 1개 이상
    //      int lastIndex = 0; // 마지막 번호를 선언하고
    //      for (int i = 0; i < commentList.size(); i++) { // 총 댓글 수 for문으로 반복
    //        if (commentList.get(i).getCommentBoardNo() == xxxBoard.getNo() 
    //            && commentList.get(i).getWhichBoard().equals(xxxBoard.getWhichBoard())) {
    //          // 만약 i번째의 댓글의 게시판번호와 임시게시판 번호가 같고
    //          // i번째의 설정된 게시판의 댓글에서 임시게시판의 설정된 게시판이 같다면 
    //          lastIndex++; // +1 해준다.
    //        }
    //      }
    //
    //      comment.setNo(++lastIndex); // 기존에 있던 댓글 갯수에서 +1
    //      comment.setCommentBoardNo(xxxBoard.getNo()); // 
    //      comment.setCommentWriter(xxxBoard.getWriter().getId());
    //      comment.setCommenter(AuthLoginHandler.getLoginUser().getId());
    //      System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
    //      comment.setCommentContent(Prompt.inputString("댓글 내용> "));
    //
    //      commentList.add(comment);
    //      return;
    //
    //
    //      //        request.setAttribute("no", no);
    //      //        request.getRequestDispatcher("/comment/add").forward(request);
    //
    //    }
  }
}
