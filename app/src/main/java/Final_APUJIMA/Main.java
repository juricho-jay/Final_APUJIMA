package Final_APUJIMA;

import java.util.LinkedList;
import java.util.List;
import Final_APUJIMA.domain.DoctorMember;
import Final_APUJIMA.domain.Member;
import Final_APUJIMA.handler.DoctorMemberHandler;
import Final_APUJIMA.handler.Medicine;
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
        boolean status = docmemberHandler.login();
        if (status == true) {
          LoginPage();
        }
        break;
      case 2:
        boolean status1 = memberHandler.login();
        if(status1 == true) {
          LoginPage();
        }
        
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
        docmemberHandler.FindPassword();
        break;
      case 2:
        memberHandler.FindPassword();
        break;
    }
  }

  //관리자 메뉴는 바로 memberHandler.list() 호출

  
  //로그인 페이지 메서드
  public static void LoginPage() {
    while(true) {
      int menuNo = doMainMenu2();
      System.out.println();

      if (menuNo == 0) {
        System.out.println("APUJIMA에 방문해주셔서 감사합니다!\n다음에 또 만나요!");
        System.out.println();
        break;
      } else if (menuNo == 1) { 
        doIntroMenu();
      } else if (menuNo == 2) {
        doMedicineMenu();
      } else if (menuNo == 3) {
        doHealerMenu();
      } else if (menuNo == 4) {
        doCommunityMenu();
      }
        else {
        System.out.println("메뉴 번호가 유효하지 않습니다.");
      }
      System.out.println();
    }

    Prompt.close();
  }


  //메인메뉴 메서드
  static int doMainMenu2() {
//    MemberHandler m = new MemberHandler(memberList);
//    m.LoginInfo();
    
    System.out.println("회원님, [APUJIMA]에 오신 것을 환영합니다.");
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
    System.out.println("We always with you, 모든 사람들이 아프지 않길 바라는 커뮤니티 ,APUJIMA입니다. ");
   
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
    DoctorMemberHandler d = new DoctorMemberHandler(doctormemberList);
    System.out.println("[HEALER] 페이지입니다. 선택해주세요");
    System.out.println("1)의사 리스트");
    System.out.println("2)상담 신청하기");
    
    int select = Prompt.inputInt("번호를 선택해주세요 > ");
    if (select == 1) {
    d.doctorList();  
    
    } else if (select == 2) {
      System.out.println("준비중..^^");
    }
    
    
    
  }

  //ID찾기메뉴 메서드
  private static void doCommunityMenu() {
    System.out.println("[커뮤니티] 페이지입니다. 선택해주세요.");
    System.out.println("0) 게시글 목록");
    System.out.println("1) 게시글 등록");
    System.out.println("2) 게시글 수정");
    System.out.println("3) 게시글 삭제");
    System.out.println("4) 답글 달기");
    int select = Prompt.inputInt("메뉴를 선택해주세요>  ");

    if (select == 1) {
      docmemberHandler.FindId();
    }else {
      memberHandler.FindId();
    }

  }



  //관리자 메뉴는 바로 memberHandler.list() 호출

  }
    




