package pms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import Menu.Menu;
import Menu.MenuGroup;
import pms.domain.CounselingMember;
import pms.domain.DoctorMember;
import pms.domain.FreeBoard;
import pms.domain.Medicine;
import pms.domain.Member;
import pms.handler.Command;
import pms.handler.DoctorAdd;
import pms.handler.DoctorFindId;
import pms.handler.DoctorFindPassword;
import pms.handler.DoctorLogin;
import pms.handler.FreeBoardListHandler;
import pms.handler.IntroMenu;
import pms.handler.MedicineAddHandler;
import pms.handler.MedicineListHandler;
import pms.handler.MedicineSearchHandler;
import pms.handler.MemberAddHandler;
import pms.handler.MemberFindId;
import pms.handler.MemberFindPassword;
import pms.handler.MemberLogin;
import util.Prompt;

public class Main {
  List<Member> memberList = new LinkedList<>();
  List<CounselingMember> counselingmemberList = new LinkedList<>();
  List<DoctorMember> doctormemberList = new LinkedList<>();
  List<Medicine> medicineList = new LinkedList<>();
  List<FreeBoard> freeBoardList = new LinkedList<>();

  DoctorLogin doctorLogin = new DoctorLogin(doctormemberList);
  DoctorAdd doctorAdd = new DoctorAdd(doctormemberList);
  DoctorFindId doctorFindId = new DoctorFindId(doctormemberList);
  DoctorFindPassword doctorFindPassword = new DoctorFindPassword(doctormemberList);



  MemberLogin memberLogin = new MemberLogin(memberList);
  MemberAddHandler memberAdd = new MemberAddHandler(memberList);
  MemberFindId memberFindId = new MemberFindId(memberList);
  MemberFindPassword memberFindPassword = new MemberFindPassword(memberList);


  FreeBoardListHandler FreeboardList = new FreeBoardListHandler(freeBoardList);


  MedicineAddHandler medicineAdd = new MedicineAddHandler(medicineList);
  MedicineListHandler medicinelist = new MedicineListHandler(medicineList);
  MedicineSearchHandler medicineSearch = new MedicineSearchHandler(medicineList);


  IntroMenu intro = new IntroMenu();
  HashMap<String,Command> commandMap = new HashMap<>();

  class MenuItem extends Menu {
    String menuId;

    public MenuItem(String title, String menuId) {
      super(title);
      this.menuId = menuId;
    }

    public MenuItem(String title, int accessScope, String menuId) {
      super(title, accessScope);
      this.menuId = menuId;
    }

    @Override
    public void execute() {
      Command command = commandMap.get(menuId);
      command.execute();
    }

  }


  public static void main(String[] args) {
    Main main = new Main();
    main.service();
  }

  void service() {
    createMainMenu().execute();
    Prompt.close();
  }

  Menu createMainMenu() {
    System.out.println("\r\n"
        + "|￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣ |\r\n"
        + "|[APUJIMA]에 오신 것을 환영합니다.|\r\n"
        + "|＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ |\r\n"
        + "(\\__/) ||\r\n"
        + "(•ㅅ•).||\r\n"
        + "/ . . . .づ\r\n"
        + "");
    MenuGroup mainMenuGroup = new MenuGroup("WELCOME TO APUJIMA!");
    mainMenuGroup.setPrevMenuTitle("종료");

    MenuGroup nologin = new MenuGroup("비회원");
    mainMenuGroup.add(nologin);

    nologin.add(new Menu("소개") {
      @Override
      public void execute() {
        intro.doIntroMenu();
      }
    });

    MenuGroup nologinMedicine = new MenuGroup("약국");
    nologin.add(nologinMedicine);

    nologinMedicine.add(new Menu("의약품 소개") {
      @Override
      public void execute() {
        medicinelist.execute();;
      }
    });

    nologinMedicine.add(new Menu("의약품 검색") {
      @Override
      public void execute() {
        medicineSearch.execute();
      }
    });

    MenuGroup nologinHealer = new MenuGroup("힐러");
    nologin.add(nologinHealer);

    nologinHealer.add(new Menu("게시판 조회") {
      @Override
      public void execute() {
        //medicinelist.medicineList();;
      }
    });

    MenuGroup nologinCommunity = new MenuGroup("커뮤니티");
    nologin.add(nologinCommunity);

    nologinCommunity.add(new Menu("게시판 조회") {
      @Override
      public void execute() {
        FreeboardList.execute();
      }
    });




    MenuGroup login = new MenuGroup("로그인");
    mainMenuGroup.add(login);


    login.add(new Menu("로그인") {
      @Override
      public void execute() {
        memberLogin.execute();
      }
    });


    login.add(new Menu("ID 찾기") {
      @Override
      public void execute() {
        memberFindId.execute();;
      }
    });

    login.add(new Menu("PW 찾기") {
      @Override
      public void execute() {
        memberFindPassword.execute();;
      }
    });

    MenuGroup afterlogin = new MenuGroup("메인메뉴");
    login.add(afterlogin);

    afterlogin.add(new Menu("소개", Menu.ACCESS_GENERAL) {
      @Override
      public void execute() {
        intro.doIntroMenu();
      }
    });

    afterlogin.add(new Menu("커뮤니티", Menu.ACCESS_GENERAL) {
      @Override
      public void execute() {
        intro.doIntroMenu();
      }
    });




    MenuGroup singUp = new MenuGroup("회원가입");
    mainMenuGroup.add(singUp);


    singUp.add(new Menu("회원가입") {
      @Override
      public void execute() {
        memberAdd.execute();
      }
    });




    return mainMenuGroup;
  }


}





