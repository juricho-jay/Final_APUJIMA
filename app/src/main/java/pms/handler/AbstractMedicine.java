package pms.handler;

import java.util.List;
import pms.domain.Medicine;

public class AbstractMedicine {

  List<Medicine> medicineList; 

  public AbstractMedicine(List<Medicine> medicineList) {
    this.medicineList = medicineList; 


    Medicine testMedicine = new Medicine();
    testMedicine.setName("브린텔릭스");
    testMedicine.setAgeLimit(19);
    testMedicine.setShape("네모");
    testMedicine.setColor("붉은색");
    testMedicine.setEffect("신경 안정제");

    medicineList.add(testMedicine);

    Medicine testMedicine2 = new Medicine();
    testMedicine.setName("아빌리파이");
    testMedicine.setAgeLimit(7);
    testMedicine.setShape("네모");
    testMedicine.setColor("붉은색");
    testMedicine.setEffect("신경 안정제");

    medicineList.add(testMedicine2);

    Medicine testMedicine3 = new Medicine();
    testMedicine.setName("로라반");
    testMedicine.setAgeLimit(19);
    testMedicine.setShape("초록색 동그란 알약");
    testMedicine.setColor("붉은색");
    testMedicine.setEffect("소화제");

    medicineList.add(testMedicine3);

    Medicine testMedicine4 = new Medicine();
    testMedicine.setName("프로작");
    testMedicine.setAgeLimit(12);
    testMedicine.setShape("네모난 알약");
    testMedicine.setColor("붉은색");
    testMedicine.setEffect("수면제");

    medicineList.add(testMedicine4);

    Medicine testMedicine5 = new Medicine();
    testMedicine.setName("알프람");
    testMedicine.setAgeLimit(5);
    testMedicine.setShape("네모");
    testMedicine.setColor("세모");
    testMedicine.setEffect("엔돌핀");

    medicineList.add(testMedicine5);


  }

  protected Medicine validMedicine(String input) {

    for (Medicine medicine : medicineList) {
      if (medicine.equals(input)) {
        return medicine;
      }
    }
    return null;
  }

}
