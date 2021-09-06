package pms.handler;

import java.util.List;
import pms.domain.DoctorMember;
import util.Prompt;

public class DoctorFindId extends AbstractDoctor{

  public DoctorFindId(List<DoctorMember> doctormemberList) {
    super(doctormemberList);
    // TODO Auto-generated constructor stub
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
      DoctorMember doctormember = validFindId(Name,PhoneNo);
      if(doctormember == null) {
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

}
