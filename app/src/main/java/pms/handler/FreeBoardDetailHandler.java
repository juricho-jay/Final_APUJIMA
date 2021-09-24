package pms.handler;

import java.util.List;
import pms.domain.Comment;
import pms.domain.FreeBoard;
import util.Prompt;

public class FreeBoardDetailHandler extends AbstractFreeBoardHandler{

  List<FreeBoard> reportList;
  List<Comment> commentList;

  public FreeBoardDetailHandler(List<FreeBoard> freeBoardList,
      List<FreeBoard> reportList, List<Comment> commentList) {
    super(freeBoardList);
    this.reportList = reportList;
    this.commentList = commentList;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[상세보기] 페이지입니다.");
    System.out.println();
    int num = Prompt.inputInt("게시글 번호> ");

    FreeBoard freeBoard = findByNo(num);


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
    System.out.printf("좋아요: %d\n", freeBoard.getLike());

    System.out.println();
    System.out.println("[댓글]");
    for (Comment comment : commentList) {
      if (comment.getCommentBoardNo() != 0) {
        if (comment.getCommentBoardNo() == freeBoard.getNo()) {
          System.out.printf("%d, %s, %s\n",
              comment.getNo(),
              comment.getCommenter(),
              comment.getCommentContent());
        }
      } 
    }   
    System.out.println();
    while(true) {
      String status = Prompt.inputString("[좋아요 (#: 👍🏻) / 신고하기(!: 🚨) /\n"
          + "댓글달기(@: 💬) / 넘어가기: Enter]> ");
      if (status.equals("#")) {
        freeBoard.setLike(freeBoard.getLike() + 1);
        System.out.println("게시글 좋아요를 눌렀습니다.");
        break;
      } else if (status.equals("@")) {
        System.out.println("[댓글 달기]");
        System.out.println();
        int commentTotal = Comment.getCommentTotal();
        Comment comment = new Comment(); 

        if (commentTotal == 0) {
          comment.setNo(1);
          commentTotal++;
          Comment.setCommentTotal(commentTotal);
          comment.setCommentBoardNo(freeBoard.getNo());
          comment.setCommentWriter(freeBoard.getWriter().getId());
          comment.setCommenter(AuthLoginHandler.getLoginUser().getId());
          System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
          comment.setCommentContent(Prompt.inputString("댓글 내용> "));

          commentList.add(comment);
          break;
        } else {
          int lastIndex = 0;
          for (int i = 0; i < commentList.size(); i++) {
            if (commentList.get(i).getCommentBoardNo() == freeBoard.getNo()) {
              lastIndex++;
            }
          }

          Comment.setCommentTotal(commentTotal++);
          comment.setNo(++lastIndex);
          comment.setCommentBoardNo(freeBoard.getNo());
          comment.setCommentWriter(freeBoard.getWriter().getId());
          comment.setCommenter(AuthLoginHandler.getLoginUser().getId());
          System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
          comment.setCommentContent(Prompt.inputString("댓글 내용> "));

          commentList.add(comment);
          break;


          //        request.setAttribute("no", no);
          //        request.getRequestDispatcher("/comment/add").forward(request);

        }
      } else if (status.equals("!")) {
        freeBoard.setReason(Prompt.inputString("신고 사유를 작성해 주세요> "));
        reportList.add(freeBoard);
        freeBoard.setRequester(AuthLoginHandler.loginUser.getId());
        System.out.println("신고 접수가 완료되었습니다. 깨끗한 게시판 문화를 만드는데 도움을 주셔서 감사합니다!");
        break;
      }
      else if (status.equals("")){
        break;
      } else {
        System.out.println("메뉴에 맞는 명령어를 입력해 주세요.");
        continue;
      }
    }

    if (freeBoard.getWriter().getId().equals(AuthLoginHandler.loginUser.getId())) {
      request.setAttribute("no", num);
      while (true) {
        String input = Prompt.inputString("내 글 관리: 변경(U), 삭제(D), 이전(0)>");
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