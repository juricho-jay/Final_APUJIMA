package Final_APUJIMA.handler;

import java.util.List;
import Final_APUJIMA.domain.DoctorMember;
import Final_APUJIMA.domain.Human;
import Final_APUJIMA.domain.Member;
import Final_APUJIMA.util.Prompt;


public class AuthLoginHandler {

  List<Member> memberList;
  List<DoctorMember> doctorMemberList;

  static Member loginUser;
  public static Member getLoginUser() {
    return loginUser;
  }

  public AuthLoginHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  Human human;

  public int login() {

    while (true) {
      System.out.println("[로그인]페이지입니다.\n아이디와 비밀번호를 입력하세요.");
      System.out.println("(취소: #)");
      String id = Prompt.inputString("아이디> ");
      if (id.contains("#")) {
        return 0;
      }

      String password = Prompt.inputString("비밀번호> ");

      human = loginTest(id, password);

      if (human == null) {
        System.out.println();
        System.out.println("아이디 또는 비밀번호가 잘못 입력되었습니다.\n"
            + "아이디와 비밀번호를 정확히 입력해 주세요.");
        System.out.println();
        continue;
      } else if (human instanceof DoctorMember) {
        System.out.println(human.getName()+" 힐러님, [APUJIMA]에 오신 것을 환영합니다.");
        System.out.println();
        return 1;
      } else if (human instanceof Member) {
        System.out.println(human.getName()+" 회원님, [APUJIMA]에 오신 것을 환영합니다.");
        System.out.println();
        return 1;
      }
    }
  }


  // 로그인 여부 검사
  private Human loginTest(String id, String password) {
    if(human instanceof Member) {
      for (Member member : memberList) {
        if (member.getId().equalsIgnoreCase(id) &&
            member.getPassword().equals(password)) {
          return member;
        }
      }
    } else if(human instanceof DoctorMember) {
      for (DoctorMember member : doctorMemberList) {
        if (member.getId().equalsIgnoreCase(id) &&
            member.getPassword().equals(password)) {
          return member;
        }
      }
    }
    return null;
  }



  public Human findID(String name, String PhoneNum) {
    for (Member member : memberList) {
      if (member.getName().equalsIgnoreCase(name) &&
          member.getPhoneNum().equals(PhoneNum)) {
        return member;
      }
    }
    return null;
  }

  // 로그인
  public Human findPassword(String email, String password) {
    for (Member member : memberList) {
      if (member.getEmail().equalsIgnoreCase(email) &&
          member.getPassword().equals(password)) {
        return member;
      }
    }
    return null;
  }

}







