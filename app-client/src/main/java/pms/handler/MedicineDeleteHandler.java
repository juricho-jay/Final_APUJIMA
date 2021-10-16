package pms.handler;

import java.util.List;
import pms.dao.MedicineDao;
import pms.domain.Medicine;
import util.Prompt;

public class MedicineDeleteHandler implements Command {

  MedicineDao medicineDao;

  public MedicineDeleteHandler(MedicineDao medicineDao) {
    this.medicineDao = medicineDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[약품 삭제] 페이지입니다.");
    System.out.println();

    String name = Prompt.inputString("약품명 (뒤로가기 #)> ");
    if (name.equals("#")) {
      return;
    }
    List<Medicine> medicineList = medicineDao.findAll();

    for (Medicine medicine : medicineList) {
      if (!medicine.getName().contains(name)) {
        System.out.println("검색하신 약품은 없는 약품입니다.");
        continue;
      }

      String input2 = Prompt.inputString(" ❗ 정말 삭제하시겠습니까? (y/N)> ");
      if(input2.equalsIgnoreCase("n") || input2.length() == 0) {
        System.out.println("약품 삭제를 취소하였습니다.");
        return;
      }

      medicineDao.delete(name);

      System.out.println("약품을 삭제하였습니다.");
    }
  }

}