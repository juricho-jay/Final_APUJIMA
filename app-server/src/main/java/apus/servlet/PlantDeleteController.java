package apus.servlet;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import apus.dao.PlantDao;
import apus.domain.Plant;
import util.Prompt;

public class PlantDeleteController implements Command{

  PlantDao plantDao;
  SqlSession sqlSession;

  public PlantDeleteController(PlantDao plantDao, SqlSession sqlSession) {
    this.plantDao = plantDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[화분 삭제] 페이지입니다.");
    System.out.println();
    String loginUser = AuthLoginHandler.getLoginUser().getId();

    int count = 0;

    List<Plant> plantList = plantDao.findAll();

    if (plantList == null) {
      System.out.println("화분이 존재하지 않습니다.");
      return;
    }

    for (Plant plant : plantList) {
      if (plant.getOwnerName().getId().equals(loginUser)) {
        System.out.printf("%d, %s\n",
            plant.getNo(),
            plant.getPlantName());
        count++;
      }
    }

    if (count == 0) {
      System.out.println("화분이 존재하지 않습니다.");
    }

    int no = Prompt.inputInt("삭제할 화분 번호> ");

    String input = Prompt.inputString("❗ 정말 삭제하시겠습니까? (y/N)> ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("화분 삭제를 취소하였습니다.");
      return;
    }

    plantDao.delete(no);
    sqlSession.commit();

    System.out.println("화분을 삭제하였습니다.");
    return;
  }
}
