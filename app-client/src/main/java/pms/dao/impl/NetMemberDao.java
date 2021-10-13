package pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pms.dao.MemberDao;
import pms.domain.Member;
import request.RequestAgent;

public class NetMemberDao implements MemberDao{
  RequestAgent requestAgent;

  public NetMemberDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }



  @Override
  public void insert(Member member) throws Exception {
    requestAgent.request("member.insert", member);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public List<Member> findAll() throws Exception {
    requestAgent.request("member.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }

    return new ArrayList<>(requestAgent.getObjects(Member.class));
  }

  @Override
  public Member findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("member.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Member.class);
  }

  @Override
  public Member findByName(String name) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("name", name);

    requestAgent.request("member.selectOneByName", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Member.class);
  }

  @Override
  public void update(Member member) throws Exception {
    requestAgent.request("member.update", member);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("member.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  public void check(Member member) throws Exception {
    requestAgent.request("member.check", member);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("중복되는 아이디 입니다. 다른 아이디를 사용해 주세요.");
      // throw new Exception(requestAgent.getObject(String.class));
    }
  }



}
