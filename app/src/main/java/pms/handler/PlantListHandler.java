package pms.handler;

import java.util.List;
import pms.domain.Plant;
import util.Prompt;

public class PlantListHandler extends AbstractPlantHandler {

  public PlantListHandler(List<Plant> plantList) {
    super(plantList);
  }
  @Override
  public void execute(CommandRequest request) throws Exception {

    int count = 0;
    int findCount = 0;


    System.out.println("[화분 목록]");
    System.out.println();


    for (int i = 0 ; i<plantList.size(); i++) {
      System.out.println("화분 이름: " + plantList.get(i).getPlantName());
      System.out.println("화분 주인의 아이디: " + plantList.get(i).getOwnerName());
      count++;
    }

    if(count == 0) {
      System.out.println("화분이 없습니다.");
      return;
    }

    while (true) {
      String input = Prompt.inputString("[검색(S) / 뒤로가기(0)] ");

      switch (input) {
        case "S":
        case "s":
          System.out.println("[화분 상세검색] 페이지입니다. ");
          String find = Prompt.inputString("검색할 화분이나 Id를 입력해주세요> ");
          for(Plant plantList : plantList) {
            if(!plantList.getOwnerName().contains(find) &&  !plantList.getPlantName().contains(find)) {
              continue;
            }
            findCount++;
            System.out.println();
            System.out.println("화분 이름: " + plantList.getPlantName());
            System.out.println("화분 주인: " + plantList.getOwnerName());
            System.out.println("화분 누적 경험치: " + plantList.getExp());
            System.out.println("화분 레벨: " + plantList.getLevel());
            System.out.println("화분 모양: " + plantList.getShape());

            System.out.println("화분을 키운 날짜: " + plantList.getRegisteredDate());
            System.out.println();
          } if (findCount == 0) {
            System.out.println("찾는 ID나 화분이 없습니다.");
          }



        case "0" :
          return;
        default :
          System.out.println("명령어가 올바르지 않습니다!");
      }
    }


  }



}
