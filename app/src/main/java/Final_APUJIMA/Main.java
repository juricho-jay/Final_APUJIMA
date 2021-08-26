package Final_APUJIMA;

import java.util.LinkedList;
import java.util.List;
import Final_APUJIMA.domain.DoctorMember;
import Final_APUJIMA.domain.Member;
import Final_APUJIMA.handler.DoctorMemberHandler;
import Final_APUJIMA.handler.MemberHandler;
import Final_APUJIMA.util.Prompt;

public class Main {

  public static void main(String[] args) {

    List<Member> memberList = new LinkedList<>();
    List<DoctorMember> doctormemberList = new LinkedList<>();
    MemberHandler memberHandler = new MemberHandler(memberList);
    DoctorMemberHandler docmemberHandler = new DoctorMemberHandler(doctormemberList);
    while(true) {
      System.out.println("[APUJIMA]에 오신 것을 환영합니다.");
      System.out.println("원하시는 메뉴를 선택해 주세요.");
      System.out.println();
      System.out.println();

      System.out.println("1) 로그인");
      System.out.println("2) 회원가입");
      System.out.println("3) 종료");
      System.out.println("4) 관리자 기능: 아이디 목록 보기");
      int no = (Prompt.inputInt("번호? >"));
      System.out.println();


      switch(no) {
        case 1: System.out.println("[로그인] 페이지입니다.");
        System.out.println("원하시는 메뉴를 선택해주세요.");
        System.out.println();
        System.out.println("1) 의사 로그인");
        System.out.println("2) 일반회원 로그인");
        System.out.println("3) 메인 페이지");


        int LoginNo = (Prompt.inputInt("번호? "));
        System.out.println();

        switch(LoginNo) {
          case 1: 
            docmemberHandler.login();
            break;
          case 2:
            memberHandler.login();
          case 3:
            break;

        }

        //조회했을때 id와 비밀번호가 일치한다면, 로그인 성공

        break;
        case 2: System.out.println("[회원가입] 페이지입니다. 선택해주세요");
        System.out.println();

        System.out.println("1) 의사 회원가입");
        System.out.println("2) 일반 회원가입");
        System.out.println("3) 뒤로가기");
        System.out.println("0) 종료");
        int signupNo = (Prompt.inputInt("번호? "));
        System.out.println();

        switch(signupNo) {
          case 1: 
            docmemberHandler.add();
            break;
          case 2:
            memberHandler.add();
            break;
          case 3:
            break;
          case 0:
            return;
        }
        break;

        case 3: System.out.println("그만두기");
        return;

        case 0: 
          System.out.println("1)의사 회원정보");
          System.out.println("2)회원 회원정보");
          int memno = (Prompt.inputInt("번호?"));
          System.out.println();
          switch(memno){
            case 1:
              docmemberHandler.list();
              break;
            case 2:
              memberHandler.list();
              break;
          }
      }
    }
  }
}

