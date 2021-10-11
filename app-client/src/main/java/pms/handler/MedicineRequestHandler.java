package pms.handler;

import pms.domain.Medicine;
import request.RequestAgent;
import util.Prompt;

public class MedicineRequestHandler implements Command {

  RequestAgent requestAgent;

  public MedicineRequestHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
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
      if(input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("관리자에게 약품등록 요청을 취소하였습니다.");
        return;
      }
      //      if(input.equalsIgnoreCase("y")) {
      //        System.out.println("관리자에게 약품등록을 요청하였습니다.");

      // 약품 요청은 requestTable 담당
      requestAgent.request("request.insert", medicine);

      //      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      //        System.out.println("약품 등록 요청 실패!");
      //        System.out.println(requestAgent.getObject(String.class));
      //        return;
      //      }

      //      } else if (input.equalsIgnoreCase("n") || input.equals("")) {
      //        System.out.println("등록 요청이 취소되었습니다.");
      //        break;
      //      } else {
      //        System.out.println("입력이 잘못되었습니다."); 
      //      }

      System.out.println("관리자에게 약품등록을 요청하였습니다.");
      break;
    }
  }
}




