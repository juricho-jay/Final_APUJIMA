package pms;

import java.util.LinkedList;
import java.util.List;
import Menu.Menu;
import Menu.MenuGroup;
import pms.domain.CounselingMember;
import pms.domain.DoctorMember;
import pms.domain.FreeBoard;
import pms.domain.Medicine;
import pms.domain.Member;
import pms.handler.DoctorAdd;
import pms.handler.DoctorFindId;
import pms.handler.DoctorFindPassword;
import pms.handler.DoctorLogin;
import pms.handler.FreeBoardList;
import pms.handler.IntroMenu;
import pms.handler.MedicineAdd;
import pms.handler.MedicineList;
import pms.handler.MedicineSearch;
import pms.handler.MemberAdd;
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
  MemberAdd memberAdd = new MemberAdd(memberList);
  MemberFindId memberFindId = new MemberFindId(memberList);
  MemberFindPassword memberFindPassword = new MemberFindPassword(memberList);


  FreeBoardList FreeboardList = new FreeBoardList(freeBoardList);


  MedicineAdd medicineAdd = new MedicineAdd(medicineList);
  MedicineList medicinelist = new MedicineList(medicineList);
  MedicineSearch medicineSearch = new MedicineSearch(medicineList);


  IntroMenu intro = new IntroMenu();


  public static void main(String[] args) {
    Main main = new Main();
    main.service();
  }

  void service() {
    createMenu().execute();
    Prompt.close();
  }

  Menu createMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    System.out.println("[APUJIMA]에 오신 것을 환영합니다.");
    System.out.println("원하시는 메뉴를 선택해 주세요.");
    System.out.println();

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
        medicinelist.medicineList();;
      }
    });

    nologinMedicine.add(new Menu("의약품 검색") {
      @Override
      public void execute() {
        medicineSearch.medicineSearch();
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
        FreeboardList.list();
      }
    });




    MenuGroup login = new MenuGroup("로그인");
    mainMenuGroup.add(login);

    login.add(new Menu("의사 로그인") {
      @Override
      public void execute() {
        doctorLogin.login();
      }
    });

    login.add(new Menu("일반 로그인") {
      @Override
      public void execute() {
        memberLogin.login();
      }
    });

    login.add(new Menu("의사회원 ID 찾기") {
      @Override
      public void execute() {
        doctorFindId.FindId();;
      }
    });

    login.add(new Menu("의사회원 PW 찾기") {
      @Override
      public void execute() {
        doctorFindPassword.FindPassword();;
      }
    });

    login.add(new Menu("일반회원 ID 찾기") {
      @Override
      public void execute() {
        memberFindId.FindId();;
      }
    });

    login.add(new Menu("일반회원 PW 찾기") {
      @Override
      public void execute() {
        memberFindPassword.FindPassword();;
      }
    });



    MenuGroup singUp = new MenuGroup("회원가입");
    mainMenuGroup.add(singUp);

    singUp.add(new Menu("의사 회원가입") {
      @Override
      public void execute() {
        doctorAdd.add();
      }
    });

    singUp.add(new Menu("일반 회원가입") {
      @Override
      public void execute() {
        memberLogin.login();
      }
    });




    return mainMenuGroup;
  }


}





