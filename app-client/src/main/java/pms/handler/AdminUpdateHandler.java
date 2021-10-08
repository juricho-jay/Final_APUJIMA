package pms.handler;

import java.util.Collection;
import java.util.HashMap;
import pms.domain.Medicine;
import request.RequestAgent;
import util.Prompt;

public class AdminUpdateHandler implements Command{

  RequestAgent requestAgent;

  public AdminUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[약품 정보 수정]");
    System.out.println();
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


    String input = Prompt.inputString("수정할 약품의 이름을 입력하세요 (뒤로가기 #) > ");
    if(input.equals("#")) {
      return;
    }

    HashMap<String,String> params = new HashMap<>();
    params.put("input", input);

    requestAgent.request("medicine.selectOneByName", params);
    Medicine medicine = requestAgent.getObject(Medicine.class);

    if (medicine == null) {
      System.out.println("기존 리스트에서 약품을 찾을 수 없습니다.");
    } else {
      String Mname = Prompt.inputString(String.format("약품명(%s)> ", medicine.getName()));
      int age = Prompt.inputInt(String.format("적정연령(%d)> " ,medicine.getAgeLimit())); 
      String shape = Prompt.inputString(String.format("모  양(%s)> ", medicine.getShape()));
      String color  =Prompt.inputString(String.format("색  상(%s)> ", medicine.getColor()));
      String effect = Prompt.inputString(String.format("효  능(%s)> ", medicine.getEffect())); 
      while(true) {
        String question = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");

        if(question.equalsIgnoreCase("n") || input.length() == 0) {
          System.out.println("약 정보 변경을 취소하였습니다.");
          return;
        } else if(input.equalsIgnoreCase("y")) {
          medicine.setName(Mname);
          medicine.setAgeLimit(age);
          medicine.setShape(shape);
          medicine.setColor(color);
          medicine.setEffect(effect);
          requestAgent.request("medicine.insult", medicine);
          if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
            System.out.println("수정이 완료되었습니다.");
          }
          return;
        } else {
          System.out.println("입력이 잘못되었습니다. (y/N) 중 하나를 선택해주세요.");
        }
      } 
    }
  }

  //  public Medicine findRequestMedicine(String name)  {
  //    for (Medicine medicine : requestList) {
  //      if (medicine.getName().equals(name)) {
  //        return medicine;
  //      }
  //    }
  //    return null;
  //  }

}
