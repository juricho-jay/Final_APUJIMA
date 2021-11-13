package apus.dao;

import java.util.List;
import apus.domain.Member;

public interface MemberDao {
  void insert(Member member) throws Exception;
  void insert2(Member member) throws Exception;
  List<Member> findAll() throws Exception;
  List<Member> findDoctor() throws Exception;
  List<Member> findMan() throws Exception;
  List<Member> findWoman() throws Exception;
  Member findByNo(int no) throws Exception;
  Member findByName(String name) throws Exception;
  Member findById(String id) throws Exception;
  Member findByIdPwd(String id, String password) throws Exception;
  Member findByGender(String sex) throws Exception;
<<<<<<< HEAD
  Member doctorUpdate(Member member) throws Exception;
  Member doctorUpdate2(Member member) throws Exception;
=======
  Member findByTel(String phoneNum) throws Exception;
>>>>>>> c389a7a91b693f0c4a21eeda3b4bdb3d3bc89b56
  void update(Member member) throws Exception;
  void update2(Member member) throws Exception;
  void delete(String id) throws Exception;
  Member check(Member member) throws Exception;
  Member findDoctorMajor(Member member) throws Exception;
  void pointUpdate(int no) throws Exception;

} 