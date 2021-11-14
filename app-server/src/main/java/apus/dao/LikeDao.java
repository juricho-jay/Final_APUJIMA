package apus.dao;

import java.util.List;
import apus.domain.Like;

public interface LikeDao {
  void insert(Like like) throws Exception;
  List<Like> findAll() throws Exception;
  //  Like findByNo(int no) throws Exception;
  //  Like findByName(String name) throws Exception;
  Like findBoardLike(int no, int Mno) throws Exception;
  //  void update(Like like) throws Exception;
  void delete(int no, int Mno) throws Exception;
  void autoDelete(int no) throws Exception;
  List<Like> findBoardCount(int no) throws Exception;

}

