package pms.handler;


import java.util.List;
import pms.domain.Member;
import util.Prompt;
import Menu.Menu;

public class AuthLoginHandler implements Command {

  List<Member> memberList;

  static Member loginUser;
  static int userAccessLevel = Menu.ACCESS_LOGOUT; // 기본은 로그아웃 된 상태이다.
  static Member doctorUser;

  public static Member getLoginUser() {
    return loginUser;
  }
  public static int getUserAccessLevel() {
    return userAccessLevel;
  }
  public static Member doctorUser() { // Member에서 doctorOrNot 변수 값이 2 - 의사
    return doctorUser;          //일반인은 1
  }

  public AuthLoginHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void execute() {
    System.out.println("[로그인] 페이지입니다.");
    String Id = Prompt.inputString("ID> ");
    String password = Prompt.inputString("암호> ");

    Member member = loginTest(Id, password);

    if (Id.equals("root") && password.equals("0000")) {
      Member root = new Member();
      root.setName("관리자");
      root.setEmail("admin@test.com");
      loginUser = root;
      userAccessLevel = Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL;
      return;
    } 


    if (member == null) {
      System.out.println();
      System.out.println("ID와 암호가 일치하지 않습니다.");
    } else {
      System.out.println();
      System.out.printf("%s님 환영합니다!\n", member.getName());
      loginUser = member;
      userAccessLevel = Menu.ACCESS_GENERAL;
    }
  }

  private Member loginTest(String Id, String password) {
    for (Member member : memberList) {
      if (member.getId().equalsIgnoreCase(Id) &&
          member.getPassword().equals(password)) {
        return member;
      }
    }
    return null;
  }

}

