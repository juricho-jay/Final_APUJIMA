package pms.handler;

import java.util.Collection;
import java.util.HashMap;
import pms.domain.Medicine;
import request.RequestAgent;
import util.Prompt;

public class AdminApprovalHandler implements Command {

  RequestAgent requestAgent;

  public AdminApprovalHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[약품 등록 허가]");

    requestAgent.request("request.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("약품 등록 요청건이 없습니다.");
      return;
    } else {
      Collection<Medicine> requestList = requestAgent.getObjects(Medicine.class);

      for (Medicine medicine : requestList) {
        System.out.printf("약품명 : %s\n"
            + "권장나이 : %d\n"
            + "모  양 : %s\n"
            + "색  상 : %s\n"
            + "효  능 : %s\n" ,
            medicine.getName(), medicine.getAgeLimit(), medicine.getShape(),
            medicine.getColor(), medicine.getEffect());
      }

      System.out.println();
    }

    System.out.println("승인 허가할 약품의 이름을 입력하세요.");

    String input = Prompt.inputString("약품명 (뒤로가기 #)> ");
    if (input.equals("#")) {
      return;
    }

    HashMap<String,String> params = new HashMap<>();
    params.put("name", input);

    // 요청 목록에서 승인 허가할 약품 이름 검색> 선택
    requestAgent.request("request.selectOneByName", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("승인 목록에서 해당 약품 이름을 찾을 수 없습니다.");
      return;
    }

    Medicine medicine = requestAgent.getObject(Medicine.class);

    requestAgent.request("medicine.insert", medicine);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println("약품이 등록되었습니다.");
      requestAgent.request("request.delete", params);

    } 
  } 
}

