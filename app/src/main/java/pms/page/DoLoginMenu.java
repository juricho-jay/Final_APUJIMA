package pms.page;

import pms.handler.DoctorLogin;
import pms.handler.MemberLogin;
import util.Prompt;

public class DoLoginMenu {

  DoctorLogin doctorLogin;
  MemberLogin memberLogin;

  void doLoginMenu() {
    System.out.println("[로그인] 페이지입니다.");
    System.out.println("원하시는 메뉴를 선택해주세요.");
    System.out.println();
    System.out.println("1) 의사 로그인");
    System.out.println("2) 일반회원 로그인");
    System.out.println("3) 뒤로가기");

    int LoginNo = (Prompt.inputInt("번호> "));
    System.out.println();

    switch(LoginNo) {
      case 1: 
        doctorLogin.login();
        //        while(true) {
        //          int status = doctorMemberHandler.login();
        //          if (status == 1) {
        //            LoginPage();
        //            return;
        //          } else if (status == 0) {
        //            return;
        //          }
        //        }

      case 2:
        memberLogin.login();
        //        while(true) {
        //          int status1 = memberHandler.login();
        //          if(status1 == 1) {
        //            LoginPage();
        //            return;
        //          } else if (status1 == 0) {
        //            return;
        //          }
        //        }

      case 3:
        return;
      default: 
        System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
    }
  }

}
