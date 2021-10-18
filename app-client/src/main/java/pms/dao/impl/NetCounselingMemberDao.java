package pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pms.dao.CounselingMemberDao;
import pms.domain.CounselingMember;
import request.RequestAgent;

public class NetCounselingMemberDao implements CounselingMemberDao{
  RequestAgent requestAgent;

  public NetCounselingMemberDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }



  @Override
  public void insert(CounselingMember counselingMember) throws Exception {
    requestAgent.request("counselingmember.insert", counselingMember);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return;
    }
  }

  @Override
  public List<CounselingMember> findAll() throws Exception {
    requestAgent.request("counselingmember.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return new ArrayList<>(requestAgent.getObjects(CounselingMember.class));
  }

  @Override
  public CounselingMember findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("counselingmember.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(CounselingMember.class);
  }

  @Override
  public CounselingMember findById(String id) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("id", id);

    requestAgent.request("counselingmember.selectOneById", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(CounselingMember.class);
  }

  @Override
  public CounselingMember findByName(String name) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("name", name);

    requestAgent.request("counselingmember.selectOneByName", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(CounselingMember.class);
  }

  @Override
  public void update(CounselingMember counselingMember) throws Exception {
    requestAgent.request("counselingmember.update", counselingMember);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("정보 변경 실패!");
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  // id로 찾아서 삭제
  public void delete(String id) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("id", id);

    requestAgent.request("counselingmember.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public void check(CounselingMember counselingMember) throws Exception {
    //    requestAgent.request("counselingmember.check", counselingMember);
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println("중복되는 아이디 입니다. 다른 아이디를 사용해 주세요.");
    //      // throw new Exception(requestAgent.getObject(String.class));
    //    }
  }



}
