package pms.handler;

import java.util.List;
import pms.domain.Medicine;

public class MedicineList extends AbstractMedicine{

  public MedicineList(List<Medicine> medicineList) {
    super(medicineList);
    // TODO Auto-generated constructor stub
  }

  public void medicineList() {
    System.out.println();
    System.out.println("[약 리스트]");
    System.out.println();
    for(int i = 0; i< medicineList.size(); i++) {
      System.out.println(medicineList.get(i).getName());
    }
  }

}
