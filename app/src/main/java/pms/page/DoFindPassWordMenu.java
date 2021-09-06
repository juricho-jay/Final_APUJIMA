package pms.page;

import pms.handler.DoctorFindPassword;
import pms.handler.MemberFindPassword;
import util.Prompt;

public class DoFindPassWordMenu {

  MemberFindPassword memberFindPassword;
  DoctorFindPassword doctorFindPassword;


  void doFindPassWordMenu() {
    System.out.println("[PW 찾기] 페이지입니다. 선택해주세요.");
    System.out.println("1) 의사");
    System.out.println("2) 일반");
    try{
      int memNo = (Prompt.inputInt("PW 찾기> "));
      System.out.println();
      switch(memNo){
        case 1:
          doctorFindPassword.FindPassword();
          break;
        case 2:
          memberFindPassword.FindPassword();
          break;
      }
    }
    catch(Exception e) {
      System.out.println();
      System.out.println("번호를 잘못 입력하셨습니다. 번호를 다시 입력해 주세요. \n" + e.getMessage());
      System.out.println();
    }
  }

}
