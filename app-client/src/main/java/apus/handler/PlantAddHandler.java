package apus.handler;

import java.sql.Date;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import apus.dao.MemberDao;
import apus.dao.PlantDao;
import apus.domain.Member;
import apus.domain.Plant;
import util.Prompt;


public class PlantAddHandler implements Command {

  PlantDao plantDao;
  MemberDao memberDao;
  SqlSession sqlSession;
  public PlantAddHandler(PlantDao plantDao , MemberDao memberDao, SqlSession sqlSession) {
    this.plantDao = plantDao;
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;
  }

  int plantCount = 0;// 두번째 화분부터 생성할때 사용.
  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println();
    System.out.println("[나만의 화분 만들기]");
    System.out.println();

    Plant plant = new Plant();
    String loginUser = AuthLoginHandler.getLoginUser().getId();

    Member member = memberDao.findById(loginUser);

    if(member.getPoint() < 300) {
      System.out.println("포인트가 부족하여 화분을 생성 할 수 없습니다.");
      System.out.println("현재 포인트: " +  member.getPoint());
      System.out.println("부족한 포인트: " + (300- member.getPoint()));
      return;
    }

    List<Plant> plantList = plantDao.findAll();

    if (plantList == null) {
      String input = Prompt.inputString("화분 이름> " );
      System.out.println("화분을 새로 생성합니다.");
      plant.setPlantName(input);
      plant.setOwnerName(member);
      plant.setRegisteredDate(new Date(System.currentTimeMillis()));
      plant.setExp(0);
      plant.setLevel(0);
      plant.setShape("🌱");



      plantDao.insert(plant);
      member.setPoint(member.getPoint() - 300);
      memberDao.update2(member);
      sqlSession.commit();
      System.out.println("화분에 씨앗을 심어 300포인트가 차감되었습니다.");
      return;



    }

    while(true) {
      String  input = Prompt.inputString("화분 이름> " );


      int count = 0;

      for (int i = 0; i < plantList.size(); i++) {
        if (plantList.get(i).getOwnerName().getId().equals(AuthLoginHandler.getLoginUser().getId())) {
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
        plant.setOwnerName(member);
        plant.setRegisteredDate(new Date(System.currentTimeMillis()));
        plant.setExp(0);
        plant.setLevel(0);
        plant.setShape("🌱");
        break;
      }
    }
    plantDao.insert(plant);
    member.setPoint(member.getPoint()-300);
    memberDao.update2(member);
    sqlSession.commit();
    System.out.println("화분에 씨앗을 심어 300포인트가 차감되었습니다.");


  }
}
