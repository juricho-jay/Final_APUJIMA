package pms.handler;

import java.util.List;
import pms.domain.Medicine;
import util.Prompt;

public class MedicineDetailHandler extends AbstractMedicineHandler {

  public MedicineDetailHandler(List<Medicine> medicineList) {
    super(medicineList);
    // TODO Auto-generated constructor stub
  }


  public void execute() {
    System.out.println("[상세보기] 페이지입니다.");
    System.out.println();
    String input = Prompt.inputString("약품 이름> ");

    Medicine medicine = validMedicine(input);

    if (medicine == null) {
      System.out.println("해당 이름의 약품이 없습니다.");
      return;
    }
    System.out.printf("이름: %s\n", medicine.getName());
    System.out.printf("권장연령: %d\n", medicine.getAgeLimit());
    System.out.printf("모양: %s\n", medicine.getShape()); // 우리는 익명이기 때문에 Id로
    System.out.printf("색상: %s\n", medicine.getColor());
    System.out.printf("효과: %s\n", medicine.getEffect());

  }
}




