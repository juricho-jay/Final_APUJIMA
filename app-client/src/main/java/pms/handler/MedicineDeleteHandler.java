package pms.handler;

import java.util.HashMap;
import request.RequestAgent;
import util.Prompt;

public class MedicineDeleteHandler implements Command {

  RequestAgent requestAgent;

  public MedicineDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[약품 삭제] 페이지입니다.");
    System.out.println();

    System.out.println("삭제할 의약품을 적어주세요.");
    String name = Prompt.inputString("약품명> : ");

    HashMap<String,String> params = new HashMap<>();
    params.put("name", name);

    requestAgent.request("medicine.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 약품이 없습니다.");
      return;
    }

    String input2 = Prompt.inputString(" ❗ 정말 삭제하시겠습니까? (y/N)> ");
    if(input2.equalsIgnoreCase("n") || input2.length() == 0) {
      System.out.println("약품 삭제를 취소하였습니다.");
      return;
    }
    requestAgent.request("medicine.delete", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("약품 삭제 실패!");
      System.out.println(requestAgent.getObject(String.class));
      return;
    }

    System.out.println("약품을 삭제하였습니다.");
  }

}