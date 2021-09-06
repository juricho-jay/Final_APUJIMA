package pms.page;

import pms.handler.DoctorFindId;
import pms.handler.MemberFindId;
import util.Prompt;

public class DoFindIdMenu {

  MemberFindId memberFindId;
  DoctorFindId doctorFindId;


  void doFindIdMenu() {
    System.out.println("[ID 찾기] 페이지입니다. 선택해주세요.");
    System.out.println("1) 의사");
    System.out.println("2) 일반");
    int select = Prompt.inputInt("ID 찾기> ");
    System.out.println();

    if (select == 1) {
      doctorFindId.FindId();
      System.out.println();
    }else {
      memberFindId.FindId();
      System.out.println();
    }
  }
}
