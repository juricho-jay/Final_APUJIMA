package Final_APUJIMA;

import java.util.LinkedList;
import java.util.List;
import Final_APUJIMA.domain.CounselingMember;
import Final_APUJIMA.domain.DoctorMember;
import Final_APUJIMA.domain.Member;
import Final_APUJIMA.handler.CounselingMemberHandler;
import Final_APUJIMA.handler.DoctorMemberHandler;
import Final_APUJIMA.handler.FreeBoardMenuHandler;
import Final_APUJIMA.handler.MedicineHandler;
import Final_APUJIMA.handler.MemberHandler;
import Final_APUJIMA.util.Prompt;

public class Main {
  static List<Member> memberList = new LinkedList<>();
  static List<CounselingMember> counselingmemberList = new LinkedList<>();
  static List<DoctorMember> doctormemberList = new LinkedList<>();
  static MemberHandler memberHandler = new MemberHandler(memberList);
  static DoctorMemberHandler doctorMemberHandler = new DoctorMemberHandler(doctormemberList);
  static CounselingMemberHandler counselingMemberHandler = new CounselingMemberHandler(counselingmemberList);
  static FreeBoardMenuHandler freeboardHandler = new FreeBoardMenuHandler();

  public static void main(String[] args) {

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
    System.out.println("3) 뒤로가기");

    int LoginNo = (Prompt.inputInt("번호> "));
    System.out.println();

    switch(LoginNo) {


      case 1: 
        while(true) {
          int status = doctorMemberHandler.login();
          if (status == 1) {
            LoginPage();
            return;
          } else if (status == 0) {
            return;
          }
        }

      case 2:
        while(true) {
          int status1 = memberHandler.login();
          if(status1 == 1) {
            LoginPage();
            return;
          } else if (status1 == 0) {
            return;
          }
        }

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
        doctorMemberHandler.add();
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
    System.out.println();

    if (select == 1) {
      doctorMemberHandler.FindId();
      System.out.println();
    }else {
      memberHandler.FindId();
      System.out.println();
    }

  }

  //PW찾기메뉴 메서드
  private static void doFindPassWordMenu() {
    System.out.println("[PW 찾기] 페이지입니다. 선택해주세요.");
    System.out.println("1) 의사");
    System.out.println("2) 일반");
    try{
      int memNo = (Prompt.inputInt("PW 찾기> "));
      System.out.println();
      switch(memNo){
        case 1:
          doctorMemberHandler.FindPassword();
          break;
        case 2:
          memberHandler.FindPassword();
          break;
      }
    }
    catch(Exception e) {
      System.out.println();
      System.out.println("번호를 잘못 입력하셨습니다. 번호를 다시 입력해 주세요. \n" + e.getMessage());
      System.out.println();
    }
  }

  //관리자 메뉴는 바로 memberHandler.list() 호출


  //로그인 페이지 메서드
  public static void LoginPage() {
    while(true) {
      try {
        int menuNo = doMainMenu2();
        System.out.println();

        if (menuNo == 0) {
          System.out.println("정상적으로 로그아웃 되었습니다.");
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


    //    Prompt.close();2
  }


  //메인메뉴 메서드
  static int doMainMenu2() {
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


  //로그인메뉴 메서드
  static void doIntroMenu() {
    while(true) {
      System.out.println("[소개] 페이지입니다.");
      System.out.println("We always with you, 모든 사람들이 아프지 않길 바라는 커뮤니티, "
          + "APUJIMA입니다.");
      System.out.println();

      System.out.println("0) 뒤로가기");
      int num = Prompt.inputInt("번호> ");
      if(num == 0)
        return;
    }
  }
  //회원가입메뉴 메서드
  static void doMedicineMenu() {
    System.out.println("[약국] 페이지입니다. 선택해주세요");
    MedicineHandler medicine = new MedicineHandler();
    System.out.println("1) 약 목록");
    System.out.println("2) 약 찾기");
    int select = Prompt.inputInt("선택> ");
    if (select == 1) {
      medicine.Mlist();
    } else if (select == 2) {
      medicine.SearchM();
    } else {
      System.out.println("잘못 선택하셨습니다 ");
    }
  }

  private static void doHealerMenu() {
    DoctorMemberHandler doctorHandler = new DoctorMemberHandler(doctormemberList);
    System.out.println("[HEALER] 페이지입니다. 선택해주세요");
    System.out.println();
    System.out.println("1) 의사 리스트");
    System.out.println("2) 상담 신청하기");

    int select = Prompt.inputInt("선택> ");
    if (select == 1) {
      doctorHandler.doctorList();  
    } else if (select == 2) {
      counselingMemberHandler.counselingadd();
    }
  }

  //ID찾기메뉴 메서드
  private static void doCommunityMenu() {
    System.out.println("[커뮤니티] 페이지입니다. 선택해주세요.");
    System.out.println();
    System.out.println("1) 공지사항");
    System.out.println("2) APUs 자유게시판");
    System.out.println("3) Healer 지식in");
    System.out.println("0) 뒤로가기");
    int select = Prompt.inputInt("메뉴를 선택해주세요>  ");

    if (select == 1) {
      NoticeMenu();
    } else if(select == 2){
      FreeboardMenu();
    } else if(select == 3) {
      knowledgeMenu();
    } else if(select == 0) {
      doMainMenu2();
    }

  }
  //공지사항 method
  static void NoticeMenu() {
    System.out.println("[공지사항] 페이지입니다.");
  }
  //자유게시판 관련 method.
  private static void FreeboardMenu() {
    System.out.println("[APUs 자유게시판] 페이지입니다.");
    System.out.println();
    System.out.println("1) 글쓰기");
    System.out.println("2) 글목록");
    System.out.println("3) 글상세");
    System.out.println("4) 글수정");
    System.out.println("5) 글삭제");
    System.out.println("0) 뒤로가기");
    int select = Prompt.inputInt("선택> ");
    try {
      if (select == 1) {
        freeboardHandler.add();
      } else if (select == 2){
        freeboardHandler.list();
      } else if (select == 3) {
        freeboardHandler.detail();
      } else if (select == 4) {
        freeboardHandler.update();
      } else if (select == 5) {
        freeboardHandler.delete();
      } else if (select == 0) {
        doCommunityMenu();
      }
    }catch(Exception e) {

    }

  }
  //Healer 지식in method.
  static void knowledgeMenu() {
    System.out.println("[Healer 지식in] 페이지입니다.");
    System.out.println();
    System.out.println("1) 의사");
    System.out.println("2) 일반");
  }

  //관리자 메뉴는 바로 memberHandler.list() 호출

}





