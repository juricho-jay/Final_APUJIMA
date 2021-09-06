package pms.handler;

import java.util.List;
import pms.domain.DoctorBoard;
import util.Prompt;

public class DoctorBoardUpdate  extends AbstractDoctorBoard{

  public DoctorBoardUpdate(List<DoctorBoard> doctorBoardList) {
    super(doctorBoardList);
  }

  public void update() {
    System.out.println();
    System.out.println("[게시글 변경]");
    int no = Prompt.inputInt("번호? ");

    DoctorBoard doctorBoard = findByNo(no);


    if (doctorBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("제목(%s)? ",  doctorBoard.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s)? ", doctorBoard.getContent()));
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }

    doctorBoard.setTitle(title);
    doctorBoard.setContent(content);
    System.out.println("게시글을 변경하였습니다.");
    System.out.println();

  }

}
