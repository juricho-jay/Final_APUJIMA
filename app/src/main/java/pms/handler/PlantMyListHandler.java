package pms.handler;

import java.util.List;
import pms.domain.Plant;

public class PlantMyListHandler extends AbstractPlantHandler {

  public PlantMyListHandler(List<Plant> plantList) {
    super(plantList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    int count = 0;
    int plantNo =0;
    System.out.println();
    System.out.println("[내 화분 목록]");
    System.out.println();



    for (int i = 0 ; i<plantList.size(); i++) {

      if (plantList.get(i).getOwnerName().equals(AuthLoginHandler.getLoginUser().getId())) {
        plantNo++;
        System.out.println();
        System.out.println("화분 번호: " + plantNo);
        System.out.println("화분 이름: " + plantList.get(i).getPlantName());
        System.out.println("화분 누적 경험치: " + plantList.get(i).getExp());
        System.out.println("화분 레벨: " + plantList.get(i).getLevel());
        System.out.println("화분 모양: " + plantList.get(i).getShape());
        System.out.println("화분을 키운 날짜: " + plantList.get(i).getRegisteredDate());
        count++;
      }
    }
    if(count == 0) {
      System.out.println("화분이 없습니다.");
    }
  }
}