package pms.handler;

import pms.dao.MedicineDao;
import pms.dao.RequestDao;
import pms.domain.Medicine;
import util.Prompt;

public class MedicineRequestHandler implements Command {

  MedicineDao medicineDao;
  RequestDao requestDao;

  public MedicineRequestHandler(MedicineDao medicineDao, RequestDao requestDao) {
    this.medicineDao = medicineDao;
    this.requestDao = requestDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[약품 등록 요청]");

    Medicine medicine = new Medicine();


    while(true) {
      medicine.setName(Prompt.inputString("약품명> " ));

      medicineDao.check(medicine);
      if(medicine.getName() != "")
        break;
    }
    medicine.setAgeLimit(Prompt.inputInt("권장연령> "));
    medicine.setShape(Prompt.inputString("모  양> "));
    medicine.setColor(Prompt.inputString("색  상> "));
    medicine.setEffect(Prompt.inputString("효  능> "));

    while(true) {
      String input = Prompt.inputString("작성한 약품을 등록요청 하시겠습니까?(y/N)");
      if(input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("관리자에게 약품등록 요청을 취소하였습니다.");
        return;
      }

      requestDao.insert(medicine);

      System.out.println("관리자에게 약품등록을 요청하였습니다.");
      break;
    }
  }
}




