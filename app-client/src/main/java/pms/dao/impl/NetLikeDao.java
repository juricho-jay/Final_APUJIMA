package pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pms.dao.LikeDao;
import pms.domain.Like;
import request.RequestAgent;

public class NetLikeDao implements LikeDao{
  RequestAgent requestAgent;

  public NetLikeDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }


  @Override
  public void insert(Like like) throws Exception {
    requestAgent.request("like.insert", like);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return;
    }
  }

  @Override
  public List<Like> findAll() throws Exception {
    requestAgent.request("like.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return new ArrayList<>(requestAgent.getObjects(Like.class));
  }

  @Override
  public Like findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("like.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Like.class);
  }

  @Override
  public Like findByName(String name) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("name", name);

    requestAgent.request("like.selectOneByName", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Like.class);
  }

  @Override
  public void update(Like like) throws Exception {
    requestAgent.request("like.update", like);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return;
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("like.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return;
    }
  }
}



