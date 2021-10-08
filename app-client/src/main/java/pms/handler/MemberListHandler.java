package pms.handler;

import java.util.Collection;
import pms.domain.Member;
import request.RequestAgent;

public class MemberListHandler implements Command{

  RequestAgent requestAgent;

  public MemberListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[회원 목록]");

    requestAgent.request("member.selectList", null);


    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("목록 조회 실패!");
      return;
    }

    Collection<Member> memberList = requestAgent.getObjects(Member.class);

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

