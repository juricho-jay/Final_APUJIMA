package pms.handler;

import java.util.HashMap;
import pms.domain.Plant;
import request.RequestAgent;
import util.Prompt;
// where is plant.grow?
public class PlantGrowHandler implements Command {

  RequestAgent requestAgent;
  public PlantGrowHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[화분 물주기] 페이지입니다.");
    int plusExp = 130;
    //    String name = Prompt.inputString("화분의 이름> ");
    //    Plant plant = findByPlantName(name);

    String name = Prompt.inputString("화분의 이름? ");

    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", String.valueOf(name));

    requestAgent.request("plant.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("찾는 화분이 없습니다. 다시 입력해주세요");
      return;
    }
    Plant plant = requestAgent.getObject(Plant.class);

    if (plant.getOwnerName() != AuthLoginHandler.getLoginUser().getId()) {
      System.out.println("내 화분이 아니어서 물을 줄 수 없습니다.");
      return;
    }



    //    if (plant == null) {
    //      System.out.println("찾는 화분이 없습니다. 다시 입력해주세요");
    //      return;
    //    }

    if(AuthLoginHandler.getLoginUser().getCount() < 30) {
      System.out.println("포인트가 부족하여 물을 줄 수 없습니다.");
      return;
    }

    if(plant.getOwnerName().equals(AuthLoginHandler.getLoginUser().getId())) {
      String input = Prompt.inputString("화분에 물을 주시겠습니까? 30포인트가 차감됩니다. (y/N)> ");

      if(input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("화분 물주기를 취소하였습니다.");
        return;

      }else if (plant.getExp() >= 500 ) {
        System.out.println("화분의 경험치가 가득 차서 물을 줄 수 없습니다.");
        return;

      } else if ( (plant.getExp() + plusExp >= 500)) {
        String check = Prompt.inputString( " 화분에 물을 주어도 " + (500-plant.getExp()) 
            + " 밖에 경험치가 오르지 않습니다"
            + " 그래도 화분에 물을 주시겠습니까? 30포인트가 차감됩니다. (y/N)> ");

        if (check.equalsIgnoreCase("n") || check.length() == 0) {
          System.out.println("화분 물주기를 취소하였습니다.");

        } else if (check.equalsIgnoreCase("y") || check.length() == 0) {
          System.out.println("화분에 물을 주었습니다.");
          plant.setExp(500);
          System.out.println("최대 경험치량 도달! 경험치가 500으로 고정됩니다.");
          AuthLoginHandler.getLoginUser().setCount(AuthLoginHandler.getLoginUser().getCount()-30);
          System.out.println("식물에 물을 주어 30포인트가 사용되었습니다.");
          return;
        }

      } else {
        System.out.println();
        System.out.println( plant.getPlantName() +" 화분에 물을 주었습니다");
        plant.setExp(plant.getExp() + plusExp);

        if (plant.getExp() >= 500) {
          plant.setExp(500);
          System.out.println("최대 경험치량 도달! 경험치가 500으로 고정됩니다.");
          System.out.println();
        }
        AuthLoginHandler.getLoginUser().setCount(AuthLoginHandler.getLoginUser().getCount()-30);

        System.out.println("식물에 물을 주어 30포인트가 사용되었습니다.");
        System.out.println();
      }

      if(plant.getExp() >= 100 && plant.getExp()< 200) {
        System.out.println();
        System.out.println("레벨 업! 1단계 화분으로 성장했습니다!");
        plant.setShape("\u2618");
        plant.setLevel(1);
      }

      else if(plant.getExp() >= 200  && plant.getExp()< 300) {
        System.out.println();
        System.out.println("레벨 업! 2단계 화분으로 성장했습니다!");
        plant.setShape( "\uD83C\uDF38");
        plant.setLevel(2);
      }

      else if(plant.getExp() >= 300) {
        System.out.println();
        System.out.println("레벨 업! 3단계 화분으로 성장했습니다!");
        plant.setShape("\uD83D\uDC90");
        plant.setLevel(3);
      }

      requestAgent.request("plant.update", plant);


      if (plant.getLevel() == 0) {
        System.out.println("1단계까지 필요한 경험치의 양 " + (100 - plant.getExp()));

      } else if (plant.getLevel() == 1) {
        System.out.println("2단계까지 필요한 경험치의 양 " + (200 - plant.getExp()));

      } else if (plant.getLevel() == 2 ) {
        System.out.println("3단계까지 필요한 경험치의 양 " + (300 - plant.getExp()));
      }

      System.out.println("현재까지의 경험치의 양 " + plant.getExp());

    } else {
      System.out.println("본인 화분이 아니어 물을 줄 수 없습니다.");
    }
  }
}