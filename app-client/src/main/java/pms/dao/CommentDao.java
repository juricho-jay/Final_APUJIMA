package pms.dao;

import java.util.List;
import pms.domain.Comment;

public interface CommentDao {
  void insert(Comment comment) throws Exception;
  List<Comment> findAll() throws Exception;
  Comment findByNo(int no) throws Exception;
  Comment findByName(String name) throws Exception;
  void update(Comment comment) throws Exception;
  void delete(int no) throws Exception;
  List<Comment> findByKeyword(String keyword) throws Exception;
  List<Comment> findComment(int boardNo) throws Exception;
}
