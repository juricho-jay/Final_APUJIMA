package apus.handler;

import java.util.List;
import apus.dao.MedicineDao;
import apus.domain.Medicine;
import util.Prompt;

public class MedicineSearchHandler implements Command {

  MedicineDao medicineDao;

  public MedicineSearchHandler(MedicineDao medicineDao) {
    this.medicineDao = medicineDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[약품 검색] 페이지입니다.");
    System.out.println();
    int count = 0;

    String name = Prompt.inputString("검색어 (뒤로가기 #)> ");
    if (name.equals("#")) {
      return;
    }

    List<Medicine> medicineList = medicineDao.findByKeyword(name);

    for (Medicine medicine : medicineList) {
      if (!medicine.getName().contains(name) &&
          !medicine.getEffect().contains(name)) {
        continue;
      }
      count++;
      System.out.printf("약품명 : %s\n"
          + "권장연령 : %d\n"
          + "모양 : %s\n"
          + "색상 : %s\n"
          + "효능 : %s\n",
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