package pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pms.dao.NoticeBoardDao;
import pms.domain.NoticeBoard;
import request.RequestAgent;

public class NetNoticeBoardDao implements NoticeBoardDao{

  RequestAgent requestAgent;

  public NetNoticeBoardDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(NoticeBoard noticeBoard) throws Exception {
    requestAgent.request("noticeBoard.insert", noticeBoard);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public List<NoticeBoard> findAll() throws Exception {
    requestAgent.request("noticeBoard.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }

    return new ArrayList<>(requestAgent.getObjects(NoticeBoard.class));
  }

  @Override
  public NoticeBoard findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("noticeBoard.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(NoticeBoard.class);
  }

  @Override
  public NoticeBoard findByName(String name) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("name", name);

    requestAgent.request("noticeBoard.selectOneByName", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(NoticeBoard.class);
  }

  public List<NoticeBoard> findByKeyword(String keyword) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", keyword);

    requestAgent.request("noticeBoard.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("해당 키워드로 검색 가능한 게시글이 없습니다.");
    }

    return new ArrayList<>(requestAgent.getObjects(NoticeBoard.class));
  }


  @Override
  public void update(NoticeBoard noticeBoard) throws Exception {
    requestAgent.request("noticeBoard.update", noticeBoard);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("noticeBoard.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }

  }


}
