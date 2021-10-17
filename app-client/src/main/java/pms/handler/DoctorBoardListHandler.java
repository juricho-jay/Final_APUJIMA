package pms.handler;

import java.util.List;
import pms.dao.DoctorBoardDao;
import pms.domain.DoctorBoard;

public class DoctorBoardListHandler implements Command {

  DoctorBoardDao doctorBoardDao;

  public DoctorBoardListHandler(DoctorBoardDao doctorBoardDao) {
    this.doctorBoardDao = doctorBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 목록] 페이지입니다.");
    System.out.println();

    List<DoctorBoard> doctorBoardList = doctorBoardDao.findAll();

    if (doctorBoardList == null) {
      System.out.println("작성된 게시물이 없습니다.");
      return;
    }


    for (DoctorBoard doctorBoard : doctorBoardList) {
      System.out.printf("%d, %s, %s, %s, %d\n",
          doctorBoard.getNo(),
          doctorBoard.getTitle(),
          doctorBoard.getWriter().getId(), //우리는 name이 아니라 id를 가져옴
          doctorBoard.getRegisteredDate(),
          doctorBoard.getViewCount());
    }
  }
}
