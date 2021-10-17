package pms.handler;

import java.util.List;
import pms.dao.MedicineDao;
import pms.domain.Medicine;

public class MedicineListHandler implements Command {

  MedicineDao medicineDao;

  public MedicineListHandler(MedicineDao medicineDao) {
    this.medicineDao = medicineDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[약 리스트]");
    System.out.println();

    List<Medicine> medicineList = medicineDao.findAll();

    if (medicineList == null) {
      System.out.println("현재 약품 리스트가 없습니다.");
      return;
    }

    for (Medicine medicine : medicineList) {
      System.out.printf("번호 : %d\n"
          + "약품명 : %s\n"
          + "권장연령 : %d\n"
          + "모양 : %s\n"
          + "색상 :  %s\n"
          + "효능 :  %s\n",
          medicine.getNo(),
          medicine.getName(),
          medicine.getAgeLimit(),
          medicine.getShape(),
          medicine.getColor(),
          medicine.getEffect());
      System.out.println();
    }
    //serachM();

  }
}