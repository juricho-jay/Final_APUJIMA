package apus.handler;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import apus.dao.MedicineDao;
import apus.domain.Medicine;
import util.Prompt;

public class AdminApprovalHandler implements Command {

  MedicineDao medicineDao;
  SqlSession sqlSession;

  public AdminApprovalHandler(MedicineDao medicineDao, SqlSession sqlSession) {
    this.medicineDao = medicineDao;
    this.sqlSession = sqlSession;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[약품 등록 허가]");

    // 요청 리스트에서 약품이름 검색하면 약품리스트에 추가하고 요청 리스트에는 삭제
    List<Medicine> requestList = medicineDao.findRequest();
    if (requestList == null) {
      System.out.println("약품 승인 요청건이 없습니다.");
      return;
    }
    for (Medicine medicine : requestList) {
      System.out.printf("약품명 : %s\n"
          + "권장나이 : %d\n"
          + "모  양 : %s\n"
          + "색  상 : %s\n"
          + "효  능 : %s\n"
          + "약품 요청자 : %s\n" ,
          medicine.getName(), medicine.getAgeLimit(), medicine.getShape(),
          medicine.getColor(), medicine.getEffect(), medicine.getRequester().getId());
      System.out.println();
    }

    System.out.println("승인 허가할 약품의 이름을 입력하세요.");

    String name = Prompt.inputString("약품명 (뒤로가기 #)> ");
    if (name.equals("#")) {
      return;
    }
    Medicine medicine = medicineDao.findByName(name);

    String input2 = Prompt.inputString("❗ 정말 승인하시겠습니까? (y/N)> ");
    if(input2.equalsIgnoreCase("n") || input2.length() == 0) {
      medicineDao.requestCancle(medicine);
      sqlSession.commit();
      System.out.println("약품 승인을 취소하였습니다.");
      return;
    }

    medicineDao.requestApprove(medicine);
    sqlSession.commit();

    System.out.println("약품 승인을 완료하였습니다.");

  } 
}

