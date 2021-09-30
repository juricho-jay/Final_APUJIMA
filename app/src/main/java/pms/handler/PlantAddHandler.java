package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.Plant;
import util.Prompt;

public class PlantAddHandler extends AbstractPlantHandler{

  public PlantAddHandler(List<Plant> plantList) {
    super(plantList);
  }


  @Override
  public void execute(CommandRequest request) throws Exception {

    Plant plant = new Plant();


    System.out.println("");
    System.out.println("나만의 화분 만들기 페이지입니다.");
    System.out.println();


    if(AuthLoginHandler.getLoginUser().getCount() < 300) {
      System.out.println("포인트가 부족하여 화분을 생성 할 수 없습니다.");
      System.out.println("현재 포인트: " +  AuthLoginHandler.getLoginUser().getCount());
      System.out.println("부족한 포인트: " + (300- AuthLoginHandler.getLoginUser().getCount()));
      return;
    }

    while(true) {
      String input = Prompt.inputString("화분 이름> " );
      //(if 한 아이디 안에서 중복된 화분 못만들게 ) 
      //AuthLoginHandler.getLoginUser().plantList().get(i).getPlantName();

      for (int i = 0; i<plantList.size(); i++) {
        if (plantList.get(i).getOwnerName().equals(AuthLoginHandler.getLoginUser().getId())) {
          if(input.equals(plantList.get(i).getPlantName())) {
            System.out.println("화분 이름이 중복되어 만들 수 없습니다. 다시 입력해 주세요");
            return;
          }
        } 
      }
      plant.setPlantName(input);
      plant.setOwnerName(AuthLoginHandler.getLoginUser().getId());
      plant.setRegisteredDate(new Date(System.currentTimeMillis()));
      plant.setExp(0);
      plant.setLevel(0);
      plant.setShape("\uD83C\uDF31");
      AuthLoginHandler.getLoginUser().setCount(AuthLoginHandler.getLoginUser().getCount() -300);
      System.out.println("화분에 씨앗이 심어졌습니다!");
      System.out.println("화분에 씨앗을 심어 300포인트가 차감되었습니다.");
      plantList.add(plant);
      return;

    }





  }
}



