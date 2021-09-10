package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.Member;
import util.Prompt;

public class MemberAddHandler extends AbstractMemberHandler{

  public MemberAddHandler(List<Member> memberList) {
    super(memberList); 

    //    Member testUser = new Member();
    //    testUser.setName("shin");
    //    testUser.setDoctor(2);
    //    testUser.setId("aaa");
    //    testUser.setPassword("1111");
    //    testUser.setEmail("aaa@test.com");
    //    testUser.setPhoneNum("010-1111-1111");
    //    testUser.setBirthDay("2000-09-01");
    //    testUser.setPhoto("a.png");
    //    testUser.setSex("여");
    //    testUser.setInterest("심리학");
    //    testUser.setDoctorLicense("심리치료사 1급");
    //    testUser.setRegisteredDate(new Date(System.currentTimeMillis()));
    //
    //    memberList.add(testUser);

    //    testUser = new Member();
    //    testUser.setName("cho");
    //    testUser.setId("bbb");
    //    testUser.setPassword("2222");
    //    testUser.setEmail("bbb@test.com");
    //    testUser.setPhoneNum("010-2222-2222");
    //    testUser.setBirthDay("2000-10-01");
    //    testUser.setPhoto("b.png");
    //    testUser.setSex("여");
    //    testUser.setRegisteredDate(new Date(System.currentTimeMillis()));

    //    memberList.add(testUser);
    //
    //    testUser = new Member();
    //    testUser.setName("kim");
    //    testUser.setId("ccc");
    //    testUser.setPassword("3333");
    //    testUser.setEmail("ccc@test.com");
    //    testUser.setPhoneNum("010-3333-3333");
    //    testUser.setBirthDay("2000-11-01");
    //    testUser.setPhoto("c.png");
    //    testUser.setSex("남");
    //    testUser.setRegisteredDate(new Date(System.currentTimeMillis()));
    //
    //    memberList.add(testUser);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("[회원가입] 페이지입니다.");
    Member member = new Member();

    while (true) {
      member.setDoctor(Prompt.inputInt("1.일반 2.의사 선택> "));
      if (member.getDoctor() == 1) {
        break;
      } else if (member.getDoctor() == 2) {
        member.setDoctorLicense(Prompt.inputString("의료인 면허 증명서> "));
        member.setInterest(Prompt.inputString("전문 분야> "));
        break;
      } else {
        System.out.println("1 or 2 중에 하나로 입력해 주세요.");
        continue;
      }
    }

    while(true) {
      member.setName(Prompt.inputString("이름> "));
      if (member.getName().contains("#")) {
        System.out.println("이름에는 특수문자를 사용할 수 없습니다.");
        System.out.println();
      } else {
        break;
      }
    }
    while(true) {
      member.setId(Prompt.inputString("아이디> "));
      if (member.getId().contains("#")) {
        System.out.println("아이디에는 특수문자를 사용할 수 없습니다.");
        System.out.println();
        continue;
      } 

      for(int i = 0 ; i < memberList.size() ; i++) {
        if(member.getId().equals(memberList.get(i).getId())) {
          System.out.println("중복되는 아이디 입니다. 다른 아이디를 사용해 주세요.");
          member.setId("");
          continue;
        }
      }
      if(member.getId() != "")
        break;
    }

    member.setPassword(Prompt.inputString("비밀번호> "));
    member.setBirthDay(Prompt.inputString("생년월일> "));
    while(true) {
      member.setEmail(Prompt.inputString("이메일> "));
      if(member.getEmail().contains("@") && member.getEmail().length() > 2) {
        break;
      } else {
        System.out.println("올바른 양식으로 이메일을 작성해주세요.(@만 입력하실 수 없습니다.)");
      }
    }
    member.setPhoneNum(Prompt.inputString("전화번호> "));
    member.setPhoto(Prompt.inputString("회원 사진> "));

    while(true) {
      member.setSex(Prompt.inputString("성별(남/여)> "));
      if(member.getSex().equalsIgnoreCase("남") || member.getSex().equalsIgnoreCase("여")) {
        break;
      } else {
        System.out.println("남 or 여 중에 하나로 다시 입력해주세요");
      }
    }

    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(member);
    System.out.println();
    System.out.println("회원가입 완료!");
  }


}
