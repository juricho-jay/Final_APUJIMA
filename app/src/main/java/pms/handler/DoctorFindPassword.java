package pms.handler;

import java.util.List;
import pms.domain.DoctorMember;
import util.Prompt;

public class DoctorFindPassword extends AbstractDoctor{

  public DoctorFindPassword(List<DoctorMember> doctormemberList) {
    super(doctormemberList);
    // TODO Auto-generated constructor stub
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
      DoctorMember doctormember = validFindPassword(id,PhoneNo);
      if(doctormember == null) {
        System.out.println("입력이 잘못되었습니다. 정보를 찾을 수 없습니다.");
        System.out.println();
        continue;
      }else {
        int num = (int)(Math.random() * 1000000);
        System.out.println("임시 비밀번호를 발급해 드리겠습니다.");
        System.out.printf("임시 비밀번호는 %d 입니다. 임시 비밀번호로 로그인 해주세요.", num);
        doctormember.setPassword(Integer.toString(num));
        System.out.println();
        return;
      }
    }
  }

}
