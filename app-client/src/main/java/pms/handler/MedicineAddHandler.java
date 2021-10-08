package pms.handler;

import java.util.List;
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

    requestAgent.request("medicine.selectList", null);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      Medicine.lastIndex = 1;
      medicine.setNo(Medicine.lastIndex);

    } else {
      List<Medicine> medicineList = (List<Medicine>) requestAgent.getObjects(Medicine.class);

      if(Medicine.lastIndex != medicineList.size()) {

        Medicine.lastIndex = medicineList.get(medicineList.size()-1).getNo();
        medicine.setNo(++Medicine.lastIndex);

      } else {
        medicine.setNo(++Medicine.lastIndex);
      }
    }


    //    medicine.setName(Prompt.inputString("약품명> "));
    // if(약이름이 같으면 이미 있는 약입니다!)
    while(true) {
      String input = Prompt.inputString("약품명> " );
      List<Medicine> medicineList = (List<Medicine>) requestAgent.getObjects(Medicine.class);

      int count = 0;
      for (int i = 0; i < medicineList.size(); i++) {
        if (medicineList.get(i).getName().equals(input)) {
          System.out.println("이미 등록된 약품입니다. 다시 입력해 주세요.");
          count++;
        }
      } 

      if (count != 0) {
        continue;
      } else {

        medicine.setAgeLimit(Prompt.inputInt("권장 나이> "));
        medicine.setShape(Prompt.inputString("모  양> "));
        medicine.setColor(Prompt.inputString("색  상> "));
        medicine.setEffect(Prompt.inputString("효  능> "));


        requestAgent.request("medicine.insert", medicine);

        if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
          System.out.println("약품이 등록되었습니다.");
          return;
        }
        System.out.println("약품 등록 실패!");
      }
    }
  }
}