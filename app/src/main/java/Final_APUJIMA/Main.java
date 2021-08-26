package Final_APUJIMA;

import java.util.LinkedList;
import java.util.List;
import Final_APUJIMA.domain.DoctorMember;
import Final_APUJIMA.domain.Member;
import Final_APUJIMA.handler.DoctorMemberHandler;
import Final_APUJIMA.handler.MemberHandler;
import Final_APUJIMA.util.Prompt;

public class Main {
  static List<Member> memberList = new LinkedList<>();
  static List<DoctorMember> doctormemberList = new LinkedList<>();
  static MemberHandler memberHandler = new MemberHandler(memberList);
  static DoctorMemberHandler docmemberHandler = new DoctorMemberHandler(doctormemberList);

  public static void main(String[] args) {

    while(true) {
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
        docmemberHandler.doctorList();
      } else {
        System.out.println("메뉴 번호가 유효하지 않습니다.");
      }
      System.out.println();
    }

    Prompt.close();
  }


  //메인메뉴 메서드
  static int doMainMenu() {
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


  //로그인메뉴 메서드
  static void doLoginMenu() {
    System.out.println("[로그인] 페이지입니다.");
    System.out.println("원하시는 메뉴를 선택해주세요.");
    System.out.println();
    System.out.println("1) 의사 로그인");
    System.out.println("2) 일반회원 로그인");
    System.out.println("3) 메인 페이지");

    int LoginNo = (Prompt.inputInt("번호> "));
    System.out.println();

    switch(LoginNo) {
      case 1: 
        docmemberHandler.login();
        break;
      case 2:
        memberHandler.login();
        break;
      case 3:
        return;
      default: 
        System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
    }
  }

  //회원가입메뉴 메서드
  static void doSignupMenu() {
    System.out.println("[회원가입] 페이지입니다. 선택해주세요");
    System.out.println();

    System.out.println("1) 의사 회원가입");
    System.out.println("2) 일반 회원가입");
    System.out.println("3) 뒤로가기");
    int signupNo = (Prompt.inputInt("번호> "));
    System.out.println();

    switch(signupNo) {
      case 1: 
        docmemberHandler.add();
        break;
      case 2:
        memberHandler.add();
        break;
      case 3:
        return;
      default:
        System.out.println("잘못된 번호입니다. 다시 입력해주세요.");

    }
  }

  //ID찾기메뉴 메서드
  private static void doFindIdMenu() {
    System.out.println("[ID 찾기] 페이지입니다. 선택해주세요.");
    System.out.println("1) 의사");
    System.out.println("2) 일반");
    int select = Prompt.inputInt("ID 찾기> ");

    if (select == 1) {
      docmemberHandler.FindId();
    }else {
      memberHandler.FindId();
    }

  }

  //PW찾기메뉴 메서드
  private static void doFindPassWordMenu() {
    System.out.println("[PW 찾기] 페이지입니다. 선택해주세요.");
    System.out.println("1) 의사 회원정보");
    System.out.println("2) 일반 회원정보");
    int memNo = (Prompt.inputInt("PW 찾기> "));
    System.out.println();
    switch(memNo){
      case 1:
        docmemberHandler.list();
        break;
      case 2:
        memberHandler.list();
        break;
    }
  }

  //관리자 메뉴는 바로 memberHandler.list() 호출

}

