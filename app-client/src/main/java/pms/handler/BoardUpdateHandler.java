package pms.handler;

import pms.dao.BoardDao;
import pms.domain.Board;
import util.Prompt;

public class BoardUpdateHandler implements Command {

  BoardDao boardDao;

  public BoardUpdateHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 변경] 페이지입니다.");
    System.out.println();
    int no = (int)request.getAttribute("no");

    Board board = boardDao.findByNo(no);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }


    String title = Prompt.inputString(String.format("제목(%s)> ", board.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s)> ", board.getContent()));

    String input = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }

    board.setTitle(title);
    board.setContent(content);
    boardDao.update(board);


    System.out.println("게시글을 변경하였습니다.");

  }
}
