package pms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import Menu.Menu;
import Menu.MenuGroup;
import pms.domain.CounselingMember;
import pms.domain.DoctorBoard;
import pms.domain.DoctorMember;
import pms.domain.FreeBoard;
import pms.domain.Medicine;
import pms.domain.Member;
import pms.domain.NoticeBoard;
import pms.handler.AuthLoginHandler;
import pms.handler.AuthLogoutHandler;
import pms.handler.AuthUserInfoHandler;
import pms.handler.Command;
import pms.handler.CounselingMemberAddHandler;
import pms.handler.CounselingMemberDoctorListHandler;
import pms.handler.CounselingMemberMyListHandler;
import pms.handler.DoctorAdd;
import pms.handler.DoctorBoardAddHandler;
import pms.handler.DoctorBoardDeleteHandler;
import pms.handler.DoctorBoardDetailHandler;
import pms.handler.DoctorBoardListHandler;
import pms.handler.DoctorBoardSearchHandler;
import pms.handler.DoctorBoardUpdateHandler;
import pms.handler.DoctorFindId;
import pms.handler.DoctorFindPassword;
import pms.handler.DoctorLogin;
import pms.handler.DoctorMemberListHandler;
import pms.handler.FreeBoardAddHandler;
import pms.handler.FreeBoardDeleteHandler;
import pms.handler.FreeBoardDetailHandler;
import pms.handler.FreeBoardListHandler;
import pms.handler.FreeBoardSearchHandler;
import pms.handler.FreeBoardUpdateHandler;
import pms.handler.IntroMenu;
import pms.handler.MedicineAddHandler;
import pms.handler.MedicineDeleteHandler;
import pms.handler.MedicineDetailHandler;
import pms.handler.MedicineListHandler;
import pms.handler.MedicineSearchHandler;
import pms.handler.MedicineUpdateHandler;
import pms.handler.MemberAddHandler;
import pms.handler.MemberFindId;
import pms.handler.MemberFindPassword;
import pms.handler.MemberListHandler;
import pms.handler.MemberLogin;
import pms.handler.NoticeBoardAddHandler;
import pms.handler.NoticeBoardDeleteHandler;
import pms.handler.NoticeBoardDetailHandler;
import pms.handler.NoticeBoardListHandler;
import pms.handler.NoticeBoardSearchHandler;
import pms.handler.NoticeBoardUpdateHandler;
import util.Prompt;

public class Main {
  List<Member> memberList = new LinkedList<>();
  List<CounselingMember> counselingMemberList = new LinkedList<>();
  List<DoctorMember> doctormemberList = new LinkedList<>();
  List<Medicine> medicineList = new LinkedList<>();
  List<FreeBoard> freeBoardList = new LinkedList<>();
  List<NoticeBoard> noticeBoardList = new LinkedList<>();
  List<DoctorBoard> doctorBoardList = new LinkedList<>();

  DoctorLogin doctorLogin = new DoctorLogin(doctormemberList);
  DoctorAdd doctorAdd = new DoctorAdd(doctormemberList);
  DoctorFindId doctorFindId = new DoctorFindId(doctormemberList);
  DoctorFindPassword doctorFindPassword = new DoctorFindPassword(doctormemberList);



  MemberLogin memberLogin = new MemberLogin(memberList);
  MemberAddHandler memberAdd = new MemberAddHandler(memberList);
  MemberFindId memberFindId = new MemberFindId(memberList);
  MemberFindPassword memberFindPassword = new MemberFindPassword(memberList);


  FreeBoardListHandler FreeboardList = new FreeBoardListHandler(freeBoardList);


  //MedicineAddHandler medicineAdd = new MedicineAddHandler(medicineList);
  //MedicineListHandler medicinelist = new MedicineListHandler(medicineList);
  //MedicineSearchHandler medicineSearch = new MedicineSearchHandler(medicineList);


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

  public Main() {
    commandMap.put("/member/add", new MemberAddHandler(memberList));
    commandMap.put("/member/list", new MemberListHandler(memberList));


    commandMap.put("/counselingMember/list", new DoctorMemberListHandler(memberList));
    commandMap.put("/counselingMember/add", new CounselingMemberAddHandler(counselingMemberList));
    commandMap.put("/counselingMember/myList", new CounselingMemberMyListHandler(counselingMemberList));
    commandMap.put("/counselingMember/doctorList", new CounselingMemberDoctorListHandler(counselingMemberList));
    // 바로 위에꺼 상담신청 이력

    commandMap.put("/noticeBoard/add", new NoticeBoardAddHandler(noticeBoardList));
    commandMap.put("/noticeBoard/list", new NoticeBoardListHandler(noticeBoardList));
    commandMap.put("/noticeBoard/detail", new NoticeBoardDetailHandler(noticeBoardList));
    commandMap.put("/noticeBoard/update", new NoticeBoardUpdateHandler(noticeBoardList));
    commandMap.put("/noticeBoard/delete", new NoticeBoardDeleteHandler(noticeBoardList));
    commandMap.put("/noticeBoard/search", new NoticeBoardSearchHandler(noticeBoardList));


    commandMap.put("/freeBoard/add", new FreeBoardAddHandler(freeBoardList));
    commandMap.put("/freeBoard/list", new FreeBoardListHandler(freeBoardList));
    commandMap.put("/freeBoard/detail", new FreeBoardDetailHandler(freeBoardList));
    commandMap.put("/freeBoard/update", new FreeBoardUpdateHandler(freeBoardList));
    commandMap.put("/freeBoard/delete", new FreeBoardDeleteHandler(freeBoardList));
    commandMap.put("/freeBoard/search", new FreeBoardSearchHandler(freeBoardList));


    commandMap.put("/doctorBoard/add", new DoctorBoardAddHandler(doctorBoardList));
    commandMap.put("/doctorBoard/list", new DoctorBoardListHandler(doctorBoardList));
    commandMap.put("/doctorBoard/detail", new DoctorBoardDetailHandler(doctorBoardList));
    commandMap.put("/doctorBoard/update", new DoctorBoardUpdateHandler(doctorBoardList));
    commandMap.put("/doctorBoard/delete", new DoctorBoardDeleteHandler(doctorBoardList));
    commandMap.put("/doctorBoard/search", new DoctorBoardSearchHandler(doctorBoardList));

    commandMap.put("/medicine/add", new MedicineAddHandler(medicineList));
    commandMap.put("/medicine/list", new MedicineListHandler(medicineList));
    commandMap.put("/medicine/detail", new MedicineDetailHandler(medicineList));
    commandMap.put("/medicine/update", new MedicineUpdateHandler(medicineList));
    commandMap.put("/medicine/delete", new MedicineDeleteHandler(medicineList));
    commandMap.put("/medicine/search", new MedicineSearchHandler(medicineList));




    commandMap.put("/auth/login", new AuthLoginHandler(memberList));
    commandMap.put("/auth/logout", new AuthLogoutHandler());
    commandMap.put("/auth/userInfo", new AuthUserInfoHandler(memberList));

    commandMap.put("/intro", new IntroMenu());
    //    commandMap.put("/community/freeBoard", new FreeBoardHashMap(freeBoardList));
  }



  void service() {

    loadMembers();

    createMainMenu().execute();
    Prompt.close();

    saveMembers();
  }

  private void loadMembers() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("member.data"))) {

      memberList.addAll((List<Member>) in.readObject());

      System.out.println("멤버 데이터 로딩 완료!");

    } catch (Exception e) {
      System.out.println("파일에서 멤버 데이터를 읽어 오는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveMembers() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("member.data"))) {

         out.writeObject(memberList);

      System.out.println("멤버 데이터 저장 완료!");

    } catch (Exception e) {
      System.out.println("멤버 데이터를 파일에 저장 중 오류 발생!");
      e.printStackTrace();
    }
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


    mainMenuGroup.add(new MenuItem("로그인", Menu.ACCESS_LOGOUT, "/auth/login"));
    mainMenuGroup.add(new MenuItem("내정보", Menu.ACCESS_GENERAL, "/auth/userInfo"));
    mainMenuGroup.add(new MenuItem("로그아웃", Menu.ACCESS_GENERAL, "/auth/logout"));

    mainMenuGroup.add(new MenuItem("소개", Menu.ACCESS_LOGOUT, "/intro"));
    mainMenuGroup.add(createMedicineMenu());
    mainMenuGroup.add(createCounselingMenu());
    mainMenuGroup.add(createMemberMenu());
    mainMenuGroup.add(createNoticeMenu());
    mainMenuGroup.add(createFreeBoardMenu());
    mainMenuGroup.add(createDoctorBoardMenu());



    //    mainMenuGroup.add(createCommunityMenu());


    return mainMenuGroup;
  }


  private Menu createFreeBoardMenu() {
    MenuGroup freeBoardMenu = new MenuGroup("APUs 자유게시판");

    freeBoardMenu.add(new MenuItem("글쓰기", Menu.ACCESS_GENERAL, "/freeBoard/add"));
    freeBoardMenu.add(new MenuItem("목록", "/freeBoard/list"));
    freeBoardMenu.add(new MenuItem("상세보기", "/freeBoard/detail"));
    freeBoardMenu.add(new MenuItem("변경", Menu.ACCESS_GENERAL, "/freeBoard/update"));
    freeBoardMenu.add(new MenuItem("삭제", Menu.ACCESS_GENERAL, "/freeBoard/delete"));
    freeBoardMenu.add(new MenuItem("검색", "/freeBoard/search"));
    return freeBoardMenu;
  }

  private Menu createNoticeMenu() {
    MenuGroup noticeMenu = new MenuGroup("공지사항");

    noticeMenu.add(new MenuItem("글쓰기", Menu.ACCESS_ADMIN, "/notice/add"));
    noticeMenu.add(new MenuItem("목록", "/notice/list"));
    noticeMenu.add(new MenuItem("상세보기", "/notice/detail"));
    noticeMenu.add(new MenuItem("변경", Menu.ACCESS_ADMIN, "/notice/update"));
    noticeMenu.add(new MenuItem("삭제", Menu.ACCESS_ADMIN, "/notice/delete"));
    noticeMenu.add(new MenuItem("검색", "/notice/search"));
    return noticeMenu;
  }

  private Menu createDoctorBoardMenu() {
    MenuGroup doctorBoardMenu = new MenuGroup("HEALER 지식in");

    doctorBoardMenu.add(new MenuItem("글쓰기", Menu.ACCESS_GENERAL, "/doctorBoard/add"));
    doctorBoardMenu.add(new MenuItem("목록", "/doctorBoard/list"));
    doctorBoardMenu.add(new MenuItem("상세보기", "/doctorBoard/detail"));
    doctorBoardMenu.add(new MenuItem("변경", Menu.ACCESS_GENERAL, "/doctorBoard/update"));
    doctorBoardMenu.add(new MenuItem("삭제", Menu.ACCESS_GENERAL, "/doctorBoard/delete"));
    doctorBoardMenu.add(new MenuItem("검색", "/doctorBoard/search"));
    return doctorBoardMenu;
  }





  private Menu createMemberMenu() {
    MenuGroup memberMenu = new MenuGroup("회원", Menu.ACCESS_LOGOUT);

    memberMenu.add(new MenuItem("회원가입", Menu.ACCESS_LOGOUT, "/member/add"));
    memberMenu.add(new MenuItem("회원 목록", Menu.ACCESS_ADMIN, "/member/list"));

    return memberMenu;
  }

  private Menu createCounselingMenu() {
    MenuGroup memberMenu = new MenuGroup("HEALER");
    memberMenu.add(new MenuItem("의사 리스트", Menu.ACCESS_LOGOUT, "/counselingMember/list"));
    memberMenu.add(new MenuItem("상담신청", Menu.ACCESS_GENERAL, "/counselingMember/add"));
    memberMenu.add(new MenuItem("My 상담 목록", Menu.ACCESS_GENERAL, "/counselingMember/myList"));
    memberMenu.add(new MenuItem("Healer 상담 목록", Menu.ACCESS_DOCTOR, "/counselingMember/doctorList"));

    return memberMenu;
  }

  private Menu createMedicineMenu() {
    MenuGroup medicineMenu = new MenuGroup("약국");

    medicineMenu.add(new MenuItem("약품 목록", "/medicine/list"));
    medicineMenu.add(new MenuItem("약품 추가", Menu.ACCESS_ADMIN, "/medicine/add"));
    medicineMenu.add(new MenuItem("약품 수정", Menu.ACCESS_ADMIN, "/medicine/update"));
    medicineMenu.add(new MenuItem("약품 삭제", Menu.ACCESS_ADMIN, "/medicine/delete"));
    medicineMenu.add(new MenuItem("약품 검색",  "/medicine/search"));
    medicineMenu.add(new MenuItem("약품 상세보기", "/medicine/detail"));

    return medicineMenu;
  }
}