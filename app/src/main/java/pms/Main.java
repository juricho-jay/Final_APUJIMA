package pms;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import Menu.Menu;
import Menu.MenuGroup;
import pms.context.ApplicationContextListener;
import pms.domain.Bucket;
import pms.domain.Comment;
import pms.domain.CounselingMember;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.Like;
import pms.domain.MailBox;
import pms.domain.Medicine;
import pms.domain.Member;
import pms.domain.NoticeBoard;
import pms.domain.Plant;
import pms.handler.AdminApprovalHandler;
import pms.handler.AdminDoctorReportDeleteHandler;
import pms.handler.AdminListHandler;
import pms.handler.AdminReportDeleteHandler;
import pms.handler.AdminUpdateHandler;
import pms.handler.AttendanceCheckHandler;
import pms.handler.AuthLoginHandler;
import pms.handler.AuthLogoutHandler;
import pms.handler.AuthUserInfoHandler;
import pms.handler.BucketAddHandler;
import pms.handler.BucketCompleteHandler;
import pms.handler.BucketDetailHandler;
import pms.handler.BucketListHandler;
import pms.handler.BucketSearchHandler;
import pms.handler.Command;
import pms.handler.CommandRequest;
import pms.handler.CommentAddHandler;
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
import pms.handler.LikeAddCancelHandler;
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
import pms.handler.MemberPrompt;
import pms.handler.NoticeBoardAddHandler;
import pms.handler.NoticeBoardDeleteHandler;
import pms.handler.NoticeBoardDetailHandler;
import pms.handler.NoticeBoardListHandler;
import pms.handler.NoticeBoardSearchHandler;
import pms.handler.NoticeBoardUpdateHandler;
import pms.handler.PlantAddHandler;
import pms.handler.PlantGrowHandler;
import pms.handler.PlantListHandler;
import pms.handler.PlantMyListHandler;
import pms.handler.WiseSaying;
import pms.listener.AppInitListener;
import pms.listener.FileListener;
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
  List<DoctorBoard> doctorReportList = new LinkedList<>();
  List<Comment> commentList = new LinkedList<>();
  List<Bucket> bucketList = new LinkedList<>();
  List<Plant> plantList = new LinkedList<>();
  List<Like> likeList = new LinkedList<>();
  List<Date> dateList = new ArrayList<>();
  List<Member> memberCheckList = new LinkedList<>();

  IntroMenu intro = new IntroMenu();
  HashMap<String,Command> commandMap = new HashMap<>();

  MemberPrompt memberPrompt = new MemberPrompt(memberList);

  //옵저버 관련 필드와 메서드
  // => 옵저버(리스너) 목록
  List<ApplicationContextListener> listeners = new ArrayList<>();

  //=> 옵저버(리스너)를 등록하는 메서드
  public void addApplicationContextListener(ApplicationContextListener listener) {
    this.listeners.add(listener);
  }

  // => 옵저버(리스너)를 제거하는 메서드
  public void removeApplicationContextListener(ApplicationContextListener listener) {
    this.listeners.remove(listener);
  }


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
      try {
        command.execute(new CommandRequest(commandMap));
      } catch (Exception e) {
        System.out.printf("%s 명령을 실행하는 중 오류 발생!\n", menuId);
        e.printStackTrace();
      }
    }

  }


  public static void main(String[] args) {
    Main main = new Main();
    main.addApplicationContextListener(new FileListener());
    main.addApplicationContextListener(new AppInitListener());
    main.service();
  }

  public Main() {
    commandMap.put("/admin/approval", new AdminApprovalHandler(requestList, medicineList));
    commandMap.put("/admin/update", new AdminUpdateHandler(requestList, medicineList));
    commandMap.put("/admin/list", new AdminListHandler(requestList, medicineList, reportList, doctorReportList));

    commandMap.put("/admin/delete", new AdminReportDeleteHandler(freeBoardList , reportList, mailBoxList));
    commandMap.put("/admin/doctordelete", new AdminDoctorReportDeleteHandler(doctorBoardList, doctorReportList, mailBoxList));


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
    commandMap.put("/noticeBoard/detail", new NoticeBoardDetailHandler(noticeBoardList, commentList, likeList));
    commandMap.put("/noticeBoard/update", new NoticeBoardUpdateHandler(noticeBoardList));
    commandMap.put("/noticeBoard/delete", new NoticeBoardDeleteHandler(noticeBoardList));
    commandMap.put("/noticeBoard/search", new NoticeBoardSearchHandler(noticeBoardList));

    commandMap.put("/freeBoard/add", new FreeBoardAddHandler(freeBoardList));
    commandMap.put("/freeBoard/list", new FreeBoardListHandler(freeBoardList));
    commandMap.put("/freeBoard/detail", new FreeBoardDetailHandler(freeBoardList, reportList, commentList, likeList));
    commandMap.put("/freeBoard/update", new FreeBoardUpdateHandler(freeBoardList));
    commandMap.put("/freeBoard/delete", new FreeBoardDeleteHandler(freeBoardList));
    commandMap.put("/freeBoard/search", new FreeBoardSearchHandler(freeBoardList));

    commandMap.put("/doctorBoard/add", new DoctorBoardAddHandler(doctorBoardList));
    commandMap.put("/doctorBoard/list", new DoctorBoardListHandler(doctorBoardList));
    commandMap.put("/doctorBoard/detail", new DoctorBoardDetailHandler(doctorBoardList, doctorReportList, commentList, likeList));
    commandMap.put("/doctorBoard/update", new DoctorBoardUpdateHandler(doctorBoardList));
    commandMap.put("/doctorBoard/delete", new DoctorBoardDeleteHandler(doctorBoardList));
    commandMap.put("/doctorBoard/search", new DoctorBoardSearchHandler(doctorBoardList));

    commandMap.put("/member/add", new MemberAddHandler(memberList));
    commandMap.put("/member/list", new MemberListHandler(memberList));

    commandMap.put("/auth/login", new AuthLoginHandler(memberList));
    commandMap.put("/auth/logout", new AuthLogoutHandler());
    commandMap.put("/auth/userInfo", new AuthUserInfoHandler(memberList));
    commandMap.put("/auth/check", new AttendanceCheckHandler(dateList, memberCheckList));



    commandMap.put("/mailBox/send", new MailBoxSendHandler(mailBoxList, memberPrompt));
    commandMap.put("/mailBox/list", new MailBoxListHandler(mailBoxList));
    commandMap.put("/mailBox/detail", new MailBoxDetailHandler(mailBoxList));
    commandMap.put("/mailBox/delete", new MailBoxDeleteHandler(mailBoxList));

    commandMap.put("/comment/add", new CommentAddHandler(commentList, freeBoardList, doctorBoardList, noticeBoardList));

    commandMap.put("/like/addCancel", new LikeAddCancelHandler(likeList, freeBoardList, doctorBoardList, noticeBoardList));


    commandMap.put("/bucket/add", new BucketAddHandler(bucketList));
    commandMap.put("/bucket/list", new BucketListHandler(bucketList));
    commandMap.put("/bucket/detail", new BucketDetailHandler(bucketList));
    commandMap.put("/bucket/complete", new BucketCompleteHandler(bucketList));
    commandMap.put("/bucket/search", new BucketSearchHandler(bucketList));

    commandMap.put("/plant/add", new PlantAddHandler(plantList));
    commandMap.put("/plant/grow", new PlantGrowHandler(plantList));
    commandMap.put("/plant/list", new PlantListHandler(plantList));
    commandMap.put("/plant/mylist", new PlantMyListHandler(plantList));



    commandMap.put("/wiseSaying/saying", new WiseSaying());



  }

  private void notifyOnApplicationStarted() {
    HashMap<String,Object> params = new HashMap<>();
    params.put("memberList", memberList);
    params.put("counselingMemberList", counselingMemberList);
    params.put("noticeBoardList", noticeBoardList);
    params.put("freeBoardList", freeBoardList);
    params.put("doctorBoardList", doctorBoardList);
    params.put("medicineList", medicineList);
    params.put("mailBoxList", mailBoxList);
    params.put("bucketList", bucketList);
    params.put("dateList", dateList);
    params.put("memberCheckList", memberCheckList);
    params.put("reportList", reportList);
    params.put("doctorReportList", doctorReportList);
    params.put("likeList", likeList);
    params.put("commentList", commentList);

    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(params);
    }
  }

  private void notifyOnApplicationEnded() {
    HashMap<String,Object> params = new HashMap<>();
    params.put("memberList", memberList);
    params.put("counselingMemberList", counselingMemberList);
    params.put("noticeBoardList", noticeBoardList);
    params.put("freeBoardList", freeBoardList);
    params.put("doctorBoardList", doctorBoardList);
    params.put("medicineList", medicineList);
    params.put("mailBoxList", mailBoxList);
    params.put("bucketList", bucketList);
    params.put("dateList", dateList);
    params.put("memberCheckList", memberCheckList);
    params.put("reportList", reportList);
    params.put("doctorReportList", doctorReportList);
    params.put("likeList", likeList);
    params.put("commentList", commentList);

    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(params);
    }
  }

  void service() {

    notifyOnApplicationStarted();

    createMainMenu().execute();
    Prompt.close();

    notifyOnApplicationEnded();
  }




  Menu createMainMenu() {

    MenuGroup mainMenuGroup = new MenuGroup("WELCOME TO APUJIMA!");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(createApprovalMenu());
    mainMenuGroup.add(new MenuItem("소개", "/intro"));
    mainMenuGroup.add(createMedicineMenu());
    mainMenuGroup.add(createCounselingMenu());

    mainMenuGroup.add(createCommunityMenu());
    mainMenuGroup.add(createBucketMenu());
    mainMenuGroup.add(createPlantMenu());

    mainMenuGroup.add(new MenuItem("로그인", Menu.ACCESS_LOGOUT, "/auth/login"));
    mainMenuGroup.add(new MenuItem("회원가입", Menu.ACCESS_LOGOUT, "/member/add"));
    mainMenuGroup.add(new MenuItem("내정보", Menu.ACCESS_GENERAL | Menu.ACCESS_DOCTOR | Menu.ACCESS_ADMIN, "/auth/userInfo"));
    mainMenuGroup.add(new MenuItem("출석체크", Menu.ACCESS_GENERAL | Menu.ACCESS_DOCTOR , "/auth/check"));
    mainMenuGroup.add(createMailBoxMenu());
    mainMenuGroup.add(new MenuItem("로그아웃", Menu.ACCESS_GENERAL | Menu.ACCESS_DOCTOR | Menu.ACCESS_ADMIN, "/auth/logout"));


    return mainMenuGroup;
  }


  private Menu createApprovalMenu() {
    MenuGroup approvalMenu = new MenuGroup("관리자 메뉴", Menu.ACCESS_ADMIN);

    approvalMenu.add(new MenuItem("승인 요청 / 신고 목록", Menu.ACCESS_ADMIN,"/admin/list")); // AdminListHandler
    //   approvalMenu.add(new MenuItem("승인 내역", Menu.ACCESS_ADMIN,"/admin/*")); // AdminListHandler
    MenuGroup approvalManagement = new MenuGroup("승인 관리", Menu.ACCESS_ADMIN);

    approvalManagement.add(new MenuItem("약품 승인", Menu.ACCESS_ADMIN, "/admin/approval")); // 여기서 3지선다 승인, 삭제, 뒤로가기
    approvalManagement.add(new MenuItem("약품 변경", Menu.ACCESS_ADMIN, "/admin/update")); // 변경 or not
    approvalManagement.add(new MenuItem("자유게시판 신고 승인", Menu.ACCESS_ADMIN, "/admin/delete")); // 신고하시겠습니까? yes => 삭제
    approvalManagement.add(new MenuItem("Healer 게시판 신고 승인", Menu.ACCESS_ADMIN, "/admin/doctordelete"));


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
    memberMenu.add(new MenuItem("의사 리스트", Menu.ACCESS_LOGOUT | Menu.ACCESS_GENERAL | Menu.ACCESS_DOCTOR | Menu.ACCESS_ADMIN ,"/counselingMember/list"));
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

    noticeMenu.add(new MenuItem("글쓰기", Menu.ACCESS_ADMIN,"/noticeBoard/add"));
    noticeMenu.add(new MenuItem("목록", "/noticeBoard/list"));
    noticeMenu.add(new MenuItem("상세보기", "/noticeBoard/detail"));
    noticeMenu.add(new MenuItem("검색", "/noticeBoard/search"));
    return noticeMenu;
  }

  private Menu createFreeBoardMenu() {
    MenuGroup freeBoardMenu = new MenuGroup("APUs 자유게시판");

    freeBoardMenu.add(new MenuItem("글쓰기", Menu.ACCESS_GENERAL | Menu.ACCESS_DOCTOR, "/freeBoard/add"));
    freeBoardMenu.add(new MenuItem("목록", "/freeBoard/list"));
    freeBoardMenu.add(new MenuItem("상세보기", "/freeBoard/detail"));
    freeBoardMenu.add(new MenuItem("검색", "/freeBoard/search"));
    return freeBoardMenu;
  }

  private Menu createDoctorBoardMenu() {
    MenuGroup doctorBoardMenu = new MenuGroup("HEALER 지식in");

    doctorBoardMenu.add(new MenuItem("글쓰기", Menu.ACCESS_GENERAL, "/doctorBoard/add"));
    doctorBoardMenu.add(new MenuItem("목록", "/doctorBoard/list"));
    doctorBoardMenu.add(new MenuItem("상세보기", "/doctorBoard/detail"));
    doctorBoardMenu.add(new MenuItem("검색", "/doctorBoard/search"));
    return doctorBoardMenu;
  }

  private Menu createMailBoxMenu() {
    MenuGroup mailBoxMenu = new MenuGroup("쪽지함", Menu.ACCESS_GENERAL | Menu.ACCESS_DOCTOR | Menu.ACCESS_ADMIN);

    mailBoxMenu.add(new MenuItem("쪽지 전송", "/mailBox/send"));
    mailBoxMenu.add(new MenuItem("목록", "/mailBox/list"));
    mailBoxMenu.add(new MenuItem("상세보기", "/mailBox/detail"));
    mailBoxMenu.add(new MenuItem("삭제", "/mailBox/delete"));


    return mailBoxMenu;
  }

  private Menu createBucketMenu() {
    MenuGroup bucketMenu = new MenuGroup("버킷리스트", Menu.ACCESS_GENERAL | Menu.ACCESS_DOCTOR);

    bucketMenu.add(new MenuItem("버킷리스트 추가", "/bucket/add"));
    bucketMenu.add(new MenuItem("버킷리스트 목록", "/bucket/list"));
    //bucketMenu.add(new MenuItem("버킷리스트 상세", "/bucket/detail"));
    //  bucketMenu.add(new MenuItem("버킷리스트 달성체크", "/bucket/complete"));


    return bucketMenu;

  }

  private Menu createPlantMenu() {
    MenuGroup plantMenu = new MenuGroup("화분 키우기", Menu.ACCESS_GENERAL | Menu.ACCESS_DOCTOR);

    plantMenu.add(new MenuItem("화분 새로 키우기", "/plant/add"));
    plantMenu.add(new MenuItem("화분 물 주기", "/plant/grow"));
    plantMenu.add(new MenuItem("APUs 화분 보기", "/plant/list"));
    plantMenu.add(new MenuItem("내 화분", "/plant/mylist"));



    return plantMenu;

  }



}