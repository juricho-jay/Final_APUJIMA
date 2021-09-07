package pms.handler;

import java.util.List;
import pms.domain.Member;

public abstract class AbstractMemberHandler implements Command {

  List<Member> memberList; 
  static Member loginUser;

  public AbstractMemberHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  protected Member validLogin(String id, String password) {
    for (Member member : memberList) {
      if (member.getId().equals(id) && member.getPassword().equals(password)){
        System.out.println();
        System.out.println("로그인 성공!");
        loginUser = member;
        System.out.println();
        return member;
      }
    }
    return null;
  }

  protected Member validFindID(String Name,String PhoneNo)   {
    Member [] arr = memberList.toArray(new Member[0]);
    for (Member member : arr) {
      if (member.getName().equals(Name) && (member.getPhoneNum().equals(PhoneNo))) {
        return member;
      }
    }
    return null;
  }

  protected Member validFindPassword(String id,String PhoneNo)   {
    Member [] arr = memberList.toArray(new Member[0]);
    for (Member member : arr) {
      if (member.getId().equals(id) && (member.getPhoneNum().equals(PhoneNo))) {
        return member;
      }
    }
    return null;
  }

}
