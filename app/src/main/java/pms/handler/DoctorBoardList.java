package pms.handler;

import java.util.List;
import pms.domain.DoctorBoard;

public class DoctorBoardList extends AbstractDoctorBoard{

  public DoctorBoardList(List<DoctorBoard> doctorBoardList) {
    super(doctorBoardList);
    // TODO Auto-generated constructor stub
  }

  public void list() {
    System.out.println("[게시글 목록]");
    for (DoctorBoard doctorBoard : doctorBoardList) {
      System.out.printf("%d, %s, %s,\n", 
          doctorBoard.getNo(), 
          doctorBoard.getTitle(), 
          doctorBoard.getContent());
    }
  }

}
