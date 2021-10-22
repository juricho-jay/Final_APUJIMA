package pms.handler;

import java.util.List;
import pms.dao.MedicineDao;
import pms.dao.RequestDao;
import pms.domain.Medicine;
import util.Prompt;

public class AdminApprovalHandler implements Command {

  RequestDao requestDao;
  MedicineDao medicineDao;

  public AdminApprovalHandler(RequestDao requestDao, MedicineDao medicineDao) {
    this.requestDao = requestDao;
    this.medicineDao = medicineDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[약품 등록 허가]");

    // 요청 리스트에서 약품이름 검색하면 약품리스트에 추가하고 요청 리스트에는 삭제
    List<Medicine> requestList = requestDao.findAll();
    if (requestList == null) {
      System.out.println("약품 승인 요청건이 없습니다.");
      return;
    }
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

    System.out.println("승인 허가할 약품의 이름을 입력하세요.");

    String name = Prompt.inputString("약품명 (뒤로가기 #)> ");
    if (name.equals("#")) {
      return;
    }
    Medicine medicine = requestDao.findByName(name);

    String input2 = Prompt.inputString("❗ 정말 승인하시겠습니까? (y/N)> ");
    if(input2.equalsIgnoreCase("n") || input2.length() == 0) {
      System.out.println("약품 승인을 취소하였습니다.");
      return;
    }

    List<Medicine> medicineList = medicineDao.findAll();

    //    if (medicineList.size() == 0) {
    //      Medicine.lastIndex = 1;
    //      medicine.setNo(Medicine.lastIndex);
    //    } else if(Medicine.lastIndex != medicineList.size()) {
    //      Medicine.lastIndex = medicineList.get(medicineList.size()-1).getNo();
    //      medicine.setNo(++Medicine.lastIndex);
    //    } else {
    //      medicine.setNo(++Medicine.lastIndex);
    //    }

    medicineDao.insert(medicine);

    System.out.println("약품 승인을 완료하였습니다.");

    requestDao.delete(name);


  } 
}

