package pms.dao;

import java.util.List;
import pms.domain.Bucket;

public interface BucketDao {
  void insert(Bucket bucket) throws Exception;
  List<Bucket> findAll() throws Exception;
  List<Bucket> findByKeyword(String keyword) throws Exception;
  Bucket findByNo(int no) throws Exception;
  void update(Bucket bucket) throws Exception;
  void delete(int no) throws Exception;
}
