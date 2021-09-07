package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.DoctorBoard;
import util.Prompt;

public class DoctorBoardAddHandler extends AbstractDoctorBoardHandler{

  public DoctorBoardAddHandler(List<DoctorBoard> doctorBoardList) {
    super(doctorBoardList);
    // TODO Auto-generated constructor stub
  }

  public void execute() {
    System.out.println("[새 게시글]");

    DoctorBoard doctorBoard = new DoctorBoard();

    doctorBoard.setNo(Prompt.inputInt("번호? ")); 
    doctorBoard.setTitle(Prompt.inputString("제목? "));
    doctorBoard.setContent(Prompt.inputString("내용? "));

    doctorBoard.setWriter(AuthLoginHandler.getLoginUser());
    doctorBoard.setRegisteredDate(new Date(System.currentTimeMillis()));

    doctorBoardList.add(doctorBoard);
  }

}
