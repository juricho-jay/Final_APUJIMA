package pms.handler;

import java.util.List;
import pms.domain.Comment;
import pms.domain.DoctorBoard;
import pms.domain.Like;
import util.Prompt;

public class DoctorBoardDetailHandler extends AbstractDoctorBoardHandler {
  List<DoctorBoard> doctorReportList;
  List<Comment> commentList;
  List<Like> likeList;


  public DoctorBoardDetailHandler(List<DoctorBoard> doctorBoardList, 
      List<DoctorBoard> doctorReportList, List<Comment> commentList, 
      List<Like> likeList) {
    super(doctorBoardList);
    this.doctorReportList = doctorReportList;
    this.commentList = commentList;
    this.likeList = likeList;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[상세보기] 페이지입니다.");
    System.out.println();
    int num = Prompt.inputInt("게시글 번호> ");
    DoctorBoard doctorBoard = findByNo(num);
    String loginUser = AuthLoginHandler.getLoginUser().getId();

    if (doctorBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", doctorBoard.getTitle());
    System.out.printf("내용: %s\n", doctorBoard.getContent());
    System.out.printf("작성자: %s\n", doctorBoard.getWriter().getId()); // 우리는 익명이기 때문에 Id로
    System.out.printf("등록일: %s\n", doctorBoard.getRegisteredDate());

    doctorBoard.setViewCount(doctorBoard.getViewCount() + 1);
    System.out.printf("조회수: %d\n", doctorBoard.getViewCount());

    String whichBoard = doctorBoard.getWhichBoard();

    if (likeList.size() == 0) {
      System.out.print("[좋아요 ♡ :");
    }

    for (int i = 0; i < likeList.size(); i++) {
      if (likeList.get(i).getLikeBoardNo() == doctorBoard.getNo() && 
          likeList.get(i).getWhichBoard().equals(whichBoard) &&
          likeList.get(i).getLiker().getId().equals(AuthLoginHandler.getLoginUser().getId())) {
        System.out.print("[좋아요 ♥ :");
        break;
      } else if (i == (likeList.size() - 1)) {
        System.out.print("[좋아요 ♡ :");
        break;
      }
    }

    int count = 0;
    for (int j = 0; j < likeList.size(); j++) {
      if (likeList.get(j).getLikeBoardNo() != 0) {
        if (likeList.get(j).getLikeBoardNo() == doctorBoard.getNo() && 
            likeList.get(j).getWhichBoard().equals(whichBoard)) {
          count++;
        }
      }   
    }

    System.out.printf(" %d]\n", count);


    System.out.println();
    System.out.println("[댓글]");
    for (Comment comment : commentList) {
      if (comment.getCommentBoardNo() != 0) {
        if (comment.getCommentBoardNo() == doctorBoard.getNo() 
            && comment.getWhichBoard().equals(whichBoard)) {
          System.out.printf("%d, %s, %s\n",
              comment.getNo(),
              comment.getCommenter(),
              comment.getCommentContent());
        }

      }   
    }

    System.out.println();
    request.setAttribute("num", num);
    request.setAttribute("boardType", "doctorBoard");

    while(true) {

      String status = Prompt.inputString("[좋아요 (#: ♡) / 신고하기(!: 🚨) /\n"
          + "댓글달기(@: 💬) / 넘어가기: Enter]> ");
      if (status.equals("#")) {
        request.getRequestDispatcher("/like/addCancel").forward(request);
        return;

      } else if (status.equals("@")) {
        request.getRequestDispatcher("/comment/add").forward(request);
        return;

      } else if (status.equals("!")) {
        doctorBoard.setReason(Prompt.inputString("신고 사유를 작성해 주세요> "));
        doctorReportList.add(doctorBoard);
        doctorBoard.setRequester(loginUser);
        System.out.println("신고 접수가 완료되었습니다. 깨끗한 게시판 문화를 만드는데 도움을 주셔서 감사합니다!");
        break;
      }
      else if (status.equals("")){
        break;
      } else {
        System.out.println("메뉴에 맞는 명령어를 입력해 주세요.");
        continue;
      }
    }

    if (doctorBoard.getWriter().getId().equals(loginUser)) {
      request.setAttribute("num", num);
      while (true) {
        String input = Prompt.inputString("변경(U), 삭제(D), 이전(0)>");
        switch (input) {
          case "U":
          case "u":
            request.getRequestDispatcher("/doctorBoard/update").forward(request);
            return;
          case "D":
          case "d":
            request.getRequestDispatcher("/doctorBoard/delete").forward(request);
            return;
          case "0":
            return;
          default:
            System.out.println("명령어가 올바르지 않습니다!");
        }
      }
    }
  }
}
