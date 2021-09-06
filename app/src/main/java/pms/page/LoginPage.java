package pms.page;

public class LoginPage {

  public  void LoginPage() {
    while(true) {
      try {
        int menuNo = doMainMenu2();
        System.out.println();

        if (menuNo == 0) {
          System.out.println("정상적으로 로그아웃 되었습니다.");
          MemberHandler.LoginUser = null;
          System.out.println();

          return;
        } else if (menuNo == 1) { 
          doIntroMenu();
        } else if (menuNo == 2) {
          doMedicineMenu();
        } else if (menuNo == 3) {
          doHealerMenu();
        } else if (menuNo == 4) {
          doCommunityMenu();
        } else if (menuNo == 5) {
          memberHandler.displayLoginUser();
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
  }

}
