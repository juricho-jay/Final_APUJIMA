package pms.handler;

import java.util.List;
import pms.dao.MemberDao;
import pms.dao.PlantDao;
import pms.domain.Member;
import pms.domain.Plant;
import util.Prompt;
// Line 61 : null.
public class PlantGrowHandler implements Command {
  PlantDao plantDao;
  MemberDao memberDao;


  public PlantGrowHandler(PlantDao plantDao , MemberDao memberDao) {
    this.plantDao = plantDao;
    this.memberDao = memberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[화분 물주기] 페이지입니다.");
    int plusExp = 130;

    String loginUser = AuthLoginHandler.getLoginUser().getId();

    Member member = memberDao.findById(loginUser);

    if(member.getPoint() < 30) {
      System.out.println("포인트가 부족하여 물을 줄 수 없습니다.");
      return;
    }

    List<Plant> plantList = plantDao.findAll();


    for (Plant plant :plantList) {
      if(plant.getOwnerName().equals(loginUser)) {
        System.out.printf("화분 이름: %s\n", plant.getPlantName());
        System.out.printf("화분 누적 경험치: %d\n", plant.getExp());
        System.out.printf("화분 레벨: %d\n", plant.getLevel());
        System.out.printf("화분 모양: %s\n", plant.getShape());
        System.out.printf("화분을 키운 날짜: %s\n", plant.getRegisteredDate());
        System.out.println();
      }
    }


    String name = Prompt.inputString("물을 줄 화분의 이름> ");
    Plant growPlant = null;
    for (int i = 0; i < plantList.size(); i++) {

      if (plantList.get(i).getOwnerName().equals(loginUser) && plantList.get(i).getPlantName().equals(name)) {
        growPlant = plantList.get(i);
        break;
      }
    }




    String input = Prompt.inputString("화분에 물을 주시겠습니까? 30포인트가 차감됩니다. (y/N)> ");

    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("화분 물주기를 취소하였습니다.");
      return;

    }else if (growPlant.getExp() >= 500 ) {
      System.out.println("화분의 경험치가 가득 차서 물을 줄 수 없습니다.");
      return;

    } else if ( (growPlant.getExp() + plusExp >= 500)) {
      String check = Prompt.inputString( " 화분에 물을 주어도 " + (500-growPlant.getExp()) 
          + " 밖에 경험치가 오르지 않습니다"
          + " 그래도 화분에 물을 주시겠습니까? 30포인트가 차감됩니다. (y/N)> ");

      if (check.equalsIgnoreCase("n") || check.length() == 0) {
        System.out.println("화분 물주기를 취소하였습니다.");

      } else if (check.equalsIgnoreCase("y") || check.length() == 0) {
        System.out.println("화분에 물을 주었습니다.");
        growPlant.setExp(500);
        plantDao.update(growPlant);
        System.out.println("최대 경험치량 도달! 경험치가 500으로 고정됩니다.");
        member.setPoint(member.getPoint()-30);
        memberDao.update(member);
        System.out.println("식물에 물을 주어 30포인트가 사용되었습니다.");
        return;
      }

    } else {
      System.out.println();
      System.out.println(growPlant.getPlantName() +" 화분에 물을 주었습니다");
      growPlant.setExp(growPlant.getExp() + plusExp);
      plantDao.update(growPlant);
      member.setPoint(member.getPoint()-30);
      memberDao.update(member);
      System.out.println("식물에 물을 주어 30포인트가 사용되었습니다.");
      System.out.println();
    }

    if(growPlant.getExp() >= 100 && growPlant.getExp()< 200) {
      System.out.println();
      System.out.println("레벨 업! 1단계 화분으로 성장했습니다!");
      growPlant.setShape("\u2618");
      growPlant.setLevel(1);
      plantDao.update(growPlant);
    }

    else if(growPlant.getExp() >= 200  && growPlant.getExp()< 300) {
      System.out.println();
      System.out.println("레벨 업! 2단계 화분으로 성장했습니다!");
      growPlant.setShape( "\uD83C\uDF38");
      growPlant.setLevel(2);
      plantDao.update(growPlant);
    }

    else if(growPlant.getExp() >= 300) {
      System.out.println();
      System.out.println("레벨 업! 3단계 화분으로 성장했습니다!");
      growPlant.setShape("\uD83D\uDC90");
      growPlant.setLevel(3);
      plantDao.update(growPlant);
    }

    plantDao.update(growPlant);


    if (growPlant.getLevel() == 0) {
      System.out.println("1단계까지 필요한 경험치의 양 " + (100 - growPlant.getExp()));

    } else if (growPlant.getLevel() == 1) {
      System.out.println("2단계까지 필요한 경험치의 양 " + (200 - growPlant.getExp()));

    } else if (growPlant.getLevel() == 2 ) {
      System.out.println("3단계까지 필요한 경험치의 양 " + (300 - growPlant.getExp()));
    }

    System.out.println("현재까지의 경험치의 양 " + growPlant.getExp());

  } 
}
