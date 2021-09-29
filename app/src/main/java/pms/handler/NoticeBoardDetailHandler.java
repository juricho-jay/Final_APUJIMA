package pms.handler;

import java.util.List;
import pms.domain.Member;
import pms.domain.NoticeBoard;
import util.Prompt;

public class NoticeBoardDetailHandler extends AbstractNoticeBoardHandler{
  MemberPrompt memberPrompt;
  List<Member> likeMemberList;

  public NoticeBoardDetailHandler(List<NoticeBoard> noticeBoardList,
      MemberPrompt memberPrompt, List<Member> likeMemberList) {
    super(noticeBoardList);
    this.memberPrompt = memberPrompt;
    this.likeMemberList = likeMemberList;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[상세보기] 페이지입니다.");
    System.out.println();
    int no = Prompt.inputInt("게시글 번호> ");

    NoticeBoard noticeBoard = findByNo(no);
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
      System.out.printf("좋아요 ♡ : %d\n", noticeBoard.getLike());
    } else {
      System.out.printf("좋아요 ♥︎️ : %d\n", noticeBoard.getLike());
    }

    String likeNum = Prompt.inputString("[좋아요 (#: ♡) / 넘어가기: Enter ]> ");
    if (likeNum.equals("#")) {
      if (memberPrompt.findLikeMember(loginUser) == null) {
        noticeBoard.setLike(noticeBoard.getLike() + 1);
        likeMemberList.add(AuthLoginHandler.getLoginUser());
        System.out.println("게시글 좋아요를 눌렀습니다.");
        return;
      } else {
        noticeBoard.setLike(noticeBoard.getLike() - 1);
        likeMemberList.remove(AuthLoginHandler.getLoginUser());
        System.out.println("게시글 좋아요가 취소되었습니다.");
        return;
      }
    } 

    if (noticeBoard.getWriter().getId().equals(AuthLoginHandler.loginUser.getId())) {
      request.setAttribute("no", no);
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



