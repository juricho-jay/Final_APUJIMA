package pms.handler;

import java.util.List;
import pms.domain.DoctorBoard;
import util.Prompt;

public class DoctorBoardSearchHandler extends AbstractDoctorBoardHandler {

  public DoctorBoardSearchHandler(List<DoctorBoard> doctorBoardList) {
    super(doctorBoardList);
  }


  @Override
  public void execute(CommandRequest request) {
    int count = 0;
    System.out.println("[지식인 게시글 검색] 페이지입니다.");
    System.out.println();
    String input = Prompt.inputString("검색어> ");

    for (DoctorBoard doctorBoard : doctorBoardList) {
      if (!doctorBoard.getTitle().contains(input) &&
          !doctorBoard.getContent().contains(input) &&
          !doctorBoard.getWriter().getId().contains(input))
      {
        continue;
      }
      count++;
      System.out.printf("%d, %s, %s, %s, %d, %d\n",
          doctorBoard.getNo(),
          doctorBoard.getTitle(),
          doctorBoard.getWriter().getId(),
          doctorBoard.getRegisteredDate(),
          doctorBoard.getViewCount(),
          doctorBoard.getLike());

    }
    if (count == 0 ) {
      System.out.println("찾는 게시물이 없습니다.");
    }
  }
}
