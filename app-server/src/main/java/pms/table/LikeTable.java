package pms.table;

import pms.domain.Like;
import server.DataProcessor;
import server.Request;
import server.Response;

// 역할
// - 게시글 데이터를 처리하는 일을 한다.
// 
public class LikeTable extends JsonDataTable<Like> implements DataProcessor {

  public LikeTable() {
    super("like.json", Like.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "like.insert": insert(request, response); break;
      case "like.selectList": selectList(request, response); break;
      case "like.selectOne": selectOne(request, response); break; //likeNo(like 고유번호로 찾는 메서드)
      case "like.delete": delete(request, response); break;

      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }



  private void insert(Request request, Response response) throws Exception {
    Like like = request.getObject(Like.class);
    list.add(like);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    if (list.size() == 0) {
      response.setStatus(Response.FAIL);
      response.setValue(null);
      return;
    }
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }


  private void selectOne(Request request, Response response) throws Exception {
    int likeNo = Integer.parseInt(request.getParameter("likeNo"));
    Like l = findByNo(likeNo);

    if (l != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(l);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 좋아요를 찾을 수 없습니다.");
    }
  }


  private void delete(Request request, Response response) throws Exception {
    int deleteIndex = Integer.parseInt(request.getParameter("deleteIndex"));
    int index = indexOf(deleteIndex);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 좋아요를 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private Like findByNo(int likeNo) {
    for (Like l : list) {
      if (l.getLikeNo() == likeNo) {
        return l;
      }
    }
    return null;
  }

  // 리스트에서 라이크 고유 번호로 인덱스 알아내기
  private int indexOf(int likeNo) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getLikeNo() == likeNo) {
        return i;
      }
    }
    return -1;
  }

}













