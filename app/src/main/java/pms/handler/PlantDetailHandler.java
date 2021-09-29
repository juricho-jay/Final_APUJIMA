package pms.handler;

import java.util.List;
import pms.domain.Plant;
import util.Prompt;

public class PlantDetailHandler extends AbstractPlantHandler{
  public PlantDetailHandler(List<Plant> plantList) {
    super(plantList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {


    System.out.println("[화분 상세보기] 페이지입니다.");
    String name = Prompt.inputString("화분의 이름> ");
    Plant plant = findByPlantName(name);

    if (plant == null) {
      System.out.println("식물이 없습니다.");
      return;
    }
    System.out.println("화분 이름: " + plant.getPlantName());
    System.out.println("화분 주인의 아이디: " + AuthLoginHandler.getLoginUser().getId());
    System.out.println("화분 누적 경험치: " + plant.getExp());
    System.out.println("화분 레벨: " + plant.getLevel());
    System.out.println("화분 모양: " + plant.getShape());
    System.out.println("화분을 키운 날짜: " + plant.getRegisteredDate());
  }

}
