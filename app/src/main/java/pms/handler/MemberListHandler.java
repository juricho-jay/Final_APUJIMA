package pms.handler;

import java.util.List;
import pms.domain.Member;

public class MemberListHandler extends AbstractMemberHandler{

  public MemberListHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void execute() {
    System.out.println("[회원 목록]");
    for (Member member : memberList) {
      if (member.getDoctorOrNot() == 2) {
        System.out.print("[힐러 회원] ");
        System.out.printf("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n",
            member.getName(),
            member.getId(),
            member.getBirthDay(),
            member.getEmail(),
            member.getPhoneNum(),
            member.getPhoto(),
            member.getSex(),
            member.getInterest(),
            member.getDoctorLicense(),
            member.getRegisteredDate());
      } else if(member.getDoctorOrNot() == 1) {
        System.out.print("[일반 회원] ");
        System.out.printf("%s, %s, %s, %s, %s, %s, %s, %s\n",

            member.getName(),
            member.getId(),
            member.getBirthDay(),
            member.getEmail(),
            member.getPhoneNum(),
            member.getPhoto(),
            member.getSex(),
            member.getRegisteredDate());
      } else if(member.getDoctorOrNot() == 3) {
        System.out.print("[관  리  자] ");
        System.out.printf("%s, %s, %s, %s, %s, %s, %s, %s\n",

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
}