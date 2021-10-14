package pms.dao;

import java.util.List;
import pms.domain.DoctorBoard;

public interface DoctorBoardDao {
  void insert(DoctorBoard doctorBoard) throws Exception;
  List<DoctorBoard> findAll() throws Exception;
  DoctorBoard findByNo(int no) throws Exception;
  DoctorBoard findByName(String name) throws Exception;
  void update(DoctorBoard doctorBoard) throws Exception;
  void delete(int no) throws Exception;
  List<DoctorBoard> findByKeyword(String keyword) throws Exception;
}
