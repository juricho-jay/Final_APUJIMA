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

    int no = Prompt.inputInt("번호> "); // 지정한 번호를 선택

    // 번호를 no에 저장
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no)); // 해쉬맵에 추가한다.
    // no에는 숫자값을 넣는다.

    requestAgent.request("medicine.selectOne", params); // 약품의 

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    requestAgent.request("medicine.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("약품리스트가 없습니다.");
      return;
    }

    //    int no = (int)request.getAttribute("no"); 
    //
    //    HashMap<String,String> params = new HashMap<>();
    //    params.put("no", String.valueOf(no));
    //
    //    requestAgent.request("medicine.selectList", null);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println("약품리스트가 없습니다.");
    //      return;
    //    }
    //
    //    requestAgent.request("medicine.selectOne", params);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println("해당 번호의 약품이 없습니다!");
    //      return;
    //    }

    String input = Prompt.inputString(" ❗ 정말 삭제하시겠습니까? (y/N)> ");
    if(input.equalsIgnoreCase("n") || input.length() == 0) {
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