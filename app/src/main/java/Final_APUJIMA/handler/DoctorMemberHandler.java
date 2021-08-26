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
    String id = Prompt.inputString("아이디?");
    String password = Prompt.inputString("비밀번호");
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
}
//  public void FindId() {
//    
//  }
//  
//  public DoctorMember vaildFindID(String PhoneNo,String ) {
//    DoctorMember [] arr = doctormemberList.toArray(new DoctorMember[0]);
//    for (DoctorMember doctormember : arr) {
//      if (doctormember.getName().equals(id))) {
//  }
//    }
//  }
