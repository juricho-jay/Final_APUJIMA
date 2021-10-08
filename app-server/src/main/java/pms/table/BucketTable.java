package pms.table;

import java.util.ArrayList;
import pms.domain.Bucket;
import server.DataProcessor;
import server.Request;
import server.Response;

public class BucketTable extends JsonDataTable<Bucket> implements DataProcessor {

  public BucketTable() {
    super("bucket.json", Bucket.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "bucket.insert": insert(request, response); break;
      case "bucket.selectList": selectList(request, response); break;
      case "bucket.selectListByKeyword": selectListByKeyword(request, response); break;
      case "bucket.selectOne": selectOne(request, response); break;
      case "bucket.update": update(request, response); break;
      case "bucket.delete": delete(request, response); break;

      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Bucket bucket = request.getObject(Bucket.class);
    list.add(bucket);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    if (list.size() == 0) {
      response.setStatus(Response.FAIL);
    }
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectListByKeyword(Request request, Response response) throws Exception {
    String keyword = request.getParameter("keyword");

    ArrayList<Bucket> searchResult = new ArrayList<>();
    for (Bucket bucket : list) {
      if (!bucket.getTitle().contains(keyword) &&
          !bucket.getContent().contains(keyword) &&
          !bucket.getWriter().contains(keyword)) {
        continue;
      }
      searchResult.add(bucket);
    }

    response.setStatus(Response.SUCCESS);
    response.setValue(searchResult);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Bucket bucket = findByNo(no);

    if (bucket != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(bucket);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
    }
  }

  private void update(Request request, Response response) throws Exception {
    Bucket bucket = request.getObject(Bucket.class);

    int index = indexOf(bucket.getNo());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
      return;
    }

    list.set(index, bucket);
    response.setStatus(Response.SUCCESS);
  }

  private void delete(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    int index = indexOf(no);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private Bucket findByNo(int no) {
    for (Bucket bucket : list) {
      if (bucket.getNo() == no) {
        return bucket;
      }
    }
    return null;
  }

  private int indexOf(int bucketNo) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == bucketNo) {
        return i;
      }
    }
    return -1;
  }

}


