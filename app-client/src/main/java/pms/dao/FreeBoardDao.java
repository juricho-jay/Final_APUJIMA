package pms.dao;

import java.util.List;
import pms.domain.FreeBoard;

// 역할
// - 게시글 데이터를 처리하는 객체 사용법을 정의한다.
public interface FreeBoardDao {
  void insert(FreeBoard freeBoard) throws Exception;
  List<FreeBoard> findAll() throws Exception;
  List<FreeBoard> findByKeyword(String keyword) throws Exception;
  FreeBoard findByNo(int no) throws Exception;
  void update(FreeBoard freeBoard) throws Exception;
  void delete(int no) throws Exception;
}
