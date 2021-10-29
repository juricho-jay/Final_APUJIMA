package apus.handler;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BoardDao;
import apus.dao.CommentDao;
import apus.dao.LikeDao;
import apus.dao.ReportDao;
import apus.domain.Board;
import apus.domain.Comment;
import apus.domain.Like;
import apus.domain.Report;
import util.Prompt;

public class BoardDetailHandler implements Command { 

  BoardDao boardDao;
  LikeDao likeDao;
  CommentDao commentDao;
  ReportDao reportDao;
  SqlSession sqlSession;

  public BoardDetailHandler(BoardDao boardDao, LikeDao likeDao, 
      CommentDao commentDao, ReportDao reportDao, SqlSession sqlSession) {
    this.boardDao = boardDao;
    this.likeDao = likeDao;
    this.commentDao = commentDao;
    this.reportDao = reportDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[상세보기] 페이지입니다.");
    System.out.println();
    if(AuthLoginHandler.getLoginUser() == null) {
      System.out.println("로그인 후 이용해 주시기 바랍니다.");
      return;
    }
    String loginUser = AuthLoginHandler.getLoginUser().getId();
    int no = Prompt.inputInt("게시글 번호> ");

    Board board = boardDao.findByNo(no);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성자: %s\n", board.getWriter().getId()); // 우리는 익명이기 때문에 Id로
    System.out.printf("등록일: %s\n", board.getRegisteredDate());

    board.setViewCount(board.getViewCount() + 1);
    System.out.printf("조회수: %d\n", board.getViewCount());
    boardDao.updateCount(no);
    sqlSession.commit();

    int whichBoard = board.getWhichBoard();

    List<Like> likeList = likeDao.findAll();
    if (likeList.size() == 0) {
      System.out.print("[좋아요 ♡ : 0]");
    } else {
      for (int i = 0; i < likeList.size(); i++) {
        if (likeList.get(i).getLikeBoard().getNo() == board.getNo() && 
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
        if (likeList.get(j).getLikeBoard().getNo() != 0) {
          if (likeList.get(j).getLikeBoard().getNo() == board.getNo()) {
            count++;
          }
        }   
      }

      System.out.printf(" %d]\n", count);
    }

    System.out.println();
    System.out.println("[댓글]");

    List<Comment> commentList = commentDao.findAll();


    if(commentList.size() != 0) {
      for (Comment comment : commentList) {
        if (comment.getCommentBoard().getNo() != 0) {
          if (comment.getCommentBoard().getNo() == board.getNo()) {
            System.out.printf("%d. %s, %s\n",
                comment.getNo(),
                comment.getCommenter().getId(),
                comment.getContent());
          }
        }   
      }
    }

    System.out.println();
    request.setAttribute("no", no);

    while(true) {
      String status = "";
      if (likeList.size() == 0) {
        status = Prompt.inputString("[좋아요♡(#) / 신고하기🚨(!) / \n"
            + "댓글달기💬(@) / 넘어가기(Enter)]> ");
      } else if (whichBoard == 3) {
        status = Prompt.inputString("[좋아요♡(#) / 댓글달기💬(@) / 넘어가기(Enter)]> ");
      } else {
        for (int i = 0; i < likeList.size(); i++) {
          if (likeList.get(i).getLiker().getId().equals(AuthLoginHandler.getLoginUser().getId()) &&
              likeList.get(i).getLikeBoard().getNo() == board.getNo()) {
            status = Prompt.inputString("[좋아요 취소💔(#) / 신고하기🚨(!) /\n"
                + "댓글달기💬(@) / 넘어가기(Enter)]> ");
            break;
          }  else if (i == likeList.size() - 1) {
            status = Prompt.inputString("[좋아요♡(#) / 신고하기🚨(!) /\\n"
                + "댓글달기💬(@) / 넘어가기(Enter)]> ");
          }
        }
      }

      if (status.equals("#")) {
        request.getRequestDispatcher("/like/addCancel").forward(request);
        return;

      } else if (status.equals("@")) {
        request.getRequestDispatcher("/comment/add").forward(request);
        return;

      } else if (status.equals("!")) {
        // 중복신고 차단
        List<Report> reportList = reportDao.findAll();
        for (Report report : reportList) {
          if (report.getRequestBoard().getNo() == board.getNo() &&
              report.getRequester().getId().equals(AuthLoginHandler.getLoginUser().getId())) {
            System.out.println("이미 신고 신청이 된 게시글 입니다.");
            return;
          }
        }

        Report report = new Report();
        report.setReason(Prompt.inputString("신고 사유를 작성해 주세요> "));
        report.setRequester(AuthLoginHandler.getLoginUser());
        report.setRequestBoard(board);
        reportDao.insert(report);
        sqlSession.commit();

        System.out.println("신고 접수가 완료되었습니다. 깨끗한 게시판 문화를 만드는데 도움을 주셔서 감사합니다!");
        break;

      } else if (status.equals("")){
        break;
      } else {
        System.out.println("메뉴에 맞는 명령어를 입력해 주세요.");
        continue;
      }

    } 

    // 댓글list 없는 경우
    if (commentList.size() == 0) {
      if (board.getWriter().getId().equals(loginUser)) {
        while (true) {
          System.out.println();
          String input = Prompt.inputString("[글] 변경(U) / 삭제(D) / 이전 메뉴(0)> ");
          switch (input) {
            case "U":
            case "u":
              request.getRequestDispatcher("/board/update").forward(request);
              return;
            case "D":
            case "d":
              request.getRequestDispatcher("/board/delete").forward(request);
              return;
            case "0":
              return;
            default:
              System.out.println("명령어가 올바르지 않습니다!");
          }
        }
      }
      return;
    }

    int myComment = 0;
    for (Comment comment : commentList) {
      if (comment.getCommentBoard().getNo() != 0) {
        if (comment.getCommentBoard().getNo() == board.getNo() && 
            //            comment.getWhichBoard() == whichBoard &&
            comment.getCommenter().getId().equals(loginUser)) {
          myComment++;
        }
      }   
    }

    //내 댓글도 있고 내가 게시글 글쓴이일 때
    if (myComment != 0 && board.getWriter().getId().equals(loginUser)) {
      while (true) {
        System.out.println();
        String input = Prompt.inputString("[글] 변경(U) / 삭제(D) /\n"
            + "[댓글] 변경(M) / 삭제(R) / 이전 메뉴(0)> ");
        switch (input) {
          case "U":
          case "u":
            request.getRequestDispatcher("/board/update").forward(request);
            return;
          case "D":
          case "d":
            request.getRequestDispatcher("/board/delete").forward(request);
            return;
          case "M":
          case "m":
            request.getRequestDispatcher("/comment/update").forward(request);
            return;
          case "R":
          case "r":
            request.getRequestDispatcher("/comment/delete").forward(request);
            return;
          case "0":
            return;
          default:
            System.out.println("명령어가 올바르지 않습니다!");
        }
      }
      //
      // 내 댓글이 없고 내가 게시글 글쓴이일 때 
    } else if (myComment == 0 && board.getWriter().getId().equals(loginUser)) {
      while (true) {
        System.out.println();
        String input = Prompt.inputString("[글] 변경(U) / 삭제(D) / 이전 메뉴(0)> ");
        switch (input) {
          case "U":
          case "u":
            request.getRequestDispatcher("/board/update").forward(request);
            return;
          case "D":
          case "d":
            request.getRequestDispatcher("/board/delete").forward(request);
            return;
          case "0":
            return;
          default:
            System.out.println("명령어가 올바르지 않습니다!");
        }
      }

      // 내 댓글이 있고 내가 게시글 글쓴이가 아닐 때
    } else if (myComment != 0 && !board.getWriter().getId().equals(loginUser)) {
      while (true) {
        System.out.println();
        String input = Prompt.inputString("[댓글] 변경(M) / 삭제(R) / 이전 메뉴(0)> ");
        switch (input) {
          case "M":
          case "m":
            request.getRequestDispatcher("/comment/update").forward(request);
            return;
          case "R":
          case "r":
            request.getRequestDispatcher("/comment/delete").forward(request);
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

