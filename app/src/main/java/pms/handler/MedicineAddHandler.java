package pms.handler;

import java.util.List;
import pms.domain.Medicine;
import util.Prompt;

public class MedicineAddHandler extends AbstractMedicineHandler{

  public MedicineAddHandler(List<Medicine> medicineList) {
    super(medicineList);


    Medicine testMedicine = new Medicine();
    testMedicine.setName("브린텔릭스");
    testMedicine.setAgeLimit(19);
    testMedicine.setShape("네모");
    testMedicine.setColor("붉은색");
    testMedicine.setEffect("신경 안정제");

    medicineList.add(testMedicine);

    testMedicine = new Medicine();
    testMedicine.setName("아빌리파이");
    testMedicine.setAgeLimit(7);
    testMedicine.setShape("네모");
    testMedicine.setColor("붉은색");
    testMedicine.setEffect("신경 안정제");

    medicineList.add(testMedicine);

    testMedicine = new Medicine();
    testMedicine.setName("로라반");
    testMedicine.setAgeLimit(19);
    testMedicine.setShape("초록색 동그란 알약");
    testMedicine.setColor("붉은색");
    testMedicine.setEffect("소화제");

    medicineList.add(testMedicine);

    testMedicine = new Medicine();
    testMedicine.setName("프로작");
    testMedicine.setAgeLimit(12);
    testMedicine.setShape("네모난 알약");
    testMedicine.setColor("붉은색");
    testMedicine.setEffect("수면제");

    medicineList.add(testMedicine);

    testMedicine = new Medicine();
    testMedicine.setName("알프람");
    testMedicine.setAgeLimit(5);
    testMedicine.setShape("네모");
    testMedicine.setColor("세모");
    testMedicine.setEffect("엔돌핀");

    medicineList.add(testMedicine);


  }

  @Override
  public void execute() {
    System.out.println("[약품 등록]");
    Medicine medicine = new Medicine();
    medicine.setName(Prompt.inputString("이름> "));
    medicine.setAgeLimit(Prompt.inputInt("권장 나이> "));
    medicine.setShape(Prompt.inputString("모양> "));
    medicine.setShape(Prompt.inputString("색상> "));
    medicine.setShape(Prompt.inputString("효능> "));
    medicineList.add(medicine);

    System.out.println();
    System.out.println("약품 등록 완료!");
    System.out.println();
  }


}
