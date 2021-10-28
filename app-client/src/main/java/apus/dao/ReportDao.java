package apus.dao;

import java.util.List;
import apus.domain.Board;

public interface ReportDao {
  void insert(Board freeBoard) throws Exception;
  List<Board> findAll() throws Exception;
  Board findByNo(int no) throws Exception;
  Board findByName(String name) throws Exception;
  void update(Board freeBoard) throws Exception;
  void delete(int no) throws Exception;
  void autoDelete(int no) throws Exception;
}

