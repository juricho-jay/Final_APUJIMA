package Final_APUJIMA.handler;

import Final_APUJIMA.util.Prompt;

public class HealerHandler {

  DoctorMemberHandler doctorHandler;
  CounselingMemberHandler counselingMemberHandler;

  public void doHealerMenu() {
    System.out.println("[HEALER] 페이지입니다. 선택해주세요");
    System.out.println();
    System.out.println("1) 의사 리스트");
    System.out.println("2) 상담 신청하기");

    int select = Prompt.inputInt("선택> ");
    if (select == 1) {
      doctorHandler.doctorList();  
    } else if (select == 2) {
      counselingMemberHandler.counselingadd();
    }
  }
}
