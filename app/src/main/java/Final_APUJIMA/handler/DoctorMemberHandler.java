package Final_APUJIMA.handler;

import java.util.List;
import Final_APUJIMA.domain.DoctorMember;
import Final_APUJIMA.util.Prompt;

public class DoctorMemberHandler {
  List<DoctorMember> doctormemberList; 

  public DoctorMemberHandler(List<DoctorMember> doctormemberList) {
    this.doctormemberList = doctormemberList; // => ?? 생성자 초기화
  }
  DoctorMember doctormember = new DoctorMember();



  public void add() {
    System.out.println("[의사 회원 등록]");

    doctormember.setName(Prompt.inputString("이름> "));
    doctormember.setId(Prompt.inputString("아이디> "));
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

  public boolean login() {

    System.out.println("[로그인]페이지입니다.\n아이디와 비밀번호를 입력하세요.");
    String id = Prompt.inputString("아이디> ");
    String password = Prompt.inputString("비밀번호> ");
    DoctorMember Doctormember = vaildLogin(id,password);
    if (Doctormember ==null) {
      System.out.println();
      System.out.println("아이디 또는 비밀번호가 잘못 입력되었습니다.\n"
          + "아이디와 비밀번호를 정확히 입력해 주세요.");
      System.out.println();
      return false;
    }else {
      System.out.println("로그인 성공!");
      System.out.println();
      return true;
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
    System.out.println("[아이디 찾기] 페이지입니다.");
    System.out.println("이름을 입력하세요.");
    String Name = Prompt.inputString("이름> ");
    System.out.println("휴대폰 번호를 입력해주세요.");
    String PhoneNo = Prompt.inputString("휴대폰> ");
    DoctorMember Doctormember = vaildFindID(Name,PhoneNo);
    if(Doctormember == null) {
      System.out.println();
      System.out.println("아이디를 찾을 수 없습니다.");
      System.out.println();
    }else {
      System.out.println();
      System.out.printf ("회원님의 아이디입니다. %s",doctormember.getId());
      System.out.println();

    }
  }

  public void FindPassword() {
    System.out.println("[비밀번호 찾기] 페이지입니다.");
    System.out.println("아이디를 입력하세요.");
    String id = Prompt.inputString("아이디> ");
    System.out.println("휴대폰 번호를 입력해주세요.");
    String PhoneNo = Prompt.inputString("휴대폰> ");
    DoctorMember Doctormember = vaildFindPassword(id,PhoneNo);
    if(Doctormember == null) {
      System.out.println("아이디를 찾을 수 없습니다.");
      System.out.println();
    }else {
      System.out.printf ("회원님의 비밀번호입니다. %s",doctormember.getPassword());
      System.out.println();

    }
  }

  public DoctorMember vaildFindID(String Name,String PhoneNo)   {
    DoctorMember [] arr = doctormemberList.toArray(new DoctorMember[0]);
    for (DoctorMember doctormember : arr) {
      if (doctormember.getName().equals(Name) && (doctormember.getPhoneNum().equals(PhoneNo))) {
        return doctormember;
      }
    }
    return null;
  }

  public DoctorMember vaildFindPassword(String id,String PhoneNo)   {
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