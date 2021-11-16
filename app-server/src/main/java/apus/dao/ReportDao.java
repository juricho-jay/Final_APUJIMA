package apus.dao;

import java.util.List;
import apus.domain.Report;

public interface ReportDao {
  void insert(Report report) throws Exception;
  List<Report> findAll() throws Exception;
  Report findByNo(int no) throws Exception;
  Report findByName(String name) throws Exception;
  Report findByReport(int no, String name) throws Exception;
  void confirmReport(int no) throws Exception;
  void update(Report report) throws Exception;
  void delete(int no) throws Exception;
  void autoDelete(int no) throws Exception;
}

