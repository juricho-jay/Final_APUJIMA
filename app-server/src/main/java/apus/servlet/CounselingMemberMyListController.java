package apus.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import apus.dao.CounselingDao;
import apus.dao.MemberDao;
import apus.domain.Counseling;
import apus.domain.Member;

@WebServlet("/counseling/list")
public class CounselingMemberMyListController extends HttpServlet{

  private static final long serialVersionUID = 1L;
  CounselingDao counselingDao;
  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    counselingDao = (CounselingDao) 웹애플리케이션공용저장소.getAttribute("counselingDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      Collection<Member> memberList = memberDao.findAll();
      // 클라이언트 요청을 처리하는데 필요한 데이터 준비
      Collection<Counseling> counselingList = counselingDao.findAll();

      // 뷰 컴포넌트가 준비한 데이터를 사용할 수 있도록 저장소에 보관한다.
      request.setAttribute("counselingList", counselingList);
      request.setAttribute("memberList", memberList);
      // 출력을 담당할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/counseling/CounselingList.jsp");
      요청배달자.forward(request, response);

    } catch (Exception e) {
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      e.printStackTrace();
      request.setAttribute("error", e);

      // 오류가 발생하면, 오류 내용을 출력할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
      요청배달자.forward(request, response);
    }
  }
  //
  //  public CounselingMemberMyListController(CounselingDao counselingDao, MemberDao memberDao) {
  //    this.counselingDao = counselingDao;
  //    this.memberDao = memberDao;
  //  }
  //
  //  @Override
  //  public void execute(CommandRequest request) throws Exception {
  //    Member loginUser = AuthLoginHandler.getLoginUser();
  //    System.out.println();
  //    System.out.println("[상담신청 이력] 페이지입니다.");
  //    int cnt = 0;
  //
  //
  //    List<Counseling> counselingList = counselingDao.findAll();
  //
  //    if(counselingList.size() == 0) {
  //      System.out.println("아무도 신청 안했어!");
  //      return;
  //    }
  //    Counseling counsel = new Counseling();
  //
  //    for (int i = 0; i < counselingList.size() ; i++) {
  //      if(counselingList.get(i).getClient().getId().equals(loginUser.getId())) {
  //        cnt++;
  //        counsel = counselingList.get(i);
  //        System.out.printf("이름: %s\n", counsel.getClient().getName());
  //        System.out.printf("연락처: %s\n", counsel.getClient().getPhoneNum());
  //        System.out.printf("지병여부 : %s\n", counsel.getDisease());
  //        System.out.printf("상담내용 : %s\n", counsel.getContent());
  //        System.out.printf("상담사 이름 : %s\n", counsel.getCounselor().getName());
  //        Member m = memberDao.findDoctorMajor(counsel.getCounselor());
  //        System.out.printf("상담사 전문분야 : %s\n", m.getDoctor().getMajor());
  //        System.out.println();
  //      } 
  //    }
  //
  //
  //    if (cnt == 0) {
  //      System.out.println("상담신청 내역이 없습니다.");
  //      return;
  //    }
  //
  //  }
}
