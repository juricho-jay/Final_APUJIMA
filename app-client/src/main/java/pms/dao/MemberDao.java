package pms.dao;

import java.util.List;
import pms.domain.Member;

public interface MemberDao {
  void insert(Member member) throws Exception;
  List<Member> findAll() throws Exception;
  Member findByNo(int no) throws Exception;
  Member findByName(String name) throws Exception;
  Member findById(String id) throws Exception;
  Member findByIdPwd(String id, String password) throws Exception;
  void update(Member member) throws Exception;
  void delete(String id) throws Exception;
  void check(Member member) throws Exception;
}