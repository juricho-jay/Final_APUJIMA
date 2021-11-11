package apus.servlet;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import apus.dao.MemberDao;
import apus.dao.PlantDao;
import apus.domain.Member;
import apus.domain.Plant;
import util.Prompt;
// Line 61 : null.
public class PlantGrowController implements Command {
  PlantDao plantDao;
  MemberDao memberDao;
  SqlSession sqlSession;


  public PlantGrowController(PlantDao plantDao , MemberDao memberDao, SqlSession sqlSession) {
    this.plantDao = plantDao;
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[화분 물주기] 페이지입니다.");
    int count = 0;
    int plusExp = 130;
    String loginUser = AuthLoginHandler.getLoginUser().getId();

    Member member = memberDao.findById(loginUser);

    if(member.getPoint() < 30) {
      System.out.println("포인트가 부족하여 물을 줄 수 없습니다.");
      return;
    }

    List<Plant> plantList = plantDao.findAll();

    if (plantList == null) {
      System.out.println("화분이 존재하지 않습니다.");
      return;
    }

    for (Plant plant : plantList) {
      if(plant.getOwnerName().getId().equals(loginUser)) {
        System.out.printf("화분 이름: %s\n", plant.getPlantName());
        System.out.printf("화분 누적 경험치: %d\n", plant.getExp());
        System.out.printf("화분 레벨: %d\n", plant.getLevel());
        System.out.printf("화분 모양: %s\n", plant.getShape());
        System.out.printf("화분 생성일: %s\n", plant.getRegisteredDate());
        System.out.println();
        count++;
      }
    }

    if (count == 0) {
      System.out.println("화분이 존재하지 않습니다.");
    }

    int count2 = 0;
    String name = "";
    Plant growPlant = null;
    while (true) {
      name = Prompt.inputString("물을 줄 화분의 이름> ");

      for (int i = 0; i < plantList.size(); i++) {
        if (plantList.get(i).getOwnerName().getId().equals(loginUser) &&
            plantList.get(i).getPlantName().equals(name)) {
          growPlant = plantList.get(i);
          count2++;
          break;
        }
      }

      if (count2 == 0) {
        System.out.println("해당 이름의 화분을 찾을 수 없습니다.");
        return;
      }
      break;
    }

    String input = Prompt.inputString("화분에 물을 주시겠습니까? 30포인트가 차감됩니다. (y/N)> ");

    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("화분 물주기를 취소하였습니다.");
      return;

    } else if (growPlant.getExp() >= 500 ) {
      System.out.println("화분의 경험치가 가득 차서 물을 줄 수 없습니다.");
      return;

    } else if ((growPlant.getExp() + plusExp >= 500)) {
      String check = Prompt.inputString( "화분에 물을 주어도 " + (500 - growPlant.getExp()) 
          + " 밖에 경험치가 오르지 않습니다.\n"
          + " 그래도 화분에 물을 주시겠습니까? 30포인트가 차감됩니다. (y/N)> ");

      if (check.equalsIgnoreCase("n") || check.length() == 0) {
        System.out.println("화분 물주기를 취소하였습니다.");

      } else if (check.equalsIgnoreCase("y") || check.length() == 0) {
        System.out.println("화분에 물을 주었습니다.");
        growPlant.setExp(500);
        plantDao.update(growPlant);
        System.out.println("최대 경험치량 도달! 경험치가 500으로 고정됩니다.");
        member.setPoint(member.getPoint()-30);
        memberDao.update2(member);
        System.out.println("식물에 물을 주어 30포인트가 사용되었습니다.");
        sqlSession.commit();
        return;
      }

    } else {
      System.out.println();
      System.out.println(growPlant.getPlantName() +" 화분에 물을 주었습니다");
      growPlant.setExp(growPlant.getExp() + plusExp);
      plantDao.update(growPlant);
      member.setPoint(member.getPoint()-30);
      memberDao.update2(member);
      System.out.println("식물에 물을 주어 30포인트가 사용되었습니다.");
      System.out.println();
      sqlSession.commit();
    }

    if(growPlant.getExp() >= 100 && growPlant.getExp() < 200) {
      System.out.println();
      System.out.println("레벨 업! 1단계 화분으로 성장했습니다!");
      growPlant.setShape("☘️");
      growPlant.setLevel(1);
      plantDao.update(growPlant);
      sqlSession.commit();
    }

    else if(growPlant.getExp() >= 200  && growPlant.getExp() < 300) {
      System.out.println();
      System.out.println("레벨 업! 2단계 화분으로 성장했습니다!");
      growPlant.setShape("🌳");
      growPlant.setLevel(2);
      plantDao.update(growPlant);
      sqlSession.commit();
    }

    else if(growPlant.getExp() >= 300) {
      System.out.println();
      System.out.println("레벨 업! 3단계 화분으로 성장했습니다!");
      growPlant.setShape("💐");
      growPlant.setLevel(3);
      plantDao.update(growPlant);
      sqlSession.commit();
    }

    plantDao.update(growPlant);
    sqlSession.commit();


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
