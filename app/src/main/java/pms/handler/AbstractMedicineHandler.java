package pms.handler;

import java.util.List;
import pms.domain.Medicine;

public abstract class AbstractMedicineHandler implements Command{

  List<Medicine> medicineList; 

  public AbstractMedicineHandler(List<Medicine> medicineList) {
    this.medicineList = medicineList; 

  }

  protected Medicine validMedicine(String input) {

    for (Medicine medicine : medicineList) {
      if (medicine.getName().equals(input)) {
        return medicine;
      }
    }
    return null;
  }

}
