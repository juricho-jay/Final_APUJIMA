package pms.handler;

import java.util.List;
import pms.domain.DoctorMember;
import util.Prompt;

public class DoctorLogin extends AbstractDoctor{

  public DoctorLogin(List<DoctorMember> doctormemberList) {
    super(doctormemberList);
  }

  public void login() {
    while (true) {
      System.out.println();
      System.out.println("[로그인]페이지입니다.\n아이디와 비밀번호를 입력하세요.");
      System.out.println("(취소: #)");
      String id = Prompt.inputString("아이디> ");
      if (id.contains("#")) {
        return;
      }
      String password = Prompt.inputString("비밀번호> ");
      DoctorMember doctormember = vaildLogin(id,password);

      if (doctormember == null) {
        System.out.println();
        System.out.println("아이디 또는 비밀번호가 잘못 입력되었습니다.\n"
            + "아이디와 비밀번호를 정확히 입력해 주세요.");
        System.out.println();
        continue;
      } else {
        System.out.println(doctormember.getName()+" 힐러님, [APUJIMA]에 오신 것을 환영합니다.");
        loginUser = doctormember;
        System.out.println();
        return;
      }
    }
  }

}
