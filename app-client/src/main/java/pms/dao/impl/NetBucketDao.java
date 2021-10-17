package pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pms.dao.BucketDao;
import pms.domain.Bucket;
import request.RequestAgent;

public class NetBucketDao implements BucketDao {

  RequestAgent requestAgent;

  public NetBucketDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Bucket bucket) throws Exception {
    requestAgent.request("bucket.insert", bucket);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("버킷리스트 데이터 저장 실패!");
    }
  }

  @Override
  public List<Bucket> findAll() throws Exception {
    requestAgent.request("bucket.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return new ArrayList<>(requestAgent.getObjects(Bucket.class));
  }

  @Override
  public List<Bucket> findByKeyword(String keyword) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", keyword);
    requestAgent.request("bucket.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 검색 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(Bucket.class));
  }

  @Override
  public Bucket findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("bucket.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Bucket.class);
  }

  @Override
  public void update(Bucket bucket) throws Exception {
    requestAgent.request("bucket.update", bucket);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 변경 실패!");
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("bucket.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 삭제 실패!");
    }
  }

}
