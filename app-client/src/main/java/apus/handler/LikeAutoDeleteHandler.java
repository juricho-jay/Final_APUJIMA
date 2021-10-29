package apus.handler;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BoardDao;
import apus.dao.LikeDao;
import apus.domain.Board;
import apus.domain.Like;

public class LikeAutoDeleteHandler implements Command {

  LikeDao likeDao;
  BoardDao boardDao;
  SqlSession sqlSession;


  public LikeAutoDeleteHandler(LikeDao likeDao, BoardDao boardDao, SqlSession sqlSession) {
    this.likeDao = likeDao;
    this.boardDao = boardDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int no = (int)request.getAttribute("no"); //게시판 번호

    List<Like> likeList = likeDao.findAll();

    if (likeList == null) {
      return;
    }


    Board board = boardDao.findByNo(no);

    for (int i = likeList.size() - 1; i >= 0; i--) {
      if (likeList.get(i).getLikeBoard().getNo() == board.getNo()) {
        likeDao.autoDelete(board.getNo());
      }
    }

    sqlSession.commit();
  } 
} 




