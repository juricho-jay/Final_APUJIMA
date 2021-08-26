package Final_APUJIMA.handler;
import java.util.List;
import Final_APUJIMA.domain.Member;
import Final_APUJIMA.util.Prompt;

public class MemberHandler {

  List<Member> memberList; //멤버타입의 리스트를 선언.

  public MemberHandler(List<Member> memberList) {
    this.memberList = memberList; // => ?? 생성자 초기화
  }

  Member member = new Member();


  public void add() {
    System.out.println("[회원 등록]");

    System.out.println();
    member.setName(Prompt.inputString("아이디> "));
    member.setPassword(Prompt.inputString("비밀번호> "));
    member.setEmail(Prompt.inputString("전화번호> "));
    member.setPhoneNum(Prompt.inputString("이메일> "));
    member.setBirthDay(Prompt.inputString("생년월일> "));

    memberList.add(member);
    System.out.println("회원가입 완료!");
    System.out.println();
  }

  public void list() {
    System.out.println("[회원 목록]");

    Member[] list = memberList.toArray(new Member[0]);

    for (Member member : list) {
      System.out.printf("%s, %s,\n", 
          member.getName(),
          member.getPassword()
          );
    }
    System.out.println();
  }

  public void login() {
    System.out.println("로그인 해주세요 ");
    String id = Prompt.inputString("아이디?");
    String password = Prompt.inputString("비밀번호");
    Member member = vaildLogin(id,password);
    if (member ==null) {
      System.out.println("회원가입이 되어있지 않습니다.");

    }
    System.out.println();
  }

  public Member vaildLogin(String id, String password) {
    Member [] arr = memberList.toArray(new Member[0]);
    for (Member member : arr) {
      if (member.getName().equals(id) && member.getPassword().equals(password)){
        System.out.println("로그인 성공!");
        System.out.println();
        return member;
      }
    }
    return null;
  }
}
