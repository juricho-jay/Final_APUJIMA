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
    System.out.println("[글쓰기] 페이지입니다.");
    System.out.println();
    FreeBoard freeBoard = new FreeBoard();

    freeBoard.setNo(Prompt.inputInt("번호> "));
    freeBoard.setTitle(Prompt.inputString("제목> "));
    freeBoard.setContent(Prompt.inputString("내용> "));


    freeBoard.setWriter(AuthLoginHandler.getLoginUser());
    freeBoard.setRegisteredDate(new Date(System.currentTimeMillis()));

    freeBoardList.add(freeBoard);
  }

}
