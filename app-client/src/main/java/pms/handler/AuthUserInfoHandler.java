package pms.handler;

import Menu.Menu;
import pms.dao.MemberDao;
import pms.domain.Member;
import util.Prompt;


public class AuthUserInfoHandler implements Command {

  MemberDao memberDao;

  public AuthUserInfoHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[내정보] 페이지입니다.");

    String loginUserId = AuthLoginHandler.getLoginUser().getId();


    Member loginUser = memberDao.findById(loginUserId);

    if (loginUser.getDoctorOrNot() == 2) {
      System.out.printf("%s 힐러님 환영합니다!\n", loginUser.getName());
      System.out.printf("전문 분야: %s\n", loginUser.getDoctor().getMajor());
      System.out.printf("의료인 면허 증명서: %s\n", loginUser.getDoctor().getLicense());
    } else {
      System.out.printf("%s님 환영합니다!\n", loginUser.getName());
    }
    System.out.printf("아이디: %s\n", loginUser.getId());
    System.out.printf("이메일: %s\n", loginUser.getEmail());
    System.out.printf("생년월일: %s\n", loginUser.getBirthDay());
    System.out.printf("사진: %s\n", loginUser.getPhoto());
    System.out.printf("전화: %s\n", loginUser.getPhoneNum());
    System.out.printf("성별: %s\n", loginUser.getSex());
    System.out.printf("가입일: %s\n", loginUser.getRegisteredDate());
    System.out.println("남은 포인트: " + loginUser.getPoint());


    while(true) {
      String input = Prompt.inputString("[회원 탈퇴(D) / 뒤로가기(0)]");

      switch (input) {
        case "D":
        case "d":
          String status = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) ");
          if (status.equalsIgnoreCase("n") || status.length() == 0) {
            System.out.println("회원 삭제를 취소하였습니다.");
            return;
          }

          memberDao.delete(loginUserId);

          AuthLoginHandler.loginUser = null;
          AuthLoginHandler.userAccessLevel = Menu.ACCESS_LOGOUT;
          System.out.println("회원 탈퇴가 완료되었습니다. 그동안 이용해주셔서 감사합니다.");
          return;
        case "0":
          return;
        default:
          System.out.println("명령어가 올바르지 않습니다!");
      }
    }

  }
}