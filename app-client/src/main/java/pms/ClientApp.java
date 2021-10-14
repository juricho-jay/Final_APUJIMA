package pms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Menu.Menu;
import Menu.MenuGroup;
import pms.context.ApplicationContextListener;
import pms.dao.impl.NetCommentDao;
import pms.dao.impl.NetDoctorBoardDao;
import pms.dao.impl.NetFreeBoardDao;
import pms.dao.impl.NetLikeDao;
import pms.dao.impl.NetMemberDao;
import pms.dao.impl.NetNoticeBoardDao;
import pms.handler.AdminApprovalHandler;
import pms.handler.AdminListHandler;
import pms.handler.AdminReportDeleteHandler;
import pms.handler.AdminUpdateHandler;
import pms.handler.AttendanceAutoDeleteHandler;
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
import pms.handler.CommentAutoDeleteHandler;
import pms.handler.CommentDeleteHandler;
import pms.handler.CommentUpdateHandler;
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
import pms.handler.LikeAutoDeleteHandler;
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
import pms.handler.PlantAddHandler;
import pms.handler.PlantGrowHandler;
import pms.handler.PlantListHandler;
import pms.handler.PlantMyListHandler;
import pms.handler.WiseSaying;
import pms.listener.AppInitListener;
import request.RequestAgent;
import util.Prompt;

public class ClientApp {

  RequestAgent requestAgent;

  HashMap<String,Command> commandMap = new HashMap<>();

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


  private void notifyOnApplicationStarted() {
    HashMap<String,Object> params = new HashMap<>();

    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(params);
    }
  }

  private void notifyOnApplicationEnded() {
    HashMap<String,Object> params = new HashMap<>();

    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(params);
    }
  }


  public ClientApp() throws Exception {

    // 서버와 통신을 담당할 객체 준비
    requestAgent = new RequestAgent("127.0.0.1", 8888);

    NetMemberDao memberDao = new NetMemberDao(requestAgent);
    NetFreeBoardDao freeBoardDao = new NetFreeBoardDao(requestAgent);
    NetLikeDao likeDao = new NetLikeDao(requestAgent);
    NetDoctorBoardDao doctorBoardDao = new NetDoctorBoardDao(requestAgent);
    NetNoticeBoardDao noticeBoardDao = new NetNoticeBoardDao(requestAgent);
    NetCommentDao commentDao = new NetCommentDao(requestAgent);

    // Command 객체 준비
    commandMap.put("/admin/approval", new AdminApprovalHandler(requestAgent));
    commandMap.put("/admin/update", new AdminUpdateHandler(requestAgent));
    commandMap.put("/admin/list", new AdminListHandler(requestAgent));
    commandMap.put("/admin/delete", new AdminReportDeleteHandler(requestAgent));
    commandMap.put("/intro", new IntroMenu());

    commandMap.put("/medicine/add", new MedicineAddHandler(requestAgent));
    commandMap.put("/medicine/request", new MedicineRequestHandler(requestAgent));
    commandMap.put("/medicine/list", new MedicineListHandler(requestAgent));
    commandMap.put("/medicine/update", new MedicineUpdateHandler(requestAgent));
    commandMap.put("/medicine/delete", new MedicineDeleteHandler(requestAgent));
    commandMap.put("/medicine/search", new MedicineSearchHandler(requestAgent));

    commandMap.put("/counselingMember/list", new DoctorMemberListHandler(requestAgent));
    commandMap.put("/counselingMember/add", new CounselingMemberAddHandler(requestAgent));
    commandMap.put("/counselingMember/myList", new CounselingMemberMyListHandler(requestAgent));
    commandMap.put("/counselingMember/doctorList", new CounselingMemberDoctorListHandler(requestAgent));

    commandMap.put("/noticeBoard/add", new NoticeBoardAddHandler(requestAgent));
    commandMap.put("/noticeBoard/list", new NoticeBoardListHandler(requestAgent));
    commandMap.put("/noticeBoard/detail", new NoticeBoardDetailHandler(requestAgent));
    commandMap.put("/noticeBoard/update", new NoticeBoardUpdateHandler(requestAgent));
    commandMap.put("/noticeBoard/delete", new NoticeBoardDeleteHandler(requestAgent));
    commandMap.put("/noticeBoard/search", new NoticeBoardSearchHandler(requestAgent));

    commandMap.put("/freeBoard/add", new FreeBoardAddHandler(freeBoardDao));
    commandMap.put("/freeBoard/list", new FreeBoardListHandler(freeBoardDao));
    commandMap.put("/freeBoard/detail", new FreeBoardDetailHandler(freeBoardDao, likeDao, commentDao));
    commandMap.put("/freeBoard/update", new FreeBoardUpdateHandler(freeBoardDao));
    commandMap.put("/freeBoard/delete", new FreeBoardDeleteHandler(freeBoardDao));
    commandMap.put("/freeBoard/search", new FreeBoardSearchHandler(freeBoardDao));
    //
    commandMap.put("/doctorBoard/add", new DoctorBoardAddHandler(doctorBoardDao, memberDao));
    commandMap.put("/doctorBoard/list", new DoctorBoardListHandler(requestAgent));
    commandMap.put("/doctorBoard/detail", new DoctorBoardDetailHandler(requestAgent));
    commandMap.put("/doctorBoard/update", new DoctorBoardUpdateHandler(requestAgent));
    commandMap.put("/doctorBoard/delete", new DoctorBoardDeleteHandler(requestAgent));
    commandMap.put("/doctorBoard/search", new DoctorBoardSearchHandler(requestAgent));

    commandMap.put("/member/add", new MemberAddHandler(memberDao));
    commandMap.put("/member/list", new MemberListHandler(memberDao));

    commandMap.put("/auth/login", new AuthLoginHandler(requestAgent));
    commandMap.put("/auth/logout", new AuthLogoutHandler());
    commandMap.put("/auth/userInfo", new AuthUserInfoHandler(requestAgent));
    commandMap.put("/auth/check", new AttendanceCheckHandler(requestAgent));
    commandMap.put("/auth/deleteCheck", new AttendanceAutoDeleteHandler(requestAgent));

    commandMap.put("/mailBox/send", new MailBoxSendHandler(requestAgent));
    commandMap.put("/mailBox/list", new MailBoxListHandler(requestAgent));
    commandMap.put("/mailBox/detail", new MailBoxDetailHandler(requestAgent));
    commandMap.put("/mailBox/delete", new MailBoxDeleteHandler(requestAgent));

    commandMap.put("/comment/add", new CommentAddHandler(commentDao, freeBoardDao, doctorBoardDao, noticeBoardDao));
    commandMap.put("/comment/autoDelete", new CommentAutoDeleteHandler(commentDao, freeBoardDao, doctorBoardDao, noticeBoardDao));
    commandMap.put("/comment/update", new CommentUpdateHandler(commentDao, freeBoardDao, doctorBoardDao, noticeBoardDao));
    commandMap.put("/comment/delete", new CommentDeleteHandler(commentDao, freeBoardDao, doctorBoardDao, noticeBoardDao));

    commandMap.put("/like/addCancel", new LikeAddCancelHandler(likeDao, freeBoardDao, doctorBoardDao, noticeBoardDao));
    commandMap.put("/like/autoDelete", new LikeAutoDeleteHandler(likeDao, freeBoardDao, doctorBoardDao, noticeBoardDao));


    commandMap.put("/bucket/add", new BucketAddHandler(requestAgent));
    commandMap.put("/bucket/list", new BucketListHandler(requestAgent));
    commandMap.put("/bucket/detail", new BucketDetailHandler(requestAgent));
    commandMap.put("/bucket/complete", new BucketCompleteHandler(requestAgent));
    commandMap.put("/bucket/search", new BucketSearchHandler(requestAgent));

    commandMap.put("/plant/add", new PlantAddHandler(requestAgent));
    commandMap.put("/plant/grow", new PlantGrowHandler(requestAgent));
    commandMap.put("/plant/list", new PlantListHandler(requestAgent));
    commandMap.put("/plant/mylist", new PlantMyListHandler(requestAgent));

    commandMap.put("/wiseSaying/saying", new WiseSaying());

  }


  void service() throws Exception {

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
    bucketMenu.add(new MenuItem("버킷리스트 상세", "/bucket/detail"));


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



  public static void main(String[] args) throws Exception {
    System.out.println("[PMS 클라이언트]");

    ClientApp app = new ClientApp(); 
    app.addApplicationContextListener(new AppInitListener());
    app.service();

    Prompt.close();
  }


}












