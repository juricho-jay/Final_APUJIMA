package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.Member;

public class AbstractMember {

  List<Member> memberList; 
  static Member loginUser;
  //  List<CounselingMember> counselingMemberList; //멤버타입의 리스트를 선언.
  //  CounselingMemberHandler counselingMemberHandler;

  //  public MemberHandler(List<Member> memberList, CounselingMemberHandler counselingMemberHandler) {
  //    this.memberList = memberList; // => ?? 생성자 초기화
  //    this.counselingMemberHandler = counselingMemberHandler;
  //  }

  public AbstractMember(List<Member> memberList) {
    this.memberList = memberList; // => ?? 생성자 초기화

    Member member = new Member();
    member.setName("shin");
    member.setId("aaa");
    member.setPassword("1111");
    member.setEmail("aaa@test.com");
    member.setPhoneNum("010-1111-1111");
    member.setBirthDay("2000-09-01");
    member.setPhoto("a.png");
    member.setSex("여");
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(member);

    member = new Member();
    member.setName("cho");
    member.setId("bbb");
    member.setPassword("2222");
    member.setEmail("bbb@test.com");
    member.setPhoneNum("010-2222-2222");
    member.setBirthDay("2000-10-01");
    member.setPhoto("b.png");
    member.setSex("여");
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(member);

    member = new Member();
    member.setName("kim");
    member.setId("ccc");
    member.setPassword("3333");
    member.setEmail("ccc@test.com");
    member.setPhoneNum("010-3333-3333");
    member.setBirthDay("2000-11-01");
    member.setPhoto("c.png");
    member.setSex("남");
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(member);
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
