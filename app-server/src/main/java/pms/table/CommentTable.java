package pms.table;

import java.util.ArrayList;
import pms.domain.Comment;
import server.DataProcessor;
import server.Request;
import server.Response;

public class CommentTable extends JsonDataTable<Comment> implements DataProcessor {

  public CommentTable() {
    super("comment.json", Comment.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "comment.insert": insert(request, response); break;
      case "comment.selectList": selectList(request, response); break;
      case "comment.selectListByKeyword": selectListByKeyword(request, response); break;
      case "comment.selectOne": selectOne(request, response); break;
      case "comment.update": update(request, response); break;
      case "comment.delete": delete(request, response); break;

      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }


  private void insert(Request request, Response response) throws Exception {
    Comment comment = request.getObject(Comment.class);
    list.add(comment);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    if (list.size() == 0) {
      response.setStatus(Response.FAIL);
      return;
    }
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectListByKeyword(Request request, Response response) throws Exception {
    String keyword = request.getParameter("keyword");

    ArrayList<Comment> searchResult = new ArrayList<>();
    for (Comment comment : list) {
      if (!comment.getCommentContent().contains(keyword) &&
          !comment.getCommenter().contains(keyword)) {
        continue;
      }
      searchResult.add(comment);
    }

    response.setStatus(Response.SUCCESS);
    response.setValue(searchResult);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Comment c = findByNo(no);

    if (c != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(c);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 댓글을 찾을 수 없습니다.");
    }
  }

  private void update(Request request, Response response) throws Exception {
    Comment comment = request.getObject(Comment.class);

    //코멘트 고유 번호로 찾기
    int index = indexOf(comment.getCommentNo());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 댓글을 찾을 수 없습니다.");
      return;
    }

    list.set(index, comment);
    response.setStatus(Response.SUCCESS);
  }

  private void delete(Request request, Response response) throws Exception {
    int deleteIndex = Integer.parseInt(request.getParameter("deleteIndex"));
    int index = indexOf(deleteIndex);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 댓글을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private Comment findByNo(int no) {
    for (Comment c : list) {
      if (c.getNo() == no) {
        return c;
      }
    }
    return null;
  }

  //코멘트 고유 번호로 찾기 (코멘트 고유 번호는 인덱스 번호랑 같음)
  private int indexOf(int commentNo) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getCommentNo() == commentNo) {
        return i;
      }
    }
    return -1;
  }

}
