package pms.handler;

import java.util.List;
import pms.dao.BoardDao;
import pms.dao.LikeDao;
import pms.domain.Board;
import pms.domain.Like;

public class LikeAddCancelHandler implements Command {

  LikeDao likeDao;
  BoardDao boardDao;


  public LikeAddCancelHandler(LikeDao likeDao, BoardDao boardDao) {
    this.likeDao = likeDao;
    this.boardDao = boardDao;
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

    if(likeList == null) {
      like.setLikeNo(0);
      like.setLikeBoardNo(board.getNo());
      like.setLikeWriter(board.getWriter().getId());
      like.setLiker(AuthLoginHandler.getLoginUser());
      like.setWhichBoard(board.getWhichBoard());
      likeDao.insert(like);

    } else { // likeList[0]가 존재하는 경우

      // 먼저 내가 좋아요를 누른 적이 있는지 확인
      int myLike = 0;

      for (int i = 0; i < likeList.size(); i++) {
        if (likeList.get(i).getLikeBoardNo() == board.getNo() && 
            likeList.get(i).getWhichBoard() == board.getWhichBoard() &&
            likeList.get(i).getLiker().getId().equals(loginUser)) {
          myLike++;
        }
      }

      if (myLike == 0) {
        like.setLikeNo(likeList.size());
        like.setLikeBoardNo(board.getNo());
        like.setLikeWriter(board.getWriter().getId());
        like.setLiker(AuthLoginHandler.getLoginUser());
        like.setWhichBoard(board.getWhichBoard());

        System.out.println("게시글에 좋아요를 눌렀습니다.");

        likeDao.insert(like);
        return;
      } else {
        for (int i = 0; i < likeList.size(); i++) {
          if (likeList.get(i).getLikeBoardNo() == board.getNo() && 
              likeList.get(i).getWhichBoard() == board.getWhichBoard() &&
              likeList.get(i).getLiker().getId().equals(loginUser)) {

            likeDao.delete(i);

            System.out.println("좋아요를 취소했습니다.");
            break;
          }
        }
      }
    }
  } 
}