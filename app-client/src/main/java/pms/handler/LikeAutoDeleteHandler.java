package pms.handler;

import java.util.HashMap;
import java.util.List;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.Like;
import pms.domain.NoticeBoard;
import request.RequestAgent;

public class LikeAutoDeleteHandler implements Command {

  RequestAgent requestAgent;

  public LikeAutoDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int no = (int)request.getAttribute("no"); //게시판 번호

    requestAgent.request("like.selectList", null);

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return;
    }

    List<Like> likeList = (List<Like>) requestAgent.getObjects(Like.class);


    String whichBoard = (String)request.getAttribute("boardType");


    if (whichBoard.equals("freeBoard")) {
      requestAgent.request("freeBoard.selectOne", params);
      FreeBoard freeBoard = requestAgent.getObject(FreeBoard.class);
      String whichBoard2 = freeBoard.getWhichBoard();


      for (int i = likeList.size() - 1; i >= 0; i--) {
        if (likeList.get(i).getWhichBoard().equals(whichBoard2) &&
            likeList.get(i).getLikeBoardNo() == freeBoard.getNo()) {

          HashMap<String,String> deleteIndex = new HashMap<>();
          deleteIndex.put("deleteIndex", String.valueOf(i));

          requestAgent.request("like.delete", deleteIndex);

          if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
            System.out.println("좋아요 취소 실패!");
            return;
          }
        }
      }

    } else if (whichBoard.equals("doctorBoard")) {
      requestAgent.request("doctorBoard.selectOne", params);
      DoctorBoard doctorBoard = requestAgent.getObject(DoctorBoard.class);
      String whichBoard2 = doctorBoard.getWhichBoard();

      for (int i = likeList.size() - 1; i >= 0; i--) {
        if (likeList.get(i).getWhichBoard().equals(whichBoard2) &&
            likeList.get(i).getLikeBoardNo() == doctorBoard.getNo()) {

          HashMap<String,String> deleteIndex = new HashMap<>();
          deleteIndex.put("deleteIndex", String.valueOf(i));

          requestAgent.request("like.delete", deleteIndex);

          if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
            System.out.println("좋아요 취소 실패!");
            return;
          }

        }
      }

    } else if (whichBoard.equals("noticeBoard")) {
      requestAgent.request("noticeBoard.selectOne", params);
      NoticeBoard noticeBoard = requestAgent.getObject(NoticeBoard.class);
      String whichBoard2 = noticeBoard.getWhichBoard();

      for (int i = likeList.size() - 1; i >= 0; i--) {
        if (likeList.get(i).getWhichBoard().equals(whichBoard2) &&
            likeList.get(i).getLikeBoardNo() == noticeBoard.getNo()) {

          HashMap<String,String> deleteIndex = new HashMap<>();
          deleteIndex.put("deleteIndex", String.valueOf(i));

          requestAgent.request("like.delete", deleteIndex);

          if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
            System.out.println("좋아요 취소 실패!");
            return;
          }
        }
      }

    }


  } 
}




