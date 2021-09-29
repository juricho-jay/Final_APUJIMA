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

    if (doctorBoardList.size() == 0) {
      DoctorBoard.lastIndex = 1;
      doctorBoard.setNo(DoctorBoard.lastIndex);
    } else {
      if(DoctorBoard.lastIndex != doctorBoardList.size()) {

        DoctorBoard.lastIndex = doctorBoardList.get(doctorBoardList.size()-1).getNo();
        doctorBoard.setNo(++DoctorBoard.lastIndex);

      } else {
        doctorBoard.setNo(++DoctorBoard.lastIndex);
      }
    }

    while(true) {

      doctorBoard.setTitle(Prompt.inputString("제목> "));
      if (doctorBoard.getTitle().trim().equals("")) {
        System.out.println("제목이 공백입니다. 다시 입력해주세요.");
      }
      else {
        break;
      }
    }
    while(true) {
      doctorBoard.setContent(Prompt.inputString("내용> "));
      if (doctorBoard.getContent().trim().equals("")) {
        System.out.println("내용이 공백입니다. 다시 입력해주세요.");
      } else {
        break;
      }
    }

    doctorBoard.setWriter(AuthLoginHandler.getLoginUser());
    doctorBoard.setRegisteredDate(new Date(System.currentTimeMillis()));
    doctorBoard.setWhichBoard("doctor");

    if(AuthLoginHandler.getLoginUser().getCount() < 10) {
      System.out.println("포인트가 부족하여 게시글을 작성할 수 없습니다.");
      return;
    }
    else {
      AuthLoginHandler.getLoginUser().setCount(AuthLoginHandler.getLoginUser().getCount()-10);
      System.out.println("게시글을 작성하여 10포인트가 사용되었습니다.");
    }
    doctorBoardList.add(doctorBoard);
    System.out.println("게시글이 등록되었습니다.");
  }

}
