package apus.dao;

import java.util.List;
import apus.domain.Medicine;

public interface MedicineDao {
  void insert(Medicine medicine) throws Exception;
  List<Medicine> findAll() throws Exception;
  List<Medicine> findActive() throws Exception;
  List<Medicine> findRequest() throws Exception;
  List<Medicine> findByKeyword(String keyword) throws Exception;
  Medicine findByName(String name) throws Exception;
  Medicine findByNo(int no) throws Exception;
  void update(Medicine medicine) throws Exception;
  void requestApprove(Medicine medicine) throws Exception;
  void requestCancle(Medicine medicine) throws Exception;
  void delete(String name) throws Exception;
  void delete2(String name) throws Exception;
}
