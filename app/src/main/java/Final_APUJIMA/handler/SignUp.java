package Final_APUJIMA.handler;

import java.util.LinkedList;
import java.util.List;
import Final_APUJIMA.domain.DoctorMember;
import Final_APUJIMA.domain.Member;
import Final_APUJIMA.util.Prompt;

public class SignUp {

  List<Member> memberList = new LinkedList<>();
  List<DoctorMember> doctorMemberList = new LinkedList<>();
  DoctorMemberHandler doctorMemberHandler = new DoctorMemberHandler(doctorMemberList);
  MemberHandler memberHandler = new MemberHandler(memberList);

  //회원가입메뉴 메서드
  public void doSignup() {
    System.out.println("[회원가입] 페이지입니다. 선택해주세요");
    System.out.println();

    System.out.println("1) 의사 회원가입");
    System.out.println("2) 일반 회원가입");
    System.out.println("3) 뒤로가기");
    int signupNo = (Prompt.inputInt("번호> "));
    System.out.println();

    switch(signupNo) {
      case 1: 
        doctorMemberHandler.add();
        break;
      case 2:
        memberHandler.add();
        break;
      case 3:
        return;
      default:
        System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
    }
  }

}
