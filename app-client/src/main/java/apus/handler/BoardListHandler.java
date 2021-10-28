package apus.handler;

import java.util.List;
import apus.dao.BoardDao;
import apus.domain.Board;

public class BoardListHandler implements Command{

  BoardDao boardDao;

  public BoardListHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 목록] 페이지입니다.");
    System.out.println();

    List<Board> boardList = boardDao.findAll();

    if (boardList == null) {
      System.out.println("작성된 게시글이 없습니다.");
      return;
    }

    for (Board board : boardList) {
      System.out.printf("%d, %s, %s, %s, %d\n",
          board.getNo(),
          board.getTitle(),
          board.getWriter().getId(), //우리는 name이 아니라 id를 가져옴
          board.getRegisteredDate(),
          board.getViewCount());
    }
  }
}
