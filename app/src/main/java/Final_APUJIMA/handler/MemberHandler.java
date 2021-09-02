package Final_APUJIMA.handler;

import java.util.List;
import Final_APUJIMA.domain.Member;
import Final_APUJIMA.util.Prompt;

public class MemberHandler {

  List<Member> memberList; //멤버타입의 리스트를 선언.


  public MemberHandler(List<Member> memberList) {
    this.memberList = memberList; // => ?? 생성자 초기화

  }

  public void add() {
    System.out.println("[회원 가입]");
    Member member = new Member();

    while(true) {
      member.setName(Prompt.inputString("이름> "));
      if (member.getName().contains("#")) {       
        System.out.println("이름에는 #을 사용할 수 없습니다.");
      } else {
        break;
      }
    }

    while(true) {
      member.setId(Prompt.inputString("아이디> "));
      if (member.getId().contains("#")) {
        System.out.println("아이디에는 #을 사용할 수 없습니다.");
      } else {
        break;
      }
    }

    member.setPassword(Prompt.inputString("비밀번호> "));
    member.setBirthDay(Prompt.inputString("생년월일> "));
    member.setEmail(Prompt.inputString("이메일> "));
    member.setPhoneNum(Prompt.inputString("전화번호> "));
    member.setPhoto(Prompt.inputString("회원 사진> "));
    member.setSex(Prompt.inputString("성별> "));
    member.getRegisteredDate();

    memberList.add(member);
    System.out.println();
    System.out.println("회원가입 완료!");
    System.out.println();
  }

  public void list() {
    System.out.println("[회원 목록]");

    Member[] list = memberList.toArray(new Member[0]);

    for (Member member : list) {
      System.out.printf("%s, %s, %s,\n", 
          member.getName(),
          member.getId(),
          member.getPassword()
          );
    }
    System.out.println();
  }

  public Member validLogin(String id, String password) {
    Member [] arr = memberList.toArray(new Member[0]);
    for (Member member : arr) {
      if (member.getId().equals(id) && member.getPassword().equals(password)){
        System.out.println();
        System.out.println("로그인 성공!");
        System.out.println();
        return member;
      }
    }
    return null;
  }

}




