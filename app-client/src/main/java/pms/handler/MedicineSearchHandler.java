package pms.handler;

import java.util.Collection;
import pms.dao.MedicineDao;
import pms.domain.Medicine;
import util.Prompt;

public class MedicineSearchHandler implements Command {

  MedicineDao medicineDao;

  public MedicineSearchHandler(MedicineDao medicineDao) {
    this.medicineDao = medicineDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {

    int count = 0;
    System.out.println("[약품 검색] 페이지입니다.");
    System.out.println();
    String input = Prompt.inputString("검색어 (뒤로가기 #)> ");
    if (input.equals("#")) {
      return;
    }

    Collection<Medicine> medicineList = medicineDao.findByKeyword(input);

    for (Medicine medicine : medicineList) {
      if (!medicine.getName().contains(input) &&
          !medicine.getEffect().contains(input)) {
        continue;
      }
      count++;
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

    }
    if (count == 0 ) {
      System.out.println("찾는 약품이 없습니다.");
    }
  }


}