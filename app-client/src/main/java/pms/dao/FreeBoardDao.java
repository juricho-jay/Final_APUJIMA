package pms.dao;

import java.util.List;
import pms.domain.FreeBoard;

public interface FreeBoardDao {
  void insert(FreeBoard freeBoard) throws Exception;
  List<FreeBoard> findAll() throws Exception;
  FreeBoard findByNo(int no) throws Exception;
  FreeBoard findByName(String name) throws Exception;
  void update(FreeBoard freeBoard) throws Exception;
  void delete(int no) throws Exception;
  List<FreeBoard> findByKeyword(String keyword) throws Exception;
  // void check(FreeBoard freeBoard) throws Exception;
}
