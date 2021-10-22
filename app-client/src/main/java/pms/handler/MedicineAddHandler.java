package pms.handler;

import java.util.List;
import pms.dao.MedicineDao;
import pms.domain.Medicine;
import util.Prompt;

public class MedicineAddHandler implements Command{

  MedicineDao medicineDao;

  public MedicineAddHandler(MedicineDao medicineDao) {
    this.medicineDao = medicineDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[약품 등록]");

    Medicine medicine = new Medicine();

    List<Medicine> medicineList = medicineDao.findAll();

    //    if (medicineList == null) {
    //      Medicine.lastIndex = 1;
    //      medicine.setNo(Medicine.lastIndex);
    //    } else if(Medicine.lastIndex != medicineList.size()) {
    //
    //      Medicine.lastIndex = medicineList.get(medicineList.size()-1).getNo();
    //      medicine.setNo(++Medicine.lastIndex);
    //
    //    } else {
    //      medicine.setNo(++Medicine.lastIndex);
    //    }

    while(true) {
      medicine.setName(Prompt.inputString("약품명> " ));

      medicineDao.check(medicine);
      if(medicine.getName() != "")
        break;
    }

    medicine.setAgeLimit(Prompt.inputInt("권장 나이> "));
    medicine.setShape(Prompt.inputString("모  양> "));
    medicine.setColor(Prompt.inputString("색  상> "));
    medicine.setEffect(Prompt.inputString("효  능> "));

    medicineDao.insert(medicine);

    System.out.println("약품을 등록했습니다.");


  }

}