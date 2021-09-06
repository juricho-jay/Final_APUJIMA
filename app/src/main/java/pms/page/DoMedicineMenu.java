package pms.page;

import pms.handler.MedicineList;
import util.Prompt;

public class DoMedicineMenu {

  MedicineList medicineList;

  void doMedicineMenu() {
    System.out.println();
    System.out.println("[약국] 페이지입니다. 선택해주세요");
    System.out.println("1) 약 목록");
    System.out.println("0) 뒤로가기");

    int select = Prompt.inputInt("선택> ");
    if (select == 1) {
      medicineList.medicineList();
    } else if(select == 0) {
      return;
    }
    else {
      System.out.println("잘못 선택하셨습니다 ");
    }
    System.out.println();
  }

}
