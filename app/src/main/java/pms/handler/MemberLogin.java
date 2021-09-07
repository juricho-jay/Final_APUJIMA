package pms.handler;

import java.util.List;
import pms.domain.Member;
import util.Prompt;

public class MemberLogin extends AbstractMemberHandler{

  public MemberLogin(List<Member> memberList) {
    super(memberList);
  }


  public void execute() {

    while (true) {
      System.out.println();
      System.out.println("[로그인]페이지입니다.\n아이디와 비밀번호를 입력하세요.");
      System.out.println("(취소: #)");

      String id = Prompt.inputString("아이디> ");
      if (id.contains("#")) {
        return;
      }
      String password = Prompt.inputString("비밀번호> ");
      Member member = validLogin(id,password);

      if (member == null) {
        System.out.println();
        System.out.println("아이디 또는 비밀번호가 잘못 입력되었습니다.\n"
            + "아이디와 비밀번호를 정확히 입력해 주세요.");
        System.out.println();
        continue;
      }else {
        if(member.getDoctor() == 2) {
          System.out.println(member.getName()+" 힐러님, [APUJIMA]에 오신 것을 환영합니다.");
          loginUser = member;
          System.out.println();
          return;
        } else {
          System.out.println(member.getName()+" 회원님, [APUJIMA]에 오신 것을 환영합니다.");
          loginUser = member;
          System.out.println();
        }
      }
    }
  }

}
