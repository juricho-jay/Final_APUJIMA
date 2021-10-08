package pms.handler;

import java.util.Collection;
import pms.domain.Member;
import request.RequestAgent;

public class DoctorMemberListHandler implements Command {

  RequestAgent requestAgent;

  public DoctorMemberListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();

    requestAgent.request("member.selectList", null);

    Collection<Member> memberList = requestAgent.getObjects(Member.class);

    for (Member member : memberList) {
      if (member.getDoctorOrNot() == 2) {
        System.out.println("[APUJIMA 의사]");
        System.out.printf("이름 : %s\n"
            + "전문 분야 : %s\n"
            + "이메일 : %s\n"
            + "연락처 : %s\n"
            + "성별 : %s\n",
            member.getName(),
            member.getInterest(),
            member.getEmail(),
            member.getPhoneNum(),
            member.getSex());
        System.out.println();
      }
    }
  }
}