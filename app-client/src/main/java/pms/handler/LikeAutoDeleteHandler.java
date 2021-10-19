package pms.handler;

import java.util.List;
import pms.dao.BoardDao;
import pms.dao.LikeDao;
import pms.domain.Board;
import pms.domain.Like;

public class LikeAutoDeleteHandler implements Command {

  LikeDao likeDao;
  BoardDao boardDao;


  public LikeAutoDeleteHandler(LikeDao likeDao, BoardDao boardDao) {
    this.likeDao = likeDao;
    this.boardDao = boardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int no = (int)request.getAttribute("no"); //게시판 번호

    List<Like> likeList = likeDao.findAll();

    if (likeList == null) {
      return;
    }


    Board board = boardDao.findByNo(no);
    int whichBoard = board.getWhichBoard();


    for (int i = likeList.size() - 1; i >= 0; i--) {
      if (likeList.get(i).getWhichBoard() == whichBoard &&
          likeList.get(i).getLikeBoardNo() == board.getNo()) {
        likeDao.delete(i);

      }
    }
  } 
} 




