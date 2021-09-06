package pms.page;

import pms.handler.CounselingAdd;
import pms.handler.CounselingDetail;
import pms.handler.DoctorList;
import util.Prompt;

public class DoHealerMenu {

  DoctorList doctorList;
  CounselingAdd counselingAdd;
  CounselingDetail counselingDetail;

  void doHealerMenu() {
    while(true) {
      System.out.println("[HEALER] 페이지입니다. 선택해주세요");
      System.out.println();
      System.out.println("1) 의사 리스트");
      System.out.println("2) 상담 신청하기");
      //  if(counselingmemberList.size() > 0) {
      System.out.println("3) 상담신청서 확인하기");
      // }
      System.out.println("0) 뒤로가기");
      int select = Prompt.inputInt("선택> ");
      if (select == 1) {
        doctorList.list(); 
      } else if (select == 2) {
        counselingAdd.counselingadd();
      } else if (select == 0) {
        return;
      } 
      else if (select == 3) {
        counselingDetail.counselingDetail();
      }
    }
  }

}
