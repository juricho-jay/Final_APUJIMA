package pms.handler;

import java.util.List;
import pms.domain.DoctorBoard;

public class DoctorBoardListHandler extends AbstractDoctorBoardHandler{

  public DoctorBoardListHandler(List<DoctorBoard> doctorBoardList) {
    super(doctorBoardList);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[게시글 목록] 페이지입니다.");
    System.out.println();

    if(doctorBoardList.size() == 0) {
      System.out.println("등록된 게시글이 없습니다.");
    } else {
      for (DoctorBoard doctorBoard : doctorBoardList) {
        System.out.printf("%d, %s, %s, %s, %d, %d\n",
            doctorBoard.getNo(),
            doctorBoard.getTitle(),
            doctorBoard.getWriter().getId(), //우리는 name이 아니라 id를 가져옴
            doctorBoard.getRegisteredDate(),
            doctorBoard.getViewCount(),
            doctorBoard.getLike());
      }
    }
  }
}
