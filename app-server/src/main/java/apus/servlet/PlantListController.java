package apus.servlet;

import java.util.List;
import apus.dao.PlantDao;
import apus.domain.Plant;
import util.Prompt;


public class PlantListController implements Command {
  PlantDao plantDao;

  public PlantListController(PlantDao plantDao ) {
    this.plantDao = plantDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    int findCount = 0;
    System.out.println();
    System.out.println("[화분 목록]");

    List<Plant> plantList = plantDao.findAll();

    if (plantList.size() == 0) {
      System.out.println("화분이 존재하지 않습니다.");
      return;
    }

    for (Plant plant : plantList) {
      System.out.printf("%d, %s, %s\n",
          plant.getNo(),
          plant.getPlantName(),
          plant.getOwnerName().getId());
    }

    while (true) {
      String input = Prompt.inputString("[검색(S) / 뒤로가기(0)] ");

      switch (input) {
        case "S":
        case "s":
          System.out.println("[화분 상세검색] 페이지입니다. ");
          String find = Prompt.inputString("화분/아이디 검색> ");
          for (Plant findplantList : plantList) {
            if (!findplantList.getOwnerName().getId().contains(find) && 
                !findplantList.getPlantName().contains(find)) {
              continue;
            }

            findCount++;
            System.out.println();
            System.out.println("화분 이름: " + findplantList.getPlantName());
            System.out.println("화분 주인: " + findplantList.getOwnerName().getId());
            System.out.println("화분 누적 경험치: " + findplantList.getExp());
            System.out.println("화분 레벨: " + findplantList.getLevel());
            System.out.println("화분 모양: " + findplantList.getShape());
            System.out.println("화분 생성일: " + findplantList.getRegisteredDate());
            System.out.println();
          } 

          if (findCount == 0) {
            System.out.println("입력한 아이디나 화분이 없습니다.");
            return;
          }
          break;

        case "0":
          return;
        default :
          System.out.println("명령어가 올바르지 않습니다!");
      }
    }


  }



}