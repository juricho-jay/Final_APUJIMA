package pms.handler;

import java.util.List;
import pms.domain.Medicine;
import util.Prompt;

public class MedicineRequestHandler implements Command{
  List<Medicine> requestList;

  public MedicineRequestHandler(List<Medicine> requestList) {
    this.requestList = requestList;

  }


  @Override
  public void execute(CommandRequest request) {
    System.out.println();
    System.out.println("[약품 등록 요청]");
    Medicine medicine = new Medicine();
    medicine.setName(Prompt.inputString("약품명> "));
    medicine.setAgeLimit(Prompt.inputInt("권장연령> "));
    medicine.setShape(Prompt.inputString("모  양> "));
    medicine.setColor(Prompt.inputString("색  상> "));
    medicine.setEffect(Prompt.inputString("효  능> "));

    while(true) {
      String input = Prompt.inputString("작성한 약품을 등록요청 하시겠습니까?(y/N)");
      if(input.equalsIgnoreCase("y")) {
        System.out.println("관리자에게 약품등록을 요청하였습니다.");
        requestList.add(medicine);
        break;
      } else if(input.equalsIgnoreCase("n") || input.equals("")) {
        System.out.println("등록요청이 취소되었습니다.");
        break;
      }
      else {
        System.out.println("입력이 잘못되었습니다.");
      }
    }

  }


}
