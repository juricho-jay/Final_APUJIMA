package apus.handler;

import apus.dao.MemberDao;
import apus.domain.Member;
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
      String input = Prompt.inputString("[내정보 변경(U) / 회원 탈퇴(D) / 뒤로가기(0)]");

      switch (input) {
        case "U":
        case "u":
          request.getRequestDispatcher("/member/update").forward(request);
          return;
        case "D":
        case "d":
          request.getRequestDispatcher("/member/delete").forward(request);
          return;
        case "0":
          return;
        default:
          System.out.println("명령어가 올바르지 않습니다!");
      }
    }

  }
}