package pms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
import pms.domain.FreeBoard;
import pms.domain.MailBox;
import pms.domain.Medicine;
import pms.domain.Member;
import pms.domain.NoticeBoard;
import pms.handler.AdminApprovalHandler;
import pms.handler.AdminListHandler;
import pms.handler.AdminReportDeleteHandler;
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
import pms.handler.MailBoxDeleteHandler;
import pms.handler.MailBoxDetailHandler;
import pms.handler.MailBoxListHandler;
import pms.handler.MailBoxSendHandler;
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
import pms.handler.WiseSaying;
import util.Prompt;

public class Main {
  List<Member> memberList = new LinkedList<>();
  List<CounselingMember> counselingMemberList = new LinkedList<>();
  List<Medicine> medicineList = new LinkedList<>();
  List<Medicine> requestList = new LinkedList<>();
  List<FreeBoard> freeBoardList = new LinkedList<>();
  List<NoticeBoard> noticeBoardList = new LinkedList<>();
  List<DoctorBoard> doctorBoardList = new LinkedList<>();
  List<FreeBoard> reportList = new LinkedList<>();
  List<MailBox> mailBoxList = new LinkedList<>();


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
    commandMap.put("/admin/list", new AdminListHandler(requestList, medicineList, reportList, freeBoardList));
    commandMap.put("/admin/delete", new AdminReportDeleteHandler(freeBoardList , reportList));


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


    commandMap.put("/mailBox/send", new MailBoxSendHandler(mailBoxList, memberList));
<<<<<<< HEAD
    commandMap.put("/mailBox/list", new MailBoxListHandler(mailBoxList, memberList));
    commandMap.put("/mailBox/detail", new MailBoxDetailHandler(mailBoxList, memberList));
    commandMap.put("/mailBox/delete", new MailBoxDeleteHandler(mailBoxList, memberList));
=======
    commandMap.put("/mailBox/list", new MailBoxListHandler(mailBoxList));
    commandMap.put("/mailBox/detail", new MailBoxDetailHandler(mailBoxList));
    commandMap.put("/mailBox/delete", new MailBoxDeleteHandler(mailBoxList));
>>>>>>> ca788885b7b606093eb32fc4621700eec49f018b


    commandMap.put("/wiseSaying/saying", new WiseSaying());

  }

  void service() {

    loadObjects("freeboard.data", freeBoardList);
    loadObjects("report.data", reportList);
    loadObjects("member.data", memberList);
    loadObjects("medicine.data", medicineList);

    createMainMenu().execute();
    Prompt.close();

    saveObjects("freeboard.data", freeBoardList);
    saveObjects("report.data", reportList);
    saveObjects("member.data", memberList);
    saveObjects("medicine.data", medicineList);
    System.out.println("[APUJIMA]에 방문해 주셔서 감사합니다. 좋은하루 되시기 바랍니다!");
  }

  private <E> void loadObjects(String filepath, List<E> list) {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(
            new FileInputStream(filepath)))) {

      list.addAll((List<E>) in.readObject());

      System.out.printf("%s 파일 로딩 완료!\n", filepath);

    } catch (Exception e) {
      System.out.printf("%s 파일에서 데이터를 읽어 오는 중 오류 발생!\n", filepath);
      e.printStackTrace();
    }
  }


  private <E> void saveObjects(String filepath, List<E> list) {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(
            new FileOutputStream(filepath)))) {

      out.writeObject(list);

      System.out.printf("%s 파일 저장 완료!\n", filepath);

    } catch (Exception e) {
      System.out.printf("%s 파일에 데이터를 저장 중 오류 발생!\n", filepath);
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


    //   mainMenuGroup.add(new MenuItem("명언", Menu.ACCESS_GENERAL | Menu.ACCESS_DOCTOR, "/wiseSaying/saying"));
    mainMenuGroup.add(createApprovalMenu());
    mainMenuGroup.add(new MenuItem("소개", "/intro"));
    mainMenuGroup.add(createMedicineMenu());
    mainMenuGroup.add(createCounselingMenu());

    mainMenuGroup.add(createCommunityMenu());

    mainMenuGroup.add(new MenuItem("로그인", Menu.ACCESS_LOGOUT, "/auth/login"));
    mainMenuGroup.add(new MenuItem("회원가입", Menu.ACCESS_LOGOUT, "/member/add"));
    mainMenuGroup.add(new MenuItem("내정보", Menu.ACCESS_GENERAL | Menu.ACCESS_DOCTOR, "/auth/userInfo"));
    mainMenuGroup.add(createMailBoxMenu());
    mainMenuGroup.add(new MenuItem("로그아웃", Menu.ACCESS_GENERAL | Menu.ACCESS_DOCTOR | Menu.ACCESS_ADMIN, "/auth/logout"));

    //    mainMenuGroup.add(createCommunityMenu());


    return mainMenuGroup;
  }


  private Menu createApprovalMenu() {
    MenuGroup approvalMenu = new MenuGroup("관리자 메뉴", Menu.ACCESS_ADMIN);

    approvalMenu.add(new MenuItem("승인 요청 / 신고 목록", Menu.ACCESS_ADMIN,"/admin/list")); // AdminListHandler
    //   approvalMenu.add(new MenuItem("승인 내역", Menu.ACCESS_ADMIN,"/admin/*")); // AdminListHandler
    MenuGroup approvalManagement = new MenuGroup("승인 관리", Menu.ACCESS_ADMIN);

    approvalManagement.add(new MenuItem("약품 승인", Menu.ACCESS_ADMIN, "/admin/approval")); // 여기서 3지선다 승인, 삭제, 뒤로가기
    approvalManagement.add(new MenuItem("약품 변경", Menu.ACCESS_ADMIN, "/admin/update")); // 변경 or not
    approvalManagement.add(new MenuItem("게시판 신고 승인", Menu.ACCESS_ADMIN, "/admin/delete")); // 신고하시겠습니까? yes => 삭제



    approvalMenu.add(approvalManagement);
    approvalMenu.add(new MenuItem("회원 목록", Menu.ACCESS_ADMIN, "/member/list"));



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
    memberMenu.add(new MenuItem("의사 리스트", Menu.ACCESS_LOGOUT | Menu.ACCESS_GENERAL | Menu.ACCESS_DOCTOR, "/counselingMember/list"));
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

    freeBoardMenu.add(new MenuItem("글쓰기", Menu.ACCESS_GENERAL | Menu.ACCESS_DOCTOR, "/freeBoard/add"));
    freeBoardMenu.add(new MenuItem("목록", "/freeBoard/list"));
    freeBoardMenu.add(new MenuItem("상세보기", "/freeBoard/detail"));
    freeBoardMenu.add(new MenuItem("변경", Menu.ACCESS_GENERAL | Menu.ACCESS_DOCTOR, "/freeBoard/update"));
    freeBoardMenu.add(new MenuItem("삭제", Menu.ACCESS_GENERAL | Menu.ACCESS_DOCTOR, "/freeBoard/delete"));
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

  private Menu createMailBoxMenu() {
    MenuGroup mailBoxMenu = new MenuGroup("쪽지함", Menu.ACCESS_GENERAL | Menu.ACCESS_ADMIN);

    mailBoxMenu.add(new MenuItem("쪽지 전송", "/mailBox/send"));
    mailBoxMenu.add(new MenuItem("목록", "/mailBox/list"));
    mailBoxMenu.add(new MenuItem("상세보기", "/mailBox/detail"));
    mailBoxMenu.add(new MenuItem("삭제", "/mailBox/delete"));


    return mailBoxMenu;
  }




}