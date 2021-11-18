package apus.dao;

import java.util.List;
import apus.domain.Board;

public interface BoardDao {
  void insert(Board board) throws Exception;
  void insert2(Board board) throws Exception;
  void insert3(Board board) throws Exception;
  List<Board> findAll() throws Exception;
  Board findByNo(int no) throws Exception;
  // board findByName(String name) throws Exception;
  void update(Board board) throws Exception;
  void updateCount(int no) throws Exception;
  void delete(int no) throws Exception;
  List<Board> findByKeyword(String keyword) throws Exception;
  // void check(Board board) throws Exception;
  void insertComment(int boardNo) throws Exception;
  void deleteComment(int boardNo) throws Exception;
  //  void insertLike(int boardNo) throws Exception;
  //  void deleteLike(int boardNo) throws Exception;
  List<Board> searchByKeyWord(String name) throws Exception;
  List<Board> findFreeBoard() throws Exception;
  List<Board> findDoctorBoard() throws Exception;
  List<Board> findNoticeBoard() throws Exception;

}
