package pms.handler;

import java.util.List;
import pms.domain.Medicine;
import util.Prompt;

public class MedicineUpdateHandler extends AbstractMedicineHandler {

  public MedicineUpdateHandler(List<Medicine> medicineList) {
    super(medicineList);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void execute() {
    System.out.println("[약품 변경] 페이지입니다.");
    System.out.println();
    String input = Prompt.inputString("약품 이름> ");

    Medicine medicine = validMedicine(input);

    if (medicine == null) {
      System.out.println("해당 이름의 약품이 없습니다.");
      return;
    } 

    String name = Prompt.inputString(String.format("약품 이름(%s)> ", medicine.getName()));
    int ageLimit = Prompt.inputInt(String.format("권장 연령(%d)> ", medicine.getAgeLimit()));
    String shape = Prompt.inputString(String.format("모양(%s)> ", medicine.getShape()));
    String color = Prompt.inputString(String.format("색상(%s)> ", medicine.getColor()));
    String effect = Prompt.inputString(String.format("효과(%s)> ", medicine.getEffect()));

    String input2 = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
    if(input2.equalsIgnoreCase("n") || input2.length() == 0) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }

    medicine.setName(name);
    medicine.setAgeLimit(ageLimit);
    medicine.setShape(shape);
    medicine.setColor(color);
    medicine.setEffect(effect);
    System.out.printf("%s 약품 정보를 변경하였습니다.", medicine.getName());
  }
}
