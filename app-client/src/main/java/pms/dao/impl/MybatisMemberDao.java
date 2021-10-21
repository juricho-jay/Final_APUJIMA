package pms.dao.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import pms.dao.MemberDao;
import pms.domain.Member;

public class MybatisMemberDao  implements MemberDao{

  Connection con;
  SqlSession sqlSession;

  public MybatisMemberDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }


  @Override
  public void insert(Member member) throws Exception {
    sqlSession.insert("MemberMapper.insert", member);


    sqlSession.insert("MemberMapper.insert2", member);
    sqlSession.commit();
  }

  @Override
  public List<Member> findAll() throws Exception {
    return sqlSession.selectList("MemberMapper.findAll");
  }

  //  @Override
  //  public Member findByNo(int no) throws Exception {
  //    return sqlSession.selectOne("MemberMapper.findByNo")
  //  }

  @Override
  public Member findByName(String name) throws Exception {
    return sqlSession.selectOne("MemberMapper.findByName", name);
  }

  @Override
  public Member findById(String id) throws Exception {
    return sqlSession.selectOne("MemberMapper.findById", id);
  }


  @Override
  public Member findByIdPwd(String id, String password) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("id", id);
    params.put("password", password);

    return sqlSession.selectOne("MemberMapper.findByIdPwd", params);
  }

  @Override
  public void update(Member member) throws Exception {
    sqlSession.update("MemberMapper.update", member);
    sqlSession.commit();
  }

  @Override
  public void delete(String id) throws Exception {
    sqlSession.delete("MemberMapper.delete", id);
    sqlSession.commit();
  }

  // @Override
  //  public void check(Member member) throws Exception {
  //    // TODO Auto-generated method stub
  //
  //  }

}
