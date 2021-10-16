package pms.dao;

import java.util.List;
import pms.domain.DateCheck;

public interface DateCheckDao {
  void insert(DateCheck dateCheck) throws Exception;
  List<DateCheck> findAll() throws Exception;
  void delete() throws Exception;
}