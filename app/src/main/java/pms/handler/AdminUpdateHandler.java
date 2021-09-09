package pms.handler;

import java.util.List;
import pms.domain.Medicine;
import util.Prompt;

public class AdminUpdateHandler implements Command{

  List<Medicine> requestList;
  List<Medicine> medicineList;


  public AdminUpdateHandler(List<Medicine> requestList, List<Medicine> medicineList) {
    this.requestList = requestList;
    this.medicineList = medicineList;
    // TODO Auto-generated constructor stub
  }

  public void execute() {
    System.out.println();
    System.out.println("[약품 정보 수정]");

    for (int i = 0; i < requestList.size(); i++) {
      System.out.println(requestList.get(i).getName());
      System.out.println(requestList.get(i).getEffect());
      System.out.println();
    }

    String name = Prompt.inputString("수정할 약품의 이름을 입력하세요 > ");
    Medicine medicine = findRequestMedicine(name);

    if (medicine == null) {
      System.out.println("기존 리스트에서 약품을 찾을 수 없습니다.");
    } else {
      String Mname = Prompt.inputString(String.format("약의 이름(%s)> ", medicine.getName()));
      int age = Prompt.inputInt(String.format("약복용 적정나이 (%s)> " ,medicine.getAgeLimit())); 
      String shape = Prompt.inputString(String.format("약의 모양(%s)> ", medicine.getShape()));
      String color  =Prompt.inputString(String.format("약의 색깔(%s)> ", medicine.getColor()));
      String effect = Prompt.inputString(String.format("약의 효과(%s)> ", medicine.getEffect())); 

      String input = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
      if(input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("약 정보 변경을 취소하였습니다.");
        return;
      }
      medicine.setName(Mname);
      medicine.setAgeLimit(age);
      medicine.setShape(shape);
      medicine.setColor(color);
      medicine.setEffect(effect);
      System.out.println("수정이 완료되었습니다.");
    } 
  }



  public Medicine findRequestMedicine(String name)  {
    for (Medicine requestList : requestList) {
      if (requestList.getName().equals(name)) {
        return requestList;
      }
    }
    return null;
  }

}
