package pms.dao;

import java.util.List;
import pms.domain.Medicine;

public interface RequestDao {
  void insert(Medicine medicine) throws Exception;
  List<Medicine> findAll() throws Exception;
  List<Medicine> findByKeyword(String keyword) throws Exception;
  Medicine findByNo(int no) throws Exception;
  Medicine findByName(String name) throws Exception;
  void update(Medicine medicine) throws Exception;
  void delete(String name) throws Exception;
  void check(Medicine medicine) throws Exception;
}
