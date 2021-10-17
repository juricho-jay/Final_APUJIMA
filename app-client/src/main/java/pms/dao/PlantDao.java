package pms.dao;

import java.util.List;
import pms.domain.Plant;

public interface PlantDao {
  void insert(Plant plant) throws Exception;
  List<Plant> findAll() throws Exception;
  List<Plant> findByKeyword(String keyword) throws Exception;
  Plant findByName(String name) throws Exception;
  void update(Plant plant) throws Exception;
  void delete(int no) throws Exception;
}
