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


}
