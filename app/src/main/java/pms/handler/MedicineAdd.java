package pms.handler;

import java.util.List;
import pms.domain.Medicine;
import util.Prompt;

public class MedicineAdd extends AbstractMedicine{

  public MedicineAdd(List<Medicine> medicineList) {
    super(medicineList);
  }

  public void medicineadd() {
    System.out.println("[약품 등록]");
    Medicine medicine = new Medicine();
    medicine.setName(Prompt.inputString("이름> "));
    medicine.setAgeLimit(Prompt.inputInt("권장 나이> "));
    medicine.setShape(Prompt.inputString("모양> "));
    medicine.setShape(Prompt.inputString("색상> "));
    medicine.setShape(Prompt.inputString("효능> "));
    medicineList.add(medicine);

    System.out.println();
    System.out.println("약품 등록 완료!");
    System.out.println();
  }


}
