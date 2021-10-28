package apus.dao;

import java.util.List;
import apus.domain.Counseling;

public interface CounselingDao {
  void insert(Counseling counseling) throws Exception;
  List<Counseling> findAll() throws Exception;
  Counseling findByNo(int no) throws Exception;
  Counseling findByName(String name) throws Exception;
  Counseling findById(String id) throws Exception;
  void update(Counseling counseling) throws Exception;
  void delete(String id) throws Exception;
  void check(Counseling counseling) throws Exception;
}