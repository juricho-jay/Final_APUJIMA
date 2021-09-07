package pms.handler;

import java.util.List;
import pms.domain.DoctorBoard;
import util.Prompt;

public class DoctorBoardSearchHandler extends AbstractDoctorBoardHandler {

  public DoctorBoardSearchHandler(List<DoctorBoard> doctorBoardList) {
    super(doctorBoardList);
  }


  @Override
  public void execute() {
    System.out.println("[지식인 게시글 검색] 페이지입니다.");
    System.out.println();
    String input = Prompt.inputString("검색어> ");

    for (DoctorBoard doctorBoard : doctorBoardList) {
      if (!doctorBoard.getTitle().contains(input) &&
          !doctorBoard.getContent().contains(input) &&
          !doctorBoard.getWriter().getId().contains(input)) {
        continue;
      }
      System.out.printf("%d, %s, %s, %s, %d, %d\n",
          doctorBoard.getNo(),
          doctorBoard.getTitle(),
          doctorBoard.getWriter().getId(),
          doctorBoard.getRegisteredDate(),
          doctorBoard.getViewCount(),
          doctorBoard.getLike());
    }
  }
}
