package apus.handler;

import org.apache.ibatis.session.SqlSession;
import apus.dao.MedicineDao;
import apus.domain.Medicine;
import util.Prompt;

public class MedicineDeleteHandler implements Command {

  MedicineDao medicineDao;
  SqlSession sqlSession;

  public MedicineDeleteHandler(MedicineDao medicineDao, SqlSession sqlSession) {
    this.medicineDao = medicineDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[약품 삭제] 페이지입니다.");
    System.out.println();

    String name = Prompt.inputString("약품명 (뒤로가기 #)> ");
    if (name.equals("#")) {
      return;
    }
    Medicine medicine = medicineDao.findByName(name);

    if (medicine == null) {
      System.out.println("해당 약품이 없습니다.");
      return;
    }

    String input2 = Prompt.inputString(" ❗ 정말 삭제하시겠습니까? (y/N)> ");
    if(input2.equalsIgnoreCase("n") || input2.length() == 0) {
      System.out.println("약품 삭제를 취소하였습니다.");
      return;
    }

    medicineDao.delete(name);
    sqlSession.commit();

    System.out.println("약품을 삭제하였습니다.");
  }
}

