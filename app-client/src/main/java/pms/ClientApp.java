package pms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Menu.Menu;
import Menu.MenuGroup;
import pms.context.ApplicationContextListener;
import pms.dao.MailBoxDao;
import pms.dao.impl.MariadbMemberDao;
import pms.dao.impl.NetBoardDao;
import pms.dao.impl.NetBucketDao;
import pms.dao.impl.NetCommentDao;
import pms.dao.impl.NetCounselingMemberDao;
import pms.dao.impl.NetDateCheckDao;
import pms.dao.impl.NetLikeDao;
import pms.dao.impl.NetMailBoxDao;
import pms.dao.impl.NetMedicineDao;
import pms.dao.impl.NetPlantDao;
import pms.dao.impl.NetReportDao;
import pms.dao.impl.NetRequestDao;
import pms.handler.AdminApprovalHandler;
import pms.handler.AdminListHandler;
import pms.handler.AdminReportDeleteHandler;
import pms.handler.AttendanceCheckHandler;
import pms.handler.AuthLoginHandler;
import pms.handler.AuthLogoutHandler;
import pms.handler.AuthUserInfoHandler;
import pms.handler.BoardAddHandler;
import pms.handler.BoardDeleteHandler;
import pms.handler.BoardDetailHandler;
import pms.handler.BoardListHandler;
import pms.handler.BoardSearchHandler;
import pms.handler.BoardUpdateHandler;
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
import pms.handler.DoctorMemberListHandler;
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
  Connection con;

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
    con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/apusdb?user=root&password=1111");
    //NetMemberDao memberDao = new NetMemberDao(requestAgent);
    MariadbMemberDao memberDao = new MariadbMemberDao(con);

    NetBoardDao boardDao = new NetBoardDao(requestAgent);
    NetLikeDao likeDao = new NetLikeDao(requestAgent);
    NetCommentDao commentDao = new NetCommentDao(requestAgent);
    NetBucketDao bucketDao = new NetBucketDao(requestAgent);
    NetMedicineDao medicineDao = new NetMedicineDao(requestAgent);
    MailBoxDao mailBoxDao = new NetMailBoxDao(requestAgent);
    NetReportDao reportDao = new NetReportDao(requestAgent);
    NetDateCheckDao dateCheckDao = new NetDateCheckDao(requestAgent);
    NetRequestDao requestDao = new NetRequestDao(requestAgent);
    NetPlantDao plantDao = new NetPlantDao(requestAgent);
    NetCounselingMemberDao counselingMemberDao = new NetCounselingMemberDao(requestAgent);



    // Command 객체 준비
    commandMap.put("/admin/approval", new AdminApprovalHandler(requestDao, medicineDao));
    commandMap.put("/admin/list", new AdminListHandler(requestDao, reportDao));
    commandMap.put("/admin/delete", new AdminReportDeleteHandler(reportDao, boardDao, mailBoxDao));
    commandMap.put("/intro", new IntroMenu());

    commandMap.put("/medicine/add", new MedicineAddHandler(medicineDao));
    commandMap.put("/medicine/request", new MedicineRequestHandler(medicineDao, requestDao));
    commandMap.put("/medicine/list", new MedicineListHandler(medicineDao));
    commandMap.put("/medicine/update", new MedicineUpdateHandler(medicineDao));
    commandMap.put("/medicine/delete", new MedicineDeleteHandler(medicineDao));
    commandMap.put("/medicine/search", new MedicineSearchHandler(medicineDao));

    commandMap.put("/counselingMember/list", new DoctorMemberListHandler(memberDao));
    commandMap.put("/counselingMember/add", new CounselingMemberAddHandler(counselingMemberDao));
    commandMap.put("/counselingMember/myList", new CounselingMemberMyListHandler(counselingMemberDao));
    commandMap.put("/counselingMember/doctorList", new CounselingMemberDoctorListHandler(counselingMemberDao));

    commandMap.put("/noticeBoard/add", new BoardAddHandler(boardDao));
    commandMap.put("/noticeBoard/list", new BoardListHandler(boardDao));
    commandMap.put("/noticeBoard/detail", new BoardDetailHandler(boardDao, likeDao, commentDao, reportDao));
    commandMap.put("/noticeBoard/update", new BoardUpdateHandler(boardDao));
    commandMap.put("/noticeBoard/delete", new BoardDeleteHandler(boardDao));
    commandMap.put("/noticeBoard/search", new BoardSearchHandler(boardDao));

    commandMap.put("/freeBoard/add", new BoardAddHandler(boardDao));
    commandMap.put("/freeBoard/list", new BoardListHandler(boardDao));
    commandMap.put("/freeBoard/detail", new BoardDetailHandler(boardDao, likeDao, commentDao, reportDao));
    commandMap.put("/freeBoard/update", new BoardUpdateHandler(boardDao));
    commandMap.put("/freeBoard/delete", new BoardDeleteHandler(boardDao));
    commandMap.put("/freeBoard/search", new BoardSearchHandler(boardDao));

    commandMap.put("/doctorBoard/add", new BoardAddHandler(boardDao));
    commandMap.put("/doctorBoard/list", new BoardListHandler(boardDao));
    commandMap.put("/doctorBoard/detail", new BoardDetailHandler(boardDao, likeDao, commentDao, reportDao));
    commandMap.put("/doctorBoard/update", new BoardUpdateHandler(boardDao));
    commandMap.put("/doctorBoard/delete", new BoardDeleteHandler(boardDao));
    commandMap.put("/doctorBoard/search", new BoardSearchHandler(boardDao));

    commandMap.put("/member/add", new MemberAddHandler(memberDao));
    commandMap.put("/member/list", new MemberListHandler(memberDao));

    commandMap.put("/auth/login", new AuthLoginHandler(requestAgent));
    commandMap.put("/auth/logout", new AuthLogoutHandler());
    commandMap.put("/auth/userInfo", new AuthUserInfoHandler(memberDao));
    commandMap.put("/auth/check", new AttendanceCheckHandler(dateCheckDao, memberDao));

    commandMap.put("/mailBox/send", new MailBoxSendHandler(mailBoxDao , memberDao));
    commandMap.put("/mailBox/list", new MailBoxListHandler(mailBoxDao));
    commandMap.put("/mailBox/detail", new MailBoxDetailHandler(mailBoxDao));
    commandMap.put("/mailBox/delete", new MailBoxDeleteHandler(mailBoxDao));

    commandMap.put("/comment/add", new CommentAddHandler(commentDao, boardDao));
    commandMap.put("/comment/autoDelete", new CommentAutoDeleteHandler(commentDao, boardDao));
    commandMap.put("/comment/update", new CommentUpdateHandler(commentDao, boardDao));
    commandMap.put("/comment/delete", new CommentDeleteHandler(commentDao, boardDao));

    commandMap.put("/like/addCancel", new LikeAddCancelHandler(likeDao, boardDao));
    commandMap.put("/like/autoDelete", new LikeAutoDeleteHandler(likeDao, boardDao));

    commandMap.put("/bucket/add", new BucketAddHandler(bucketDao));
    commandMap.put("/bucket/list", new BucketListHandler(bucketDao));
    commandMap.put("/bucket/detail", new BucketDetailHandler(bucketDao));
    commandMap.put("/bucket/complete", new BucketCompleteHandler(bucketDao));
    commandMap.put("/bucket/search", new BucketSearchHandler(bucketDao));

    commandMap.put("/plant/add", new PlantAddHandler(plantDao,memberDao));
    commandMap.put("/plant/grow", new PlantGrowHandler(plantDao, memberDao));
    commandMap.put("/plant/list", new PlantListHandler(plantDao));
    commandMap.put("/plant/mylist", new PlantMyListHandler(plantDao));

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
    //    approvalManagement.add(new MenuItem("약품 변경", Menu.ACCESS_ADMIN, "/admin/update")); // 변경 or not
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












