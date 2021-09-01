package Final_APUJIMA;

import java.util.LinkedList;
import java.util.List;
import Final_APUJIMA.domain.CounselingMember;
import Final_APUJIMA.domain.DoctorMember;
import Final_APUJIMA.domain.Member;
import Final_APUJIMA.handler.AuthLoginHandler;
import Final_APUJIMA.handler.CounselingMemberHandler;
import Final_APUJIMA.handler.DoctorMemberHandler;
import Final_APUJIMA.handler.FinderHandler;
import Final_APUJIMA.handler.FreeBoardHandler;
import Final_APUJIMA.handler.MemberHandler;
import Final_APUJIMA.handler.SignUp;
import Final_APUJIMA.util.Prompt;

public class Main {
  List<Member> memberList = new LinkedList<>();
  List<CounselingMember> counselingmemberList = new LinkedList<>();
  List<DoctorMember> doctormemberList = new LinkedList<>();
  DoctorMemberHandler doctorMemberHandler = new DoctorMemberHandler(doctormemberList);
  CounselingMemberHandler counselingMemberHandler = new CounselingMemberHandler(counselingmemberList);
  MemberHandler memberHandler = new MemberHandler(memberList);
  FreeBoardHandler freeboardHandler = new FreeBoardHandler();
  AuthLoginHandler authLoginHandler = new AuthLoginHandler(memberList);
  SignUp signUp = new SignUp();
  FinderHandler finderHandler = new FinderHandler();

  public static void main(String[] args) {
    Main main = new Main();
    main.greetingMenu();

  }

  void greetingMenu() {
    while(true) {
      try {
        int menuNo = doMainMenu();
        System.out.println();

        if (menuNo == 0) {
          System.out.println("APUJIMA에 방문해주셔서 감사합니다!\n다음에 또 만나요!");
          System.out.println();
          break;
        } else if (menuNo == 1) { 
          authLoginHandler.login(); // 로그인
        } else if (menuNo == 2) {
          signUp.doSignup(); // 회원가입
        } else if (menuNo == 3) {
          finderHandler.findId(); // 아이디 찾기
        } else if (menuNo == 4) {
          finderHandler.findPassword(); // 비밀번호 찾기
        } else if (menuNo == 5) {
          doctorMemberHandler.doctorList(); // 관리자로 써주라
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


  //메인메뉴 메서드
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

  //메인메뉴 메서드






  //공지사항 method

  //관리자 메뉴는 바로 memberHandler.list() 호출

}