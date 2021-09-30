package pms.handler;

import java.util.List;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.Like;
import pms.domain.NoticeBoard;
import pms.domain.XBoard;

public class LikeAddCancelHandler extends AbstractLikeHandler {



  public LikeAddCancelHandler(List<Like> likeList, List<FreeBoard> freeBoardList, 
      List<DoctorBoard> doctorBoardList, List<NoticeBoard> noticeBoardList) {
    super(likeList, freeBoardList, doctorBoardList, noticeBoardList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int no = (int)request.getAttribute("num");
    String loginUser = AuthLoginHandler.getLoginUser().getId();

    int likeTotal = likeList.size(); 


    String whichBoard = (String)request.getAttribute("boardType");

    XBoard xxxBoard = new XBoard();

    if (whichBoard.equals("freeBoard")) {
      FreeBoard freeBoard = findByFreeBoardNo(no);
      xxxBoard = freeBoard;
    } else if (whichBoard.equals("doctorBoard")) {
      DoctorBoard doctorBoard = findByDoctorBoardNo(no);
      xxxBoard = doctorBoard;
    } else if (whichBoard.equals("noticeBoard")) {
      NoticeBoard noticeBoard = findByNoticeBoardNo(no);
      xxxBoard = noticeBoard;
    }

    if (likeTotal == 0) { // 전체 게시판에서 좋아요를 한 번도 누른 적 없는 경우
      Like like = new Like();
      like.setLikeNo(0);
      like.setLikeBoardNo(xxxBoard.getNo());
      like.setLikeWriter(xxxBoard.getWriter().getId());
      like.setLiker(AuthLoginHandler.getLoginUser());
      like.setWhichBoard(xxxBoard.getWhichBoard());
      System.out.println("게시글에 좋아요를 눌렀습니다.");
      likeList.add(like);
      return;

    } else if (likeTotal != 0) { // likeList[0]가 존재하는 경우


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
        Like like2 = new Like();
        like2.setLikeNo(likeList.size());
        like2.setLikeBoardNo(xxxBoard.getNo());
        like2.setLikeWriter(xxxBoard.getWriter().getId());
        like2.setLiker(AuthLoginHandler.getLoginUser());
        like2.setWhichBoard(xxxBoard.getWhichBoard());
        System.out.println("게시글에 좋아요를 눌렀습니다.");
        likeList.add(like2);
        return;

      } else {
        for (int k = 0; k < likeList.size(); k++) {
          if (likeList.get(k).getLikeBoardNo() == xxxBoard.getNo() && 
              likeList.get(k).getWhichBoard().equals(xxxBoard.getWhichBoard()) &&
              likeList.get(k).getLiker().getId().equals(loginUser)) {
            Like l = findByLikeNo(k);
            likeList.remove(l);
            System.out.println("좋아요를 취소했습니다.");
            break;
          }
        }
      }
    }
  } 
}




