package pms.handler;

import java.util.List;
import pms.domain.Member;

public class DoctorMemberListHandler extends AbstractMemberHandler{

  public DoctorMemberListHandler(List<Member> memberList) {
    super(memberList);
  }

  public void execute() {
    System.out.println();
    for (Member member : memberList) {
      if (member.getDoctor() == 2) {
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
      }
    }
  }
}