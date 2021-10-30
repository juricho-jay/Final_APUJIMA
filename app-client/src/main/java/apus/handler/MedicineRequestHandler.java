package apus.handler;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import apus.dao.MedicineDao;
import apus.domain.Medicine;
import util.Prompt;

public class MedicineRequestHandler implements Command {

  MedicineDao medicineDao;
  SqlSession sqlSession;

  public MedicineRequestHandler(MedicineDao medicineDao, SqlSession sqlSession) {
    this.medicineDao = medicineDao;
    this.sqlSession = sqlSession;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[약품 등록 요청]");
    System.out.println();

    Medicine medicine = new Medicine();
    List<Medicine> medicineList = medicineDao.findAll();

    String input = "";
    while(true) {
      input = Prompt.inputString("약품명> ");

      if(input.equals("#")) {
        break;
      }

      for (Medicine m : medicineList) {
        if (m.getName().equals(input)) {
          System.out.println("이미 등록된 약품입니다.");
          return;
        }
      }

      if(medicine.getName() != "") {
        break;
      }
    }

    medicine.setName(input);
    medicine.setAgeLimit(Prompt.inputInt("권장연령> "));
    medicine.setShape(Prompt.inputString("모  양> "));
    medicine.setColor(Prompt.inputString("색  상> "));
    medicine.setEffect(Prompt.inputString("효  능> "));
    medicine.setActive(0);
    medicine.setCheck(0);
    medicine.setRequester(AuthLoginHandler.getLoginUser());

    while(true) {
      String input2 = Prompt.inputString("작성한 약품을 등록요청 하시겠습니까?(y/N)");
      if(input2.equalsIgnoreCase("n") || input2.length() == 0) {
        System.out.println("관리자에게 약품등록 요청을 취소하였습니다.");
        return;
      }

      //      requestDao.insert(medicine);
      //      sqlSession.commit();

      medicineDao.insert(medicine);
      sqlSession.commit();
      System.out.println("관리자에게 약품등록을 요청하였습니다.");

      break;
    }
  }
}




