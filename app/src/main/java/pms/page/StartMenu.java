package pms.page;

public class StartMenu {

  void startMenu() {
    while(true) {
      try {
        int menuNo = doMainMenu();
        System.out.println();

        if (menuNo == 0) {
          System.out.println("APUJIMA에 방문해주셔서 감사합니다!\n다음에 또 만나요!");
          System.out.println();
          break;
        } else if (menuNo == 1) { 
          doLoginMenu();
        } else if (menuNo == 2) {
          doSignupMenu();
        } else if (menuNo == 3) {
          doFindIdMenu();
        } else if (menuNo == 4) {
          doFindPassWordMenu();
        } else if (menuNo == 5) {
          doctorMemberHandler.doctorList();
        } else {
          System.out.println("메뉴 번호가 유효하지 않습니다.");
        }
        System.out.println();
      }

      catch(NumberFormatException e) {
        System.out.println();
        System.out.println("번호를 잘못 입력하셨습니다. 번호를 다시 입력해 주세요. \n" + e.getMessage());
        System.out.println();
      }
    }
  }

}
