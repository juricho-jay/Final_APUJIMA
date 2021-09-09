package pms.handler;


import java.sql.Date;
import java.util.List;
import Menu.Menu;
import pms.domain.Member;
import util.Prompt;

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
    System.out.println();
    System.out.println("[로그인]페이지입니다.\n아이디와 비밀번호를 입력하세요.");
    System.out.println("(취소: #)");

    String Id = Prompt.inputString("아이디> ");
    if (Id.contains("#")) {
      return;
    }
    String password = Prompt.inputString("비밀번호> ");
    System.out.println();

    Member member = loginTest(Id, password);

    if (Id.equals("root") && password.equals("0000")) {
      Member root = new Member();
      root.setName("관리자");
      root.setEmail("admin@test.com");
      root.setId("admin");
      root.setPassword("0000");
      root.setEmail("admin@test.com");
      root.setBirthDay("0000-000--00");
      root.setPhoneNum("010-0000-0000");
      root.setPhoto("1.jpg");
      root.setSex("여");
      root.setRegisteredDate(new Date(System.currentTimeMillis()));
      loginUser = root;
      memberList.add(loginUser);
      userAccessLevel = Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL;
      System.out.println("관리자님, [APUJIMA]에 오신 것을 환영합니다.");
      return;
    } 


    if (member == null) {
      System.out.println();
      System.out.println("ID와 암호가 일치하지 않습니다.");
    } else {
      if(member.getDoctor() == 2) {
        System.out.println(member.getName()+" 힐러님, [APUJIMA]에 오신 것을 환영합니다.");
        loginUser = member;
        userAccessLevel = Menu.ACCESS_DOCTOR;
      } else if(member.getDoctor() == 1) {
        System.out.println(member.getName()+" 회원님, [APUJIMA]에 오신 것을 환영합니다.");
        loginUser = member;
        userAccessLevel = Menu.ACCESS_GENERAL;
      } 
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

