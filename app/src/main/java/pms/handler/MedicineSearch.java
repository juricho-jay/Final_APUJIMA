package pms.handler;

import java.util.List;
import pms.domain.Medicine;
import util.Prompt;

public class MedicineSearch extends AbstractMedicine{

  public MedicineSearch(List<Medicine> medicineList) {
    super(medicineList);
  }

  public void medicineSearch() {


    String input = Prompt.inputString("찾을 약의 이름을 입력해주세요 > ");
    System.out.println();
    Medicine medicine = validMedicine(input);
    if(medicine == null) {
      System.out.println("찾는 약이 없습니다.");
    }
    else {
      System.out.printf("약의 이름 : %s 이며 %s 역할을 합니다.", medicine.getName(), medicine.getEffect());
      System.out.println();
    }
  }
}



