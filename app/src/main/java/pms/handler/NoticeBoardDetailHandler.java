package pms.handler;

import java.util.List;
import pms.domain.Comment;
import pms.domain.Member;
import pms.domain.NoticeBoard;
import util.Prompt;

public class NoticeBoardDetailHandler extends AbstractNoticeBoardHandler{
  List<Comment> commentList;
  MemberPrompt memberPrompt;
  List<Member> likeMemberList;

  public NoticeBoardDetailHandler(List<NoticeBoard> noticeBoardList,
      List<Comment> commentList, MemberPrompt memberPrompt, 
      List<Member> likeMemberList) {
    super(noticeBoardList);
    this.commentList = commentList;
    this.memberPrompt = memberPrompt;
    this.likeMemberList = likeMemberList;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[상세보기] 페이지입니다.");
    System.out.println();
    int num = Prompt.inputInt("게시글 번호> ");
    NoticeBoard noticeBoard = findByNo(num);
    String loginUser = AuthLoginHandler.getLoginUser().getId();


    if (noticeBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", noticeBoard.getTitle());
    System.out.printf("내용: %s\n", noticeBoard.getContent());
    System.out.printf("작성자: %s\n", noticeBoard.getWriter().getId()); // 우리는 익명이기 때문에 Id로
    System.out.printf("등록일: %s\n", noticeBoard.getRegisteredDate());

    noticeBoard.setViewCount(noticeBoard.getViewCount() + 1);
    System.out.printf("조회수: %d\n", noticeBoard.getViewCount());

    if (memberPrompt.findLikeMember(loginUser) == null) { 
      System.out.printf("[좋아요 ♡ : %d]\n", noticeBoard.getLike());
    } else {
      System.out.printf("[좋아요 ♥ : %d]\n", noticeBoard.getLike());
    }

    System.out.println();
    System.out.println("[댓글]");
    for (Comment comment : commentList) {
      String whichBoard = noticeBoard.getWhichBoard();
      if (comment.getCommentBoardNo() != 0) {
        if (comment.getCommentBoardNo() == noticeBoard.getNo() 
            && comment.getWhichBoard().equals(whichBoard)) {
          System.out.printf("%d, %s, %s\n",
              comment.getNo(),
              comment.getCommenter(),
              comment.getCommentContent());
        }
      }   
    }

    System.out.println();
    request.setAttribute("num", num);
    request.setAttribute("boardType", "noticeBoard");

    while(true) {
      String status = Prompt.inputString("[좋아요 (#: ♡) /\n"
          + "댓글달기(@: 💬) / 넘어가기: Enter]> ");
      if (status.equals("#")) {
        if (memberPrompt.findLikeMember(loginUser) == null) {
          noticeBoard.setLike(noticeBoard.getLike() + 1);
          likeMemberList.add(AuthLoginHandler.getLoginUser());
          System.out.println("게시글 좋아요를 눌렀습니다.");
          break;
        } else {
          noticeBoard.setLike(noticeBoard.getLike() - 1);
          likeMemberList.remove(AuthLoginHandler.getLoginUser());
          System.out.println("게시글 좋아요가 취소되었습니다.");
          break;
        }

      } else if (status.equals("@")) {
        request.getRequestDispatcher("/comment/add").forward(request);
        return;

      } else if (status.equals("")){
        break;
      } else {
        System.out.println("메뉴에 맞는 명령어를 입력해 주세요.");
        continue;
      }
    } 


    if (noticeBoard.getWriter().getId().equals(loginUser)) {
      request.setAttribute("num", num);
      while (true) {
        String input = Prompt.inputString("변경(U), 삭제(D), 이전(0)>");
        switch (input) {
          case "U":
          case "u":
            request.getRequestDispatcher("/noticeBoard/update").forward(request);
            return;
          case "D":
          case "d":
            request.getRequestDispatcher("/noticeBoard/delete").forward(request);
            return;
          case "0":
            return;
          default:
            System.out.println("명령어가 올바르지 않습니다!");
        }
      }
    }
  }
}



