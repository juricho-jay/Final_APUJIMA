package pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pms.dao.MailBoxDao;
import pms.domain.MailBox;
import request.RequestAgent;

public class NetMailBoxDao implements MailBoxDao {

  RequestAgent requestAgent;

  public NetMailBoxDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(MailBox mailbox) throws Exception {
    requestAgent.request("mailBox.insert", mailbox);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("쪽지함 데이터 저장 실패!");
      return;
    }
  }

  @Override
  public List<MailBox> findAll() throws Exception {
    requestAgent.request("mailBox.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return new ArrayList<>(requestAgent.getObjects(MailBox.class));
  }

  @Override
  public MailBox findByNo(int no) throws Exception {
    HashMap<String , String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("mailBox.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }
    return requestAgent.getObject(MailBox.class);
  }
  @Override
  public List<MailBox> findByKeyword(String keyword) throws Exception {
    HashMap<String , String> params = new HashMap<>();
    params.put("keyword", keyword);
    requestAgent.request("mailBox.selectListByKeyword", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }
    return new ArrayList<>(requestAgent.getObjects(MailBox.class));

  }

  @Override
  public void update(MailBox mailBox) throws Exception {

    requestAgent.request("mailBox.update", mailBox);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public void delete(int no) throws Exception {

    HashMap<String ,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("mailBox.delete", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return;
    }
  }

  @Override
  public MailBox findById(String id) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("id", id);

    requestAgent.request("mailBox.selectOneById", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(MailBox.class);
  }




}
