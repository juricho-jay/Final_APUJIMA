package pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pms.dao.CommentDao;
import pms.domain.Comment;
import request.RequestAgent;

public class NetCommentDao implements CommentDao{

  RequestAgent requestAgent;

  public NetCommentDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Comment comment) throws Exception {
    requestAgent.request("comment.insert", comment);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return;
    }
  }

  @Override
  public List<Comment> findAll() throws Exception {
    requestAgent.request("comment.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return new ArrayList<>(requestAgent.getObjects(Comment.class));
  }

  @Override
  public Comment findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("comment.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("댓글을 찾을 수 없습니다.");
      return null;
    }

    return requestAgent.getObject(Comment.class);
  }

  @Override
  public Comment findByName(String name) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("name", name);

    requestAgent.request("comment.selectOneByName", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("댓글을 찾을 수 없습니다.");
      return null;
    }

    return requestAgent.getObject(Comment.class);
  }

  @Override
  public List<Comment> findByKeyword(String keyword) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", keyword);
    requestAgent.request("comment.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("게시글 검색 실패!");
      return null;
    }

    return new ArrayList<>(requestAgent.getObjects(Comment.class));
  }


  @Override
  public void update(Comment comment) throws Exception {
    requestAgent.request("comment.update", comment);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("정보 변경 실패!");
      return;
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("comment.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("정보 삭제 실패!");
      return;
    }

  }


}
