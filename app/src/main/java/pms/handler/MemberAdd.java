package pms.handler;

import java.util.List;
import pms.domain.Member;
import util.Prompt;

public class MemberAdd extends AbstractMember{

  public MemberAdd(List<Member> memberList) {
    super(memberList); // => ?? 생성자 초기화
  }

  public void add() {
    System.out.println("[회원 등록]");
    Member member = new Member();
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

    member.getRegisteredDate();

    memberList.add(member);
    System.out.println();
    System.out.println("회원가입 완료!");
    System.out.println();
  }


}
