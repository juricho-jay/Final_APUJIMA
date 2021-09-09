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
import pms.handler.AdminApprovalHandler;
import pms.handler.AdminUpdateHandler;
import pms.handler.AuthLoginHandler;
import pms.handler.AuthLogoutHandler;
import pms.handler.AuthUserInfoHandler;
import pms.handler.Command;
import pms.handler.CounselingMemberAddHandler;
import pms.handler.CounselingMemberDoctorListHandler;
import pms.handler.CounselingMemberMyListHandler;
import pms.handler.DoctorBoardAddHandler;
import pms.handler.DoctorBoardDeleteHandler;
import pms.handler.DoctorBoardDetailHandler;
import pms.handler.DoctorBoardListHandler;
import pms.handler.DoctorBoardSearchHandler;
import pms.handler.DoctorBoardUpdateHandler;
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
import pms.handler.MedicineListHandler;
import pms.handler.MedicineRequestHandler;
import pms.handler.MedicineSearchHandler;
import pms.handler.MedicineUpdateHandler;
import pms.handler.MemberAddHandler;
import pms.handler.MemberListHandler;
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
  List<Medicine> requestList = new LinkedList<>();
  List<FreeBoard> freeBoardList = new LinkedList<>();
  List<NoticeBoard> noticeBoardList = new LinkedList<>();
  List<DoctorBoard> doctorBoardList = new LinkedList<>();
  List<FreeBoard> reportList = new LinkedList<>(); 


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
    commandMap.put("/admin/approval", new AdminApprovalHandler(requestList, medicineList));
    commandMap.put("/admin/update", new AdminUpdateHandler(requestList, medicineList));
    // commandMap.put("/admin/list", new 구현예정);

    commandMap.put("/intro", new IntroMenu());

    commandMap.put("/medicine/add", new MedicineAddHandler(medicineList));
    commandMap.put("/medicine/request", new MedicineRequestHandler(requestList));
    commandMap.put("/medicine/list", new MedicineListHandler(medicineList));
    commandMap.put("/medicine/update", new MedicineUpdateHandler(medicineList));
    commandMap.put("/medicine/delete", new MedicineDeleteHandler(medicineList));
    commandMap.put("/medicine/search", new MedicineSearchHandler(medicineList));

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
    commandMap.put("/freeBoard/detail", new FreeBoardDetailHandler(freeBoardList, reportList));
    commandMap.put("/freeBoard/update", new FreeBoardUpdateHandler(freeBoardList));
    commandMap.put("/freeBoard/delete", new FreeBoardDeleteHandler(freeBoardList));
    commandMap.put("/freeBoard/search", new FreeBoardSearchHandler(freeBoardList));

    commandMap.put("/doctorBoard/add", new DoctorBoardAddHandler(doctorBoardList));
    commandMap.put("/doctorBoard/list", new DoctorBoardListHandler(doctorBoardList));
    commandMap.put("/doctorBoard/detail", new DoctorBoardDetailHandler(doctorBoardList));
    commandMap.put("/doctorBoard/update", new DoctorBoardUpdateHandler(doctorBoardList));
    commandMap.put("/doctorBoard/delete", new DoctorBoardDeleteHandler(doctorBoardList));
    commandMap.put("/doctorBoard/search", new DoctorBoardSearchHandler(doctorBoardList));


    commandMap.put("/member/add", new MemberAddHandler(memberList));
    commandMap.put("/member/list", new MemberListHandler(memberList));

    commandMap.put("/auth/login", new AuthLoginHandler(memberList));
    commandMap.put("/auth/logout", new AuthLogoutHandler());
    commandMap.put("/auth/userInfo", new AuthUserInfoHandler(memberList));



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

  private void loadMedicine() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("medicine.data"))) {

      medicineList.addAll((List<Medicine>) in.readObject());

      System.out.println("약품 데이터 로딩 완료!");

    } catch (Exception e) {
      System.out.println("파일에서 약품 데이터를 읽어 오는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveMedicine() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("medicine.data"))) {

      out.writeObject(medicineList);

      System.out.println("약품 데이터 저장 완료!");

    } catch (Exception e) {
      System.out.println("약품 데이터를 파일에 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }
  private void loadNoticeBoard() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("noticeBoard.data"))) {

      noticeBoardList.addAll((List<NoticeBoard>) in.readObject());

      System.out.println("공지사항 데이터 로딩 완료!");

    } catch (Exception e) {
      System.out.println("파일에서 공지사항 데이터를 읽어 오는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveNoticeBoard() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("noticeBoard.data"))) {

      out.writeObject(noticeBoardList);

      System.out.println("공지사항 데이터 저장 완료!");

    } catch (Exception e) {
      System.out.println("공지사항 데이터를 파일에 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void loadFreeBoard() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("freeBoard.data"))) {

      freeBoardList.addAll((List<FreeBoard>) in.readObject());

      System.out.println("자유게시판 데이터 로딩 완료!");

    } catch (Exception e) {
      System.out.println("파일에서 자유게시판 데이터를 읽어 오는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveFreeBoard() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("freeBoard.data"))) {

      out.writeObject(freeBoardList);

      System.out.println("자유게시판 데이터 저장 완료!");

    } catch (Exception e) {
      System.out.println("자유게시판 데이터를 파일에 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void loadDoctorMember() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("doctorMember.data"))) {

      doctormemberList.addAll((List<DoctorMember>) in.readObject());

      System.out.println("의사멤버 데이터 로딩 완료!");

    } catch (Exception e) {
      System.out.println("파일에서 의사멤버 데이터를 읽어 오는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveDoctorMember() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("doctorMember.data"))) {

      out.writeObject(doctormemberList);

      System.out.println("의사멤버 데이터 저장 완료!");

    } catch (Exception e) {
      System.out.println("의사멤버 데이터를 파일에 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void loadDoctorBoard() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("doctorBoard.data"))) {

      doctorBoardList.addAll((List<DoctorBoard>) in.readObject());

      System.out.println("HEALER지식in 데이터 로딩 완료!");

    } catch (Exception e) {
      System.out.println("파일에서 HEALER지식in 데이터를 읽어 오는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveDoctorBoard() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("doctorBoard.data"))) {

      out.writeObject(doctorBoardList);

      System.out.println("HEALER지식in 데이터 저장 완료!");

    } catch (Exception e) {
      System.out.println("HEALER지식in 데이터를 파일에 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void loadCounselingMember() {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("counselingMember.data"))) {

      counselingMemberList.addAll((List<CounselingMember>) in.readObject());

      System.out.println("상담신청 데이터 로딩 완료!");

    } catch (Exception e) {
      System.out.println("파일에서 상담신청 데이터를 읽어 오는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveCounselingMember() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("counselingMember.data"))) {

      out.writeObject(counselingMemberList);

      System.out.println("상담신청 데이터 저장 완료!");

    } catch (Exception e) {
      System.out.println("상담신청 데이터를 파일에 저장 중 오류 발생!");
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

    mainMenuGroup.add(createApprovalMenu());
    mainMenuGroup.add(new MenuItem("소개", "/intro"));
    mainMenuGroup.add(createMedicineMenu());
    mainMenuGroup.add(createCounselingMenu());

    mainMenuGroup.add(createCommunityMenu());

    mainMenuGroup.add(new MenuItem("로그인", Menu.ACCESS_LOGOUT, "/auth/login"));
    mainMenuGroup.add(new MenuItem("내정보", Menu.ACCESS_GENERAL, "/auth/userInfo"));
    mainMenuGroup.add(new MenuItem("로그아웃", Menu.ACCESS_GENERAL, "/auth/logout"));

    mainMenuGroup.add(createMemberMenu());


    //    mainMenuGroup.add(createCommunityMenu());


    return mainMenuGroup;
  }


  private Menu createApprovalMenu() {
    MenuGroup approvalMenu = new MenuGroup("승인 관리", Menu.ACCESS_ADMIN);

    approvalMenu.add(new MenuItem("승인 허가", Menu.ACCESS_ADMIN, "/admin/approval")); // AdminApprovalHandler
    approvalMenu.add(new MenuItem("약품 수정", Menu.ACCESS_ADMIN, "/admin/update"));
    approvalMenu.add(new MenuItem("승인 내역", Menu.ACCESS_ADMIN,"/admin/list")); // AdminListHandler
    return approvalMenu;
  }


  //소개는 바로 intro 연결

  private Menu createMedicineMenu() {
    MenuGroup medicineMenu = new MenuGroup("약국");

    medicineMenu.add(new MenuItem("약품 목록", "/medicine/list"));
    medicineMenu.add(new MenuItem("약품 추가", Menu.ACCESS_ADMIN, "/medicine/add"));
    medicineMenu.add(new MenuItem("약품 등록 요청", Menu.ACCESS_DOCTOR, "/medicine/request"));
    medicineMenu.add(new MenuItem("약품 수정", Menu.ACCESS_ADMIN, "/medicine/update"));
    medicineMenu.add(new MenuItem("약품 삭제", Menu.ACCESS_ADMIN, "/medicine/delete"));
    medicineMenu.add(new MenuItem("약품 검색", "/medicine/search"));

    return medicineMenu;
  }

  private Menu createCounselingMenu() {
    MenuGroup memberMenu = new MenuGroup("HEALER");
    memberMenu.add(new MenuItem("의사 리스트", Menu.ACCESS_LOGOUT, "/counselingMember/list"));
    memberMenu.add(new MenuItem("상담신청", Menu.ACCESS_GENERAL, "/counselingMember/add"));
    memberMenu.add(new MenuItem("My 상담 목록", Menu.ACCESS_GENERAL, "/counselingMember/myList"));
    memberMenu.add(new MenuItem("Healer 상담 목록", Menu.ACCESS_DOCTOR, "/counselingMember/doctorList"));

    return memberMenu;
  }

  private Menu createCommunityMenu() {
    MenuGroup communityMenu = new MenuGroup("커뮤니티");

    communityMenu.add(createNoticeMenu());
    communityMenu.add(createFreeBoardMenu());
    communityMenu.add(createDoctorBoardMenu());

    return communityMenu;
  }

  private Menu createNoticeMenu() {
    MenuGroup noticeMenu = new MenuGroup("공지사항");

    noticeMenu.add(new MenuItem("글쓰기", Menu.ACCESS_ADMIN, "/noticeBoard/add"));
    noticeMenu.add(new MenuItem("목록", "/noticeBoard/list"));
    noticeMenu.add(new MenuItem("상세보기", "/noticeBoard/detail"));
    noticeMenu.add(new MenuItem("변경", Menu.ACCESS_ADMIN, "/noticeBoard/update"));
    noticeMenu.add(new MenuItem("삭제", Menu.ACCESS_ADMIN, "/noticeBoard/delete"));
    noticeMenu.add(new MenuItem("검색", "/noticeBoard/search"));
    return noticeMenu;
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





}