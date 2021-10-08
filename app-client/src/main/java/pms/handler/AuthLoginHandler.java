package pms.handler;


import java.util.HashMap;
import Menu.Menu;
import pms.domain.Member;
import request.RequestAgent;
import util.Prompt;

public class AuthLoginHandler implements Command {

  RequestAgent requestAgent;

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

  public AuthLoginHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
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

    HashMap<String,String> params = new HashMap<>();
    params.put("id", id);
    params.put("password", password);

    requestAgent.request("member.selectOneByIdPassword", params);

    //  Member member = loginTest(Id, password);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      Member member = requestAgent.getObject(Member.class);

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

    } else {
      System.out.println();
      System.out.println("ID와 암호가 일치하지 않습니다.");
    }
  }

}

