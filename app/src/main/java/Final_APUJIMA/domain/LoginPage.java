package Final_APUJIMA.domain;

import java.util.LinkedList;
import java.util.List;
import Final_APUJIMA.handler.DoctorMemberHandler;
import Final_APUJIMA.handler.Medicine;
import Final_APUJIMA.handler.MemberHandler;
import Final_APUJIMA.util.Prompt;

public class LoginPage {
  static List<Member> memberList = new LinkedList<>();
  static List<DoctorMember> doctormemberList = new LinkedList<>();
  static MemberHandler memberHandler = new MemberHandler(memberList);
  static DoctorMemberHandler docmemberHandler = new DoctorMemberHandler(doctormemberList);
  
  public void LoginPage() {
  while(true) {
    int menuNo = doMainMenu();
    System.out.println();

    if (menuNo == 0) {
      System.out.println("APUJIMA에 방문해주셔서 감사합니다!\n다음에 또 만나요!");
      System.out.println();
      break;
    } else if (menuNo == 1) { 
      doIntroMenu();
    } else if (menuNo == 2) {
      doSignupMenu();
    } else if (menuNo == 3) {
      doFindIdMenu();
    } else if (menuNo == 4) {
      doFindPassWordMenu();
    } else if (menuNo == 5) {
      memberHandler.list();
    } else {
      System.out.println("메뉴 번호가 유효하지 않습니다.");
    }
    System.out.println();
  }

  Prompt.close();
}


//메인메뉴 메서드
static int doMainMenu() {
  MemberHandler m1= new MemberHandler(memberList);
  System.out.println( MemberHanlder.memberList.getId() + "님");
  System.out.println("[APUJIMA]에 오신 것을 환영합니다.");
  System.out.println("원하시는 메뉴를 선택해 주세요.");
  System.out.println();

  System.out.println("1) 소개");
  System.out.println("2) 약국");
  System.out.println("3) HEALER");
  System.out.println("4) 커뮤니티");
  System.out.println("0) 종료");
  return Prompt.inputInt("메뉴 번호> ");
}


//로그인메뉴 메서드
static void doIntroMenu() {
  System.out.println("[소개] 페이지입니다.");
  System.out.println("");
 
}
//회원가입메뉴 메서드
static void doMedicineMenu() {
  System.out.println("[약국] 페이지입니다. 선택해주세요");
  Medicine m = new Medicine();
  int a = Prompt.inputInt("1) 약 목록, 2) 약 찾기 >");
  if ( a == 1) {
    m.Mlist();
  }else if (a == 2) {
    m.MSearch();
  }
  else {
    System.out.println("잘못 선택하셨습니다 ");
  }
}

private static void doHealerMenu() {
  System.out.println("[HEALER] 페이지입니다. 선택해주세요");
  System.out.println("1)의사 리스트");
  System.out.println("2)의사 정보보기");
  System.out.println("3)상담 신청하기");
  
}

//ID찾기메뉴 메서드
private static void doFindIdMenu() {
  System.out.println("[커뮤니티] 페이지입니다. 선택해주세요.");
  System.out.println("0) 게시글 목록");
  System.out.println("1) 게시글 등록");
  System.out.println("2) 게시글 수정");
  System.out.println("3) 게시글 삭제");
  System.out.println("4) 답글 달기");
  int select = Prompt.inputInt("ID 찾기> ");

  if (select == 1) {
    docmemberHandler.FindId();
  }else {
    memberHandler.FindId();
  }

}



//관리자 메뉴는 바로 memberHandler.list() 호출

}
  

