package pms.handler;

import java.util.List;
import pms.domain.Member;
import util.Prompt;

public class MemberFindId extends AbstractMemberHandler{

  public MemberFindId(List<Member> memberList) {
    super(memberList); // => ?? 생성자 초기화
  }

  public void execute() {
    while (true) {
      System.out.println("[ID 찾기] 페이지입니다.");
      System.out.println("(취소: #)");
      String Name = Prompt.inputString("이름> ");
      if (Name.contains("#")) {
        return;
      }
      String PhoneNo = Prompt.inputString("휴대폰> ");
      Member member = validFindID(Name, PhoneNo);
      if(member == null) {
        System.out.println("아이디를 찾을 수 없습니다.");
        System.out.println();
        continue;
      }else {
        System.out.println();
        System.out.printf ("회원님의 아이디 입니다. [ %s ]",member.getId());
        System.out.println();
        return;
      }
    }
  }

}
