package pms.handler;

import java.util.List;
import pms.domain.Member;

public class MemberList extends AbstractMember{

  public MemberList(List<Member> memberList) {
    super(memberList);
  }

  public void list() {
    System.out.println("[회원 목록]");

    for (Member member : memberList) {
      System.out.printf("%s, %s, %s,\n", 
          member.getName(),
          member.getId(),
          member.getPassword()
          );
    }
    System.out.println();
  }


}
