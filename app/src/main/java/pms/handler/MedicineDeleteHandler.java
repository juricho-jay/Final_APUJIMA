package pms.handler;

import java.util.List;
import pms.domain.Medicine;
import util.Prompt;

public class MedicineDeleteHandler extends AbstractMedicineHandler {

  public MedicineDeleteHandler(List<Medicine> medicineList) {
    super(medicineList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[약품 삭제] 페이지입니다.");
    System.out.println();
    String input = Prompt.inputString("약품 이름> ");

    Medicine medicine = validMedicine(input);

    if (medicine == null) {
      System.out.println("해당 이름의 약품이 없습니다.");
      return;
    }

    String input2 = Prompt.inputString(" ❗ 정말 삭제하시겠습니까? (y/N)> ");
    if(input2.equalsIgnoreCase("n") || input2.length() == 0) {
      System.out.printf("%s 약품 삭제를 취소하였습니다.", medicine.getName());
      return;
    }

    medicineList.remove(medicine);
    System.out.printf("%s 약품을 삭제하였습니다." , medicine.getName());
  }
}
