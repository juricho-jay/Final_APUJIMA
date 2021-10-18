package pms.dao;

import java.util.List;
import pms.domain.Board;

public interface BoardDao {
  void insert(Board board) throws Exception;
  List<Board> findAll() throws Exception;
  Board findByNo(int no) throws Exception;
  // board findByName(String name) throws Exception;
  void update(Board board) throws Exception;
  void delete(int no) throws Exception;
  List<Board> findByKeyword(String keyword) throws Exception;
  // void check(Board board) throws Exception;
}
