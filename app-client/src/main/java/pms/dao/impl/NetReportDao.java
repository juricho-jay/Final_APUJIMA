package pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pms.dao.ReportDao;
import pms.domain.Board;
import request.RequestAgent;

public class NetReportDao implements ReportDao{
  RequestAgent requestAgent;

  public NetReportDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }


  @Override
  public void insert(Board freeBoard) throws Exception {
    requestAgent.request("report.insert", freeBoard);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return;
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    requestAgent.request("report.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return new ArrayList<>(requestAgent.getObjects(Board.class));
  }

  @Override
  public Board findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("report.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Board.class);
  }

  @Override
  public Board findByName(String name) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("name", name);

    requestAgent.request("report.selectOneByName", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Board.class);
  }

  @Override
  public void update(Board freeBoard) throws Exception {
    requestAgent.request("report.update", freeBoard);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("정보 변경 실패!");
      return;
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("report.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("삭제 실패!");
      return;
    }
  }


  @Override
  public void autoDelete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("report.autoDelete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("삭제 실패!");
      return;
    }
  }

}



