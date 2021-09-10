package pms.handler;

import java.util.List;
import pms.domain.Medicine;
import util.Prompt;

public class AdminApprovalListHandler implements Command {

  List<Medicine> requestList;
  List<Medicine> medicineList;


  public AdminApprovalListHandler(List<Medicine> requestList, List<Medicine> medicineList) {
    this.requestList = requestList;
    this.medicineList = medicineList;
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("[승인 허가 / 신고 글 삭제 내역]");

    for(int i = 0; i< requestList.size(); i++) {
      System.out.printf("약품명 : %s\n 효  능 : %s\n" ,requestList.get(i).getName(), requestList.get(i).getEffect());
    }

    System.out.println("승인 허가할 약품의 이름을 입력하세요.");

    String input = Prompt.inputString("약품명> ");

    for(int i = 0; i< requestList.size(); i++) {
      if(input.equals(requestList.get(i).getName()) ) {
        medicineList.add(requestList.get(i));
        System.out.println("약품이 등록되었습니다.");

        System.out.println();
      }
    }
  }
}
