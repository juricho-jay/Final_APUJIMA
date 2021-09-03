package Final_APUJIMA.handler;

import java.sql.Date;
import java.util.List;
import Final_APUJIMA.domain.DoctorMember;
import Final_APUJIMA.util.Prompt;

public class DoctorMemberHandler {
  List<DoctorMember> doctormemberList; 

  public DoctorMemberHandler(List<DoctorMember> doctormemberList) {
    this.doctormemberList = doctormemberList; // => ?? 생성자 초기화

    DoctorMember testUser = new DoctorMember();
    testUser.setName("aaa");
    testUser.setId("aaaaaa");
    testUser.setPassword("1111");
    testUser.setBirthDay("0101");
    testUser.setEmail("ggg@test.com");
    testUser.setPhoneNum("010-1111-1111");
    testUser.setPhoto("aaa.gif");
    testUser.setSex("female");
    testUser.setRegisteredDate(new Date(System.currentTimeMillis()));

    doctormemberList.add(testUser);

    testUser = new DoctorMember();
    testUser.setName("bbb");
    testUser.setId("bbbbbb");
    testUser.setPassword("2222");
    testUser.setBirthDay("0202");
    testUser.setEmail("hhh@test.com");
    testUser.setPhoneNum("010-2222-2222");
    testUser.setPhoto("bbb.gif");
    testUser.setSex("male");
    testUser.setRegisteredDate(new Date(System.currentTimeMillis()));

    doctormemberList.add(testUser);

    testUser = new DoctorMember();
    testUser.setName("ccc");
    testUser.setId("cccccc");
    testUser.setPassword("3333");
    testUser.setBirthDay("0303");
    testUser.setEmail("iii@test.com");
    testUser.setPhoneNum("010-3333-3333");
    testUser.setPhoto("ccc.gif");
    testUser.setSex("female");
    testUser.setRegisteredDate(new Date(System.currentTimeMillis()));

    doctormemberList.add(testUser);
  }


  public void add() {
    System.out.println("[의사 회원 등록]");
    DoctorMember doctormember = new DoctorMember();

    while(true) {
      while(true())
      doctormember.setName(Prompt.inputString("이름> "));
      for(int i = 0; i < doctormember.getName().length(); i++) {
        char a = doctormember.getName().charAt(i);
        if(a>='0' && a <= '9') {
          System.out.println("이름에는 숫자가 들어갈 수 없습니다.");
          continue;
        }
      }
      if (doctormember.getName().contains("#")) {
        System.out.println("이름에는 #을 사용할 수 없습니다.");
      } else {
        break;
      }
    }
    while(true) {
      doctormember.setId(Prompt.inputString("아이디> "));
      if (doctormember.getId().contains("#")) {
        System.out.println("아이디에는 #을 사용할 수 없습니다.");
      } else {
        break;
      }
    }
    doctormember.setPassword(Prompt.inputString("비밀번호> "));
    doctormember.setBirthDay(Prompt.inputString("생년월일> "));
    doctormember.setEmail(Prompt.inputString("이메일> "));
    doctormember.setPhoneNum(Prompt.inputString("전화번호> "));
    doctormember.setPhoto(Prompt.inputString("의사 증명서> "));
    doctormember.setSex(Prompt.inputString("성별> "));
    doctormember.setInterest(Prompt.inputString("관심분야> "));
    doctormember.getRegisteredDate();


    doctormemberList.add(doctormember);

    System.out.println("회원가입 완료!");
    System.out.println("[APUJIMA]의 회원이 되신 것을 환영합니다!");
    System.out.println();
  }

  public void list() {
    System.out.println("[회원 목록]");

    DoctorMember[] list = doctormemberList.toArray(new DoctorMember[0]);

    for (DoctorMember doctormember : list) {
      System.out.printf("%s, %s, %s ,\n", 
          doctormember.getName(),
          doctormember.getId(),
          doctormember.getPassword()
          );
    }
  }

  public int login() {
    while (true) {System.out.println("[로그인]페이지입니다.\n아이디와 비밀번호를 입력하세요.");
    System.out.println("(취소: #)");
    String id = Prompt.inputString("아이디> ");
    if (id.contains("#")) {
      return 0;
    }
    String password = Prompt.inputString("비밀번호> ");
    DoctorMember Doctormember = vaildLogin(id,password);

    if (Doctormember ==null) {
      System.out.println();
      System.out.println("아이디 또는 비밀번호가 잘못 입력되었습니다.\n"
          + "아이디와 비밀번호를 정확히 입력해 주세요.");
      System.out.println();
      continue;
    } else {
      System.out.println(Doctormember.getName()+" 힐러님, [APUJIMA]에 오신 것을 환영합니다.");
      System.out.println();
      return 1;
    }
    }
  }
  public DoctorMember vaildLogin(String id, String password) {
    DoctorMember [] arr = doctormemberList.toArray(new DoctorMember[0]);
    for (DoctorMember doctormember : arr) {
      if (doctormember.getId().equals(id) && doctormember.getPassword().equals(password)) {
        System.out.println();
        System.out.println("로그인 성공!");
        System.out.println();
        return doctormember;
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
      DoctorMember Doctormember = validFindId(Name,PhoneNo);
      if(Doctormember == null) {
        System.out.println();
        System.out.println("아이디를 찾을 수 없습니다.");
        System.out.println();
        continue;
      } else {
        System.out.println();
        System.out.printf ("회원님의 아이디입니다. [ %s ]",doctormember.getId());
        System.out.println();
        return;
      }
    }
  }
  public void FindPassword() {
    while (true) {
      System.out.println("[비밀번호 찾기] 페이지입니다.");
      System.out.println("(취소: #)");
      String id = Prompt.inputString("아이디> ");
      if(id.contains("#")) {
        return;
      }
      String PhoneNo = Prompt.inputString("휴대폰> ");
      DoctorMember Doctormember = validFindPassword(id,PhoneNo);
      if(Doctormember == null) {
        System.out.println("입력이 잘못되었습니다. 정보를 찾을 수 없습니다.");
        System.out.println();
        continue;
      }else {
        System.out.printf ("회원님의 비밀번호입니다. [ %s ]",doctormember.getPassword());
        System.out.println();
        return;
      }
    }
  }

  public DoctorMember validFindId(String Name,String PhoneNo)   {
    DoctorMember [] arr = doctormemberList.toArray(new DoctorMember[0]);
    for (DoctorMember doctormember : arr) {
      if (doctormember.getName().equals(Name) && (doctormember.getPhoneNum().equals(PhoneNo))) {
        return doctormember;
      }
    }
    return null;
  }

  public DoctorMember validFindPassword(String id,String PhoneNo)   {
    DoctorMember [] arr = doctormemberList.toArray(new DoctorMember[0]);
    for (DoctorMember doctormember : arr) {
      if (doctormember.getId().equals(id) && (doctormember.getPhoneNum().equals(PhoneNo))) {
        return doctormember;
      }
    }
    return null;
  }


  public void doctorList() {
    DoctorMember[] doctorMember = new DoctorMember[4];
    DoctorMember doc1 = new DoctorMember();
    DoctorMember doc2 = new DoctorMember();
    DoctorMember doc3 = new DoctorMember();
    DoctorMember doc4 = new DoctorMember();
    System.out.println();

    doc1.setName("조주리");  
    doc1.setInterest("프로그래머 멘탈케어");
    doctorMember[0] = doc1;

    doc2.setName("신현지");  
    doc2.setInterest("조원 멘탈케어");
    doctorMember[1] = doc2;

    doc3.setName("김진현");  
    doc3.setInterest("프로그래밍");
    doctorMember[2] = doc3;

    doc4.setName("김태호");  
    doc4.setInterest("조원들 간식 담당");
    doctorMember[3] = doc4;

    for(int i = 0 ; i < doctorMember.length ; i++) {
      System.out.println((i + 1)+". " + "Dr." + doctorMember[i].getName() + " 전문분야 : " +
          doctorMember[i].getInterest());
    }
    System.out.println();

  }

}