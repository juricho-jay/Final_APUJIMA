package pms.dao;

import java.util.List;
import pms.domain.CounselingMember;

public interface CounselingMemberDao {
  void insert(CounselingMember counselingMember) throws Exception;
  List<CounselingMember> findAll() throws Exception;
  CounselingMember findByNo(int no) throws Exception;
  CounselingMember findByName(String name) throws Exception;
  CounselingMember findById(String id) throws Exception;
  void update(CounselingMember counselingMember) throws Exception;
  void delete(String id) throws Exception;
  void check(CounselingMember counselingMember) throws Exception;
}