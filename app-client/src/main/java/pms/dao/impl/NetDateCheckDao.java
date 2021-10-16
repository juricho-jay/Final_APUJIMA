package pms.dao.impl;

import java.util.ArrayList;
import java.util.List;
import pms.dao.DateCheckDao;
import pms.domain.DateCheck;
import request.RequestAgent;

public class NetDateCheckDao implements DateCheckDao {
  RequestAgent requestAgent;

  public NetDateCheckDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }



  @Override
  public void insert(DateCheck dateCheck) throws Exception {
    requestAgent.request("dateCheck.insert", dateCheck);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public List<DateCheck> findAll() throws Exception {
    requestAgent.request("dateCheck.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return new ArrayList<>(requestAgent.getObjects(DateCheck.class));
  }

  //  @Override
  //  public DateCheck findByNo(int no) throws Exception {
  //    HashMap<String,String> params = new HashMap<>();
  //    params.put("no", String.valueOf(no));
  //
  //    requestAgent.request("dateCheck.selectOne", params);
  //
  //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //      return null;
  //    }
  //
  //    return requestAgent.getObject(DateCheck.class);
  //  }
  //
  //  @Override
  //  public DateCheck findById(String id) throws Exception {
  //    HashMap<String,String> params = new HashMap<>();
  //    params.put("id", id);
  //
  //    requestAgent.request("dateCheck.selectOneById", params);
  //
  //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //      return null;
  //    }
  //
  //    return requestAgent.getObject(DateCheck.class);
  //  }
  //
  //  @Override
  //  public DateCheck findByName(String name) throws Exception {
  //    HashMap<String,String> params = new HashMap<>();
  //    params.put("name", name);
  //
  //    requestAgent.request("dateCheck.selectOneByName", params);
  //
  //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //      return null;
  //    }
  //
  //    return requestAgent.getObject(DateCheck.class);
  //  }
  //
  //  @Override
  //  public void update(DateCheck dateCheck) throws Exception {
  //    requestAgent.request("dateCheck.update", dateCheck);
  //
  //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //      System.out.println("포인트 차감 실패!");
  //      throw new Exception(requestAgent.getObject(String.class));
  //    }
  //  }

  @Override
  public void delete() throws Exception {

    requestAgent.request("dateCheck.delete", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      requestAgent.getObject(String.class);
      // 데이터 정리 완료
    } else {
      System.out.println("데이터 정리 완료!");
    }
  }

  //  @Override
  //  public void check(DateCheck dateCheck) throws Exception {
  //    requestAgent.request("dateCheck.check", dateCheck);
  //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //      System.out.println("중복되는 아이디 입니다. 다른 아이디를 사용해 주세요.");
  //      // throw new Exception(requestAgent.getObject(String.class));
  //    }
  //  }



}
