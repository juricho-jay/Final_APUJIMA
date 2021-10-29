package apus.handler;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BoardDao;
import apus.dao.LikeDao;
import apus.domain.Board;
import apus.domain.Like;

public class LikeAddCancelHandler implements Command {

  LikeDao likeDao;
  BoardDao boardDao;
  SqlSession sqlSession;


  public LikeAddCancelHandler(LikeDao likeDao, BoardDao boardDao, SqlSession sqlSession) {
    this.likeDao = likeDao;
    this.boardDao = boardDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int no = (int)request.getAttribute("no");

    String loginUser = AuthLoginHandler.getLoginUser().getId();

    Like like = new Like();

    Board board = boardDao.findByNo(no);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    List<Like> likeList = likeDao.findAll();

    if(likeList.size() == 0) {
      like.setLikeBoard(board);
      like.setLiker(AuthLoginHandler.getLoginUser());
      likeDao.insert(like);
      sqlSession.commit();

    } else { // likeList[0]가 존재하는 경우

      // 먼저 내가 좋아요를 누른 적이 있는지 확인
      int myLike = 0;

      for (int i = 0; i < likeList.size(); i++) {
        if (likeList.get(i).getLikeBoard().getNo() == board.getNo() && 
            likeList.get(i).getLiker().getId().equals(loginUser)) {
          myLike++;
        }
      }

      if (myLike == 0) {
        like.setLikeBoard(board);
        like.setLiker(AuthLoginHandler.getLoginUser());

        System.out.println("게시글에 좋아요를 눌렀습니다.");

        likeDao.insert(like);
        sqlSession.commit();

        return;
      } else {
        for (int i = 0; i < likeList.size(); i++) {
          if (likeList.get(i).getLikeBoard().getNo() == board.getNo() && 
              likeList.get(i).getLiker().getId().equals(loginUser)) {

            likeDao.delete(board.getNo(), AuthLoginHandler.getLoginUser().getNo());

            System.out.println("좋아요를 취소했습니다.");
            break;
          }
        }
        sqlSession.commit();
      }
    }
  } 
}