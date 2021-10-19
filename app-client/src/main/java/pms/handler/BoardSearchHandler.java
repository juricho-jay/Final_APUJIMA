package pms.handler;

import java.util.List;
import pms.dao.BoardDao;
import pms.domain.Board;
import util.Prompt;

public class BoardSearchHandler implements Command {

  BoardDao boardDao;

  public BoardSearchHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int count = 0;
    System.out.println("[게시글 검색] 페이지입니다.");
    System.out.println();
    String input = Prompt.inputString("검색어> ");

    List<Board> boardList = boardDao.findByKeyword(input);

    for (Board board : boardList) {
      if (!board.getTitle().contains(input) &&
          !board.getContent().contains(input) &&
          !board.getWriter().getId().contains(input)) {
        continue;
      }
      count++;
      System.out.printf("%d, %s, %s, %s, %d\n",
          board.getNo(),
          board.getTitle(),
          board.getWriter().getId(),
          board.getRegisteredDate(),
          board.getViewCount());
    }

    if (count == 0 ) {
      System.out.println("찾는 게시물이 없습니다.");
    }
  }
}
