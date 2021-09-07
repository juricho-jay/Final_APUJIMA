package pms.handler;

import java.util.List;
import pms.domain.Member;

public class MemberListHandler extends AbstractMemberHandler{

  public MemberListHandler(List<Member> memberList) {
    super(memberList);
  }

  public void execute() {
    System.out.println("[회원 목록]");

    for (Member member : memberList) {
      if (member.getDoctor() == 2) {
        System.out.println("[힐러 회원]");
        System.out.printf("%s, %s\n",
            member.getInterest(),
            member.getDoctorLicense());
      }
      System.out.printf("%s, %s, %s, %s, %s, %s\n",

          member.getName(),
          member.getId(),
          member.getBirthDay(),
          member.getEmail(),
          member.getPhoneNum(),
          member.getPhoto(),
          member.getSex(),
          member.getRegisteredDate());

    }
  }
}