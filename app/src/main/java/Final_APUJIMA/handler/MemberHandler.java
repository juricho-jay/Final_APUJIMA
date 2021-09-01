package Final_APUJIMA.handler;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import Final_APUJIMA.domain.CounselingMember;
import Final_APUJIMA.domain.Member;
import Final_APUJIMA.util.Prompt;

public class MemberHandler {

  List<Member> memberList; //멤버타입의 리스트를 선언.
  Member LoginUser;

  public MemberHandler(List<Member> memberList) {
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

  Member member = new Member();

  static List<CounselingMember> counselingmemberList = new LinkedList<>();
  static CounselingMemberHandler counselingMemberHandler = new CounselingMemberHandler(counselingmemberList);


  public void add() {
    System.out.println("[회원 등록]");
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

  public int login() {
    //MemberHandler memberHandler = new MemberHandler(memberList);

    while (true) {
      System.out.println("[로그인]페이지입니다.\n아이디와 비밀번호를 입력하세요.");
      System.out.println("(취소: #)");

      String id = Prompt.inputString("아이디> ");
      if (id.contains("#")) {
        return 0;
      }
      String password = Prompt.inputString("비밀번호> ");
      Member member = validLogin(id,password);

      if (member == null) {
        System.out.println();
        System.out.println("회원가입이 되어있지 않습니다.");
        System.out.println("다시 로그인 해주세요");
        System.out.println();

        System.out.println("아이디 또는 비밀번호가 잘못 입력되었습니다.\n"
            + "아이디와 비밀번호를 정확히 입력해 주세요.");
        System.out.println();
        continue;
      }else {
        System.out.println(member.getName()+" 회원님, [APUJIMA]에 오신 것을 환영합니다.");
        System.out.println();
        return 1;
      }
    }
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

  public void FindId() {
    while (true) {
      System.out.println("[ID 찾기] 페이지입니다.");
      System.out.println("(취소: #)");
      String Name = Prompt.inputString("이름> ");
      if (Name.contains("#")) {
        return;
      }
      String PhoneNo = Prompt.inputString("휴대폰> ");
      Member member = validFindID(Name, PhoneNo);
      if(member == null) {
        System.out.println("아이디를 찾을 수 없습니다.");
        System.out.println();
        continue;
      }else {
        System.out.println();
        System.out.printf ("회원님의 아이디 입니다. [ %s ]",member.getId());
        System.out.println();
        return;
      }
    }
  }

  public void FindPassword() {
    while (true) {System.out.println("[PW 찾기] 페이지입니다.");
    System.out.println("(취소: #)");
    String id = Prompt.inputString("아이디> ");
    if (id.contains("#")) {
      return;
    }
    String PhoneNo = Prompt.inputString("휴대폰> ");
    Member member = validFindPassword(id, PhoneNo);
    if(member == null) {
      System.out.println("입력이 잘못되었습니다. 정보를 찾을 수 없습니다.");
      System.out.println();
      continue;
    }else {
      System.out.printf ("회원님의 비밀번호입니다. [ %s ]",member.getPassword());
      System.out.println();
      return;
    }
    }
  }

  public Member validFindID(String Name,String PhoneNo)   {
    Member [] arr = memberList.toArray(new Member[0]);
    for (Member member : arr) {
      if (member.getName().equals(Name) && (member.getPhoneNum().equals(PhoneNo))) {
        return member;
      }
    }
    return null;
  }

  public Member validFindPassword(String id,String PhoneNo)   {
    Member [] arr = memberList.toArray(new Member[0]);
    for (Member member : arr) {
      if (member.getId().equals(id) && (member.getPhoneNum().equals(PhoneNo))) {
        return member;
      }
    }
    return null;
  }

  public void displayLoginUser() {
    //MemberHandler memberHandler = new MemberHandler(memberList);


    System.out.println("[내 정보] 페이지입니다.\n ");
    System.out.println(member.getName() + "님 환영합니다!");

    System.out.println("아이디 : "+ member.getId());
    System.out.println("이메일 : "+ member.getEmail());
    System.out.println("생년월일 : "+ member.getBirthDay());
    System.out.println("사진 : "+ member.getPhoto());
    System.out.println("전화번호 : " + member.getPhoneNum());
    System.out.println("성별" + member.getSex());
    counselingMemberHandler.counselingDetail();
  }
}