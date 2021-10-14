package pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pms.dao.DoctorBoardDao;
import pms.domain.DoctorBoard;
import request.RequestAgent;

public class NetDoctorBoardDao implements DoctorBoardDao{

  RequestAgent requestAgent;

  public NetDoctorBoardDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(DoctorBoard doctorBoard) throws Exception {
    requestAgent.request("doctorBoard.insert", doctorBoard);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public List<DoctorBoard> findAll() throws Exception {
    requestAgent.request("doctorBoard.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }

    return new ArrayList<>(requestAgent.getObjects(DoctorBoard.class));
  }

  @Override
  public DoctorBoard findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("doctorBoard.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(DoctorBoard.class);
  }

  @Override
  public DoctorBoard findByName(String name) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("name", name);

    requestAgent.request("doctorBoard.selectOneByName", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(DoctorBoard.class);
  }

  public List<DoctorBoard> findByKeyword(String keyword) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", keyword);
    requestAgent.request("doctorBoard.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("해당 키워드로 검색 가능한 게시글이 없습니다.");
    }

    return new ArrayList<>(requestAgent.getObjects(DoctorBoard.class));
  }


  @Override
  public void update(DoctorBoard doctorBoard) throws Exception {
    requestAgent.request("doctorBoard.update", doctorBoard);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("doctorBoard.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }

  }


}
