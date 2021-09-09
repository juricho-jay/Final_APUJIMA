package pms.handler;

import java.util.List;
import pms.domain.Medicine;
import util.Prompt;

public class MedicineRequestHandler implements Command{
  List<Medicine> requestList;

  public MedicineRequestHandler(List<Medicine> requestList) {
    this.requestList = requestList;

    Medicine testRequest = new Medicine();
    testRequest.setName("자낙스");
    testRequest.setAgeLimit(15);
    testRequest.setShape("타원형");
    testRequest.setColor("주황");
    testRequest.setEffect("불안 장애 치료 및 완화");

    requestList.add(testRequest);

    testRequest = new Medicine();
    testRequest.setName("리보트릴");
    testRequest.setAgeLimit(15);
    testRequest.setShape("원형");
    testRequest.setColor("주황");
    testRequest.setEffect("간질, 공황 장애");

    requestList.add(testRequest);

  }


  @Override
  public void execute() {
    System.out.println();
    System.out.println("[약품 등록 요청]");
    Medicine medicine = new Medicine();
    medicine.setName(Prompt.inputString("약품명> "));
    medicine.setAgeLimit(Prompt.inputInt("권장 나이> "));
    medicine.setShape(Prompt.inputString("모양> "));
    medicine.setColor(Prompt.inputString("색상> "));
    medicine.setEffect(Prompt.inputString("효능> "));

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
