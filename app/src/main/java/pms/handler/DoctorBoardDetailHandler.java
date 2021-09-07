package pms.handler;

import java.util.List;
import pms.domain.DoctorBoard;
import util.Prompt;

public class DoctorBoardDetailHandler extends AbstractDoctorBoardHandler{

  public DoctorBoardDetailHandler(List<DoctorBoard> doctorBoardList) {
    super(doctorBoardList);
  }


  public void execute() {
    System.out.println("[상세보기] 페이지입니다.");
    System.out.println();
    int no = Prompt.inputInt("게시글 번호> ");

    DoctorBoard doctorBoard = findByNo(no);

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
    System.out.printf("좋아요: %d\n", doctorBoard.getLike());

    String likeNum = Prompt.inputString("[ 좋아요 (#: 👍🏻) / 넘어가기: Enter ]> ");
    if (likeNum.equals("#")) {
      doctorBoard.setLike(doctorBoard.getLike() + 1);
    } else {
      return;
    }
  }
}
