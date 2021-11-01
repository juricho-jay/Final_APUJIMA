package apus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import Menu.Menu;
import Menu.MenuGroup;
import apus.context.ApplicationContextListener;
import apus.dao.BoardDao;
import apus.dao.BucketDao;
import apus.dao.CommentDao;
import apus.dao.CounselingDao;
import apus.dao.DateCheckDao;
import apus.dao.LikeDao;
import apus.dao.MailBoxDao;
import apus.dao.MedicineDao;
import apus.dao.MemberDao;
import apus.dao.PlantDao;
import apus.dao.ReportDao;
import apus.handler.AdminApprovalHandler;
import apus.handler.AdminListHandler;
import apus.handler.AdminReportDeleteHandler;
import apus.handler.AttendanceCheckHandler;
import apus.handler.AuthLoginHandler;
import apus.handler.AuthLogoutHandler;
import apus.handler.AuthUserInfoHandler;
import apus.handler.BoardAddHandler;
import apus.handler.BoardDeleteHandler;
import apus.handler.BoardDetailHandler;
import apus.handler.BoardListHandler;
import apus.handler.BoardSearchHandler;
import apus.handler.BoardUpdateHandler;
import apus.handler.BucketAddHandler;
import apus.handler.BucketCompleteHandler;
import apus.handler.BucketDetailHandler;
import apus.handler.BucketListHandler;
import apus.handler.BucketSearchHandler;
import apus.handler.Command;
import apus.handler.CommandRequest;
import apus.handler.CommentAddHandler;
import apus.handler.CommentAutoDeleteHandler;
import apus.handler.CommentDeleteHandler;
import apus.handler.CommentUpdateHandler;
import apus.handler.CounselingMemberAddHandler;
import apus.handler.CounselingMemberDoctorListHandler;
import apus.handler.CounselingMemberMyListHandler;
import apus.handler.DoctorMemberListHandler;
import apus.handler.IntroMenu;
import apus.handler.LikeAddCancelHandler;
import apus.handler.LikeAutoDeleteHandler;
import apus.handler.MailBoxDeleteHandler;
import apus.handler.MailBoxDetailHandler;
import apus.handler.MailBoxListHandler;
import apus.handler.MailBoxSendHandler;
import apus.handler.MedicineAddHandler;
import apus.handler.MedicineDeleteHandler;
import apus.handler.MedicineListHandler;
import apus.handler.MedicineRequestHandler;
import apus.handler.MedicineSearchHandler;
import apus.handler.MedicineUpdateHandler;
import apus.handler.MemberAddHandler;
import apus.handler.MemberDeleteHandler;
import apus.handler.MemberListHandler;
import apus.handler.MemberUpdateHandler;
import apus.handler.PlantAddHandler;
import apus.handler.PlantDeleteHandler;
import apus.handler.PlantGrowHandler;
import apus.handler.PlantListHandler;
import apus.handler.PlantMyListHandler;
import apus.handler.WiseSaying;
import apus.listener.AppInitListener;
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

    // Mybatis의 SqlSession 객체 준비
    SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(
        "apus/conf/mybatis-config.xml")).openSession();

    MemberDao memberDao = sqlSession.getMapper(MemberDao.class);    
    MailBoxDao mailBoxDao = sqlSession.getMapper(MailBoxDao.class);
    BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
    CommentDao commentDao = sqlSession.getMapper(CommentDao.class);
    PlantDao plantDao = sqlSession.getMapper(PlantDao.class);
    MedicineDao medicineDao = sqlSession.getMapper(MedicineDao.class);
    BucketDao bucketDao = sqlSession.getMapper(BucketDao.class);
    DateCheckDao dateCheckDao = sqlSession.getMapper(DateCheckDao.class);
    CounselingDao counselingDao = sqlSession.getMapper(CounselingDao.class);
    LikeDao likeDao = sqlSession.getMapper(LikeDao.class);
    ReportDao reportDao = sqlSession.getMapper(ReportDao.class);




    // Command 객체 준비
    commandMap.put("/admin/approval", new AdminApprovalHandler(medicineDao, sqlSession));
    commandMap.put("/admin/list", new AdminListHandler(medicineDao, reportDao));
    commandMap.put("/admin/delete", new AdminReportDeleteHandler(reportDao, boardDao, mailBoxDao,sqlSession));
    commandMap.put("/intro", new IntroMenu());

    commandMap.put("/medicine/add", new MedicineAddHandler(medicineDao, sqlSession));
    commandMap.put("/medicine/request", new MedicineRequestHandler(medicineDao, sqlSession));
    commandMap.put("/medicine/list", new MedicineListHandler(medicineDao));
    commandMap.put("/medicine/update", new MedicineUpdateHandler(medicineDao, sqlSession));
    commandMap.put("/medicine/delete", new MedicineDeleteHandler(medicineDao, sqlSession));
    commandMap.put("/medicine/search", new MedicineSearchHandler(medicineDao));

    commandMap.put("/counselingMember/list", new DoctorMemberListHandler(memberDao));
    commandMap.put("/counselingMember/add", new CounselingMemberAddHandler(counselingDao, memberDao, sqlSession));
    commandMap.put("/counselingMember/myList", new CounselingMemberMyListHandler(counselingDao, memberDao));
    commandMap.put("/counselingMember/doctorList", new CounselingMemberDoctorListHandler(counselingDao));

    commandMap.put("/noticeBoard/add", new BoardAddHandler(boardDao, sqlSession));
    commandMap.put("/noticeBoard/list", new BoardListHandler(boardDao));
    commandMap.put("/noticeBoard/detail", new BoardDetailHandler(boardDao, likeDao, commentDao, reportDao, sqlSession));
    commandMap.put("/board/update", new BoardUpdateHandler(boardDao));
    commandMap.put("/board/delete", new BoardDeleteHandler(boardDao, sqlSession, commentDao, likeDao));
    commandMap.put("/noticeBoard/search", new BoardSearchHandler(boardDao));

    commandMap.put("/freeBoard/add", new BoardAddHandler(boardDao, sqlSession));
    commandMap.put("/freeBoard/list", new BoardListHandler(boardDao));
    commandMap.put("/freeBoard/detail", new BoardDetailHandler(boardDao, likeDao, commentDao, reportDao, sqlSession));
    commandMap.put("/board/update", new BoardUpdateHandler(boardDao));
    commandMap.put("/board/delete", new BoardDeleteHandler(boardDao, sqlSession, commentDao, likeDao));
    commandMap.put("/freeBoard/search", new BoardSearchHandler(boardDao));

    commandMap.put("/doctorBoard/add", new BoardAddHandler(boardDao, sqlSession));
    commandMap.put("/doctorBoard/list", new BoardListHandler(boardDao));
    commandMap.put("/doctorBoard/detail", new BoardDetailHandler(boardDao, likeDao, commentDao, reportDao, sqlSession));
    commandMap.put("/board/update", new BoardUpdateHandler(boardDao));
    commandMap.put("/board/delete", new BoardDeleteHandler(boardDao, sqlSession, commentDao, likeDao));
    commandMap.put("/doctorBoard/search", new BoardSearchHandler(boardDao));

    commandMap.put("/member/add", new MemberAddHandler(memberDao, sqlSession));
    commandMap.put("/member/list", new MemberListHandler(memberDao));
    commandMap.put("/member/update", new MemberUpdateHandler(memberDao));
    commandMap.put("/member/delete", new MemberDeleteHandler(memberDao, sqlSession));

    commandMap.put("/auth/login", new AuthLoginHandler(memberDao));
    commandMap.put("/auth/logout", new AuthLogoutHandler());
    commandMap.put("/auth/userInfo", new AuthUserInfoHandler(memberDao));
    commandMap.put("/auth/check", new AttendanceCheckHandler(dateCheckDao, memberDao, sqlSession));

    commandMap.put("/mailBox/send", new MailBoxSendHandler(mailBoxDao , memberDao, sqlSession));
    commandMap.put("/mailBox/list", new MailBoxListHandler(mailBoxDao));
    commandMap.put("/mailBox/detail", new MailBoxDetailHandler(mailBoxDao, sqlSession));
    commandMap.put("/mailBox/delete", new MailBoxDeleteHandler(mailBoxDao, sqlSession));

    commandMap.put("/comment/add", new CommentAddHandler(commentDao, boardDao, sqlSession, memberDao));
    commandMap.put("/comment/autoDelete", new CommentAutoDeleteHandler(commentDao, boardDao, sqlSession));
    commandMap.put("/comment/update", new CommentUpdateHandler(commentDao, boardDao, memberDao, sqlSession));
    commandMap.put("/comment/delete", new CommentDeleteHandler(commentDao, boardDao, memberDao, sqlSession));

    commandMap.put("/like/addCancel", new LikeAddCancelHandler(likeDao, boardDao, sqlSession));
    commandMap.put("/like/autoDelete", new LikeAutoDeleteHandler(likeDao, boardDao, sqlSession));

    commandMap.put("/bucket/add", new BucketAddHandler(bucketDao, sqlSession));
    commandMap.put("/bucket/list", new BucketListHandler(bucketDao));
    commandMap.put("/bucket/detail", new BucketDetailHandler(bucketDao));
    commandMap.put("/bucket/complete", new BucketCompleteHandler(bucketDao, sqlSession));
    commandMap.put("/bucket/search", new BucketSearchHandler(bucketDao, sqlSession));

    commandMap.put("/plant/add", new PlantAddHandler(plantDao,memberDao, sqlSession));
    commandMap.put("/plant/grow", new PlantGrowHandler(plantDao, memberDao, sqlSession));
    commandMap.put("/plant/list", new PlantListHandler(plantDao));
    commandMap.put("/plant/mylist", new PlantMyListHandler(plantDao));
    commandMap.put("/plant/delete", new PlantDeleteHandler(plantDao, sqlSession));

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
    plantMenu.add(new MenuItem("화분 삭제", "/plant/delete"));


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












