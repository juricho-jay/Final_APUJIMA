package pms.page;

import util.Prompt;

public class DoMainMenu {

  int doMainMenu() {
    System.out.println("[APUJIMA]에 오신 것을 환영합니다.");
    System.out.println("원하시는 메뉴를 선택해 주세요.");
    System.out.println();

    System.out.println("1) 로그인");
    System.out.println("2) 회원가입");
    System.out.println("3) ID 찾기");
    System.out.println("4) PW 찾기");
    System.out.println("5) 관리자 기능: 아이디 목록 보기");
    System.out.println("0) 종료");
    return Prompt.inputInt("메뉴 번호> ");
  }
}
