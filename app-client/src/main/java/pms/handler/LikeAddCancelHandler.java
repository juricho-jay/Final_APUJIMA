package pms.handler;

import java.util.HashMap;
import java.util.List;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.Like;
import pms.domain.NoticeBoard;
import pms.domain.XBoard;
import request.RequestAgent;

public class LikeAddCancelHandler implements Command {

  RequestAgent requestAgent;


  public LikeAddCancelHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int no = (int)request.getAttribute("no");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    String loginUser = AuthLoginHandler.getLoginUser().getId();


    Like like = new Like();

    String whichBoard = (String)request.getAttribute("boardType");

    XBoard xxxBoard = new XBoard();

    if (whichBoard.equals("freeBoard")) {
      requestAgent.request("freeBoard.selectOne", params);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 번호의 게시판이 없습니다!");
        return;
      }

      FreeBoard freeBoard = requestAgent.getObject(FreeBoard.class);
      xxxBoard = freeBoard;
    } else if (whichBoard.equals("doctorBoard")) {
      requestAgent.request("doctorBoard.selectOne", params);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 번호의 게시판이 없습니다!");
        return;
      }

      DoctorBoard doctorBoard = requestAgent.getObject(DoctorBoard.class);
      xxxBoard = doctorBoard;
    } else if (whichBoard.equals("noticeBoard")) {
      requestAgent.request("noticeBoard.selectOne", params);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 번호의 게시판이 없습니다!");
        return;
      }

      NoticeBoard noticeBoard = requestAgent.getObject(NoticeBoard.class);
      xxxBoard = noticeBoard;
    }

    requestAgent.request("like.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      like.setLikeNo(0);
      like.setLikeBoardNo(xxxBoard.getNo());
      like.setLikeWriter(xxxBoard.getWriter().getId());
      like.setLiker(AuthLoginHandler.getLoginUser());
      like.setWhichBoard(xxxBoard.getWhichBoard());

      requestAgent.request("like.insert", like);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("좋아요 저장 실패!");
        return;
      }

    } else if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) { // likeList[0]가 존재하는 경우

      List<Like> likeList = (List<Like>) requestAgent.getObjects(Like.class);

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

        requestAgent.request("like.insert", like);
        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("좋아요 저장 실패!");
          return;
        }

        return;
      } else {
        for (int k = 0; k < likeList.size(); k++) {
          if (likeList.get(k).getLikeBoardNo() == xxxBoard.getNo() && 
              likeList.get(k).getWhichBoard().equals(xxxBoard.getWhichBoard()) &&
              likeList.get(k).getLiker().getId().equals(loginUser)) {

            HashMap<String,String> deleteIndex = new HashMap<>();
            deleteIndex.put("deleteIndex", String.valueOf(k));

            requestAgent.request("like.delete", deleteIndex);

            if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
              System.out.println("좋아요 취소 실패!");
              return;
            }

            System.out.println("좋아요를 취소했습니다.");
            break;
          }
        }
      }
    }
  } 
}




