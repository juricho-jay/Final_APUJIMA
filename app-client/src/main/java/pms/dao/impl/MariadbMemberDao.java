package pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pms.dao.MemberDao;
import pms.domain.Member;

public class MariadbMemberDao  implements MemberDao{

  Connection con;

  public MariadbMemberDao(Connection con) {
    this.con = con;
  }


  @Override
  public void insert(Member member) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into apus_member(name,id,password,birth,tel,email,"
            + "photo,sex,point,role,active) "
            +  "values(?,?,password(?),?,?,?,?,?,?,?,?)")){

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getId());
      stmt.setString(3, member.getPassword());
      stmt.setDate(4, member.getBirthDay());
      stmt.setString(5, member.getPhoneNum());
      stmt.setString(6, member.getEmail());
      stmt.setString(7, member.getPhoto());
      stmt.setString(8, member.getSex());
      stmt.setInt(9, member.getPoint());
      stmt.setInt(10, member.getDoctorOrNot());
      stmt.setInt(11, member.getActive());

      if (stmt.executeUpdate() == 0) {
        throw new Exception ("회원 데이터 저장 실패!");
      }
    }

  }

  @Override
  public List<Member> findAll() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_no,name,id,password,birth,tel,email,"
            + "photo,sex,created_dt,point,role,active"
            + " from apus_member"
            + " order by member_no asc"
        );
        ResultSet rs = stmt.executeQuery()) {
      ArrayList<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_no"));
        member.setName(rs.getString("name"));
        member.setId(rs.getString("id"));
        member.setBirthDay(rs.getDate("birth"));
        member.setPhoneNum(rs.getString("tel"));
        member.setEmail(rs.getString("email"));
        member.setPhoto(rs.getString("photo"));
        member.setSex(rs.getString("sex"));
        member.setRegisteredDate(rs.getDate("created_dt"));
        member.setPoint(rs.getInt("point"));
        member.setDoctorOrNot(rs.getInt("role"));
        member.setActive(rs.getInt("active"));

        list.add(member);
      }
      return list;
    }
  }

  //  @Override
  //  public Member findByNo(int no) throws Exception {
  //    try (PreparedStatement stmt = con.prepareStatement(
  //        "select member_no,name,id,password,birth,tel,email,"
  //            + "photo,sex,created_dt,point,role,active"
  //            + " from apus_member"
  //            + " where member_no=" + no
  //        );
  //        ResultSet rs = stmt.executeQuery()) {
  //
  //      if (!rs.next()) {
  //        return null;
  //      }
  //      Member member = new Member();
  //      member.setNo(rs.getInt("member_no"));
  //      member.setName(rs.getString("name"));
  //      member.setId(rs.getString("id"));
  //      member.setBirthDay(rs.getDate("birth"));
  //      member.setPhoneNum(rs.getString("tel"));
  //      member.setEmail(rs.getString("email"));
  //      member.setPhoto(rs.getString("photo"));
  //      member.setSex(rs.getString("sex"));
  //      member.setRegisteredDate(rs.getDate("created_dt"));
  //      member.setPoint(rs.getInt("point"));
  //      member.setDoctorOrNot(rs.getInt("role"));
  //      member.setActive(rs.getInt("active"));
  //      return member;
  //    }
  //  }

  @Override
  public Member findByName(String name) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_no,name,id,password,birth,tel,email,"
            + "photo,sex,created_dt,point,role,active"
            + " from apus_member"
            + " where name=?"
        )) {

      stmt.setString(1, name);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }
        Member member = new Member();
        member.setNo(rs.getInt("member_no"));
        member.setName(rs.getString("name"));
        member.setId(rs.getString("id"));
        member.setBirthDay(rs.getDate("birth"));
        member.setPhoneNum(rs.getString("tel"));
        member.setEmail(rs.getString("email"));
        member.setPhoto(rs.getString("photo"));
        member.setSex(rs.getString("sex"));
        member.setRegisteredDate(rs.getDate("created_dt"));
        member.setPoint(rs.getInt("point"));
        member.setDoctorOrNot(rs.getInt("role"));
        member.setActive(rs.getInt("active"));
        return member;
      }
    }
  }
  @Override
  public Member findById(String id) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_no,name,id,password,birth,tel,email,"
            + "photo,sex,created_dt,point,role,active"
            + " from apus_member"
            + " where id=?"
        )) {

      stmt.setString(1, id);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }
        Member member = new Member();
        member.setNo(rs.getInt("member_no"));
        member.setName(rs.getString("name"));
        member.setId(rs.getString("id"));
        member.setBirthDay(rs.getDate("birth"));
        member.setPhoneNum(rs.getString("tel"));
        member.setEmail(rs.getString("email"));
        member.setPhoto(rs.getString("photo"));
        member.setSex(rs.getString("sex"));
        member.setRegisteredDate(rs.getDate("created_dt"));
        member.setPoint(rs.getInt("point"));
        member.setDoctorOrNot(rs.getInt("role"));
        member.setActive(rs.getInt("active"));
        return member;
      }
    }
  }

  @Override
  public void update(Member member) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update apus_member set"
            + " name=?,id=?,password=password(?),birth=?,"
            + "tel=?,email=?,"
            + "photo=?,sex=?,point=?,role=?"
            + " where member_no=?"
        )) {
      stmt.setString(1, member.getName());
      stmt.setString(2, member.getId());
      stmt.setString(3, member.getPassword());
      stmt.setDate(4, member.getBirthDay());
      stmt.setString(5, member.getPhoneNum());
      stmt.setString(6, member.getEmail());
      stmt.setString(7, member.getPhoto());
      stmt.setString(8, member.getSex());
      stmt.setInt(9, member.getPoint());
      stmt.setInt(10, member.getDoctorOrNot());

      if(stmt.executeUpdate() == 0) {
        throw new Exception("회원 데이터 변경 실패!");
      }
    }
  }

  @Override
  public void delete(String id) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from apus_member where id=?")){

      stmt.setString(1, id);

      if(stmt.executeUpdate() == 0) {
        throw new Exception("회원 데이터 삭제 실패!");
      }
    }
  }


  @Override
  public Member findByIdPwd(String id, String password) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_no,name,id,password,birth,tel,email,"
            + "photo,sex,created_dt,point,role,active"
            + " from apus_member"
            + " where id=? and password=password(?)"
        )) {

      stmt.setString(1, id);
      stmt.setString(2, password);
      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }
        Member member = new Member();
        member.setNo(rs.getInt("member_no"));
        member.setName(rs.getString("name"));
        member.setId(rs.getString("id"));
        member.setBirthDay(rs.getDate("birth"));
        member.setPhoneNum(rs.getString("tel"));
        member.setEmail(rs.getString("email"));
        member.setPhoto(rs.getString("photo"));
        member.setSex(rs.getString("sex"));
        member.setRegisteredDate(rs.getDate("created_dt"));
        member.setPoint(rs.getInt("point"));
        member.setDoctorOrNot(rs.getInt("role"));
        member.setActive(rs.getInt("active"));
        return member;
      }
    }
  }


  @Override
  public void check(Member member) throws Exception {
    // TODO Auto-generated method stub

  }


  // @Override
  //  public void check(Member member) throws Exception {
  //    // TODO Auto-generated method stub
  //
  //  }

}
