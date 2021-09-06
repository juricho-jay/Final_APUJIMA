package pms.page;

import pms.handler.DoctorAdd;
import pms.handler.MemberAdd;
import util.Prompt;

public class DoSignUpMenu {

  DoctorAdd doctorAdd;
  MemberAdd memberAdd;

  void doSignupMenu() {
    System.out.println("[회원가입] 페이지입니다. 선택해주세요");
    System.out.println();

    System.out.println("1) 의사 회원가입");
    System.out.println("2) 일반 회원가입");
    System.out.println("3) 뒤로가기");
    int signupNo = (Prompt.inputInt("번호> "));
    System.out.println();

    switch(signupNo) {
      case 1: 
        doctorAdd.add();
        break;
      case 2:
        memberAdd.add();
        break;
      case 3:
        return;
      default:
        System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
    }
  }

}
