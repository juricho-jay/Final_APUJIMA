package pms.handler;

import java.util.List;
import pms.domain.DoctorBoard;
import util.Prompt;

public class DoctorBoardDetail extends AbstractDoctorBoard{

  public DoctorBoardDetail(List<DoctorBoard> doctorBoardList) {
    super(doctorBoardList);
  }

  DoctorBoardUpdate doctorBoardUpdate;
  DoctorBoardDelete doctorBoardDelete;

  public void detail() {
    System.out.println();
    System.out.println("[게시글 상세보기]");
    int no = Prompt.inputInt("번호? ");

    DoctorBoard doctorBoard = findByNo(no);

    if (doctorBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    while(true) {
      System.out.printf("제목: %s\n", doctorBoard.getTitle());
      System.out.printf("내용: %s\n", doctorBoard.getContent());
      System.out.println();
      if(doctorBoard.getLike() == 0) {
        System.out.printf("[1.♡ : %s )] ", doctorBoard.getLike());
      } else {
        System.out.printf("[1.❤ : %s )] ", doctorBoard.getLike());
      }
      System.out.print("[2.수정] ");
      System.out.print("[3.삭제] ");
      System.out.println("[0.뒤로가기]");
      int select = Prompt.inputInt("선택> ");
      System.out.println();
      if (select == 1) {
        doctorBoard.setLike(doctorBoard.getLike() +1);
        continue;
      } else if (select == 2) {
        doctorBoardUpdate.update();
      } else if (select == 3) {
        doctorBoardDelete.delete();
        return;
      } else if (select == 0) {
        System.out.println();
        return;
      }
    }

  }


}
