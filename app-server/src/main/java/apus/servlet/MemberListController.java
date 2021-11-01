package apus.servlet;

import java.util.List;
import apus.dao.MemberDao;
import apus.domain.Member;

public class MemberListController implements Command{

  MemberDao memberDao;

  public MemberListController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[회원 목록]");


    List<Member> memberList = memberDao.findAll();

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
            member.getDoctor().getMajor(),
            member.getDoctor().getLicense(),
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
