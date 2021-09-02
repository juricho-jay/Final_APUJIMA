package Final_APUJIMA.handler;

import java.sql.Date;
import java.util.List;
import Final_APUJIMA.domain.DoctorMember;
import Final_APUJIMA.util.Prompt;

public class DoctorMemberHandler {

  List<DoctorMember> doctormemberList; //멤버타입의 리스트를 선언.

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
      doctormember.setName(Prompt.inputString("이름> "));
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


  public void doctorList() {
    DoctorMember[] doctorMember = new DoctorMember[10];
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