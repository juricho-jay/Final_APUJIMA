package pms.handler;

import java.util.List;
import pms.domain.DoctorMember;
import util.Prompt;

public class DoctorAdd extends AbstractDoctor{

  public DoctorAdd(List<DoctorMember> doctormemberList) {
    super(doctormemberList);
    // TODO Auto-generated constructor stub
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
        System.out.println("아이디에는 특수문자를 사용할 수 없습니다.");
        System.out.println();
        continue;
      } 

      for(int i = 0 ; i < doctormemberList.size() ; i++) {
        if(doctormember.getId().equals(doctormemberList.get(i).getId())) {
          System.out.println("중복되는 아이디 입니다. 다른 아이디를 사용해 주세요.");
          doctormember.setId("");
          continue;
        }
      }
      if(doctormember.getId() != "")
        break;
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
  
}
