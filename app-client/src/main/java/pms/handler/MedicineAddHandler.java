package pms.handler;

import pms.domain.Medicine;
import request.RequestAgent;
import util.Prompt;

public class MedicineAddHandler implements Command{

  RequestAgent requestAgent;

  public MedicineAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[약품 등록]");

    Medicine medicine = new Medicine();

    medicine.setName(Prompt.inputString("약품명> "));
    // if(약이름이 같으면 이미 있는 약입니다!)
    medicine.setAgeLimit(Prompt.inputInt("권장 나이> "));
    medicine.setShape(Prompt.inputString("모  양> "));
    medicine.setShape(Prompt.inputString("색  상> "));
    medicine.setShape(Prompt.inputString("효  능> "));

    //    medicineList.add(medicine);


    requestAgent.request("medicine.insert", medicine);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println("약품이 등록되었습니다.");
      return;
    }
    System.out.println("약품 등록 실패!");
  }


}