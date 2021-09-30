package pms.handler;

import java.util.List;
import pms.domain.Comment;
import pms.domain.FreeBoard;
import pms.domain.Like;
import util.Prompt;

public class FreeBoardDetailHandler extends AbstractFreeBoardHandler{
  List<FreeBoard> reportList;
  List<Comment> commentList;
  List<Like> likeList;

  public FreeBoardDetailHandler(List<FreeBoard> freeBoardList,
      List<FreeBoard> reportList, List<Comment> commentList,
      List<Like> likeList) {
    super(freeBoardList);
    this.reportList = reportList;
    this.commentList = commentList;
    this.likeList = likeList;

  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[상세보기] 페이지입니다.");
    System.out.println();
    int num = Prompt.inputInt("게시글 번호> ");
    FreeBoard freeBoard = findByNo(num);
    String loginUser = AuthLoginHandler.getLoginUser().getId();

    if (freeBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    System.out.printf("제목: %s\n", freeBoard.getTitle());
    System.out.printf("내용: %s\n", freeBoard.getContent());
    System.out.printf("작성자: %s\n", freeBoard.getWriter().getId()); // 우리는 익명이기 때문에 Id로
    System.out.printf("등록일: %s\n", freeBoard.getRegisteredDate());

    freeBoard.setViewCount(freeBoard.getViewCount() + 1);
    System.out.printf("조회수: %d\n", freeBoard.getViewCount());

    String whichBoard = freeBoard.getWhichBoard();

    if (likeList.size() == 0) {
      System.out.print("[좋아요 ♡ :");
    }

    for (int i = 0; i < likeList.size(); i++) {
      if (likeList.get(i).getLikeBoardNo() == freeBoard.getNo() && 
          likeList.get(i).getWhichBoard().equals(whichBoard) &&
          likeList.get(i).getLiker().getId().equals(AuthLoginHandler.getLoginUser().getId())) {
        System.out.print("[좋아요 ♥ :");
        break;
      } else if (i == (likeList.size() - 1)) {
        System.out.print("[좋아요 ♡ :");
        break;
      }
    }

    int count = 0;
    for (int j = 0; j < likeList.size(); j++) {
      if (likeList.get(j).getLikeBoardNo() != 0) {
        if (likeList.get(j).getLikeBoardNo() == freeBoard.getNo() && 
            likeList.get(j).getWhichBoard().equals(whichBoard)) {
          count++;
        }
      }   
    }

    System.out.printf(" %d]\n", count);

    System.out.println();
    System.out.println("[댓글]");
    for (Comment comment : commentList) {
      if (comment.getCommentBoardNo() != 0) {
        if (comment.getCommentBoardNo() == freeBoard.getNo() && 
            comment.getWhichBoard().equals(whichBoard)) {
          System.out.printf("%d. %s, %s\n",
              comment.getNo(),
              comment.getCommenter(),
              comment.getCommentContent());
        }
      }   
    }

    System.out.println();
    request.setAttribute("num", num);
    request.setAttribute("boardType", "freeBoard");

    while(true) {
      String status = "";

      for (int i = 0; i < likeList.size(); i++) {
        if (likeList.get(i).getLiker().getId().equals(AuthLoginHandler.getLoginUser().getId()) &&
            likeList.get(i).getWhichBoard().equals(whichBoard)) {
          status = Prompt.inputString("[좋아요 취소(#: ㅠㅠ) / 신고하기(!: 🚨) /\n"
              + "댓글달기(@: 💬) / 넘어가기: Enter]> ");
          break;
        }  else if (i == (likeList.size() - 1)) {
          status = Prompt.inputString("[좋아요 (#: ♡) / 신고하기(!: 🚨) /\n"
              + "댓글달기(@: 💬) / 넘어가기: Enter]> ");
        }
      }

      if (status.equals("#")) {
        request.getRequestDispatcher("/like/addCancel").forward(request);
        return;

      } else if (status.equals("@")) {
        request.getRequestDispatcher("/comment/add").forward(request);
        return;

      } else if (status.equals("!")) {
        freeBoard.setReason(Prompt.inputString("신고 사유를 작성해 주세요> "));
        reportList.add(freeBoard);
        freeBoard.setRequester(loginUser);
        System.out.println("신고 접수가 완료되었습니다. 깨끗한 게시판 문화를 만드는데 도움을 주셔서 감사합니다!");
        break;

      } else if (status.equals("")){
        break;
      } else {
        System.out.println("메뉴에 맞는 명령어를 입력해 주세요.");
        continue;
      }
    } 

    if (freeBoard.getWriter().getId().equals(loginUser)) {
      request.setAttribute("num", num);
      while (true) {
        String input = Prompt.inputString("변경(U), 삭제(D), 이전(0)>");
        switch (input) {
          case "U":
          case "u":
            request.getRequestDispatcher("/freeBoard/update").forward(request);
            return;
          case "D":
          case "d":
            request.getRequestDispatcher("/freeBoard/delete").forward(request);
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








