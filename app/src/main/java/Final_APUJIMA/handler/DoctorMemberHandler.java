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

  public void login() {

    System.out.println("로그인 해주세요 ");
    String id = Prompt.inputString("아이디> ");
    String password = Prompt.inputString("비밀번호> ");
    DoctorMember Doctormember = vaildLogin(id,password);
    if (Doctormember ==null) {
      System.out.println("회원가입이 되어있지 않습니다.");
      System.out.println();

    }

  }
  public DoctorMember vaildLogin(String id, String password) {
    DoctorMember [] arr = doctormemberList.toArray(new DoctorMember[0]);
    for (DoctorMember doctormember : arr) {
      if (doctormember.getId().equals(id) && doctormember.getPassword().equals(password)) {
        System.out.println("로그인 성공!");
        System.out.println();
        return doctormember;
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
    DoctorMember Doctormember = vaildFindID(Name,PhoneNo);
    if(Doctormember == null) {
      System.out.println("아이디를 찾을 수 없습니다.");
      System.out.println();
    }else {
      System.out.printf ("회원님의 아이디 입니다. %s",doctormember.getPassword());
      System.out.println();

    }
  }

  public void FindPassword() {
    System.out.println("비밀번호 찾기 페이지입니다.");
    System.out.println("아이디를 입력하세요");
    String id = Prompt.inputString("아이디> ");
    System.out.println("휴대폰 번호를 입력해주세요");
    String PhoneNo = Prompt.inputString("휴대폰> ");
    DoctorMember Doctormember = vaildFindPassword(id,PhoneNo);
    if(Doctormember == null) {
      System.out.println("아이디를 찾을 수 없습니다.");
      System.out.println();
    }else {
      System.out.printf ("회원님의 비밀번호 입니다. %s",doctormember.getPassword());
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
    DoctorMember[] doctorMember = new DoctorMember[3];
    DoctorMember doc1 = new DoctorMember();
    DoctorMember doc2 = new DoctorMember();
    DoctorMember doc3 = new DoctorMember();

    doc1.setName("조주리");  
    doc1.setInterest("멘탈케어");
    doctorMember[0] = doc1;

    doc1.setName("조주리");  
    doc1.setInterest("멘탈케어");
    doctorMember[1] = doc2;

    doc1.setName("조주리");  
    doc1.setInterest("멘탈케어");
    doctorMember[2] = doc3;

    for(int i = 0 ; i < doctorMember.length ; i++) {
      System.out.println((i + 1)+". " + doctorMember[i].getName() + "전문분야 : " +
          doctorMember[i].getInterest());
    }

  }

}