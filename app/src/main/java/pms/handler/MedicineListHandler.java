package pms.handler;

import java.util.List;
import pms.domain.Medicine;

public class MedicineListHandler extends AbstractMedicineHandler{

  public MedicineListHandler(List<Medicine> medicineList) {
    super(medicineList);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("[약 리스트]");
    System.out.println();
    for(int i = 0; i< medicineList.size(); i++) {
      System.out.println(medicineList.get(i).getName());
    }

    //serachM();

  }

  //  public void searchM() {
  //    while(true) {
  //      System.out.println("1) 의약품 검색");
  //      System.out.println("0) 뒤로가기");
  //      int input0 = Prompt.inputInt("선택> ");
  //      if (input0 == 0) {
  //        return;
  //      }else {
  //        String input = Prompt.inputString("약 이름 검색> ");
  //        System.out.println();
  //        String medicine = findM(input);
  //        if(medicine == null) {
  //          System.out.println("찾는 약이 없습니다.");
  //        }
  //        else {
  //          System.out.println(medicine + ": 안정제 역할을 합니다.");
  //          System.out.println();
  //        }
  //      }
  //    }
  //  }




}
