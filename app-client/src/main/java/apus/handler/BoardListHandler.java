package apus.handler;

import java.util.List;
import apus.dao.BoardDao;
import apus.domain.Board;
import util.Prompt;

public class BoardListHandler implements Command{

  BoardDao boardDao;

  public BoardListHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[게시글 목록] 페이지입니다.");
    System.out.println();

    int selectBoard = 0;
    while (true) {
      System.out.println("1.자유게시판 2.지식in 게시판 3.공지사항 (0:돌아가기)>" );
      try {
        selectBoard = Prompt.inputInt("게시판 선택> ");

        if (selectBoard == 0) {
          return;
        }

        if (selectBoard < 1 || selectBoard > 3) {
          System.out.println("다시 입력하세요.");
        } else {
          break;
        }
      } catch (Exception e) {
        System.out.println("다시 입력하세요.");
      }
    }

    List<Board> boardList = boardDao.findAll();

    if (boardList == null) {
      System.out.println("작성된 게시글이 없습니다.");
      return;
    }

    if (selectBoard == 1) {
      for (Board board : boardList) {
        if (board.getWhichBoard() == 1) {
          System.out.printf("%d, %s, %s, %s, %d\n",
              board.getNo(),
              board.getTitle(),
              board.getWriter().getId(), //우리는 name이 아니라 id를 가져옴
              board.getRegisteredDate(),
              board.getViewCount());
        }
      }
    } else if (selectBoard == 2) {
      for (Board board : boardList) {
        if (board.getWhichBoard() == 2) {
          System.out.printf("%d, %s, %s, %s, %d\n",
              board.getNo(),
              board.getTitle(),
              board.getWriter().getId(), //우리는 name이 아니라 id를 가져옴
              board.getRegisteredDate(),
              board.getViewCount());
        }
      }
    } else if (selectBoard == 3) {
      for (Board board : boardList) {
        if (board.getWhichBoard() == 3) {
          System.out.printf("%d, %s, %s, %s, %d\n",
              board.getNo(),
              board.getTitle(),
              board.getWriter().getId(), //우리는 name이 아니라 id를 가져옴
              board.getRegisteredDate(),
              board.getViewCount());
        }
      }
    }
  }
}
