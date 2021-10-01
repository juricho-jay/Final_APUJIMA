package pms.handler;

import java.util.List;
import pms.domain.Comment;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.NoticeBoard;
import util.Prompt;

public class CommentUpdateHandler extends AbstractCommentHandler {

  public CommentUpdateHandler(List<Comment> commentList, List<FreeBoard> freeBoardList,
      List<DoctorBoard> doctorBoardList, List<NoticeBoard> noticeBoardList) {
    super(commentList, freeBoardList, doctorBoardList, noticeBoardList);

  }



  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println();
    System.out.println("[댓글 변경]");
    System.out.println();
    int no = Prompt.inputInt("번호? ");
    try {
      String whichBoard = (String)request.getAttribute("boardType"); // 어떤 게시판인지 String으로 변수 지정
      //    Comment comment = findByNo(no);

      if (whichBoard.equals("freeBoard")) { 
        FreeBoard freeBoard = findByFreeBoardNo(no); 

        if (commentList.size() == 0) {
          return;
        }

        String commentContent = null;
        int commentNo = 0;
        Comment comment = new Comment();

        for (int i = 0; i < commentList.size(); i++) {
          if (commentList.get(i).getWhichBoard().equals(freeBoard.getWhichBoard())&& 
              commentList.get(i).getCommentBoardNo() == freeBoard.getNo() &&
              commentList.get(i).getNo() == no && 
              commentList.get(i).getCommenter().equals(AuthLoginHandler.getLoginUser().getId())) {
            commentNo = i;
            comment = commentList.get(i);
            break;
          }
        }

        String newCommentContent = Prompt.inputString(String.format("댓글 내용(%s)> ", commentContent));
        String input = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
        if(input.equalsIgnoreCase("n") || input.length() == 0) {
          System.out.println("댓글 변경을 취소하였습니다.");
          return;
        }

        comment.setCommentContent(newCommentContent);
        commentList.set(commentNo, comment);
        System.out.println("댓글을 변경하였습니다.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}