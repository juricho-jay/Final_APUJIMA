package pms.handler;


import Menu.Menu;
import pms.dao.MemberDao;
import pms.domain.Member;
import util.Prompt;

public class AuthLoginHandler implements Command {

  MemberDao memberDao;

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

  public AuthLoginHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[로그인]페이지입니다.\n아이디와 비밀번호를 입력하세요.");
    System.out.println("(취소: #)");

    String id = Prompt.inputString("아이디> ");
    if (id.contains("#")) {
      return;
    }
    String password = Prompt.inputString("비밀번호> ");
    System.out.println();

    Member member = memberDao.findByIdPwd(id, password);

    //  Member member = loginTest(Id, password);
    if (member == null) {
      System.out.println("ID와 암호가 일치하지 않습니다.");
      return;
    }

    if(member.getDoctorOrNot() == 3) {
      System.out.println("관리자님, [APUJIMA]에 오신 것을 환영합니다.");
      loginUser = member;
      userAccessLevel = Menu.ACCESS_ADMIN;
      return;
    }

    if(member.getDoctorOrNot() == 2) {
      System.out.println(member.getName()+" 힐러님, [APUJIMA]에 오신 것을 환영합니다.");
      loginUser = member;
      userAccessLevel = Menu.ACCESS_DOCTOR;
      WiseSaying wise = new WiseSaying();
      wise.execute(request);
      return;

    } else if(member.getDoctorOrNot() == 1) {
      System.out.println(member.getName()+"님, [APUJIMA]에 오신 것을 환영합니다.");
      loginUser = member;
      userAccessLevel = Menu.ACCESS_GENERAL;
      WiseSaying wise = new WiseSaying();
      wise.execute(request);
      return;
    } 

  }

}

