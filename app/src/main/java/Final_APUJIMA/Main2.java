package Final_APUJIMA;

import Final_APUJIMA.util.Prompt;

public class Main2 {

  public int doMainMenu2() {
    //    MemberHandler m = new MemberHandler(memberList);
    //    m.LoginInfo();

    System.out.println("원하시는 메뉴를 선택해 주세요.");
    System.out.println();

    System.out.println("1) 소개");
    System.out.println("2) 약국");
    System.out.println("3) HEALER");
    System.out.println("4) 커뮤니티");
    System.out.println("5) 내 정보");
    System.out.println("0) 로그아웃");
    return Prompt.inputInt("메뉴 번호> ");
  }

}
