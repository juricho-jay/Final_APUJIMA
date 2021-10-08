package pms.handler;

import java.util.HashMap;
import pms.domain.Medicine;
import request.RequestAgent;
import util.Prompt;

public class MedicineUpdateHandler implements Command {

  RequestAgent requestAgent;

  public MedicineUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[약품 변경] 페이지입니다.");
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

    Medicine medicine = requestAgent.getObject(Medicine.class);

    String name = Prompt.inputString(String.format("약품 이름(%s)> ", medicine.getName()));
    int ageLimit = Prompt.inputInt(String.format("권장 연령(%d)> ", medicine.getAgeLimit()));
    String shape = Prompt.inputString(String.format("모양(%s)> ", medicine.getShape()));
    String color = Prompt.inputString(String.format("색상(%s)> ", medicine.getColor()));
    String effect = Prompt.inputString(String.format("효과(%s)> ", medicine.getEffect()));

    String input2 = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
    if(input2.equalsIgnoreCase("n") || input2.length() == 0) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }
    //    requestAgent.request("medicine.delete", params);

    medicine.setName(name);
    medicine.setAgeLimit(ageLimit);
    medicine.setShape(shape);
    medicine.setColor(color);
    medicine.setEffect(effect);

    requestAgent.request("medicine.update", medicine);
    //    requestAgent.request("medicine.insert", medicine);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("약품 변경 실패!");
      System.out.println(requestAgent.getObject(String.class));
      return;
    }

    System.out.println("약품 정보를 변경하였습니다.");
  }
}