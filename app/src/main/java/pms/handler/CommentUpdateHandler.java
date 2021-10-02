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
    try {
      System.out.println();
      System.out.println("[댓글 변경]");
      System.out.println();
      int no = Prompt.inputInt("번호? ");

      int boardNo = (int)request.getAttribute("num"); // freeBoardDetailHandler에서 입력한 게시판 번호 불러오기
      String whichBoard = (String)request.getAttribute("boardType"); // 어떤 게시판인지 String으로 변수 지정

      if (whichBoard.equals("freeBoard")) { 
        FreeBoard freeBoard = findByFreeBoardNo(boardNo); // 아까 입력한 no는 댓글 번호 여기는 boardNo 입력

        if (commentList.size() == 0) {
          return;
        }

        String commentContent = null;
        Comment comment = new Comment();

        for (int i = 0; i < commentList.size(); i++) {
          if (commentList.get(i).getWhichBoard().equals(freeBoard.getWhichBoard())&& 
              commentList.get(i).getCommentBoardNo() == freeBoard.getNo() &&
              commentList.get(i).getNo() == no && 
              commentList.get(i).getCommenter().equals(AuthLoginHandler.getLoginUser().getId())) {
            comment = commentList.get(i);
            commentContent = commentList.get(i).getCommentContent();
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

        System.out.println("댓글을 변경하였습니다.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
