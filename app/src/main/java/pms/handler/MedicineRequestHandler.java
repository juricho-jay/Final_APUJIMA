package pms.handler;

import java.util.LinkedList;
import java.util.List;
import pms.domain.Medicine;
import util.Prompt;

public class MedicineRequestHandler extends AbstractMedicineHandler{

  public MedicineRequestHandler(List<Medicine> medicineList) {
    super(medicineList);
    // TODO Auto-generated constructor stub
  }

  List<Medicine> requestList = new LinkedList<>();

  public void execute() {
    System.out.println();
    System.out.println("[약품 등록 허가]");
    Medicine medicine = new Medicine();
    medicine.setName(Prompt.inputString("약품명> "));
    medicine.setAgeLimit(Prompt.inputInt("권장 나이> "));
    medicine.setShape(Prompt.inputString("모양> "));
    medicine.setShape(Prompt.inputString("색상> "));
    medicine.setShape(Prompt.inputString("효능> "));

    while(true) {
      String input = Prompt.inputString("작성한 약품을 등록요청 하시겠습니까?(y/N)");
      if(input.equalsIgnoreCase("y")) {
        System.out.println("관리자에게 약품등록을 요청하였습니다.");
        requestList.add(medicine);
        // 관리자에게 요청하기

        break;
      } else if(input.equalsIgnoreCase("n") || input.equals("")) {
        System.out.println("등록요청이 취소되었습니다.");
        break;
      }
      else {
        System.out.println("입력이 잘못되었습니다.");
      }
    }


    // medicineList.add(medicine);

    System.out.println();
    System.out.println("약품 등록 완료!");
    System.out.println();
  }
}
