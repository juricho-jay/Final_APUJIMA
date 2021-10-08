package pms.handler;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import pms.domain.Member;
import pms.domain.Plant;
import request.RequestAgent;
import util.Prompt;


public class PlantAddHandler implements Command {

  RequestAgent requestAgent;
  public PlantAddHandler(RequestAgent requestAgent ) {
    this.requestAgent = requestAgent;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println();
    System.out.println("[나만의 화분 만들기]");
    System.out.println();

    Plant plant = new Plant();
    String loginUser = AuthLoginHandler.getLoginUser().getId();
    HashMap<String, String> params = new HashMap<>();
    params.put("id", loginUser);



    if(AuthLoginHandler.getLoginUser().getCount() < 300) {
      System.out.println("포인트가 부족하여 화분을 생성 할 수 없습니다.");
      System.out.println("현재 포인트: " +  AuthLoginHandler.getLoginUser().getCount());
      System.out.println("부족한 포인트: " + (300- AuthLoginHandler.getLoginUser().getCount()));

      return;
    }

    requestAgent.request("plant.selectList", null);


    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      String input = Prompt.inputString("화분 이름> " );
      System.out.println("아직 생성된 화분이 없습니다.");
      plant.setPlantName(input);
      plant.setOwnerName(loginUser);
      plant.setRegisteredDate(new Date(System.currentTimeMillis()));
      plant.setExp(0);
      plant.setLevel(0);
      plant.setShape("\uD83C\uDF31");


      requestAgent.request("plant.insert", plant);
      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("화분 등록 실패!");
        return;
      }
      requestAgent.request("member.selectOne",params);
      Member member = requestAgent.getObject(Member.class);
      member.setCount(member.getCount()-300);
      requestAgent.request("member.update", member);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("포인트 차감 실패!");
        return;
      }
      System.out.println("화분에 씨앗을 심어 300포인트가 차감되었습니다.");
      return;
    }


    List<Plant> plantList =(List<Plant>) requestAgent.getObjects(Plant.class);

    while(true) {
      String input = Prompt.inputString("화분 이름> " );
      // AuthLoginHandler.getLoginUser().plantList().get(i).getPlantName();


      int count = 0;

      for (int i = 0; i < plantList.size(); i++) {
        if (plantList.get(i).getOwnerName().equals(AuthLoginHandler.getLoginUser().getId())) {
          if(input.equals(plantList.get(i).getPlantName())) {
            System.out.println("화분 이름이 중복되어 만들 수 없습니다. 다시 입력해 주세요");
            count++;
          }
        } 
      }

      if (count != 0) {
        continue;
      } else {

        plant.setPlantName(input);
        plant.setOwnerName(loginUser);
        plant.setRegisteredDate(new Date(System.currentTimeMillis()));
        plant.setExp(0);
        plant.setLevel(0);
        plant.setShape("\uD83C\uDF31");
        break;
      }
    }

    requestAgent.request("plant.insert", plant);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("화분 등록 실패!");
      return;
    }
    requestAgent.request("member.selectOne",params);
    Member member = requestAgent.getObject(Member.class);
    member.setCount(member.getCount()-300);
    requestAgent.request("member.update", member);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("포인트 차감 실패!");
      return;
    }
    System.out.println("화분에 씨앗을 심어 300포인트가 차감되었습니다.");


  }
}
