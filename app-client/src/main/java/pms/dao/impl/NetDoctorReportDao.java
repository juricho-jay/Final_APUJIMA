package pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pms.dao.DoctorReportDao;
import pms.domain.DoctorBoard;
import request.RequestAgent;

public class NetDoctorReportDao implements DoctorReportDao{
  RequestAgent requestAgent;

  public NetDoctorReportDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }


  @Override
  public void insert(DoctorBoard doctorBoard) throws Exception {
    requestAgent.request("doctorReport.insert", doctorBoard);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public List<DoctorBoard> findAll() throws Exception {
    requestAgent.request("doctorReport.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return new ArrayList<>(requestAgent.getObjects(DoctorBoard.class));
  }

  @Override
  public DoctorBoard findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("doctorReport.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(DoctorBoard.class);
  }

  @Override
  public DoctorBoard findByName(String name) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("name", name);

    requestAgent.request("doctorReport.selectOneByName", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(DoctorBoard.class);
  }

  @Override
  public void update(DoctorBoard doctorBoard) throws Exception {
    requestAgent.request("doctorReport.update", doctorBoard);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("doctorReport.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }
}



