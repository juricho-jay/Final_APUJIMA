package Final_APUJIMA.handler;

import Final_APUJIMA.Main2;

public class Login {

  DoctorMemberHandler doctorMemberHandler;
  MemberHandler memberHandler;
  AuthLoginHandler authLoginHandler;
  Main2 main2;
  IntroHandler introHandler;
  MedicineHandler medicineHandler;
  HealerHandler healerHandler;
  CommunityHandler communityHandler;
  AuthUserInfoHandler authUserInfoHandler;



  //로그인 페이지 메서드
  public void LoginPage() {
    while(true) {
      try {
        int menuNo = main2.doMainMenu2();
        System.out.println();

        if (menuNo == 0) {
          System.out.println("정상적으로 로그아웃 되었습니다.");
          System.out.println();
          return;

        } else if (menuNo == 1) { 
          introHandler.doIntroMenu();
        } else if (menuNo == 2) {
          medicineHandler.doMedicineMenu();
        } else if (menuNo == 3) {
          healerHandler.doHealerMenu();
        } else if (menuNo == 4) {
          communityHandler.doCommunityMenu();
        } else if (menuNo == 5) {
          authUserInfoHandler.displayLoginUser();
        }
        else {
          System.out.println("메뉴 번호가 유효하지 않습니다.");
        }
        System.out.println();
      }catch(NumberFormatException e) {

        System.out.println();
        System.out.println("번호를 잘못 입력하셨습니다. 번호를 다시 입력해 주세요." + e.getMessage());
        System.out.println();
      }
    }

    //    Prompt.close();2
  }


}

