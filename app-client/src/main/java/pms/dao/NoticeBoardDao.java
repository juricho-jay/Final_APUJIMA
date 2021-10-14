package pms.dao;

import java.util.List;
import pms.domain.NoticeBoard;

public interface NoticeBoardDao {
  void insert(NoticeBoard noticeBoard) throws Exception;
  List<NoticeBoard> findAll() throws Exception;
  NoticeBoard findByNo(int no) throws Exception;
  NoticeBoard findByName(String name) throws Exception;
  void update(NoticeBoard noticeBoard) throws Exception;
  void delete(int no) throws Exception;
  List<NoticeBoard> findByKeyword(String keyword) throws Exception;
}
