package apus.dao;

import java.util.List;
import apus.domain.Board;
import apus.domain.Report;

public interface ReportDao {
  void insert(Report report) throws Exception;
  List<Board> findAll() throws Exception;
  Board findByNo(int no) throws Exception;
  Board findByName(String name) throws Exception;
  void update(Report report) throws Exception;
  void delete(int no) throws Exception;
  void autoDelete(int no) throws Exception;
}

