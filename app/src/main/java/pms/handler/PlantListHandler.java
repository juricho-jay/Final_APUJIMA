package pms.handler;

import java.util.List;
import pms.domain.Plant;

public class PlantListHandler extends AbstractPlantHandler {

  public PlantListHandler(List<Plant> plantList) {
    super(plantList);
  }
  @Override
  public void execute(CommandRequest request) throws Exception {

    int count = 0;


    System.out.println("[화분 목록]");
    System.out.println();

    for (int i = 0 ; i<plantList.size(); i++) {
      System.out.println("화분 이름: " + plantList.get(i).getPlantName());
      System.out.println("화분 주인의 아이디: " + plantList.get(i).getOwnerName());
      count++;
    }

    if(count == 0) {
      System.out.println("화분이 없습니다.");
    }

  }


}
