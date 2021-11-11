package apus.servlet;


import java.util.List;
import apus.dao.PlantDao;
import apus.domain.Plant;

public class PlantMyListController implements Command {

  PlantDao plantDao;

  public PlantMyListController(PlantDao plantDao) {
    this.plantDao = plantDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    String loginUser = AuthLoginHandler.getLoginUser().getId();

    int count = 0;
    System.out.println();
    System.out.println("[내 화분 목록]");

    List<Plant> plantList = plantDao.findAll();

    if (plantList == null) {
      System.out.println("목록 불러오기 실패.");
    }

    for (Plant plant :plantList) {
      if(plant.getOwnerName().getId().equals(loginUser)) {
        count++;
        System.out.printf("화분 번호: %d\n", count);
        System.out.printf("화분 이름: %s\n", plant.getPlantName());
        System.out.printf("화분 누적 경험치: %d\n", plant.getExp());
        System.out.printf("화분 레벨: %d\n", plant.getLevel());
        System.out.printf("화분 모양: %s\n", plant.getShape());
        System.out.printf("화분 생성일: %s\n", plant.getRegisteredDate());
        System.out.println();
      }
    }

    if (count == 0) {
      System.out.println("심은 식물이 없습니다.");
    }

  }
}