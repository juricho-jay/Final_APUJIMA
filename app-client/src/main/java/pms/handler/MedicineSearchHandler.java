package pms.handler;

import java.util.Collection;
import java.util.HashMap;
import pms.domain.Medicine;
import request.RequestAgent;
import util.Prompt;

public class MedicineSearchHandler implements Command {

  RequestAgent requestAgent;

  public MedicineSearchHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println();
    String input = Prompt.inputString("검색할 약품명을 입력해주세요 > ");
    System.out.println();

    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", String.valueOf(input));

    requestAgent.request("medicine.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("목록 조회 실패!");
      return;
    }

    Collection<Medicine> medicineList = requestAgent.getObjects(Medicine.class);

    //    int count = 0;
    for (Medicine medicine : medicineList) {
      if (!medicine.getName().contains(input)) {
        continue;
      }
      //      count++;
      System.out.printf("약품명: %s\n", medicine.getName());
      System.out.printf("권장연령: %d\n", medicine.getAgeLimit());
      System.out.printf("모  양: %s\n", medicine.getShape());
      System.out.printf("색  상: %s\n", medicine.getColor());
      System.out.printf("효  능: %s\n", medicine.getEffect());
      System.out.println();

    }
    //    if (count == 0 ) {
    //      System.out.println("찾는 약품이 없습니다.");
    //    }
  }


}