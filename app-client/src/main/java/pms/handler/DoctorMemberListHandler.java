package pms.handler;

import java.util.List;
import pms.dao.MemberDao;
import pms.domain.Member;

public class DoctorMemberListHandler implements Command {

  MemberDao memberDao;

  public DoctorMemberListHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();


    List<Member> memberList = memberDao.findAll();

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