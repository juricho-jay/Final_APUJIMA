package apus.handler;

import java.sql.Date;
import apus.dao.BoardDao;
import apus.domain.Board;
import util.Prompt;

public class BoardAddHandler implements Command{

  BoardDao boardDao;

  public BoardAddHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[게시판 글쓰기] 페이지입니다.");
    System.out.println();

    Board board = new Board();

    while (true) {
      System.out.println("1.자유게시판 2.지식in 게시판 3.공지사항 (0:돌아가기)>" );
      try {
        int selectBoard = Prompt.inputInt("게시판 선택> ");

        if (selectBoard == 0) {
          return;
        }

        if (selectBoard == 3 && AuthLoginHandler.getLoginUser().getDoctorOrNot() != 3) {
          System.out.println("관리자만 공지사항을 입력할 수 있습니다.");
          continue;
        }

        if (selectBoard < 1 || selectBoard > 3) {
          System.out.println("다시 입력하세요.");
        } else {
          board.setWhichBoard(selectBoard);
          break;
        }
      } catch (Exception e) {
        System.out.println("다시 입력하세요.");
      }
    }


    while(true) {

      board.setTitle(Prompt.inputString("제목> "));
      if (board.getTitle().trim().equals("")) {
        System.out.println("제목이 공백입니다. 다시 입력해주세요.");
      }
      else {
        break;
      }
    }
    while(true) {
      board.setContent(Prompt.inputString("내용> "));
      if (board.getContent().trim().equals("")) {
        System.out.println("내용이 공백입니다. 다시 입력해주세요.");
      } else {
        break;
      }
    }

    board.setWriter(AuthLoginHandler.getLoginUser());
    board.setRegisteredDate(new Date(System.currentTimeMillis()));
    board.setWhichBoard(board.getWhichBoard());

    if (board.getWhichBoard() == 1) {
      boardDao.insert(board);
    } else if (board.getWhichBoard() == 2) {
      boardDao.insert2(board);
    } else if (board.getWhichBoard() == 3) {
      boardDao.insert3(board);
    }

    System.out.println("게시글이 등록되었습니다.");
  }

}
