package pms.dao;

import java.util.List;
import pms.domain.FreeBoard;

public interface ReportDao {
  void insert(FreeBoard freeBoard) throws Exception;
  List<FreeBoard> findAll() throws Exception;
  FreeBoard findByNo(int no) throws Exception;
  FreeBoard findByName(String name) throws Exception;
  void update(FreeBoard freeBoard) throws Exception;
  void delete(int no) throws Exception;
  void autoDelete(int no) throws Exception;
}

