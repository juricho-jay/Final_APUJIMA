package apus.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import apus.dao.CounselingDao;
import apus.dao.MemberDao;
import apus.domain.Counseling;
import apus.domain.Member;

@WebServlet("/counseling/add")
public class CounselingMemberAddController extends HttpServlet{

  private static final long serialVersionUID = 1L;
  CounselingDao counselingDao;
  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    counselingDao = (CounselingDao) 웹애플리케이션공용저장소.getAttribute("counselingDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Counseling counseling = new Counseling();

    //    List<Member> memberList = null;

    String user = request.getParameter("counselor.id");
    String user2 = request.getParameter("client.id");
    try {
      Member member = memberDao.findByName(user);
      Member member2 = memberDao.findByName(user2);
      counseling.setClient(member2);
      counseling.setCounselor(member);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    counseling.setDisease(request.getParameter("disease"));
    counseling.setContent(request.getParameter("content"));

    try {

      counselingDao.insert(counseling);
      sqlSession.commit();
      response.setHeader("Refresh", "1;url=list");
      request.getRequestDispatcher("CounselingMemberAdd.jsp").forward(request, response);

    } catch (Exception e) {
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      e.printStackTrace();
      request.setAttribute("error", e);

      // 오류가 발생하면, 오류 내용을 출력할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
      요청배달자.forward(request, response);
    }
  }
}

//  @Override
//  public void execute(CommandRequest request) throws Exception {
//    System.out.println();
//    System.out.println("[상담 신청]페이지입니다.");
//    System.out.println();
//    Member loginUser = AuthLoginHandler.getLoginUser();
//
//    Counseling counseling = new Counseling();
//    List<Member> memberList = null;
//
//
//    System.out.printf("이름: %s\n", loginUser.getName());
//    System.out.printf("연락처: %s\n", loginUser.getPhoneNum());
//    counseling.setClient(loginUser);
//
//    counseling.setDisease(Prompt.inputString("지병여부> "));
//    if(counseling.getDisease().trim().equals("")) {
//      System.out.println("입력하시지 않아 공백으로 지정되었습니다. 상담시 상담사에게 말씀해주세요.");
//      counseling.setDisease("미기입");
//    }
//    counseling.setContent(Prompt.inputString("상담내용> "));
//    if(counseling.getContent().trim().equals("")) {
//      System.out.println("입력하시지 않아 공백으로 지정되었습니다. 상담시 상담사에게 말씀해주세요.");
//      counseling.setContent("미기입");
//    }
//
//    while(true) {
//      System.out.println();
//      System.out.println("상담사 성별> ");
//      System.out.println("1. 여성 상담사");
//      System.out.println("2. 남성 상담사");
//      System.out.println("3. 상관없음");
//      try {
//        int counselorStatus = Prompt.inputInt("선택> ");
//
//        String stateLabel = null;
//        switch (counselorStatus) {
//          case 1:
//            stateLabel = "여";
//            break;
//          case 2:
//            stateLabel = "남";
//            break;
//          case 3:
//            stateLabel = "상관없음";
//            break;
//          default:
//            System.out.println("잘못 입력하셨습니다. 1 ~ 3중에 다시 입력해주세요. ");
//            continue;
//        }
//        if(stateLabel.equals("여")) {
//          memberList = memberDao.findWoman();
//          if(memberList == null) {
//            while(true) {
//              String input = Prompt.inputString("현재 상담 가능한 여성 상담사가 없습니다. 남성 상담사가 상담을 진행해도 괜찮으시겠습니까?(y/N)");
//              if(input.equalsIgnoreCase("y")) {
//                memberList = memberDao.findMan();
//                System.out.println("상담 가능한 남성 상담사가 배정되었습니다.");
//                break;
//              } else if(input.equalsIgnoreCase("n") || input.length() == 0) {
//                System.out.println("상담신청이 취소되었습니다. 상담 가능한 여성 상담사가 생기면 연락드리겠습니다.");
//                break;
//              } else {
//                System.out.println("잘못된 입력입니다.");
//              }
//            }
//          }
//        } else if(stateLabel.equals("남")) {
//          memberList = memberDao.findMan();
//          if(memberList == null) {
//            while(true) {
//              String input = Prompt.inputString("현재 상담 가능한 남성 상담사가 없습니다. 여성 상담사가 상담을 진행해도 괜찮으시겠습니까?(y/N)");
//              if(input.equalsIgnoreCase("y")) {
//                memberList = memberDao.findWoman();
//                System.out.println("상담 가능한 여성 상담사가 배정되었습니다.");
//                break;
//              } else if(input.equalsIgnoreCase("n") || input.length() == 0) {
//                System.out.println("상담신청이 취소되었습니다. 상담 가능한 남성 상담사가 생기면 연락드리겠습니다.");
//                break;
//              } else {
//                System.out.println("잘못된 입력입니다.");
//              }
//            }
//          }
//        } else if(stateLabel.equals("상관없음")) {
//          memberList = memberDao.findDoctor();
//        }
//        int index = (int)(Math.random()*memberList.size());
//        counseling.setCounselor(memberList.get(index));
//        break;
//      } catch(Exception e) {
//        System.out.println("잘못 입력하셨습니다. 1 ~ 3중에 다시 입력해주세요. ");
//      }
//    }
//
//    counselingDao.insert(counseling);
//    sqlSession.commit();
//
//    System.out.println();
//    System.out.println("신청이 완료되었습니다.");
//  }
//
//}
