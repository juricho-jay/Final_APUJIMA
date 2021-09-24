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

    if (DoctorBoard.lastIndex == 1) {
      doctorBoard.setNo(DoctorBoard.lastIndex);
      DoctorBoard.lastIndex++;

    }else {
      doctorBoard.setNo(DoctorBoard.lastIndex++);
    }
    doctorBoard.setTitle(Prompt.inputString("제목> "));
    doctorBoard.setContent(Prompt.inputString("내용> "));
    doctorBoard.setWriter(AuthLoginHandler.getLoginUser());
    doctorBoard.setRegisteredDate(new Date(System.currentTimeMillis()));

    if(AuthLoginHandler.getLoginUser().getCount() < 10) {
      System.out.println("포인트가 부족하여 게시글을 작성할 수 없습니다.");
      return;
    }
    else {
      AuthLoginHandler.getLoginUser().setCount(AuthLoginHandler.getLoginUser().getCount()-10);
      System.out.println("게시글을 작성하여 10포인트가 사용되었습니다.");
    }
    doctorBoardList.add(doctorBoard);
  }

}
