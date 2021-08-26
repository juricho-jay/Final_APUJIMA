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

    member.setName(Prompt.inputString("이름> "));
    member.setId(Prompt.inputString("아이디> "));
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

  public boolean login() {
    System.out.println("[로그인]페이지입니다.\n아이디와 비밀번호를 입력하세요.");
    String id = Prompt.inputString("아이디> ");
    String password = Prompt.inputString("비밀번호> ");
    Member member = vaildLogin(id,password);

    if (member ==null) {
      System.out.println();
      System.out.println("회원가입이 되어있지 않습니다.");
    return false;
    }else {
      System.out.println();
      return true;
    }

  }

  public Member vaildLogin(String id, String password) {
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
 
  public void FindId() {
    System.out.println("아이디 찾기 페이지입니다.");
    System.out.println("이름을 입력하세요");
    String Name = Prompt.inputString("이름> ");
    System.out.println("휴대폰 번호를 입력해주세요");
    String PhoneNo = Prompt.inputString("휴대폰> ");
    Member member = vaildFindID(Name, PhoneNo);
    if(member == null) {
      System.out.println("아이디를 찾을 수 없습니다.");
      System.out.println();
    }else {
      System.out.printf ("회원님의 아이디 입니다. %s",member.getId());
      System.out.println();

    }
  }

  public void FindPassword() {
    System.out.println("비밀번호 찾기 페이지입니다.");
    System.out.println("아이디를 입력하세요");
    String id = Prompt.inputString("아이디> ");
    System.out.println("휴대폰 번호를 입력해주세요");
    String PhoneNo = Prompt.inputString("휴대폰> ");
    Member member = vaildFindPassword(id, PhoneNo);
    if(member == null) {
      System.out.println("입력이 잘못되었습니다. 정보를 찾을 수 없습니다.");
      System.out.println();
    }else {
      System.out.printf ("회원님의 비밀번호 입니다. %s",member.getPassword());
      System.out.println();

    }
  }

  public Member vaildFindID(String Name,String PhoneNo)   {
    Member [] arr = memberList.toArray(new Member[0]);
    for (Member member : arr) {
      if (member.getName().equals(Name) && (member.getPhoneNum().equals(PhoneNo))) {
        return member;
      }
    }
    return null;
  }

  public Member vaildFindPassword(String id,String PhoneNo)   {
    Member [] arr = memberList.toArray(new Member[0]);
    for (Member member : arr) {
      if (member.getId().equals(id) && (member.getPhoneNum().equals(PhoneNo))) {
        return member;
      }
    }
    return null;
  }
//  
//  public void LoginInfo() {
//    String Id = member.getId();
//    Member member = validLoginInfo(Id);
//    if( member.getId() != null ) {
//      System.out.println(member.getId() + "님 ");
//    }
//  }
//  
//  public Member validLoginInfo(String ID) {
//    Member [] arr = memberList.toArray(new Member[0]);
//    for (Member member : arr) {
//      if (member.getId().equals(ID)) {
//        return member;
//      }
//  }
//    return null;
//}
}