package pms.handler;

import java.util.List;
import pms.domain.DoctorBoard;
import util.Prompt;

public class DoctorBoardAdd extends AbstractDoctorBoard{

  public DoctorBoardAdd(List<DoctorBoard> doctorBoardList) {
    super(doctorBoardList);
    // TODO Auto-generated constructor stub
  }

  public void add() {
    System.out.println("[새 게시글]");

    DoctorBoard doctorUserBoard = new DoctorBoard();

    doctorUserBoard.setNo(Prompt.inputInt("번호? ")); 
    doctorUserBoard.setTitle(Prompt.inputString("제목? "));
    doctorUserBoard.setContent(Prompt.inputString("내용? "));

    doctorBoardList.add(doctorUserBoard);
  }

}
