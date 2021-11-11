package apus.dao;

import java.util.List;
import apus.domain.DateCheck;

public interface DateCheckDao {
  void insert(DateCheck dateCheck) throws Exception;
  List<DateCheck> findAll() throws Exception;
  DateCheck findByMemberAndDate(int no) throws Exception;
}