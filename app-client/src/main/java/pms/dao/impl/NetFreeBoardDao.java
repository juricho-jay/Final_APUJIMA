package pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pms.dao.FreeBoardDao;
import pms.domain.FreeBoard;
import request.RequestAgent;

public class NetFreeBoardDao implements FreeBoardDao{

  RequestAgent requestAgent;

  public NetFreeBoardDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(FreeBoard freeBoard) throws Exception {
    requestAgent.request("freeBoard.insert", freeBoard);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public List<FreeBoard> findAll() throws Exception {
    requestAgent.request("freeBoard.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("작성된 게시물이 없습니다.");
      return null;
    }

    return new ArrayList<>(requestAgent.getObjects(FreeBoard.class));
  }

  @Override
  public FreeBoard findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("freeBoard.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(FreeBoard.class);
  }

  @Override
  //  public FreeBoard findByName(String name) throws Exception {
  //    HashMap<String,String> params = new HashMap<>();
  //    params.put("name", name);
  //
  //    requestAgent.request("freeBoard.selectOneByName", params);
  //
  //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //      return null;
  //    }
  //
  //    return requestAgent.getObject(FreeBoard.class);
  //  }

  public List<FreeBoard> findByKeyword(String keyword) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", keyword);
    requestAgent.request("freeBoard.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("게시글 검색 실패!");
      return null;
    }

    return new ArrayList<>(requestAgent.getObjects(FreeBoard.class));
  }


  @Override
  public void update(FreeBoard freeBoard) throws Exception {
    requestAgent.request("freeBoard.update", freeBoard);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("정보 변경 실패!");
      return;
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("freeBoard.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("삭제 실패!");
      return;
    }

  }


}
