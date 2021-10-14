package pms.handler;

import java.util.Collection;
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

    String input = Prompt.inputString("약품명 (뒤로가기 #)> ");
    if (input.equals("#")) {
      return;
    }
    Collection<Medicine> medicineList = medicineDao.findAll();

    for (Medicine medicine : medicineList) {
      if (!medicine.getName().contains(input)) {
        continue;
      }

      String input2 = Prompt.inputString(" ❗ 정말 삭제하시겠습니까? (y/N)> ");
      if(input2.equalsIgnoreCase("n") || input2.length() == 0) {
        System.out.println("약품 삭제를 취소하였습니다.");
        return;
      }

      medicineDao.delete(input);

      System.out.println("약품을 삭제하였습니다.");
    }
  }

}