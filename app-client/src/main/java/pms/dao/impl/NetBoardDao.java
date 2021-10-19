package pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pms.dao.BoardDao;
import pms.domain.Board;
import request.RequestAgent;

public class NetBoardDao implements BoardDao {

  RequestAgent requestAgent;

  public NetBoardDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Board board) throws Exception {
    requestAgent.request("board.insert", board);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return;
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    requestAgent.request("board.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("작성된 게시물이 없습니다.");
      return null;
    }

    return new ArrayList<>(requestAgent.getObjects(Board.class));
  }

  @Override
  public Board findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("board.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Board.class);
  }

  @Override
  //  public Board findByName(String name) throws Exception {
  //    HashMap<String,String> params = new HashMap<>();
  //    params.put("name", name);
  //
  //    requestAgent.request("board.selectOneByName", params);
  //
  //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //      return null;
  //    }
  //
  //    return requestAgent.getObject(Board.class);
  //  }

  public List<Board> findByKeyword(String keyword) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", keyword);
    requestAgent.request("board.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("게시글 검색 실패!");
      return null;
    }

    return new ArrayList<>(requestAgent.getObjects(Board.class));
  }


  @Override
  public void update(Board board) throws Exception {
    requestAgent.request("board.update", board);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("정보 변경 실패!");
      return;
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("board.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("삭제 실패!");
      return;
    }

  }


}
