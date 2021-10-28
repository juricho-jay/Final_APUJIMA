package apus.handler;

import java.sql.Date;
import java.util.List;
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
      System.out.println("1.자유게시판 2.지식in 게시판 3.공지사항");
      try {
        int selectBoard = Prompt.inputInt("게시판 선택> ");

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


    List<Board> boardList = boardDao.findAll();

    //    if (boardList == null) {
    //      Board.lastIndex = 1;
    //      board.setNo(Board.lastIndex);
    //    } else {
    //      if(Board.lastIndex != boardList.size()) {
    //        Board.lastIndex = boardList.get(boardList.size()-1).getNo();
    //        board.setNo(++Board.lastIndex);
    //      } else {
    //        board.setNo(++Board.lastIndex);
    //      }
    //    }

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

    boardDao.insert(board);
    System.out.println("게시글이 등록되었습니다.");
  }

}
