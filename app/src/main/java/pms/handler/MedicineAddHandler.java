package pms.handler;

import java.util.List;
import pms.domain.Medicine;
import util.Prompt;

public class MedicineAddHandler extends AbstractMedicineHandler{

  public MedicineAddHandler(List<Medicine> medicineList) {
    super(medicineList);

  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[약품 등록]");
    Medicine medicine = new Medicine();
    medicine.setName(Prompt.inputString("약품명> "));
    // if(약이름이 같으면 이미 있는 약입니다!)
    medicine.setAgeLimit(Prompt.inputInt("권장 나이> "));
    medicine.setShape(Prompt.inputString("모  양> "));
    medicine.setShape(Prompt.inputString("색  상> "));
    medicine.setShape(Prompt.inputString("효  능> "));
    medicineList.add(medicine);

    System.out.println();
    System.out.println("약품 등록 완료!");
    System.out.println();
  }


}
