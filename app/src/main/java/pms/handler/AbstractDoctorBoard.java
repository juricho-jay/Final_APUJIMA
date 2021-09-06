package pms.handler;

import java.util.List;
import pms.domain.DoctorBoard;

public class AbstractDoctorBoard {

  List<DoctorBoard> doctorBoardList; 

  public AbstractDoctorBoard(List<DoctorBoard> doctorBoardList) {
    this.doctorBoardList = doctorBoardList; // => ?? 생성자 초기화
  }

  protected DoctorBoard findByNo(int no) {
    for (DoctorBoard doctorBoard : doctorBoardList) {
      if (doctorBoard.getNo() == no) {
        return doctorBoard;
      }
    }
    return null;
  }


}
