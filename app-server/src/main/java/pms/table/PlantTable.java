package pms.table;

import java.util.ArrayList;
import pms.domain.Plant;
import server.DataProcessor;
import server.Request;
import server.Response;

public class PlantTable extends JsonDataTable<Plant> implements DataProcessor {

  public PlantTable() {
    super("plant.json", Plant.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "plant.insert": insert(request, response); break;
      case "plant.selectList": selectList(request, response); break;
      case "plant.selectListByKeyword": selectListByKeyword(request, response); break;
      case "plant.selectOne": selectOne(request, response); break;
      case "plant.update": update(request, response); break;
      case "plant.delete": delete(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }


  private void selectList(Request request, Response response) throws Exception {
    if (list.size() == 0) {
      response.setStatus(Response.FAIL);
    }
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void insert(Request request, Response response) throws Exception {
    Plant plant = request.getObject(Plant.class);
    list.add(plant);
    response.setStatus(Response.SUCCESS);
  }


  private void selectListByKeyword(Request request, Response response) throws Exception {
    String keyword = request.getParameter("keyword");

    ArrayList<Plant> searchResult = new ArrayList<>();
    for (Plant plant : list) {
      if (!plant.getPlantName().contains(keyword) &&
          !plant.getOwnerName().contains(keyword)) {
        continue;
      }
      searchResult.add(plant);
    }

    response.setStatus(Response.SUCCESS);
    response.setValue(searchResult);
  }


  private void selectOne(Request request, Response response) throws Exception {
    String name = request.getParameter("name");
    Plant plant = findByPlantName(name);
    if (plant != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(plant);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 화분이 없습니다.");
    }

  }


  private void update(Request request, Response response) throws Exception {
    Plant plant = request.getObject(Plant.class);

    int index = indexOf(plant.getNo());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 화분을 찾을 수 없습니다.");
      return;
    }

    list.set(index, plant);
    response.setStatus(Response.SUCCESS);
  }

  private void delete(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    int index = indexOf(no);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 화분을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }


  protected Plant findByPlantName(String name) {
    for (Plant plant : list) {
      if (plant.getPlantName().equals(name) ) {
        return plant;
      }
    }
    return null;
  }



  private int indexOf(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}