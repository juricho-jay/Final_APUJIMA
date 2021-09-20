package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.DoctorBoard;
import util.Prompt;

public class DoctorBoardAddHandler extends AbstractDoctorBoardHandler{

  public DoctorBoardAddHandler(List<DoctorBoard> doctorBoardList) {
    super(doctorBoardList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[글쓰기] 페이지입니다.");
    System.out.println();
    DoctorBoard doctorBoard = new DoctorBoard();

    doctorBoard.setNo(DoctorBoard.lastIndex++);
    doctorBoard.setTitle(Prompt.inputString("제목> "));
    doctorBoard.setContent(Prompt.inputString("내용> "));

    doctorBoard.setWriter(AuthLoginHandler.getLoginUser());
    doctorBoard.setRegisteredDate(new Date(System.currentTimeMillis()));

    doctorBoardList.add(doctorBoard);
  }

}
