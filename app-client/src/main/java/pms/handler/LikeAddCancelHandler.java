package pms.handler;

import java.util.List;
import pms.dao.DoctorBoardDao;
import pms.dao.FreeBoardDao;
import pms.dao.LikeDao;
import pms.dao.NoticeBoardDao;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.Like;
import pms.domain.NoticeBoard;
import pms.domain.XBoard;

public class LikeAddCancelHandler implements Command {

  LikeDao likeDao;
  FreeBoardDao freeBoardDao;
  DoctorBoardDao doctorBoardDao;
  NoticeBoardDao noticeBoardDao;


  public LikeAddCancelHandler(LikeDao likeDao, FreeBoardDao freeBoardDao,
      DoctorBoardDao doctorBoardDao, NoticeBoardDao noticeBoardDao) {
    this.likeDao = likeDao;
    this.freeBoardDao = freeBoardDao;
    this.doctorBoardDao = doctorBoardDao;
    this.noticeBoardDao = noticeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int no = (int)request.getAttribute("no");

    //    HashMap<String,String> params = new HashMap<>();
    //    params.put("no", String.valueOf(no));

    String loginUser = AuthLoginHandler.getLoginUser().getId();

    Like like = new Like();

    String whichBoard = (String)request.getAttribute("boardType");

    XBoard xxxBoard = new XBoard();

    if (whichBoard.equals("freeBoard")) {
      FreeBoard freeBoard = freeBoardDao.findByNo(no);

      if (freeBoard == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }
      xxxBoard = freeBoard;

    } else if (whichBoard.equals("doctorBoard")) {
      DoctorBoard doctorBoard = doctorBoardDao.findByNo(no);
      if (doctorBoard == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }

      xxxBoard = doctorBoard;
    } else if (whichBoard.equals("noticeBoard")) {
      NoticeBoard noticeBoard = noticeBoardDao.findByNo(no);
      if (noticeBoard == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }

      xxxBoard = noticeBoard;
    }

    List<Like> likeList = likeDao.findAll();

    if(likeList.size() == 0) {
      like.setLikeNo(0);
      like.setLikeBoardNo(xxxBoard.getNo());
      like.setLikeWriter(xxxBoard.getWriter().getId());
      like.setLiker(AuthLoginHandler.getLoginUser());
      like.setWhichBoard(xxxBoard.getWhichBoard());
      likeDao.insert(like);

    } else { // likeList[0]가 존재하는 경우

      // 먼저 내가 좋아요를 누른 적이 있는지 확인
      int myLike = 0;

      for (int i = 0; i < likeList.size(); i++) {
        if (likeList.get(i).getLikeBoardNo() == xxxBoard.getNo() && 
            likeList.get(i).getWhichBoard().equals(xxxBoard.getWhichBoard()) &&
            likeList.get(i).getLiker().getId().equals(loginUser)) {
          myLike++;
        }
      }

      if (myLike == 0) {
        like.setLikeNo(likeList.size());
        like.setLikeBoardNo(xxxBoard.getNo());
        like.setLikeWriter(xxxBoard.getWriter().getId());
        like.setLiker(AuthLoginHandler.getLoginUser());
        like.setWhichBoard(xxxBoard.getWhichBoard());

        System.out.println("게시글에 좋아요를 눌렀습니다.");

        likeDao.insert(like);
        return;
      } else {
        for (int k = 0; k < likeList.size(); k++) {
          if (likeList.get(k).getLikeBoardNo() == xxxBoard.getNo() && 
              likeList.get(k).getWhichBoard().equals(xxxBoard.getWhichBoard()) &&
              likeList.get(k).getLiker().getId().equals(loginUser)) {

            //            HashMap<String,String> deleteIndex = new HashMap<>();
            //            deleteIndex.put("deleteIndex", String.valueOf(k));

            //            requestAgent.request("like.delete", deleteIndex);
            likeDao.delete(k);

            System.out.println("좋아요를 취소했습니다.");
            break;
          }
        }
      }
    }
  } 
}