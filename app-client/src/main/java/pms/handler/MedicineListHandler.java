package pms.handler;

import java.util.Collection;
import pms.domain.Medicine;
import request.RequestAgent;

public class MedicineListHandler implements Command {

  RequestAgent requestAgent;

  public MedicineListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[약 리스트]");
    System.out.println();

    requestAgent.request("medicine.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("목록 조회 실패!");
      return;
    }

    Collection<Medicine> medicineList = requestAgent.getObjects(Medicine.class);

    for (Medicine medicine : medicineList) {
      System.out.printf("%s, %d, %s, %s, %s\n",
          medicine.getName(),
          medicine.getAgeLimit(),
          medicine.getEffect(),
          medicine.getShape(),
          medicine.getColor());
    }
    //serachM();

  }

  //  public void searchM() {
  //    while(true) {
  //      System.out.println("1) 의약품 검색");
  //      System.out.println("0) 뒤로가기");
  //      int input0 = Prompt.inputInt("선택> ");
  //      if (input0 == 0) {
  //        return;
  //      }else {
  //        String input = Prompt.inputString("약 이름 검색> ");
  //        System.out.println();
  //        String medicine = findM(input);
  //        if(medicine == null) {
  //          System.out.println("찾는 약이 없습니다.");
  //        }
  //        else {
  //          System.out.println(medicine + ": 안정제 역할을 합니다.");
  //          System.out.println();
  //        }
  //      }
  //    }
  //  }




}