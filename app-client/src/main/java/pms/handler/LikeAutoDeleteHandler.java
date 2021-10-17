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

public class LikeAutoDeleteHandler implements Command {

  LikeDao likeDao;
  FreeBoardDao freeBoardDao;
  DoctorBoardDao doctorBoardDao;
  NoticeBoardDao noticeBoardDao;


  public LikeAutoDeleteHandler(LikeDao likeDao, FreeBoardDao freeBoardDao,
      DoctorBoardDao doctorBoardDao, NoticeBoardDao noticeBoardDao) {
    this.likeDao = likeDao;
    this.freeBoardDao = freeBoardDao;
    this.doctorBoardDao = doctorBoardDao;
    this.noticeBoardDao = noticeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int no = (int)request.getAttribute("no"); //게시판 번호

    List<Like> likeList = likeDao.findAll();

    if (likeList == null) {
      return;
    }
    //    requestAgent.request("like.selectList", null);
    //
    //    HashMap<String,String> params = new HashMap<>();
    //    params.put("no", String.valueOf(no));

    String whichBoard = (String)request.getAttribute("boardType");

    if (whichBoard.equals("freeBoard")) {
      //      requestAgent.request("freeBoard.selectOne", params);
      //      FreeBoard freeBoard = requestAgent.getObject(FreeBoard.class);
      FreeBoard freeBoard = freeBoardDao.findByNo(no);
      String whichBoard2 = freeBoard.getWhichBoard();


      for (int i = likeList.size() - 1; i >= 0; i--) {
        if (likeList.get(i).getWhichBoard().equals(whichBoard2) &&
            likeList.get(i).getLikeBoardNo() == freeBoard.getNo()) {

          //          HashMap<String,String> deleteIndex = new HashMap<>();
          //          deleteIndex.put("deleteIndex", String.valueOf(i));
          //          requestAgent.request("like.delete", deleteIndex);

          likeDao.delete(i);

        }
      }

    } else if (whichBoard.equals("doctorBoard")) {
      //      requestAgent.request("doctorBoard.selectOne", params);
      //      DoctorBoard doctorBoard = requestAgent.getObject(DoctorBoard.class);
      DoctorBoard doctorBoard = doctorBoardDao.findByNo(no);
      String whichBoard2 = doctorBoard.getWhichBoard();

      for (int i = likeList.size() - 1; i >= 0; i--) {
        if (likeList.get(i).getWhichBoard().equals(whichBoard2) &&
            likeList.get(i).getLikeBoardNo() == doctorBoard.getNo()) {

          //          HashMap<String,String> deleteIndex = new HashMap<>();
          //          deleteIndex.put("deleteIndex", String.valueOf(i));
          //      requestAgent.request("like.delete", deleteIndex);
          likeDao.delete(i);

        }
      }

    } else if (whichBoard.equals("noticeBoard")) {
      //      requestAgent.request("noticeBoard.selectOne", params);
      //      NoticeBoard noticeBoard = requestAgent.getObject(NoticeBoard.class);
      NoticeBoard noticeBoard = noticeBoardDao.findByNo(no);
      String whichBoard2 = noticeBoard.getWhichBoard();

      for (int i = likeList.size() - 1; i >= 0; i--) {
        if (likeList.get(i).getWhichBoard().equals(whichBoard2) &&
            likeList.get(i).getLikeBoardNo() == noticeBoard.getNo()) {

          //          HashMap<String,String> deleteIndex = new HashMap<>();
          //          deleteIndex.put("deleteIndex", String.valueOf(i));
          //          requestAgent.request("like.delete", deleteIndex);
          likeDao.delete(i);

        }
      }

    }


  } 
}




