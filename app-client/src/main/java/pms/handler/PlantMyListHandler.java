package pms.handler;


import java.util.List;
import pms.domain.Plant;
import request.RequestAgent;

public class PlantMyListHandler implements Command {

  RequestAgent requestAgent;
  public PlantMyListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    String loginUser = AuthLoginHandler.getLoginUser().getId();


    int count = 0;
    System.out.println();
    System.out.println("[내 화분 목록]");



    requestAgent.request("plant.selectList", null);

    if (loginUser == null) {
      System.out.println("로그인 하지 않았습니다.");
      return;
    }

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("목록 불러오기 실패.");
    }

    List<Plant> plantList =(List<Plant>) requestAgent.getObjects(Plant.class);

    for (Plant plant :plantList) {
      if(plant.getOwnerName().equals(loginUser)) {
        count++;
        System.out.printf("화분 번호: %d\n", count);
        System.out.printf("화분 이름: %s\n", plant.getPlantName());
        System.out.printf("화분 누적 경험치: %d\n", plant.getExp());
        System.out.printf("화분 레벨: %d\n", plant.getLevel());
        System.out.printf("화분 모양: %s\n", plant.getShape());
        System.out.printf("화분을 키운 날짜: %s\n", plant.getRegisteredDate());
      }
    }

    if (count == 0) {
      System.out.println("심은 식물이 없습니다.");
    }

  }
}