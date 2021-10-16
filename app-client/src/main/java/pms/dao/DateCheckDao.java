package pms.dao;

import java.util.List;
import pms.domain.DateCheck;

public interface DateCheckDao {
  void insert(DateCheck dateCheck) throws Exception;
  List<DateCheck> findAll() throws Exception;
  //  DateCheck findByNo(int no) throws Exception;
  //  DateCheck findByName(String name) throws Exception;
  //  DateCheck findById(String id) throws Exception;
  //  void update(DateCheck dateCheck) throws Exception;
  void delete() throws Exception;
}