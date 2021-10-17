package pms.handler;

import java.util.List;
import pms.dao.CommentDao;
import pms.dao.DoctorBoardDao;
import pms.dao.FreeBoardDao;
import pms.dao.NoticeBoardDao;
import pms.domain.Comment;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.NoticeBoard;
import pms.domain.XBoard;
import util.Prompt;

public class CommentAddHandler implements Command {

  CommentDao commentDao;
  FreeBoardDao freeBoardDao;
  DoctorBoardDao doctorBoardDao;
  NoticeBoardDao noticeBoardDao;

  public CommentAddHandler(CommentDao commentDao, FreeBoardDao freeBoardDao,
      DoctorBoardDao doctorBoardDao, NoticeBoardDao noticeBoardDao) {
    this.commentDao = commentDao;
    this.freeBoardDao = freeBoardDao;
    this.doctorBoardDao = doctorBoardDao;
    this.noticeBoardDao = noticeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[댓글 달기]");
    System.out.println();
    int no = (int)request.getAttribute("no");
    // xxxBoard디테일 핸들러에서 입력한 게시판 번호

    //xxxBoard 명령어 사용하기 위에 params에 게시판 번호 넣기
    //    HashMap<String,String> params = new HashMap<>();
    //    params.put("no", String.valueOf(no));

    Comment comment = new Comment(); 

    String whichBoard = (String)request.getAttribute("boardType");

    XBoard xxxBoard = new XBoard();

    if (whichBoard.equals("freeBoard")) {
      FreeBoard freeBoard = freeBoardDao.findByNo(no);

      if (freeBoard == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }
      comment.setWhichBoard("free");
      xxxBoard = freeBoard;

    } else if (whichBoard.equals("doctorBoard")) {
      DoctorBoard doctorBoard = doctorBoardDao.findByNo(no);

      if (doctorBoard == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }
      comment.setWhichBoard("doctor");
      xxxBoard = doctorBoard;

    } else if (whichBoard.equals("noticeBoard")) {
      NoticeBoard noticeBoard = noticeBoardDao.findByNo(no);

      if (noticeBoard == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }

      comment.setWhichBoard("notice");
      xxxBoard = noticeBoard;
    }

    List<Comment> commentList = commentDao.findAll();

    if (commentList == null) {
      comment.setNo(1);
      comment.setCommentNo(0);
      comment.setCommentBoardNo(xxxBoard.getNo());
      comment.setCommentWriter(xxxBoard.getWriter().getId());
      comment.setCommenter(AuthLoginHandler.getLoginUser().getId());
      System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
      comment.setCommentContent(Prompt.inputString("댓글 내용> "));

      commentDao.insert(comment);
      System.out.println("댓글이 등록되었습니다.");
      return;
    }

    int count = 0; //기존에 존재하는 댓글 갯수
    int conditionLast = 0; //마지막 댓글의 번호(게시물마다 1로 리셋되는 번호)
    for (int i = 0; i < commentList.size(); i++) {
      if (commentList.get(i).getCommentBoardNo() == xxxBoard.getNo() 
          && commentList.get(i).getWhichBoard().equals(xxxBoard.getWhichBoard())) {
        count++;
        conditionLast = commentList.get(i).getNo();
      }
    }

    if (conditionLast != count) {
      comment.setNo(++conditionLast);
    } else {
      comment.setNo(++count);
    }
    comment.setCommentNo(comment.getNo() - 1);
    comment.setCommentBoardNo(xxxBoard.getNo());
    comment.setCommentWriter(xxxBoard.getWriter().getId());
    comment.setCommenter(AuthLoginHandler.getLoginUser().getId());
    System.out.printf("-%s-\n", AuthLoginHandler.getLoginUser().getId());
    comment.setCommentContent(Prompt.inputString("댓글 내용> "));

    commentDao.insert(comment);
    System.out.println("댓글이 등록되었습니다.");
    return;
  }
}
