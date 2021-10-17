package pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pms.dao.PlantDao;
import pms.domain.Plant;
import request.RequestAgent;

public class NetPlantDao implements PlantDao {

  RequestAgent requestAgent;

  public NetPlantDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Plant plant) throws Exception {
    requestAgent.request("plant.insert", plant);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("화분 심기 실패!");
      return;
    }
  }

  @Override
  public List<Plant> findAll() throws Exception {
    requestAgent.request("plant.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return new ArrayList<>(requestAgent.getObjects(Plant.class));
  }

  @Override
  public List<Plant> findByKeyword(String keyword) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", keyword);
    requestAgent.request("plant.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("화분 검색 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(Plant.class));
  }

  @Override
  public Plant findByName(String name) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("name", String.valueOf(name));

    requestAgent.request("plant.selectOneByName", name);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Plant.class);
  }

  @Override
  public void update(Plant plant) throws Exception {
    requestAgent.request("plant.update", plant);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("화분 업데이트 실패!");
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("plant.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("화분 삭제 실패!");
    }
  }

}
