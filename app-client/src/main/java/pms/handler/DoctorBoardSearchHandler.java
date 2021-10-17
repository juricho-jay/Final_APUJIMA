package pms.handler;

import java.util.List;
import pms.dao.DoctorBoardDao;
import pms.domain.DoctorBoard;
import util.Prompt;

public class DoctorBoardSearchHandler implements Command {

  DoctorBoardDao doctorBoardDao;

  public DoctorBoardSearchHandler(DoctorBoardDao doctorBoardDao) {
    this.doctorBoardDao = doctorBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int count = 0;
    System.out.println("[지식인 게시글 검색] 페이지입니다.");
    System.out.println();
    String input = Prompt.inputString("검색어> ");

    List<DoctorBoard> doctorBoardList = doctorBoardDao.findByKeyword(input);

    if (doctorBoardList == null) {
      System.out.println("찾는 게시물이 없습니다.");
      return;
    }

    for (DoctorBoard doctorBoard : doctorBoardList) {
      if (!doctorBoard.getTitle().contains(input) &&
          !doctorBoard.getContent().contains(input) &&
          !doctorBoard.getWriter().getId().contains(input)) {
        continue;
      }
      count++;
      System.out.printf("%d, %s, %s, %s, %d\n",
          doctorBoard.getNo(),
          doctorBoard.getTitle(),
          doctorBoard.getWriter().getId(),
          doctorBoard.getRegisteredDate(),
          doctorBoard.getViewCount());
    }

    if (count == 0 ) {
      System.out.println("찾는 게시물이 없습니다.");
    }
  }
}
